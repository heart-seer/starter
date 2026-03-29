package com.example.studentmanager.service;

import com.example.studentmanager.mapper.StudentMapper;
import com.example.studentmanager.mapper.ScoreMapper;
import com.example.studentmanager.model.entity.Result;
import com.example.studentmanager.model.entity.StudentEntity;
import com.example.studentmanager.model.dto.student.StudentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private final StudentMapper studentMapper;
    private final ScoreMapper scoreMapper;

    public StudentService(StudentMapper studentMapper, ScoreMapper scoreMapper) {
        this.studentMapper = studentMapper;
        this.scoreMapper = scoreMapper;
    }

    //通过ID查询学生
    public Result getStudentById(Integer studentId) {
        if (studentId == null || studentId <= 0) {
            return Result.error("学生ID不能为空且必须大于0");
        }

        StudentEntity student = studentMapper.selectById(studentId);
        if (student == null) {
            return Result.error("未找到该学生");
        }
        return Result.success(student);
    }

    //查询所有学生
    public Result getAllStudents() {
        List<StudentEntity> studentList = studentMapper.selectAll();
        if (studentList.isEmpty()) {
            return Result.error("暂无学生数据");
        }
        return Result.success(studentList);
    }

    //添加学生
    public Result addStudent(StudentDTO dto) {
        if (dto == null) return Result.error("参数不能为空");

        String studentNo = dto.getStudentNo();
        String name = dto.getName();
        String gender = dto.getGender();
        String major = dto.getMajor();
        Integer userId = dto.getUserId();

        if (studentNo == null || studentNo.trim().isEmpty() ||
                name == null || gender == null || major == null || userId == null || userId <= 0) {
            return Result.error("学号、姓名、性别、专业、关联用户ID不能为空");
        }

        StudentEntity student = new StudentEntity(studentNo, name, gender, major, userId);
        int rows = studentMapper.insert(student);
        return rows > 0 ? Result.success("添加成功") : Result.error("添加失败");
    }

    //更新学生
    public Result updateStudent(Integer studentId, StudentDTO dto) {
        if (studentId == null || studentId <= 0) {
            return Result.error("学生ID不能为空且必须大于0");
        }
        if (dto == null) return Result.error("更新参数不能为空");

        StudentEntity existed = studentMapper.selectById(studentId);
        if (existed == null) {
            return Result.error("该学生不存在，无法更新");
        }

        StudentEntity student = new StudentEntity(
                studentId,
                dto.getStudentNo(),
                dto.getName(),
                dto.getGender(),
                dto.getMajor(),
                dto.getUserId()
        );

        int rows = studentMapper.update(student);
        return rows > 0 ? Result.success("更新成功") : Result.error("更新失败");
    }

    //删除学生
    public Result deleteStudent(Integer studentId) {
        if (studentId == null || studentId <= 0) {
            return Result.error("学生ID不能为空且必须大于0");
        }

        StudentEntity existed = studentMapper.selectById(studentId);
        if (existed == null) {
            return Result.error("该学生不存在，无法删除");
        }

        scoreMapper.deleteByStudentId(studentId);
        int rows = studentMapper.deleteById(studentId);
        return rows > 0 ? Result.success("删除成功") : Result.error("删除失败");
    }
}