package com.example.studentmanager.service;

import com.example.studentmanager.mapper.CourseMapper;
import com.example.studentmanager.model.entity.Result;
import com.example.studentmanager.model.entity.CourseEntity;
import com.example.studentmanager.model.dto.course.CourseDTO;
import com.example.studentmanager.model.dto.course.CourseUpdateDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    //课程Mapper
    private final CourseMapper courseMapper;

    //构造函数
    public CourseService(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    //根据课程ID查询课程信息
    public Result getCourseById(Integer courseId) {
        if (courseId == null || courseId <= 0) {
            return Result.error("课程ID不能为空且必须大于0");
        }

        CourseEntity course = courseMapper.selectById(courseId);
        if (course == null) {
            return Result.error("未找到该课程");
        }
        return Result.success(course);
    }

    //查询所有课程信息
    public Result getAllCourses() {
        List<CourseEntity> courseList = courseMapper.selectAll();
        if (courseList.isEmpty()) {
            return Result.error("暂无课程数据");
        }
        return Result.success(courseList);
    }

    //更新课程信息
    public Result updateCourse(CourseUpdateDTO dto) {
        if (dto == null) return Result.error("更新参数不能为空");

        if (dto.getCourseId() == null || dto.getCourseId() <= 0) {
            return Result.error("课程ID不能为空且必须大于0");
        }

        CourseEntity existed = courseMapper.selectById(dto.getCourseId());
        if (existed == null) {
            return Result.error("该课程不存在，无法更新");
        }

        int rows = courseMapper.update(dto);
        return rows > 0 ? Result.success("更新成功") : Result.error("更新失败");
    }
// 添加课程
    public Result addCourse(CourseDTO dto) {
        //非空校验
        if (dto == null) return Result.error("参数不能为空");

        if (dto.getCourseName() == null || dto.getCredit() == null || dto.getCredit() <= 0
                || dto.getTeacherId() == null || dto.getTeacherId() <= 0) {
            return Result.error("课程名、学分（大于0）、授课教师ID（大于0）不能为空");
        }

        String courseName = dto.getCourseName();

        if (courseName == null || courseName.trim().isEmpty()) {
            return Result.error("课程名不能为空");
        }

        //课程名重复校验
        courseName = courseName.trim();

        CourseEntity existCourse = courseMapper.selectByCourseName(courseName);
        if (existCourse != null) {
            return Result.error("该课程名已存在，不可重复添加");
        }

        CourseEntity course = new CourseEntity();
        course.setCourseName(courseName);

        int rows = courseMapper.insert(dto);

        return rows > 0 ? Result.success("添加成功") : Result.error("添加失败");
    }

    //删除课程
    public Result deleteCourse(Integer courseId) {
        if (courseId == null || courseId <= 0) {
            return Result.error("课程ID不能为空且必须大于0");
        }

        CourseEntity existed = courseMapper.selectById(courseId);
        if (existed == null) {
            return Result.error("该课程不存在，无法删除");
        }

        int rows = courseMapper.deleteById(courseId);
        return rows > 0 ? Result.success("删除成功") : Result.error("删除失败");
    }
}