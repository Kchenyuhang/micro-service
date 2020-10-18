package com.soft1851.user.domain.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/16
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("用户签到请求传参DTO")
public class UserSignInDTO {
    private Integer userId;
}
