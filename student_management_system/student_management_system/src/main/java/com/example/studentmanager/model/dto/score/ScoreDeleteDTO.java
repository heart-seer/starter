package com.example.studentmanager.model.dto.score;

public class ScoreDeleteDTO {
    private Integer studentId;
    private Integer courseId;

    public ScoreDeleteDTO() {
    }

    public ScoreDeleteDTO(Integer studentId, Integer courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }

    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }

    @Override
    public String toString() {
        return "ScoreDeleteDTO{" +
                "studentId=" + studentId +
                ", courseId=" + courseId +
                '}';
    }
}