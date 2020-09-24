package com.soft1851.cloud.study.service;

import com.soft1851.cloud.study.entity.Student;
import com.soft1851.cloud.study.vo.StudentVo;

import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/10
 */
public interface StudentService {

    List<StudentVo> getAll();

    List<StudentVo> getMan();

    List<StudentVo> getByKeyword(String keyword);
}
