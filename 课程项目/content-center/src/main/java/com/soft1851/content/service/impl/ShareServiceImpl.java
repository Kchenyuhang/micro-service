package com.soft1851.content.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soft1851.content.dao.MidUserShareMapper;
import com.soft1851.content.dao.ShareMapper;
import com.soft1851.content.domain.dto.*;
import com.soft1851.content.domain.entity.MidUserShare;
import com.soft1851.content.domain.entity.Share;
import com.soft1851.content.domain.entity.User;
import com.soft1851.content.domain.enums.AuditStatusEnum;
import com.soft1851.content.feignclient.UserCenterFeignClient;
import com.soft1851.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.client.AsyncRestTemplate;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.StringUtil;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/29
 */
@Service
@Slf4j
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {
    private final ShareMapper shareMapper;
    private final UserCenterFeignClient userCenterFeignClient;
    private final MidUserShareMapper midUserShareMapper;

    private final AsyncRestTemplate asyncRestTemplate;

    private final RocketMQTemplate rocketMQTemplate;

    @Override
    public ShareDTO findById(Integer id) {
        Share share = this.shareMapper.selectByPrimaryKey(id);
        Integer userId = share.getUserId();
        ResponseDTO responseDTO = this.userCenterFeignClient.getById(userId);
        UserDTO userDTO = convert(responseDTO);
        ShareDTO shareDTO = new ShareDTO();
//        BeanUtils.copyProperties(share, shareDTO);
        shareDTO.setShare(share);
        shareDTO.setWxNickName(userDTO.getWxNickname());
        return shareDTO;
    }

    @Override
    public PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId) {
        //启动分页
        PageHelper.startPage(pageNo, pageSize);
        //构造查询实例
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("showFlag", 1);
        //如标题关键字不空，则加上模糊查询条件，否则结果即所有数据
        if (StringUtil.isNotEmpty(title)) {
            criteria.andLike("title", "%" + title + "%");
        }
        //执行按条件查询
        List<Share> shares = this.shareMapper.selectByExample(example);
        //处理后的Share数据列表
        List<Share> shareDeal;
        // 1.如果用户未登录，那么downloadUrl全部设为null
        if (userId == null) {
            shareDeal = shares.stream()
                    .peek(share -> {
                        share.setDownloadUrl(null);
                    })
                    .collect(Collectors.toList());
        }
        // 2.如果用户登录了，那么查询一下mid_user_share，如果没有数据，那么这条share的downloadUrl也设为null
        //只有自己分享的资源才能看到下载链接，否则显示兑换
        else {
            shareDeal = shares.stream()
                    .peek(share -> {
                        MidUserShare midUserShare = this.midUserShareMapper.selectOne(
                                MidUserShare.builder()
                                        .userId(userId)
                                        .shareId(share.getId())
                                        .build()
                        );
                        if (midUserShare == null) {
                            share.setDownloadUrl(null);
                        }
                    })
                    .collect(Collectors.toList());
        }
        return new PageInfo<>(shareDeal);
    }

    @Override
    public int insertShare(ShareRequestDTO shareRequestDTO) {
        Share share = Share.builder()
                .author(shareRequestDTO.getAuthor())
                .downloadUrl(shareRequestDTO.getDownloadUrl())
                .isOriginal(shareRequestDTO.getIsOriginal())
                .price(shareRequestDTO.getPrice())
                .summary(shareRequestDTO.getSummary())
                .title(shareRequestDTO.getTitle())
                .createTime(new Date(System.currentTimeMillis()))
                .updateTime(new Date(System.currentTimeMillis()))
                .buyCount(0)
                .auditStatus("NOT_YET")
                .reason("待审核")
                .cover("https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/team.jpg")
                .showFlag(false)
                .userId(shareRequestDTO.getUserId()).build();
        return shareMapper.insert(share);
    }

    @Override
    public Share updateShare(Integer id, ShareRequestDTO shareRequestDTO) {
        Share share = shareMapper.selectByPrimaryKey(id);
        share.setAuthor(shareRequestDTO.getAuthor());
        share.setDownloadUrl(shareRequestDTO.getDownloadUrl());
        share.setIsOriginal(shareRequestDTO.getIsOriginal());
        share.setPrice(shareRequestDTO.getPrice());
        share.setSummary(shareRequestDTO.getSummary());
        share.setTitle(shareRequestDTO.getTitle());
        this.shareMapper.updateByPrimaryKey(share);
        return share;
    }

    @Override
    public Share auditById(Integer id, ShareAuditDTO shareAuditDTO) {
        //1,查询share是否存在，不存在或者当前的audit_status！=NOT_YET，那么抛出异常
        Share share = this.shareMapper.selectByPrimaryKey(id);
        System.out.println(share);
        if (share == null) {
            throw new IllegalArgumentException("参数非法！该分享不存在");
        }
        if (!Objects.equals("NOT_YET", share.getAuditStatus())) {
            throw new IllegalArgumentException("参数非法！该分享已审核通过或审核不通过");
        }
        //2,审核流程，将状态改为PASS或REJECT
        //这个app主要流程是审核，所以不需要等更新积分的结果返回，可以将增加积分改为异步
        share.setAuditStatus(shareAuditDTO.getAuditStatusEnum().toString());
        share.setReason(shareAuditDTO.getReason());
        if ("PASS".equals(shareAuditDTO.getAuditStatusEnum().toString())) {
            share.setShowFlag(true);
        } else {
            share.setShowFlag(false);
        }
        this.shareMapper.updateByPrimaryKey(share);
        //3,如果是PASS,那么发送消息给rocketmq，让用户中心去消费，并为发布人添加积分
        if (AuditStatusEnum.PASS.equals(shareAuditDTO.getAuditStatusEnum())) {
            //method-1,rocketmq异步实现加积分
            this.rocketMQTemplate.convertAndSend(
                    "add-bonus",
                    UserAddBonusMsgDTO.builder()
                            .userId(share.getUserId())
                            .bonus(50)
                            .build()
            );
            log.info("使用异步rocketmq操作修改用户积分");
            //method-2,同步调用feignClient修改用户积分
//            this.userCenterFeignClient.updateBonus(UserAddBonusMsgDTO.builder()
//                    .userId(share.getUserId())
//                    .bonus(50)
//                    .build());
//            log.info("使用feign同步操作修改用户积分");
            //method-3,使用asyncTemplate实现异步
//            String url = "http://user-center/user/bonus/pass";
//            UserAddBonusMsgDTO userAddBonusMsgDTO = UserAddBonusMsgDTO.builder().userId(share.getUserId()).bonus(50).build();
//            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//            headers.add("Content-Type", "application/json;charset=UTF-8");
//            HttpEntity<Object> httpEntity = new HttpEntity<>(userAddBonusMsgDTO, headers);
//            ListenableFuture<ResponseEntity<User>> entity = asyncRestTemplate.postForEntity(url, httpEntity, User.class);
//            entity.addCallback(new ListenableFutureCallback<ResponseEntity<User>>() {
//                @Override
//                public void onFailure(Throwable ex) {
//                    log.error("调用失败" + ex);
//                }
//
//                @Override
//                public void onSuccess(ResponseEntity<User> result) {
//                    log.info("调用成功");
//                }
//            });
//            log.info("使用asyncTemplate异步操作修改用户积分");
            //method-4,Async请求
//            this.update(share);
//            log.info("使用@Async注解实现异步请求");
        }
        return share;
    }

    @Override
    public Share exchange(ExchangeDTO exchangeDTO) {
        int userId = exchangeDTO.getUserId();
        int shareId = exchangeDTO.getShareId();
        // 1. 根据id查询share，校验是否存在
        Share share = this.shareMapper.selectByPrimaryKey(shareId);
        if (share == null) {
            throw new IllegalArgumentException("该分享不存在！");
        }
        Integer price = share.getPrice();

        // 2. 如果当前用户已经兑换过该分享，则直接返回
        MidUserShare midUserShare = this.midUserShareMapper.selectOne(
                MidUserShare.builder()
                        .shareId(shareId)
                        .userId(userId)
                        .build()
        );
        if (midUserShare != null) {
            return share;
        }

        // 3. 根据当前登录的用户id，查询积分是否够
        //这里一定要注意通过调用户中心接口得到的返回值，外面已经封装了一层了，要解析才能拿到真正的用户数据
        ResponseDTO responseDTO = this.userCenterFeignClient.getById(userId);
        UserDTO userDTO = convert(responseDTO);
        System.out.println(userDTO);
        if (price > userDTO.getBonus()) {
            throw new IllegalArgumentException("用户积分不够！");
        }

        // 4. 扣积分
        this.userCenterFeignClient.addBonus(
                UserAddBonusDTO.builder()
                        .userId(userId)
                        .bonus(price * -1)
                        .build()
        );
        //5. 向mid_user_share表里插入一条数据
        this.midUserShareMapper.insert(
                MidUserShare.builder()
                        .userId(userId)
                        .shareId(shareId)
                        .build()
        );
        return share;
    }

    @Override
    public PageInfo<Share> findMyContribute(Integer pageNo, Integer pageSize, Integer userId) {
        // 启动分页
        PageHelper.startPage(pageNo, pageSize);
        // 构造查询实例
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        List<Share> shares = this.shareMapper.selectByExample(example);
        return new PageInfo<>(shares);
    }

    @Override
    public PageInfo<Share> findMyExchange(Integer pageNo, Integer pageSize, Integer userId) {
        PageHelper.startPage(pageNo, pageSize);
        Example example = new Example(MidUserShare.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        List<MidUserShare> midUserShares = this.midUserShareMapper.selectByExample(example);
        List<Share> shareList = new ArrayList<>();
        midUserShares.forEach(midUserShare -> {
            Share share = this.shareMapper.selectByPrimaryKey(midUserShare.getShareId());
            shareList.add(share);
        });
        return new PageInfo<>(shareList);
    }

    @Override
    public PageInfo<Share> findShareNotYet() {
//        PageHelper.startPage(pageNo, pageSize);
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("auditStatus", "NOT_YET");
        List<Share> shareList = this.shareMapper.selectByExample(example);
        return new PageInfo<>(shareList);
    }

    @Override
    public PageInfo<Share> findShareAudit() {
//        PageHelper.startPage(pageNo, pageSize);
        Example example = new Example(Share.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andNotEqualTo("auditStatus", "NOT_YET");
        List<Share> shareList = this.shareMapper.selectByExample(example);
        return new PageInfo<>(shareList);
    }


    /**
     * 将统一的返回响应结果转换为UserDTO类型
     *
     * @param responseDTO
     * @return
     */
    private UserDTO convert(ResponseDTO responseDTO) {
        ObjectMapper mapper = new ObjectMapper();
        UserDTO userDTO = null;
        try {
            String json = mapper.writeValueAsString(responseDTO.getData());
            userDTO = mapper.readValue(json, UserDTO.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return userDTO;
    }
}
