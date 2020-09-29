package com.soft1851.content.service;

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
class ShareServiceTest {
    @Resource
    private ShareService shareService;

    @Test
    void findById() {
        System.out.println(shareService.findById(1));
    }
}