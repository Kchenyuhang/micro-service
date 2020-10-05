package com.soft1851.content;

import com.purgeteam.dispose.starter.annotation.EnableGlobalDispose;
import com.soft1851.content.config.GlobalFeignConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author yhChen
 */
@SpringBootApplication
@MapperScan(value = "com.soft1851.content.dao")
@EnableFeignClients(defaultConfiguration = GlobalFeignConfiguration.class)
@EnableGlobalDispose
public class ContentCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ContentCenterApplication.class, args);
    }

    /**
     * 创建 RestTemplate 实例并通过 @Bean 注解注入到 IoC 容器中
     *
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
