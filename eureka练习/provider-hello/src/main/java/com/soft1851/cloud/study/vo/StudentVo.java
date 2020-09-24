package com.soft1851.cloud.study.vo;

import com.soft1851.cloud.study.entity.Student;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/13
 */
public class StudentVo {

    private String name;
    private String sexName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSexName() {
        return sexName;
    }

    public void setSexName(String sexName) {
        this.sexName = sexName;
    }

    @Override
    public String toString() {
        return "StudentVo{" +
                ", name='" + name + '\'' +
                ", sexName='" + sexName + '\'' +
                '}';
    }

    public static StudentVo fromStudent(Student student, String sexName) {
        StudentVo result = new StudentVo();
        result.setName(student.getName());
        result.setSexName(sexName);
        return result;
    }
}
