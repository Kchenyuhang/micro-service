package com.soft1851.content.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description 用户增加积分的传输对象
 * @Date 2020/10/15
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAddBonusDTO {
    private Integer userId;
    /**
     * 积分
     */
    private Integer bonus;
}
