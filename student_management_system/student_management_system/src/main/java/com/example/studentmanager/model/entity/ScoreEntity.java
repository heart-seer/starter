package com.example.studentmanager.model.entity;

public class ScoreEntity {
    private Integer scoreId;
    private Integer studentId;
    private Integer courseId;
    private Integer score;

    public ScoreEntity() {}

    public ScoreEntity(Integer studentId, Integer courseId, Integer score) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.score = score;
    }
    public Integer getScoreId() { return scoreId; }
    public void setScoreId(Integer scoreId) { this.scoreId = scoreId; }
    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public Integer getCourseId() { return courseId; }
    public void setCourseId(Integer courseId) { this.courseId = courseId; }
    public Integer getScore() { return score; }
    public void setScore(Integer score) { this.score = score; }

    @Override
    public String toString() {
        return "ScoreEntity{" +
                "scoreId=" + scoreId +
                ", studentId=" + studentId +
                ", courseId=" + courseId +
                ", score=" + score +
                '}';
    }
}