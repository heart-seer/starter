//package com.example.studentmanager.controller;
//
//import com.example.studentmanager.model.dto.score.ScoreDeleteDTO;
//import com.example.studentmanager.model.entity.Result;
//import com.example.studentmanager.model.dto.score.ScoreDTO;
//import com.example.studentmanager.model.dto.score.ScoreStudentDTO;
//import com.example.studentmanager.model.dto.score.ScoreCourseDTO;
//import com.example.studentmanager.model.dto.score.ScoreStuCourseDTO;
//import com.example.studentmanager.service.ScoreService;
//import org.springframework.web.bind.annotation.*;
//
//
//@RestController
//@RequestMapping("/api/score")
//public class ScoreController {
//
//    private final ScoreService scoreService;
//
//    public ScoreController(ScoreService scoreService) {
//        this.scoreService = scoreService;
//    }
//
//    @PostMapping("/getByStuAndCourse")
//    public Result getScoreByStuAndCourse(@RequestBody ScoreStuCourseDTO dto) {
//        Integer studentId = dto.getStudentId();
//        Integer courseId = dto.getCourseId();
//        return scoreService.getScoreByStuAndCourse(studentId, courseId);
//    }
//
//    @PostMapping("/getByStudentId")
//    public Result getScoresByStudentId(@RequestBody ScoreStudentDTO dto) {
//        return scoreService.getScoresByStudentId(dto.getStudentId());
//    }
//
//    @PostMapping("/getByCourseId")
//    public Result getScoresByCourseId(@RequestBody ScoreCourseDTO dto) {
//        return scoreService.getScoresByCourseId(dto.getCourseId());
//    }
//
//    @PostMapping("/add")
//    public Result addScore(@RequestBody ScoreDTO dto) {
//        return scoreService.addScore(dto);
//    }
//
//    @PutMapping
//    public Result updateScore(@RequestBody ScoreDTO dto) {
//        return scoreService.updateScore(dto);
//    }
//
//    @PostMapping("/delete")
//    public Result deleteScore(@RequestBody ScoreDeleteDTO dto) {
//        return scoreService.deleteScore(dto.getStudentId(), dto.getCourseId());
//    }
//}
package com.example.studentmanager.controller;

import com.example.studentmanager.model.dto.score.ScoreDeleteDTO;
import com.example.studentmanager.model.entity.Result;
import com.example.studentmanager.model.dto.score.ScoreDTO;
import com.example.studentmanager.model.dto.score.ScoreStuCourseDTO;
import com.example.studentmanager.service.ScoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/score")
public class ScoreController {

    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    // 根据学生 ID +课程 ID查询成绩
    @PostMapping("/getByStuAndCourse")
    public Result getScoreByStuAndCourse(@RequestBody ScoreStuCourseDTO dto) {
        Integer studentId = dto.getStudentId();
        Integer courseId = dto.getCourseId();
        return scoreService.getScoreByStuAndCourse(studentId, courseId);
    }

    // 根据学生 ID查询成绩
    @PostMapping("/getByStudentId")
    public Result getScoresByStudentId(@RequestParam Integer studentId) {
        return scoreService.getScoresByStudentId(studentId);
    }

    // 根据课程 ID查询成绩
    @PostMapping("/getByCourseId")
    public Result getScoresByCourseId(@RequestParam Integer courseId) {
        return scoreService.getScoresByCourseId(courseId);
    }

    // 添加成绩
    @PostMapping("/add")
    public Result addScore(@RequestBody ScoreDTO dto) {
        return scoreService.addScore(dto);
    }

    // 修改成绩
    @PostMapping("/update")
    public Result updateScore(@RequestBody ScoreDTO dto) {
        return scoreService.updateScore(dto);
    }

    // 删除成绩
    @PostMapping("/delete")
    public Result deleteScore(@RequestBody ScoreDeleteDTO dto) {
        return scoreService.deleteScore(dto.getStudentId(), dto.getCourseId());
    }
}