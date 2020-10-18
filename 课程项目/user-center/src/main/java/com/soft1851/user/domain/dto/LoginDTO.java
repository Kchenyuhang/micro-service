package com.soft1851.user.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/12
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("登录请求传参DTO")
public class LoginDTO {
    private String openId;
    private String loginCode;
    private String wxNickname;
    private String avatarUrl;
}
