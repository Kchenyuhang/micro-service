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
 * @Date 2020/9/27
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
public class User {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer pkId;

    @Column(name = "name")
    private String name;

    @Column(name = "avatar")
    private String avatar;
}
