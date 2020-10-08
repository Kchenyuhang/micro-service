package com.soft1851.content;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.soft1851.content.domain.dto.UserDTO;
import com.soft1851.content.feignclient.TestBaiduFeignClient;
import com.soft1851.content.feignclient.TestUserCenterFeignClient;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.AsyncRestTemplate;

import javax.ws.rs.GET;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/6
 */
@RestController
@Slf4j
@RequestMapping(value = "/test")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class TestController {

    private final TestUserCenterFeignClient userCenterFeignClient;

    @GetMapping("/test-q")
    public UserDTO queryByUserDto(UserDTO userDTO) {
        return userCenterFeignClient.query(userDTO);
    }

    private final TestBaiduFeignClient testBaiduFeignClient;

    @GetMapping(value = "/baidu")
    public String baiduIndex() {
        return this.testBaiduFeignClient.index();
    }

    @GetMapping(value = "byResource")
    @SentinelResource(value = "byName", blockHandler = "handleException")
    public String byResource() {
        return "按名称限流";
    }

    public String handleException(BlockException blockException) {
        return "服务不可用";
    }

    private final AsyncRestTemplate asyncRestTemplate;

    @GetMapping(value = "/user/{id}")
    public ListenableFuture<ResponseEntity<String>> getUser(@PathVariable Integer id) {
        return asyncRestTemplate.getForEntity("http://user-center/user/one/{id}", String.class, id);
    }
}
