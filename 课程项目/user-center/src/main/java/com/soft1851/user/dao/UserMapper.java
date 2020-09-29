package com.soft1851.user.dao;

import com.soft1851.user.domain.entity.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/25
 */
public interface UserMapper extends Mapper<User> {

    /**
     * 根据id查询用户信息
     *
     * @param userId
     * @return
     */
    @Select(value = "SELECT * FROM user WHERE id = #{userId}")
    User findById(Integer userId);
}
