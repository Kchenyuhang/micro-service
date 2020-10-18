package com.soft1851.content.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/05
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Table(name = "mid_user_share")
@ApiModel("用户兑换记录表")
public class MidUserShare {
    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(name = "id", value = "主键id")
    private Integer id;

    @Column(name = "share_id")
    @ApiModelProperty(name = "shareId", value = "分享id")
    private Integer shareId;

    @Column(name = "user_id")
    @ApiModelProperty(name = "userId", value = "兑换用户id")
    private Integer userId;
}
