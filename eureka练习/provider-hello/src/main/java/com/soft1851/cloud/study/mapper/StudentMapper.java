package com.soft1851.cloud.study.mapper;

import com.soft1851.cloud.study.entity.Student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/10
 */
@Mapper
@Component(value = "studentMapper")
public interface StudentMapper {

    @Select(value = "SELECT * FROM t_student WHERE del_flag = 0")
    List<Student> selectAll();

    @Select(value = "SELECT * FROM t_student WHERE sex = 0 AND del_flag = 0")
    List<Student> selectMan();

    @Select(value = "SELECT * FROM t_student WHERE name = #{keyword} AND del_flag = 0")
    List<Student> selectByName(String keyword);
}
