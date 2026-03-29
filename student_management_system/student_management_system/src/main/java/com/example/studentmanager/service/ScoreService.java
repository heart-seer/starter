package com.example.studentmanager.service;

import com.example.studentmanager.mapper.ScoreMapper;
import com.example.studentmanager.model.entity.Result;
import com.example.studentmanager.model.entity.ScoreEntity;
import com.example.studentmanager.model.dto.score.ScoreDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreService {

    private final ScoreMapper scoreMapper;

    public ScoreService(ScoreMapper scoreMapper) {
        this.scoreMapper = scoreMapper;
    }

    //通过学生ID和课程ID查询成绩
    public Result getScoreByStuAndCourse(Integer studentId, Integer courseId) {
        if (studentId == null || studentId <= 0 || courseId == null || courseId <= 0) {
            return Result.error("学生ID和课程ID不能为空且必须大于0");
        }

        ScoreEntity score = scoreMapper.selectByStudentAndCourse(studentId, courseId);
        if (score == null) {
            return Result.error("未找到该成绩");
        }
        return Result.success(score);
    }

    //通过学生ID查询成绩
    public Result getScoresByStudentId(Integer studentId) {
        if (studentId == null || studentId <= 0) {
            return Result.error("学生ID不能为空且必须大于0");
        }

        List<ScoreEntity> scoreList = scoreMapper.selectByStudentId(studentId);
        if (scoreList.isEmpty()) {
            return Result.error("该学生暂无成绩数据");
        }
        return Result.success(scoreList);
    }

    //通过课程ID查询成绩
    public Result getScoresByCourseId(Integer courseId) {
        if (courseId == null || courseId <= 0) {
            return Result.error("课程ID不能为空且必须大于0");
        }

        List<ScoreEntity> scoreList = scoreMapper.selectByCourseId(courseId);
        if (scoreList.isEmpty()) {
            return Result.error("该课程暂无成绩数据");
        }
        return Result.success(scoreList);
    }

    //添加成绩
    public Result addScore(ScoreDTO dto) {
        if (dto == null) return Result.error("参数不能为空");

        Integer studentId = dto.getStudentId();
        Integer courseId = dto.getCourseId();
        Integer score = dto.getScore();

        if (studentId == null || studentId <= 0 || courseId == null || courseId <= 0 || score == null || score < 0 || score > 100) {
            return Result.error("学生ID、课程ID（大于0）、成绩（0-100）不能为空且格式正确");
        }

        ScoreEntity existed = scoreMapper.selectByStudentAndCourse(studentId, courseId);
        if (existed != null) {
            return Result.error("该学生的该课程成绩已存在，无法重复添加");
        }

        ScoreEntity scoreEntity = new ScoreEntity();
        scoreEntity.setStudentId(studentId);
        scoreEntity.setCourseId(courseId);
        scoreEntity.setScore(score);


        int rows = scoreMapper.insert(scoreEntity);
        return rows > 0 ? Result.success("成绩录入成功") : Result.error("成绩录入失败");
    }

    //更新成绩
    public Result updateScore(ScoreDTO dto) {
        if (dto == null) return Result.error("参数不能为空");

        Integer studentId = dto.getStudentId();
        Integer courseId = dto.getCourseId();
        Integer score = dto.getScore();

        if (studentId == null || studentId <= 0 || courseId == null || courseId <= 0 || score == null || score < 0 || score > 100) {
            return Result.error("学生ID、课程ID（大于0）、成绩（0-100）不能为空且格式正确");
        }

        ScoreEntity existed = scoreMapper.selectByStudentAndCourse(studentId, courseId);
        if (existed == null) {
            return Result.error("该成绩不存在，无法更新");
        }

        ScoreEntity scoreEntity = new ScoreEntity(studentId, courseId, score);

        int rows = scoreMapper.update(scoreEntity);
        return rows > 0 ? Result.success("成绩更新成功") : Result.error("成绩更新失败");
    }

    //删除成绩
    public Result deleteScore(Integer studentId, Integer courseId) {
        if (studentId == null || studentId <= 0 || courseId == null || courseId <= 0) {
            return Result.error("学生ID和课程ID不能为空且必须大于0");
        }

        ScoreEntity existed = scoreMapper.selectByStudentAndCourse(studentId, courseId);
        if (existed == null) {
            return Result.error("该成绩不存在，无法删除");
        }

        int rows = scoreMapper.deleteByStudentAndCourse(studentId, courseId);
        return rows > 0 ? Result.success("成绩删除成功") : Result.error("成绩删除失败");
    }
}