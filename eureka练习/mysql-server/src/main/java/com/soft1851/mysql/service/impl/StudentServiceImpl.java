package com.soft1851.mysql.service.impl;

import com.soft1851.mysql.entity.Student;
import com.soft1851.mysql.mapper.StudentMapper;
import com.soft1851.mysql.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Student> getAll() {
        return studentMapper.selectAll();
    }
}
