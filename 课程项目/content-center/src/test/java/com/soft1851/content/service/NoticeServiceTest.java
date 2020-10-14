package com.soft1851.content.service;

import com.soft1851.content.domain.entity.Notice;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/5
 */
@SpringBootTest
class NoticeServiceTest {
    @Autowired
    private NoticeService noticeService;

    @Test
    void getLatest() {
//        Notice latest = noticeService.getLatest();
//        System.out.println(latest);
    }
}