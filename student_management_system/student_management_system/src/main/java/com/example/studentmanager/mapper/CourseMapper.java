package com.example.studentmanager.mapper;

//import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.studentmanager.model.dto.course.CourseUpdateDTO;
import com.example.studentmanager.model.entity.CourseEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface CourseMapper {

    CourseEntity selectById(@Param("courseId") Integer courseId);

    List<CourseEntity> selectAll();

    int insert(CourseEntity course);

    int update(CourseUpdateDTO course);

    int deleteById(@Param("courseId") Integer courseId);

    CourseEntity selectByCourseName(@Param("courseName") String courseName);

}
//public interface CourseMapper extends BaseMapper<CourseEntity> {
//}