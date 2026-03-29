package com.example.studentmanager.model.dto.course;

import com.example.studentmanager.model.entity.CourseEntity;

public class CourseDTO extends CourseEntity {
    private String courseName;
    private Integer credit;
    private Integer teacherId;

    public CourseDTO() {}

    public CourseDTO(String courseName, Integer credit, Integer teacherId) {
        this.courseName = courseName;
        this.credit = credit;
        this.teacherId = teacherId;
    }

    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public Integer getCredit() { return credit; }
    public void setCredit(Integer credit) { this.credit = credit; }
    public Integer getTeacherId() { return teacherId; }
    public void setTeacherId(Integer teacherId) { this.teacherId = teacherId; }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", teacherId=" + teacherId +
                '}';
    }
}