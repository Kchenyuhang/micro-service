package com.soft1851.cloud.study.controller;

import com.soft1851.cloud.study.entity.Student;
import com.soft1851.cloud.study.service.StudentService;
import com.soft1851.cloud.study.vo.StudentVo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/13
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Resource
    private StudentService studentService;

    @GetMapping(value = "/all")
    public List<StudentVo> getAll() {
        return studentService.getAll();
    }

    @GetMapping(value = "/searchMan")
    public List<StudentVo> getByKeyword() {
        return studentService.getMan();
    }

    @GetMapping(value = "/name")
    public List<StudentVo> getByName(@RequestParam("keyword") String keyword) {
        return studentService.getByKeyword(keyword);
    }
}
