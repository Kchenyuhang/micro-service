package com.soft1851.user.service.impl;

import com.soft1851.user.dao.UserMapper;
import com.soft1851.user.domain.dto.UserDTO;
import com.soft1851.user.domain.entity.User;
import com.soft1851.user.domain.vo.UserVO;
import com.soft1851.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/25
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserServiceImpl implements UserService {
    private final UserMapper userMapper;

    @Override
    public List<UserDTO> getUserList() {
        List<User> userList = userMapper.selectAll();
        List<UserDTO> userDTOList = new ArrayList<>();
        userList.forEach(user -> {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(user.getId());
            userDTO.setWxId(user.getWxId());
            userDTO.setUserName(user.getWxNickname());
            userDTO.setRoles(user.getRoles());
            userDTO.setAvatarUrl(user.getAvatarUrl());
            userDTOList.add(userDTO);
        });
        return userDTOList;
    }

    @Override
    public UserDTO getUserById(Integer userId) {
        User user1 = User.builder().id(userId).build();
        User user = userMapper.selectOne(user1);
        System.out.println(user);
        UserDTO userDTO = UserDTO.builder()
                .userId(user.getId())
                .userName(user.getWxNickname())
                .avatarUrl(user.getAvatarUrl())
                .roles(user.getRoles())
                .wxId(user.getWxId()).build();
        return userDTO;
    }
}
