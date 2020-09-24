package com.soft1851.cloud.study.service.impl;


import com.soft1851.cloud.study.entity.Student;
import com.soft1851.cloud.study.mapper.StudentMapper;
import com.soft1851.cloud.study.service.StudentService;
import com.soft1851.cloud.study.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/10
 */
@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;

    private String sexName;

    @Override
    public List<StudentVo> getAll() {
        List<Student> students = studentMapper.selectAll();
        List<StudentVo> list = new ArrayList<>();
        students.forEach(student -> {
            if (student.isSex()) {
                sexName = "女";
            } else {
                sexName = "男";
            }
            StudentVo result = StudentVo.fromStudent(student, sexName);
            list.add(result);
        });
        return list;
    }

    @Override
    public List<StudentVo> getMan() {
        List<Student> students = studentMapper.selectMan();
        List<StudentVo> list = new ArrayList<>();
        students.forEach(student -> {
            if (student.isSex()) {
                sexName = "女";
            } else {
                sexName = "男";
            }
            StudentVo result = StudentVo.fromStudent(student, sexName);
            list.add(result);
        });
        return list;
    }

    @Override
    public List<StudentVo> getByKeyword(String keyword) {
        List<Student> students = studentMapper.selectByName(keyword);
        List<StudentVo> list = new ArrayList<>();
        students.forEach(student -> {
            if (student.isSex()) {
                sexName = "女";
            } else {
                sexName = "男";
            }
            StudentVo result = StudentVo.fromStudent(student, sexName);
            list.add(result);
        });
        return list;
    }
}
