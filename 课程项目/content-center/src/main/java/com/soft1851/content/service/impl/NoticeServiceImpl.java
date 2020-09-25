package com.soft1851.content.service.impl;

import com.soft1851.content.dao.NoticeMapper;
import com.soft1851.content.service.NoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/25
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class NoticeServiceImpl implements NoticeService {
    private final NoticeMapper noticeMapper;
    private final RestTemplate restTemplate;
}
