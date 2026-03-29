//package com.example.studentmanager.controller;
//
//import com.example.studentmanager.model.dto.course.CourseDeleteDTO;
//import com.example.studentmanager.model.entity.Result;
//import com.example.studentmanager.model.dto.course.CourseDTO;
//import com.example.studentmanager.model.dto.course.CourseIdDTO;
//import com.example.studentmanager.model.dto.course.CourseUpdateDTO;
//import com.example.studentmanager.service.CourseService;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/course")
//public class CourseController {
//
//    private final CourseService courseService;
//
//    public CourseController(CourseService courseService) {
//        this.courseService = courseService;
//    }
//
//    @PostMapping("/getByCourseId")
//    public Result getCourseById(@RequestBody CourseIdDTO dto) {
//        return courseService.getCourseById(dto.getCourseId());
//    }
//
//    @GetMapping
//    public Result getAllCourses() {
//        return courseService.getAllCourses();
//    }
//
//    @PostMapping("/add")
//    public Result addCourse(@RequestBody CourseDTO dto) {
//        return courseService.addCourse(dto);
//    }
//
//    @PutMapping
//    public Result updateCourse(@RequestBody CourseUpdateDTO dto) {
//        return courseService.updateCourse(dto);
//    }
//
//    @PostMapping("/delete")
//    public Result deleteCourse(@RequestBody CourseDeleteDTO dto) {
//        return courseService.deleteCourse(dto.getCourseId());
//    }
//}
package com.example.studentmanager.controller;

import com.example.studentmanager.model.entity.Result;
import com.example.studentmanager.model.dto.course.CourseDTO;
import com.example.studentmanager.model.dto.course.CourseUpdateDTO;
import com.example.studentmanager.service.CourseService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    // 查询单个课程
    @PostMapping("/getByCourseId")
    public Result getCourseById(@RequestParam Integer courseId) {
        return courseService.getCourseById(courseId);
    }

    // 查询所有课程
    @GetMapping("/getAllCourses")
    public Result getAllCourses() {
        return courseService.getAllCourses();
    }

    // 新增课程
    @PostMapping("/add")
    public Result addCourse(@RequestBody CourseDTO dto) {
        return courseService.addCourse(dto);
    }

    // 修改课程
    @PostMapping("/update")
    public Result updateCourse(@RequestBody CourseUpdateDTO dto) {
        return courseService.updateCourse(dto);
    }
    
    // 删除课程（请求参数传参）
    @PostMapping("/delete")
    public Result deleteCourse(@RequestParam Integer courseId) {
        return courseService.deleteCourse(courseId);
    }
}