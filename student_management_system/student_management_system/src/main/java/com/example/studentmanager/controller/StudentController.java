//package com.example.studentmanager.controller;
//
//import com.example.studentmanager.model.entity.Result;
//import com.example.studentmanager.model.dto.student.StudentDTO;
//import com.example.studentmanager.model.dto.student.StudentIdDTO;
//import com.example.studentmanager.model.dto.student.StudentUpdateDTO;
//import com.example.studentmanager.model.dto.student.StudentDeleteDTO;
//import com.example.studentmanager.service.StudentService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/student")
//public class StudentController {
//
//    private final StudentService studentService;
//
//    public StudentController(StudentService studentService) {
//        this.studentService = studentService;
//    }
//
//    @PostMapping("/getBystudentId")
//    public Result getStudentById(@RequestBody StudentIdDTO dto) {
//        return studentService.getStudentById(dto.getStudentId());
//    }
//
//    @GetMapping
//    public Result getAllStudents() {
//        return studentService.getAllStudents();
//    }
//
//    @PostMapping("/add")
//    public Result addStudent(@RequestBody StudentDTO dto) {
//        return studentService.addStudent(dto);
//    }
//
//    @PutMapping
//    public Result updateStudent(@RequestBody StudentUpdateDTO dto) {
//        return studentService.updateStudent(dto.getStudentId(),
//                new StudentDTO(dto.getStudentNo(), dto.getName(), dto.getGender(), dto.getMajor(), dto.getUserId()));
//    }
//
//    @PostMapping("/delete")
//    public Result deleteStudent(@RequestBody StudentDeleteDTO dto) {
//        return studentService.deleteStudent(dto.getStudentId());
//    }
//}
package com.example.studentmanager.controller;

import com.example.studentmanager.model.entity.Result;
import com.example.studentmanager.model.dto.student.StudentDTO;
import com.example.studentmanager.model.dto.student.StudentUpdateDTO;
import com.example.studentmanager.service.StudentService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    // 根据 ID查询学生
    @PostMapping("/getByStudentId")
    public Result getStudentById(@RequestParam Integer studentId) {
        return studentService.getStudentById(studentId);
    }

    // 查询所有学生
    @GetMapping("/getAllStudents")
    public Result getAllStudents() {
        return studentService.getAllStudents();
    }

    // 新增学生
    @PostMapping("/add")
    public Result addStudent(@RequestBody StudentDTO dto) {
        return studentService.addStudent(dto);
    }

    // 修改学生
    @PostMapping("/update")
    public Result updateStudent(@RequestBody StudentUpdateDTO dto) {
        return studentService.updateStudent(dto.getStudentId(),
                new StudentDTO(dto.getStudentNo(), dto.getName(), dto.getGender(), dto.getMajor(), dto.getUserId()));
    }

    // 删除学生
    @PostMapping("/delete")
    public Result deleteStudent(@RequestParam Integer studentId) {
        return studentService.deleteStudent(studentId);
    }
}