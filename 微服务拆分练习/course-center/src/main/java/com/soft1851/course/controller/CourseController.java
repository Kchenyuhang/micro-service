package com.soft1851.course.controller;

import com.soft1851.course.dto.CourseDto;
import com.soft1851.course.entity.Course;
import com.soft1851.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/18
 */
@RestController
@RequestMapping(value = "/course")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseController {
    private final CourseService courseService;

    @GetMapping(value = "/all")
    public List<CourseDto> getAll() {
        return courseService.getAll();
    }

    @GetMapping(value = "/list")
    public List<CourseDto> getByUserId(@RequestParam Integer userId) {
        return courseService.getByUserId(userId);
    }
}
