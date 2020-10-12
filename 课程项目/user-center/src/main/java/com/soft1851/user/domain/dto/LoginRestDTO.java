package com.soft1851.user.domain.dto;

import com.soft1851.user.domain.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description 登录返回结果
 * @Date 2020/10/12
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRestDTO {
    private User user;
    private String token;
}
