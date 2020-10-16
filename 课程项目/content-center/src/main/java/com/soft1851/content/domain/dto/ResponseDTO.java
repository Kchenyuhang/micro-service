package com.soft1851.content.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author yhChen
 * @Description 统一返回结果封装对象
 * @Date 2020/10/15
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseDTO {
    private Boolean succ;
    private String code;
    private String msg;
    private Object data;
    private Long ts;
}
