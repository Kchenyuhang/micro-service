package com.soft1851.user.service.impl;

import com.soft1851.user.dao.UserMapper;
import com.soft1851.user.dto.UserDto;
import com.soft1851.user.entity.User;
import com.soft1851.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/20
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public UserDto selectByUserId(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getPkId());
        userDto.setUserName(user.getName());
        userDto.setAvatar(user.getAvatar());
        return userDto;
    }
}
