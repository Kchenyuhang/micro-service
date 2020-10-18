package com.soft1851.content.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/8
 */
@Getter
@AllArgsConstructor
public enum AuditStatusEnum {
    /**
     * 待审核
     */
    NOT_YET,
    /**
     * 审核通过
     */
    PASS,
    /**
     * 审核不通过
     */
    REJECTED
}
