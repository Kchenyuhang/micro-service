package com.soft1851.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/18
 */
@SpringBootApplication
@MapperScan(basePackages = "com.soft1851.course.mapper")
public class CourseCenterMain {
    public static void main(String[] args) {
        SpringApplication.run(CourseCenterMain.class, args);
    }

    /**
     * 创建 RestTemplate 实例并通过 @Bean 注解注入到 IoC 容器中
     *
     * @return
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
