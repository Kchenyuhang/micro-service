package com.soft1851.content.service.impl;

import com.soft1851.content.dao.ShareMapper;
import com.soft1851.content.domain.dto.ShareDTO;
import com.soft1851.content.domain.dto.UserDTO;
import com.soft1851.content.domain.entity.Share;
import com.soft1851.content.feignclient.UserCenterFeignClient;
import com.soft1851.content.service.ShareService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/29
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ShareServiceImpl implements ShareService {
    private final ShareMapper shareMapper;
    private final UserCenterFeignClient userCenterFeignClient;

    @Override
    public ShareDTO findById(Integer id) {
        Share share = this.shareMapper.selectByPrimaryKey(id);
        Integer userId = share.getId();
        UserDTO userDTO = this.userCenterFeignClient.getById(userId);
        ShareDTO shareDTO = new ShareDTO();
        BeanUtils.copyProperties(share, shareDTO);
        shareDTO.setWxNickname(userDTO.getUserName());
        return shareDTO;
    }
}
