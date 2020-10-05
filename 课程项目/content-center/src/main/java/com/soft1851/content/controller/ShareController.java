package com.soft1851.content.controller;

import com.soft1851.content.domain.dto.ShareDTO;
import com.soft1851.content.domain.dto.UserDTO;
import com.soft1851.content.domain.entity.Share;
import com.soft1851.content.feignclient.TestBaiduFeignClient;
import com.soft1851.content.feignclient.TestUserCenterFeignClient;
import com.soft1851.content.service.ShareService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/29
 */
@RestController
@RequestMapping(value = "/share")
@Api(tags = "分享接口", value = "提供分享相关的Rest API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {
    private final ShareService shareService;

    @GetMapping(value = "/one/{id}")
    @ApiOperation(value = "查询指定id的分享详情", notes = "查询指定id的分享详情")
    public ShareDTO getOneById(@PathVariable Integer id) {
        return shareService.findById(id);
    }

    private final TestUserCenterFeignClient userCenterFeignClient;
    @GetMapping("/test-q")
    @ApiOperation(value = "多参数查询", notes = "多参数查询")
    public UserDTO queryByUserDto(UserDTO userDTO) {
        return userCenterFeignClient.query(userDTO);
    }
//
//    private final TestBaiduFeignClient testBaiduFeignClient;
//    @GetMapping(value = "/baidu")
//    public String baiduIndex() {
//        return this.testBaiduFeignClient.index();
//    }

    @GetMapping("/query")
    @ApiOperation(value = "分享列表", notes = "分享列表")
    public List<Share> query(
            @RequestParam(required = false) String title,
            @RequestParam(required = false, defaultValue = "1") Integer pageNo,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false) Integer userId) throws Exception {
        if (pageSize > 100) {
            pageSize = 100;
        }
        return this.shareService.query(title, pageNo, pageSize, userId).getList();
    }
}
