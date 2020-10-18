package com.soft1851.user.controller;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import com.soft1851.user.config.WxConfiguration;
import com.soft1851.user.dao.UserMapper;
import com.soft1851.user.domain.dto.*;
import com.soft1851.user.domain.entity.BonusEventLog;
import com.soft1851.user.domain.entity.User;
import com.soft1851.user.service.UserService;
import com.soft1851.user.util.JwtOperator;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/25
 */
@Slf4j
@RestController
@RequestMapping(value = "/users")
@Api(tags = "用户中心接口", value = "提供用户中心相关的Rest API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {
    private final UserMapper userMapper;
    private final UserService userService;
    private final WxMaService wxMaService;
    private final JwtOperator jwtOperator;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查询指定id的用户详情", notes = "查询指定id的用户详情")
    public ResponseDTO getById(@PathVariable Integer id) {
        return this.userService.getUserById(id);
    }

    @GetMapping(value = "/me")
    @ApiOperation(value = "查询登录用户个人详情", notes = "查询登录用户个人详情")
    public ResponseDTO query(@RequestParam Integer userId) {
        return this.userService.getUserById(userId);
    }

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录接口", notes = "登录接口")
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
        System.out.println(user);
        ResponseDTO responseDTO = this.userService.checkIsSign(UserSignInDTO.builder().userId(user.getId()).build());
        int isUserSinIn = 0;
        if ("200".equals(responseDTO.getCode())) {
            isUserSinIn = 0;
        } else {
            isUserSinIn = 1;
        }
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
                .roles(user.getRoles())
                .isUserSignIn(isUserSinIn)
                .build();
    }

    @GetMapping(value = "/bonus-logs")
    @ApiOperation(value = "查询用户积分日志", notes = "查询用户积分日志")
    public List<BonusEventLog> getUserBonusLog(
            @RequestParam Integer userId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        return this.userService.getLog(pageNo,pageSize,userId).getList();
    }

    @PutMapping(value = "/add-bonus")
    @ApiOperation(value = "添加用户积分", notes = "添加用户积分")
    public User addBonus(@RequestBody UserAddBonusDTO userAddBonusDTO) {
        log.info("增减积分接口被请求了...");
        Integer userId = userAddBonusDTO.getUserId();
        userService.addBonus(
                UserAddBonusMsgDTO.builder()
                        .userId(userId)
                        .bonus(userAddBonusDTO.getBonus())
                        .description("兑换分享...")
                        .event("BUY")
                        .build()
        );
        return this.userMapper.selectByPrimaryKey(userId);
    }

    @PutMapping(value = "/sign")
    @ApiOperation(value = "用户签到", notes = "用户签到")
    public ResponseDTO userSign(@RequestBody UserSignInDTO userSignInDTO) {
        return this.userService.signIn(userSignInDTO);
    }
}
