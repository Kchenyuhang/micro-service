package com.soft1851.course.entity;

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
 * @Date 2020/9/18
 */
@Table(name = "t_course")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(generator = "JDBC")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "cover")
    private String cover;

    @Column(name = "teacher_name")
    private String teacherName;

    @Column(name = "class_name")
    private String className;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "is_enabled")
    private Integer isEnabled;
}
