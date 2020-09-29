package com.soft1851.content.controller;

import com.soft1851.content.domain.dto.ShareDTO;
import com.soft1851.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/29
 */
@RestController
@RequestMapping(value = "/share")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareController {
    private final ShareService shareService;

    @GetMapping(value = "/one/{id}")
    public ShareDTO getOneById(@PathVariable Integer id) {
        return shareService.findById(id);
    }
}
