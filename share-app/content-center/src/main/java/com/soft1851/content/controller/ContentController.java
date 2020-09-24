package com.soft1851.content.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/23
 */
@Slf4j
@RestController
@RequestMapping(value = "content")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ContentController {

    private final DiscoveryClient discoveryClient;

    private final RestTemplate restTemplate;

    @GetMapping(value = "/discovery")
    public List<ServiceInstance> getDiscovery() {
        return discoveryClient.getInstances("content-center");
    }

    @GetMapping(value = "/hello")
    public String getHello() {
        return restTemplate.getForObject("http://localhost:8080/user/hello", String.class);
    }

    @GetMapping(value = "/call/hello")
    public String callUserController() {
        //用户中心所有的实例信息
        List<ServiceInstance> instances = discoveryClient.getInstances("user-center");
        //stream编程、Lambda表达式、函数式编程
//        String targetUrl = instances.stream()
//                .map(instance -> instance.getUri().toString() + "/user/hello")
//                .findFirst()
//                .orElseThrow(() -> new IllegalArgumentException("当前没有实例！"));
        //随机获取
        //方法一
//        String targetUrl = instances.stream().map(instance -> instance.getUri().toString() + "/user/hello")
//                .collect(Collectors.toList()).get(new Random().nextInt(instances.size()));
        //方法二
        List<String> targetUrls = instances.stream().map(instance -> instance.getUri().toString() + "/user/hello").collect(Collectors.toList());
        String targetUrl = targetUrls.get(new Random().nextInt(targetUrls.size()));
        log.info(targetUrl);
        return restTemplate.getForObject(targetUrl, String.class);
    }

    @GetMapping(value = "call/ribbon")
    public String getRibbon() {
        return restTemplate.getForObject("http://user-center/user/hello", String.class);
    }
}
