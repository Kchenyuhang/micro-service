package com.soft1851.mysql.handler;

import com.soft1851.mysql.vo.StudentVo;
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
@RequestMapping("/consumer")
public class StudentHandler {
    @Resource
    private RestTemplate restTemplate;

    @GetMapping(value = "/all")
    public List<StudentVo> getAll() {
        return restTemplate.getForObject("http://sagittarius.com:9696/student/all", List.class);
    }

    @GetMapping(value = "/man")
    public List<StudentVo> getMan() {
        return restTemplate.getForObject("http://sagittarius.com:9696/student/searchMan", List.class);
    }

    @GetMapping(value = "/name")
    public List<StudentVo> getByName(@RequestParam("keyword") String keyword) {
        return restTemplate.getForObject("http://sagittarius.com:9696/student/name?keyword=" + keyword, List.class, keyword);
    }
}
