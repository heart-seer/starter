package com.example.studentmanager.mapper;

import com.example.studentmanager.model.entity.ScoreEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface ScoreMapper {

    ScoreEntity selectByStudentAndCourse(@Param("studentId") Integer studentId,@Param("courseId") Integer courseId);

    List<ScoreEntity> selectByStudentId(@Param("studentId") Integer studentId);

    List<ScoreEntity> selectByCourseId(@Param("courseId") Integer courseId);

    int insert(ScoreEntity score);

    int update(ScoreEntity score);

    int deleteByStudentAndCourse(@Param("studentId") Integer studentId,@Param("courseId") Integer courseId);
    int deleteByStudentId(@Param("studentId") Integer studentId);
}