package com.soft1851.user.service;

import com.soft1851.user.domain.dto.UserDTO;

import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/25
 */
public interface UserService {

    /**
     * 获取所有用户信息
     *
     * @return
     */
    List<UserDTO> getUserList();

    /**
     * 根据用户id查询用户信息
     *
     * @param userId
     * @return
     */
    UserDTO getUserById(Integer userId);
}
