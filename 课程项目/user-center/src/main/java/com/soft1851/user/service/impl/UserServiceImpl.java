package com.soft1851.user.service.impl;

import com.soft1851.user.dao.BonusEventLogMapper;
import com.soft1851.user.dao.UserMapper;
import com.soft1851.user.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.user.domain.dto.UserDTO;
import com.soft1851.user.domain.entity.BonusEventLog;
import com.soft1851.user.domain.entity.User;
import com.soft1851.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
        log.info("我被调用了");
        User user = this.userMapper.selectByPrimaryKey(userId);
        UserDTO userDTO = UserDTO.builder()
                .userId(user.getId())
                .userName(user.getWxNickname())
                .avatarUrl(user.getAvatarUrl())
                .roles(user.getRoles())
                .wxId(user.getWxId()).build();
        return userDTO;
    }

    @Override
    public User getUserByUserDto(User user) {
        return userMapper.selectOne(user);
    }

    @Override
    public User updateUserBonus(UserAddBonusMsgDTO userAddBonusMsgDTO) {
        User user = this.userMapper.selectByPrimaryKey(userAddBonusMsgDTO.getUserId());
        user.setBonus(user.getBonus() + userAddBonusMsgDTO.getBonus());
        this.userMapper.updateByPrimaryKey(user);
        this.bonusEventLogMapper.insert(BonusEventLog.builder()
                .userId(userAddBonusMsgDTO.getUserId())
                .value(50)
                .event("CONTRIBUTE")
                .createTime(new Date(System.currentTimeMillis()))
                .description("投稿加积分")
                .build()
        );
        return user;
    }
}
