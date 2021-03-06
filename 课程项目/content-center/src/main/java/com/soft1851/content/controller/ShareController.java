package com.soft1851.content.controller;

import com.soft1851.content.auth.CheckLogin;
import com.soft1851.content.domain.dto.ExchangeDTO;
import com.soft1851.content.domain.dto.ShareDTO;
import com.soft1851.content.domain.dto.ShareRequestDTO;
import com.soft1851.content.domain.entity.Share;
import com.soft1851.content.service.ShareService;
import com.soft1851.content.util.JwtOperator;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/29
 */
@Slf4j
@RestController
@RequestMapping(value = "/shares")
@Api(tags = "分享接口", value = "提供分享相关的Rest API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {
    private final ShareService shareService;
    private final JwtOperator jwtOperator;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "查询指定id的分享详情", notes = "查询指定id的分享详情")
    public ShareDTO getOneById(@PathVariable Integer id) {
        return this.shareService.findById(id);
    }

    @GetMapping("/query")
    @ApiOperation(value = "分享列表", notes = "分享列表")
    public List<Share> query(
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize,
            @RequestHeader(value = "X-Token", required = false) String token) {
        if (pageSize > 100) {
            pageSize = 100;
        }
        System.out.println(token);
        Integer userId = null;

        if (!"no-token".equals(token)) {
            System.out.println(token);
            Claims claims = this.jwtOperator.getClaimsFromToken(token);
            log.info(claims.toString());
            userId = (Integer) claims.get("id");
        } else {
            log.info("没有token");
        }
        return this.shareService.query(title, pageNo, pageSize, userId).getList();
    }

    @PostMapping("/contribute")
    @CheckLogin
    @ApiOperation(value = "投稿接口", notes = "用户投稿")
    public int insertShare(@RequestBody ShareRequestDTO shareRequestDTO) {
        return shareService.insertShare(shareRequestDTO);
    }

    @PutMapping(value = "/contribute/{id}")
    @CheckLogin
    @ApiOperation(value = "编辑投稿", notes = "编辑投稿")
    public Share updateShare(@PathVariable Integer id, @RequestBody ShareRequestDTO shareRequestDTO) {
        return shareService.updateShare(id, shareRequestDTO);
    }

    @PostMapping("/exchange")
    @CheckLogin
    @ApiOperation(value = "积分兑换", notes = "积分兑换")
    public Share exchange(@RequestBody ExchangeDTO exchangeDTO) {
        System.out.println(exchangeDTO + ">>>>>>>>>>>>");
        return this.shareService.exchange(exchangeDTO);
    }

    @GetMapping(value = "/my/contributions")
//    @CheckLogin
    @ApiOperation(value = "我的投稿", notes = "我的投稿")
    public List<Share> findMyContribution(
            @RequestParam Integer userId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
//        System.out.println(this.shareService.findMyContribute(pageNo, pageSize, userId).getList());
        return this.shareService.findMyContribute(pageNo, pageSize, userId).getList();
    }

    @GetMapping(value = "/my")
    @ApiOperation(value = "我的兑换", notes = "我的兑换")
    public List<Share> findMyExchange(
            @RequestParam Integer userId,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize) {
        return this.shareService.findMyExchange(pageNo, pageSize, userId).getList();
    }
}
