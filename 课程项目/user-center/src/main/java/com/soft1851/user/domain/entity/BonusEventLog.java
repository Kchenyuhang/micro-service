package com.soft1851.user.domain.entity;

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
public class BonusEventLog {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "value")
    private Integer value;

    @Column(name = "event")
    private String event;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "description")
    private String description;
}
