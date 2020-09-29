package com.soft1851.user.controller;

import com.soft1851.user.dao.UserMapper;
import com.soft1851.user.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/23
 */
@Slf4j
@RestController
@RequestMapping(value = "/user")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserMapper userMapper;

    @GetMapping(value = "/hello")
    public String sendHello() {
        log.info("我被调用了~~~~~~");
        return "Hello World!";
    }

    @GetMapping(value = "/list")
    public List<User> getList() {
        return userMapper.selectAll();
    }
}
