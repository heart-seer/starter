package com.example.studentmanager.model.dto.student;

public class StudentUpdateDTO {
    private Integer studentId;
    private String name;
    private String gender;
    private String major;
    private Integer userId;
    private String studentNo;

    public StudentUpdateDTO(Integer studentId, String name, String gender, String major, Integer userId, String studentNo) {
        this.studentId = studentId;
        this.name = name;
        this.gender = gender;
        this.major = major;
        this.userId = userId;
        this.studentNo = studentNo;
    }

    public StudentUpdateDTO() {
    }

    public Integer getStudentId() { return studentId; }
    public void setStudentId(Integer studentId) { this.studentId = studentId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    public String getStudentNo() {
        return studentNo;
    }

    public void setStudentNo(String studentNo) {
        this.studentNo = studentNo;
    }

    @Override
    public String toString() {
        return "StudentUpdateDTO{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", major='" + major + '\'' +
                ", userId=" + userId +
                ", studentNo='" + studentNo + '\'' +
                '}';
    }
}