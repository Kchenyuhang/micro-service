package com.soft1851.user.dao;

import com.soft1851.user.dto.UserDto;
import com.soft1851.user.entity.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/20
 */
public interface UserMapper extends Mapper<User> {
}
