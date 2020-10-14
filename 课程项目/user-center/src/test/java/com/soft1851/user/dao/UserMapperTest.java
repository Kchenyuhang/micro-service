package com.soft1851.user.dao;

import com.soft1851.user.domain.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/29
 */
@SpringBootTest
class UserMapperTest {
    @Resource
    private UserMapper userMapper;

    @Test
    void findById() {
        User user = User.builder()
                .id(1).build();
        System.out.println(userMapper.selectOne(user));
    }
}