package com.example.studentmanager.model.dto.score;

public class ScoreStuCourseDTO {
    private Integer studentId;
    private Integer courseId;

    public ScoreStuCourseDTO(Integer studentId, Integer courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public ScoreStuCourseDTO() {
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    @Override
    public String toString() {
        return "ScoreStuCourseDTO{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}