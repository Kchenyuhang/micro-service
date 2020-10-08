package com.soft1851.content.service;

import com.github.pagehelper.PageInfo;
import com.soft1851.content.domain.dto.ShareRequestDTO;
import com.soft1851.content.domain.entity.Share;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.List;

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

    @Test
    void insert() {
        ShareRequestDTO shareRequestDTO = ShareRequestDTO.builder()
                .author("陈宇航")
                .cover("https://kxingchen.oss-cn-shanghai.aliyuncs.com/develop/20200420204619.png")
                .downloadUrl("https://baidu.com")
                .isOriginal(true)
                .price(10)
                .summary("测试")
                .title("测试新增")
                .userId(1).build();
        shareService.insertShare(shareRequestDTO);
    }
}