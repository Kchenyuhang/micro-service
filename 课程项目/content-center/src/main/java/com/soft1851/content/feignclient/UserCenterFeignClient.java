package com.soft1851.content.feignclient;

import com.soft1851.content.config.GlobalFeignConfiguration;
import com.soft1851.content.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.content.domain.dto.UserDTO;
import com.soft1851.content.domain.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/29
 */
//@FeignClient(name = "user-center", configuration = GlobalFeignConfiguration.class)
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

    /**
     * http://localhost:8085/user/bonus/pass/{id}
     *
     * @param userAddBonusMsgDTO
     */
    @PostMapping(value = "/user/bonus/pass")
    User updateBonus(@RequestBody UserAddBonusMsgDTO userAddBonusMsgDTO);
}
