package com.soft1851.content.feignclient;

import com.soft1851.content.config.GlobalFeignConfiguration;
import com.soft1851.content.domain.dto.ResponseDTO;
import com.soft1851.content.domain.dto.UserAddBonusDTO;
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
     * http://user-center/users/{id}
     *
     * @param id
     * @return
     */
    @GetMapping("/users/{id}")
    ResponseDTO getById(@PathVariable Integer id);

    /**
     * 用户增加积分
     *
     * @param userAddBonusDTO
     * @return
     */
    @PutMapping(value = "/users/add-bonus")
    UserDTO addBonus(@RequestBody UserAddBonusDTO userAddBonusDTO);
}
