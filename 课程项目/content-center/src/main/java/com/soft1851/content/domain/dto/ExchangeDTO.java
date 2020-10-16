package com.soft1851.content.domain.dto;

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
public class ExchangeDTO {
    private Integer userId;
    private Integer shareId;
}
