package com.soft1851.content.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.content.domain.dto.ShareDTO;
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
     * @param title
     * @param pageNo
     * @param pageSize
     * @param userId
     * @return PageInfo<Share>
     */
    PageInfo<Share> query(String title, Integer pageNo, Integer pageSize, Integer userId);
}
