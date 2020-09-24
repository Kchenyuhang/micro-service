package com.soft1851.cloud.study;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/9
 */
@SpringBootApplication
@EnableDiscoveryClient
public class ProviderHelloMain {
    public static void main(String[] args) {
        SpringApplication.run(ProviderHelloMain.class, args);
    }
}
