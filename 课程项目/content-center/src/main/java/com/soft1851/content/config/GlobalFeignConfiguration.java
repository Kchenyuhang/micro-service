package com.soft1851.content.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/29
 */
public class GlobalFeignConfiguration {
    @Bean
    public Logger.Level level() {
        // 让feign打印所有请求的细节
        return Logger.Level.FULL;
    }
}
