package com.soft1851.mysql.vo;


import com.soft1851.mysql.entity.Student;

/**
 * @Author yhChen
 * @Description
 * @Date 2020/9/13
 */
public class StudentVo {

    private Integer pkId;
    private String name;
    private String jobNumber;
    private String sexName;

    public Integer getPkId() {
        return pkId;
    }

    public void setPkId(Integer pkId) {
        this.pkId = pkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
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
                "pkId=" + pkId +
                ", name='" + name + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", sexName='" + sexName + '\'' +
                '}';
    }

    public static StudentVo fromStudent(Student student, String sexName) {
        StudentVo result = new StudentVo();
        result.setPkId(student.getPkId());
        result.setJobNumber(student.getJobNumber());
        result.setName(student.getName());
        result.setSexName(sexName);
        return result;
    }
}
