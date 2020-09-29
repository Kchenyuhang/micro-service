package com.soft1851.content.service;

import com.soft1851.content.domain.dto.ShareDTO;

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
}
