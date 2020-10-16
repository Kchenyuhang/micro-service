package com.soft1851.user.service.impl;

import com.soft1851.user.dao.BonusEventLogMapper;
import com.soft1851.user.dao.UserMapper;
import com.soft1851.user.domain.dto.LoginDTO;
import com.soft1851.user.domain.dto.ResponseDTO;
import com.soft1851.user.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.user.domain.dto.UserDTO;
import com.soft1851.user.domain.entity.BonusEventLog;
import com.soft1851.user.domain.entity.User;
import com.soft1851.user.domain.vo.UserVO;
import com.soft1851.user.service.UserService;
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
        return new ResponseDTO(true,"200","查询成功",user, 1L);
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
    public ResponseDTO getLog(UserDTO userDTO) {
        Example example = new Example(BonusEventLog.class);
        example.setOrderByClause("create_time DESC");
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId",userDTO.getId());
        List<BonusEventLog> bonusEventLogList = this.bonusEventLogMapper.selectByExample(example);
        return new ResponseDTO(true,"200","查询成功",bonusEventLogList, 1L);
    }

}
