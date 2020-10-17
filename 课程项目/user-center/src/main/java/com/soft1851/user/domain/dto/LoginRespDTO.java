package com.soft1851.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/13
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRespDTO {
    private UserRespDTO user;
    private JwtTokenRespDTO token;
    private String roles;
    private Integer isUserSignIn;
}
