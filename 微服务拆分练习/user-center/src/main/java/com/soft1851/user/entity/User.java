package com.soft1851.user.entity;

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
 * @Date 2020/9/20
 */
@Table(name = "t_user")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer pkId;

    @Column(name = "name")
    private String name;

    @Column(name = "avatar")
    private String avatar;
}
