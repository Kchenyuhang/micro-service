package com.soft1851.content.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/8
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("rocketMQ发送添加积分消息DTO")
public class UserAddBonusMsgDTO {
    @ApiModelProperty(name = "userId", value = "用户id")
    private Integer userId;
    @ApiModelProperty(name = "bonus", value = "积分")
    private Integer bonus;
}
