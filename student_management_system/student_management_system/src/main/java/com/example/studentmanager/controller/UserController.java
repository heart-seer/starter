////package com.example.studentmanager.controller;
////
////import com.example.studentmanager.model.dto.user.UserDeleteDTO;
////import com.example.studentmanager.model.dto.user.UserUsernameDTO;
////import com.example.studentmanager.model.entity.Result;
////import com.example.studentmanager.model.dto.user.UserLoginDTO;
////import com.example.studentmanager.model.dto.user.UserRegisterDTO;
////import com.example.studentmanager.service.UserService;
////import org.springframework.web.bind.annotation.*;
////
////import java.util.Map;
////
////@RestController
////@RequestMapping("/api/user")
////public class UserController {
////
////    private final UserService userService;
////
////    public UserController(UserService userService) {
////        this.userService = userService;
////    }
////
////    @PostMapping("/login")
////    public Result login(@RequestBody UserLoginDTO dto) {
////        return userService.login(dto.getUsername(), dto.getPassword());
////    }
////
////    @PostMapping("/register")
////    public Result register(@RequestBody UserRegisterDTO dto) {
////        return userService.register(new com.example.studentmanager.model.dto.user.UserDTO(
////                dto.getUsername(), dto.getPassword(), dto.getRole()));
////    }
////
////    @PostMapping("/getByusername")
////    public Result getUserByUsername(@RequestBody UserUsernameDTO dto) {
////        return userService.getUserByUsername(dto.getUsername());
////    }
////
////    @GetMapping
////    public Result getAllUsers() {
////        return userService.getAllUsers();
////    }
////
////    @PostMapping("/delete")
////    public Result deleteUser(@RequestBody UserDeleteDTO dto) {
////        return userService.deleteUser(dto.getUsername());
////    }
////}
//package com.example.studentmanager.controller;
//
//import com.example.studentmanager.model.dto.user.UserDeleteDTO;
//import com.example.studentmanager.model.dto.user.UserLoginDTO;
//import com.example.studentmanager.model.dto.user.UserRegisterDTO;
//import com.example.studentmanager.model.dto.user.UserUsernameDTO;
//import com.example.studentmanager.model.entity.Result;
//import com.example.studentmanager.service.UserService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/user")
//public class UserController {
//
//    private final UserService userService;
//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }
//
//    @PostMapping("/login")
//    public Result login(@RequestBody UserLoginDTO dto) {
//        return userService.login(dto.getUsername(), dto.getPassword());
//    }
//
//    @PostMapping("/register")
//    public Result register(@RequestBody UserRegisterDTO dto) {
//        return userService.register(dto);
//    }
//
//    @PostMapping("/getByusername")
//    public Result getUserByUsername(@RequestBody UserUsernameDTO dto) {
//        return userService.getUserByUsername(dto.getUsername());
//    }
//
//    @GetMapping
//    public Result getAllUsers() {
//        return userService.getAllUsers();
//    }
//
//    @PostMapping("/delete")
//    public Result deleteUser(@RequestBody UserDeleteDTO dto) {
//        return userService.deleteUser(dto.getUsername());
//    }
//}
package com.example.studentmanager.controller;

import com.example.studentmanager.model.dto.user.UserLoginDTO;
import com.example.studentmanager.model.dto.user.UserRegisterDTO;
import com.example.studentmanager.model.entity.Result;
import com.example.studentmanager.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 登录
    @PostMapping("/login")
    public Result login(@RequestBody UserLoginDTO dto) {
        return userService.login(dto.getUsername(), dto.getPassword());
    }

    // 注册
    @PostMapping("/register")
    public Result register(@RequestBody UserRegisterDTO dto) {
        return userService.register(dto);
    }

    // 根据用户名查询用户
    @PostMapping("/getByUsername")
    public Result getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    // 查询所有用户
    @GetMapping("/getAllUsers")
    public Result getAllUsers() {
        return userService.getAllUsers();
    }

    // 删除用户
    @PostMapping("/delete")
    public Result deleteUser(@RequestParam String username) {
        return userService.deleteUser(username);
    }
}