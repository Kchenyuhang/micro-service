package com.soft1851.content.feignclient;

import com.soft1851.content.domain.dto.UserDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/29
 */
@FeignClient(name = "user-center")
public interface UserCenterFeignClient {

    /**
     * http://user-center/user/one/{id}
     *
     * @param id
     * @return
     */
    @GetMapping("/user/one/{id}")
    UserDTO getById(@PathVariable Integer id);
}
