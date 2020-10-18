package com.soft1851.content.domain.dto;

import io.swagger.annotations.ApiModel;
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
@ApiModel("统一封装请求返回结果ResponseDTO")
public class ResponseDTO {
    private Boolean succ;
    private String code;
    private String msg;
    private Object data;
    private Long ts;
}
