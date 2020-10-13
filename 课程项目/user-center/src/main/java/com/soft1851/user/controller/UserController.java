package com.soft1851.user.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.soft1851.user.config.WxConfiguration;
import com.soft1851.user.domain.dto.*;
import com.soft1851.user.domain.entity.User;
import com.soft1851.user.service.UserService;
import com.soft1851.user.util.JwtOperator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/25
 */
@Slf4j
@RestController
@RequestMapping(value = "/users")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserService userService;
    private final WxMaService wxMaService;
    private final JwtOperator jwtOperator;

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
    public LoginRespDTO getToken(@RequestBody LoginDTO loginDTO) throws WxErrorException {
        String openId;
        //微信小程序登录，需要根据code请求openId
        if (loginDTO.getLoginCode() != null) {
            // 微信服务端校验是否已经登录的结果
            this.wxMaService.setWxMaConfig(new WxConfiguration().wxMaConfig());
            WxMaJscode2SessionResult result = this.wxMaService.getUserService().getSessionInfo(loginDTO.getLoginCode());
            log.info(result.toString());
            // 微信的openId，用户在微信这边的唯一标识
            openId = result.getOpenid();
        } else {
            openId = loginDTO.getOpenId();
        }
        // 看用户是否注册，如果没有注册就（插入），如果已经注册就返回其信息
        User user = userService.login(loginDTO, openId);
        // 颁发token
        Map<String, Object> userInfo = new HashMap<>(3);
        userInfo.put("id", user.getId());
        userInfo.put("wxNickname", user.getWxNickname());
        userInfo.put("role", user.getRoles());
        String token = jwtOperator.generateToken(userInfo);
        log.info(
                "{}登录成功，生成token = {}，有效期到：{}",
                user.getWxNickname(),
                token, jwtOperator.getExpirationTime()
        );
        return LoginRespDTO.builder()
                .user(UserRespDTO.builder()
                        .id(user.getId())
                        .wxNickname(user.getWxNickname())
                        .avatarUrl(user.getAvatarUrl())
                        .bonus(user.getBonus())
                        .build())
                .token(JwtTokenRespDTO.builder()
                        .token(token)
                        .expirationTime(jwtOperator.getExpirationTime().getTime())
                        .build())
                .build();
    }

//    @PostMapping(value = "/wxLogin")
//    public LoginRespDTO codeAuth(@RequestBody WxLoginDTO wxLoginDTO) throws WxErrorException {
//        WxMaJscode2SessionResult result = this.wxMaService.getUserService().getSessionInfo(wxLoginDTO.getCode());
//        System.out.println(result);
//        String openId = result.getOpenid();
//        LoginDTO loginDTO = LoginDTO.builder()
//                .openId(openId)
//                .wxNickname(wxLoginDTO.getWxNickname())
//                .avatarUrl(wxLoginDTO.getAvatarUrl())
//                .build();
//        User user = userService.login(loginDTO);
//        return LoginRestDTO.builder()
//                .user(user)
//                .token("aaaabbbbcccc")
//                .build();
//    }
}
