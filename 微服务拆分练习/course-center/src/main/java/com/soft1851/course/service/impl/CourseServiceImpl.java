package com.soft1851.course.service.impl;

import com.soft1851.course.dto.CourseDto;
import com.soft1851.course.dto.UserDto;
import com.soft1851.course.entity.Course;
import com.soft1851.course.mapper.CourseMapper;
import com.soft1851.course.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.util.Sqls;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/18
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CourseServiceImpl implements CourseService {
    private final CourseMapper courseMapper;

    private final RestTemplate restTemplate;

    @Override
    public List<CourseDto> getAll() {
        List<Course> courseList = courseMapper.selectAll();
        List<CourseDto> courseDtoList = new ArrayList<>();
        courseList.forEach(course -> {
            CourseDto courseDto = new CourseDto();
            Integer userId = course.getUserId();
            UserDto userDto = restTemplate.getForObject("http://112.74.99.22:8081/user/id?userId=" + userId, UserDto.class, userId);
            if (userDto != null) {
                courseDto.setCourseName(course.getName());
                courseDto.setCover(course.getCover());
                courseDto.setIsEnabled(course.getIsEnabled());
                courseDto.setUserName(userDto.getUserName());
                courseDto.setAvatar(userDto.getAvatar());
            }
            courseDtoList.add(courseDto);
        });
        return courseDtoList;
    }

    @Override
    public List<CourseDto> getByUserId(Integer userId) {
        Example example = Example.builder(Course.class)
                .select("name", "cover", "isEnabled")
                .where(Sqls.custom().andEqualTo("userId", userId))
                .build();
        List<Course> courseList = courseMapper.selectByExample(example);
        List<CourseDto> courseDtoList = new ArrayList<>();
        UserDto userDto = restTemplate.getForObject("http://112.74.99.22:8081/user/id?userId=" + userId, UserDto.class, userId);
        if (userDto != null) {
            courseList.forEach(course -> {
                CourseDto courseDto = new CourseDto();
                courseDto.setCourseName(course.getName());
                courseDto.setCover(course.getCover());
                courseDto.setIsEnabled(course.getIsEnabled());
                courseDto.setUserName(userDto.getUserName());
                courseDto.setAvatar(userDto.getAvatar());
                courseDtoList.add(courseDto);
            });
        }
        return courseDtoList;
    }
}
