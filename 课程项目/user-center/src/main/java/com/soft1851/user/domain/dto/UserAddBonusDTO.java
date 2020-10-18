package com.soft1851.user.domain.dto;

import io.swagger.annotations.ApiModel;
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
@ApiModel("用户添加积分传参DTO")
public class UserAddBonusDTO {
    private Integer userId;
    /**
     * 积分
     */
    private Integer bonus;
}
