package com.soft1851.user.service;

import com.soft1851.user.domain.dto.LoginDTO;
import com.soft1851.user.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.user.domain.dto.UserDTO;
import com.soft1851.user.domain.entity.User;

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

    /**
     * 根据user查询用户
     *
     * @param user
     * @return
     */
    User getUserByUserDto(User user);

    /**
     * 审核投稿通过
     *
     * @param userAddBonusMsgDTO
     * @return
     */
    User updateUserBonus(UserAddBonusMsgDTO userAddBonusMsgDTO);

    /**
     * 用户登录
     *
     * @param loginDTO
     * @return
     */
    User login(LoginDTO loginDTO, String openId);
}
