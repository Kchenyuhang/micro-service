package com.soft1851.user.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/23
 */
@Slf4j
@RestController
@RequestMapping(value = "/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    private final DiscoveryClient discoveryClient;

    @GetMapping(value = "/discovery")
    public List<ServiceInstance> getInstance() {
        return discoveryClient.getInstances("user-center");
    }
}
