package com.soft1851.user.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Integer userId;
    private String wxId;
    private String roles;
    private String userName;
    private String avatarUrl;
}
