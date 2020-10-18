package com.soft1851.content.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description 兑换数据传输对象
 * @Date 2020/10/15
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("兑换传参DTO")
public class ExchangeDTO {
    @ApiModelProperty(name = "userId", value = "兑换用户id")
    private Integer userId;
    @ApiModelProperty(name = "shareId", value = "被兑换的分享id")
    private Integer shareId;
}
