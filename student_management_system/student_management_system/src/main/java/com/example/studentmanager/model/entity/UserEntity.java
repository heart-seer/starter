//package com.example.studentmanager.model.entity;
//
//public class UserEntity {
//    private Integer id;
//    private String username;
//    private String password;
//    private String role;
//
//    public UserEntity() {}
//
//    public UserEntity(String username, String password, String role) {
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }
//
//    public UserEntity(Integer id, String username, String password, String role) {
//        this.id = id;
//        this.username = username;
//        this.password = password;
//        this.role = role;
//    }
//
//    public Integer getId() { return id; }
//    public void setId(Integer id) { this.id = id; }
//    public String getUsername() { return username; }
//    public void setUsername(String username) { this.username = username; }
//    public String getPassword() { return password; }
//    public void setPassword(String password) { this.password = password; }
//    public String getRole() { return role; }
//    public void setRole(String role) { this.role = role; }
//
//}
package com.example.studentmanager.model.entity;

import com.example.studentmanager.model.dto.user.UserRegisterDTO;

public class UserEntity {

    private Integer id;
    private String username;
    private String password;
    private String role;
    private Integer loginFailCount = 0;

    public UserEntity(Integer userId, String username, String password, String role) {
    }



    public UserEntity(Integer id, String username, String password, String role, Integer loginFailCount) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.loginFailCount = loginFailCount;
    }

    public UserEntity(UserRegisterDTO dto) {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getLoginFailCount() {
        return loginFailCount;
    }

    public void setLoginFailCount(Integer loginFailCount) {
        this.loginFailCount = loginFailCount;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", loginFailCount=" + loginFailCount +
                '}';
    }
}