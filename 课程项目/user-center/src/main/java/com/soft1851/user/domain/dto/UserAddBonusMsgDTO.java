package com.soft1851.user.domain.dto;

import io.swagger.annotations.ApiModel;
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
    private Integer userId;
    private Integer bonus;
    private String description;
    private String event;
}
