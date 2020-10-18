package com.soft1851.content.controller;

import com.soft1851.content.domain.dto.ShareAuditDTO;
import com.soft1851.content.domain.entity.Share;
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
 * @Date 2020/10/8
 */
@RestController
@RequestMapping(value = "admin/shares")
@Api(tags = "审核接口", value = "提供审核的 API")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareAdminController {
    private final ShareService shareService;

    @PutMapping("/audit/{id}")
    @ApiOperation(value = "审核接口", notes = "审核接口")
    public Share audit(@PathVariable Integer id, @RequestBody ShareAuditDTO shareAuditDTO) {
//        Integer shareId = Integer.parseInt(id);
        return shareService.auditById(id, shareAuditDTO);
    }

    @GetMapping(value = "/to-audit")
    @ApiOperation(value = "查询待审核的投稿", notes = "查询待审核的投稿")
    public List<Share> findShareNotYet() {
        return shareService.findShareNotYet().getList();
    }

    @GetMapping(value = "/have-audit")
    @ApiOperation(value = "查询已审核的投稿", notes = "查询已审核的投稿")
    public List<Share> findShareAudit() {
        return shareService.findShareAudit().getList();
    }
}
