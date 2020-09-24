package com.soft1851.user.service;

import com.soft1851.user.dto.UserDto;

import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/20
 */
public interface UserService {

    UserDto selectByUserId(Integer userId);
}
