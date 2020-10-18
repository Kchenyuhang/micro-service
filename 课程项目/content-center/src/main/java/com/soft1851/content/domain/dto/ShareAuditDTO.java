package com.soft1851.content.domain.dto;

import com.soft1851.content.domain.enums.AuditStatusEnum;
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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ApiModel("审核传参DTO")
public class ShareAuditDTO {
    @ApiModelProperty(name = "auditStatusEnum", value = "审核结果（PASS、REJECTED、NOT_YET）")
    private AuditStatusEnum auditStatusEnum;
    @ApiModelProperty(name = "reason", value = "理由")
    private String reason;
}
