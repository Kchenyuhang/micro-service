package com.soft1851.content;

import com.yhchen.myself.tools.utils.Md5Util;
import org.springframework.web.client.RestTemplate;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/6
 */
public class SentinelTest {
    public static void main(String[] args) throws InterruptedException {
        RestTemplate restTemplate = new RestTemplate();
        for (int i = 0; i < 100; i++) {
            String object = restTemplate.getForObject("http://localhost:8088/test/byResource", String.class);
            System.out.println(object);
            Thread.sleep(200);
        }
        String md5 = Md5Util.getMd5("123");
    }
}
