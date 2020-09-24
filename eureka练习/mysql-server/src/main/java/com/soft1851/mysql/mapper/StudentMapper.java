package com.soft1851.mysql.mapper;

import com.soft1851.mysql.entity.Student;
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
}
