package com.soft1851.user.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.soft1851.user.dao.BonusEventLogMapper;
import com.soft1851.user.dao.UserMapper;
import com.soft1851.user.domain.dto.*;
import com.soft1851.user.domain.entity.BonusEventLog;
import com.soft1851.user.domain.entity.User;
import com.soft1851.user.service.UserService;
import com.soft1851.user.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/25
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;
    private final BonusEventLogMapper bonusEventLogMapper;

    @Override
    public List<UserDTO> getUserList() {
        List<User> userList = userMapper.selectAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        userList.forEach(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setId(user.getId());
            userDTO.setWxId(user.getWxId());
            userDTO.setWxNickname(user.getWxNickname());
            userDTO.setRoles(user.getRoles());
            userDTO.setAvatarUrl(user.getAvatarUrl());
            userDTOList.add(userDTO);
        });
        return userDTOList;
    }

    @Override
    public ResponseDTO getUserById(Integer id) {
        User user = this.userMapper.selectByPrimaryKey(id);
        return new ResponseDTO(true, "200", "查询成功", user, 1L);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void addBonus(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        System.out.println(userAddBonusMsgDTO);
        // 1. 为用户加积分
        Integer userId = userAddBonusMsgDTO.getUserId();
        Integer bonus = userAddBonusMsgDTO.getBonus();
        User user = this.userMapper.selectByPrimaryKey(userId);

        user.setBonus(user.getBonus() + bonus);
        this.userMapper.updateByPrimaryKeySelective(user);

        // 2. 记录日志到bonus_event_log表里面
        this.bonusEventLogMapper.insert(
                BonusEventLog.builder()
                        .userId(userId)
                        .value(bonus)
                        .event(userAddBonusMsgDTO.getEvent())
                        .createTime(new Date())
                        .description(userAddBonusMsgDTO.getDescription())
                        .build()
        );
        log.info("积分添加完毕...");
    }

    @Override
    public User login(LoginDTO loginDTO, String openId) {
        //先根据openId查找用户
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("wxId", openId);
        List<User> users = this.userMapper.selectByExample(example);
        //没找到，是新用户，直接注册
        if (users.size() == 0) {
            User saveUser = User.builder()
                    .wxId(openId)
                    .avatarUrl(loginDTO.getAvatarUrl())
                    .wxNickname(loginDTO.getWxNickname())
                    .roles("user")
                    .bonus(100)
                    .createTime(new Date())
                    .updateTime(new Date())
                    .build();
            this.userMapper.insertSelective(saveUser);
            return saveUser;
        }
        return users.get(0);
    }

    @Override
    public PageInfo<BonusEventLog> getLog(Integer pageNo, Integer pageSize, Integer userId) {
        PageHelper.startPage(pageNo, pageSize);
        Example example = new Example(BonusEventLog.class);
        example.setOrderByClause("create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);
        List<BonusEventLog> bonusEventLogList = this.bonusEventLogMapper.selectByExample(example);
        return new PageInfo<>(bonusEventLogList);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResponseDTO signIn(UserSignInDTO signInDTO) {
        User user = this.userMapper.selectByPrimaryKey(signInDTO.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("该用户不存在！");
        }
        Example example = new Example(BonusEventLog.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id DESC");
        criteria.andEqualTo("userId", signInDTO.getUserId());
        criteria.andEqualTo("event", "SIGN_IN");
        List<BonusEventLog> bonusEventLogs = this.bonusEventLogMapper.selectByExample(example);
        if (bonusEventLogs.size() == 0) {
            this.bonusEventLogMapper.insert(BonusEventLog.builder()
                    .userId(signInDTO.getUserId())
                    .value(20)
                    .createTime(new Date())
                    .description("签到")
                    .event("SIGN_IN").build());
            user.setBonus(user.getBonus() + 20);
            this.userMapper.updateByPrimaryKeySelective(user);
            return new ResponseDTO(true, "200", "签到成功", user, 1L);
        } else {
            BonusEventLog bonusEventLog = bonusEventLogs.get(0);
            Date date = bonusEventLog.getCreateTime();
            try {
                if (DateUtil.checkAllotSign(date) == 0) {
                    this.bonusEventLogMapper.insert(BonusEventLog.builder()
                            .userId(signInDTO.getUserId())
                            .value(20)
                            .createTime(new Date())
                            .description("签到")
                            .event("SIGN_IN").build());
                    user.setBonus(user.getBonus() + 20);
                    this.userMapper.updateByPrimaryKeySelective(user);
                    return new ResponseDTO(true, "200", "签到成功", user, 1L);
                } else if (DateUtil.checkAllotSign(date) == 1) {
                    return new ResponseDTO(false, "201", "签到失败", user.getWxNickname() + "今天签到过了", 1L);
                } else if (DateUtil.checkAllotSign(date) == 2) {
                    return new ResponseDTO(false, "202", "签到失败", user.getWxNickname() + "该用户今天的数据混乱了", 1L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseDTO(true, "200", "签到成功", user, 1L);
    }

    @Override
    public ResponseDTO checkIsSign(UserSignInDTO signInDTO) {
        User user = this.userMapper.selectByPrimaryKey(signInDTO.getUserId());
        if (user == null) {
            throw new IllegalArgumentException("该用户不存在！");
        }
        Example example = new Example(BonusEventLog.class);
        Example.Criteria criteria = example.createCriteria();
        example.setOrderByClause("id DESC");
        criteria.andEqualTo("userId", signInDTO.getUserId());
        criteria.andEqualTo("event", "SIGN_IN");
        List<BonusEventLog> bonusEventLogs = this.bonusEventLogMapper.selectByExample(example);
        if (bonusEventLogs.size() == 0) {
            return new ResponseDTO(true, "200", "该用户还未签到", "可以签到", 1L);
        } else {
            BonusEventLog bonusEventLog = bonusEventLogs.get(0);
            Date date = bonusEventLog.getCreateTime();
            try {
                if (DateUtil.checkAllotSign(date) == 0) {
                    return new ResponseDTO(true, "200", "该用户还未签到", "可以签到", 1L);
                } else if (DateUtil.checkAllotSign(date) == 1) {
                    return new ResponseDTO(false, "201", "已经签到了", "不可以签到", 1L);
                } else if (DateUtil.checkAllotSign(date) == 2) {
                    return new ResponseDTO(false, "202", "数据出错了", "不可以签到", 1L);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new ResponseDTO(true, "200", "该用户还未签到", "可以签到", 1L);
    }

}
