package com.soft1851.user.domain.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserVO {
    private Integer id;
    private String wxNickname;
}
