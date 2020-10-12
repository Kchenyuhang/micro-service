package com.soft1851.content.feignclient;

import com.soft1851.content.domain.dto.UserDTO;
import org.apache.catalina.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/30
 */
@FeignClient(name = "user-center")
public interface TestUserCenterFeignClient {

    @GetMapping("/users/test/q")
    UserDTO query(@SpringQueryMap UserDTO userDTO);
}
