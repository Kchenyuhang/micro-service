package com.soft1851.course.service;

import com.soft1851.course.dto.CourseDto;
import com.soft1851.course.dto.UserDto;
import com.soft1851.course.entity.Course;

import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/18
 */
public interface CourseService {

    /**
     * 查询所有
     *
     * @return
     */
    List<CourseDto> getAll();

    /**
     * 根据用户Id查询
     *
     * @param userId
     * @return
     */
    List<CourseDto> getByUserId(Integer userId);
}
