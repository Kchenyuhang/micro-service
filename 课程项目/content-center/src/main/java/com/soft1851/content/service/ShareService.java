package com.soft1851.content.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.content.domain.dto.ExchangeDTO;
import com.soft1851.content.domain.dto.ShareAuditDTO;
import com.soft1851.content.domain.dto.ShareDTO;
import com.soft1851.content.domain.dto.ShareRequestDTO;
import com.soft1851.content.domain.entity.Share;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/29
 */
public interface ShareService {

    /**
     * 根据share的id查询
     *
     * @param id
     * @return
     */
    ShareDTO findById(Integer id);

    /**
     * 根据标题模糊查询某个用户的分享列表数据，title为空则为所有数据，查询结果分页
     *
     * @param title
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return PageInfo<Share>
     */
    PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId);

    /**
     * 投稿
     *
     * @param shareRequestDTO
     * @return
     */
    int insertShare(ShareRequestDTO shareRequestDTO);

    /**
     * 编辑
     *
     * @param id
     * @param shareRequestDTO
     * @return
     */
    Share updateShare(Integer id, ShareRequestDTO shareRequestDTO);

    /**
     * 审核投稿
     *
     * @param id
     * @param shareAuditDTO
     * @return
     */
    Share auditById(Integer id, ShareAuditDTO shareAuditDTO);

    /**
     * 积分兑换资源
     *
     * @param exchangeDTO
     * @return Share
     */
    Share exchange(ExchangeDTO exchangeDTO);

    /**
     * 我的投稿
     *
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    PageInfo<Share> findMyContribute(Integer pageNo, Integer pageSize, Integer userId);

    /**
     * 我的兑换
     *
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return
     */
    PageInfo<Share> findMyExchange(Integer pageNo, Integer pageSize, Integer userId);

    /**
     * 查询待审核的投稿
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Share> findShareNotYet(Integer pageNo, Integer pageSize);

    /**
     * 查询已审核的投稿
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    PageInfo<Share> findShareAudit(Integer pageNo, Integer pageSize);
}
