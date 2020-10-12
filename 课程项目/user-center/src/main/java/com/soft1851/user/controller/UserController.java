package com.soft1851.user.controller;

import com.soft1851.user.domain.dto.LoginDTO;
import com.soft1851.user.domain.dto.LoginRestDTO;
import com.soft1851.user.domain.dto.UserAddBonusMsgDTO;
import com.soft1851.user.domain.dto.UserDTO;
import com.soft1851.user.domain.entity.User;
import com.soft1851.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/25
 */
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;

    @GetMapping(value = "/{id}")
    public UserDTO getById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping(value = "/test/q")
    public User query(User user) {
        return userService.getUserByUserDto(user);
    }

    @PostMapping(value = "/bonus/pass")
    public User updateBonus(@RequestBody UserAddBonusMsgDTO userAddBonusMsgDTO) {
        return this.userService.updateUserBonus(userAddBonusMsgDTO);
    }

    @PostMapping(value = "/login")
    public LoginRestDTO getToken(@RequestBody LoginDTO loginDTO) {
        System.out.println(loginDTO);
        User user = userService.login(loginDTO);
        return LoginRestDTO.builder()
                .user(user)
                .token("aaabbbccc")
                .build();
    }
}
