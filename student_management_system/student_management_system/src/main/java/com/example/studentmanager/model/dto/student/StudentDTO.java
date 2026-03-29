package com.example.studentmanager.model.dto.student;

public class StudentDTO {
    private String studentNo;
    private String name;
    private String gender;
    private String major;
    private Integer userId;

    public StudentDTO() {}

    public StudentDTO(String studentNo, String name, String gender, String major, Integer userId) {
        this.studentNo = studentNo;
        this.name = name;
        this.gender = gender;
        this.major = major;
        this.userId = userId;
    }

    public String getStudentNo() { return studentNo; }
    public void setStudentNo(String studentNo) { this.studentNo = studentNo; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }
    public String getMajor() { return major; }
    public void setMajor(String major) { this.major = major; }
    public Integer getUserId() { return userId; }
    public void setUserId(Integer userId) { this.userId = userId; }

    @Override
    public String toString() {
        return "StudentDTO{" +
                "studentNo='" + studentNo + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", major='" + major + '\'' +
                ", userId=" + userId +
                '}';
    }
}