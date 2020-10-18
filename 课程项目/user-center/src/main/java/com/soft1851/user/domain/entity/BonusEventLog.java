package com.soft1851.user.domain.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/10/7
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bonus_event_log")
@ApiModel("积分日志实体类")
public class BonusEventLog {
    @Id
    @GeneratedValue(generator = "JDBC")
    @ApiModelProperty(name = "id", value = "主键id")
    private Integer id;

    @Column(name = "user_id")
    @ApiModelProperty(name = "userId", value = "用户id")
    private Integer userId;

    @Column(name = "value")
    @ApiModelProperty(name = "value", value = "分值")
    private Integer value;

    @Column(name = "event")
    @ApiModelProperty(name = "event", value = "事件")
    private String event;

    @Column(name = "create_time")
    @ApiModelProperty(name = "creatTime", value = "创建时间")
    private Date createTime;

    @Column(name = "description")
    @ApiModelProperty(name = "description", value = "描述")
    private String description;
}
