package com.soft1851.user.domain.dto;

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
public class UserSignInDTO {
    private Integer userId;
}
