package com.example.studentmanager.mapper;

import com.example.studentmanager.model.entity.StudentEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface StudentMapper {
    StudentEntity selectById(@Param("studentId") Integer studentId);

    List<StudentEntity> selectAll();

    int insert(StudentEntity student);

    int update(StudentEntity student);

    int deleteById(@Param("studentId") Integer studentId);

    int deleteByUserId(@Param("userId") Integer userId);
}