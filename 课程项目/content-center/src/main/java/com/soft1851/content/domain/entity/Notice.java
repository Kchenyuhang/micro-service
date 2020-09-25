package com.soft1851.content.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/25
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notice")
public class Notice {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "content")
    private String content;

    @Column(name = "show_flag")
    private Integer showFlag;

    @Column(name = "create_time")
    private Date createTime;
}
