package com.example.studentmanager.model.entity;

import com.example.studentmanager.model.dto.course.CourseUpdateDTO;

public class CourseEntity extends CourseUpdateDTO {
    private Integer courseId;
    private String courseName;
    private Integer credit;
    private Integer teacherId;

    public CourseEntity() {}

    public CourseEntity(Integer courseId, String courseName, Integer credit, Integer teacherId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.credit = credit;
        this.teacherId = teacherId;
    }

    public CourseEntity(String courseName, Integer credit, Integer teacherId) {
        this.courseName = courseName;
        this.credit = credit;
        this.teacherId = teacherId;
    }

    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }
    public String getCourseName() { return courseName; }
    public void setCourseName(String courseName) { this.courseName = courseName; }
    public Integer getCredit() { return credit; }
    public void setCredit(Integer credit) { this.credit = credit; }
    public Integer getTeacherId() { return teacherId; }
    public void setTeacherId(Integer teacherId) { this.teacherId = teacherId; }

    @Override
    public String toString() {
        return "CourseEntity{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", credit=" + credit +
                ", teacherId=" + teacherId +
                '}';
    }
}
