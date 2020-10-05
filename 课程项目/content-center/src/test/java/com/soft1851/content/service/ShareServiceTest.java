package com.soft1851.content.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.content.domain.entity.Share;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/5
 */
@SpringBootTest
class ShareServiceTest {
    @Autowired
    private ShareService shareService;

    @Test
    void query() {
        PageInfo<Share> query = shareService.query(null, 1, 10, 1);
        List<Share> list = query.getList();
        list.forEach(item -> System.out.println(item.getTitle() + "," + item.getDownloadUrl()));
    }
}