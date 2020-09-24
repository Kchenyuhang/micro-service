package com.soft1851.mysql.controller;

import com.soft1851.mysql.entity.Student;
import com.soft1851.mysql.service.StudentService;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/10
 */
@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Resource
    private StudentService studentService;
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/all")
    public List<Student> getAll() {
        return studentService.getAll();
    }

    @GetMapping(value = "/hello")
    public String getHello() {
        //通过restTemplate来调用provider提供的服务
        return restTemplate.getForObject("http://sagittarius.com:9696/hello", String.class);
    }
}
