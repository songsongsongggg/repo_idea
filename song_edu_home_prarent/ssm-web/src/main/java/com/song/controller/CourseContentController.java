package com.song.controller;

import com.song.domain.Course;
import com.song.domain.CourseLesson;
import com.song.domain.CourseSection;
import com.song.domain.ResponseResult;
import com.song.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/courseContent")
public class CourseContentController {

    @Autowired
    private CourseContentService courseContentService;

    /**
     * 查询课程内容
     */
    @RequestMapping("/findSectionAndLesson")
    public ResponseResult findSectionAndLessonByCourseId(@RequestParam Integer courseId) {
        try { //调用service
            List<CourseSection> sectionList = courseContentService.findSectionAndLessonByCourseId(courseId);
            //封装数据并返回
            ResponseResult result = new ResponseResult(true, 200, "响应成功", sectionList);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 回显对应的课程信息
     */
    @RequestMapping("/findCourseByCourseId")
    public ResponseResult findCourseByCourseId(@RequestParam Integer courseId) {
        try {
            //调用service
            Course course = courseContentService.findCourseByCourseId(courseId);
            ResponseResult result = new ResponseResult(true, 200, "响应成功", course);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 新建&修改章节信息
     */
    @RequestMapping("/saveOrUpdateSection")
    public ResponseResult saveOrUpdateSection(@RequestBody CourseSection section) {
        try {
            //判断携带id是修改操作否则是插入操作
            if (section.getId() == null) {
                //新增
                courseContentService.saveSection(section);
                return new ResponseResult(true, 200, "响应成功", null);
            } else {
                courseContentService.updateSection(section);
                return new ResponseResult(true, 200, "响应成功", null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 修改章节状态
     */
    @RequestMapping("/updateSectionStatus")
    public ResponseResult updateSectionStatus(@RequestParam Integer id, @RequestParam Integer status) {
        try {
            courseContentService.updateSectionStatus(id, status);
            Map<String, Integer> map = new HashMap<>();
            map.put("status", status);
            return new ResponseResult(true, 200, "响应成功", map);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 新建&修改课时信息
     */
    @RequestMapping("/saveOrUpdateLesson")
    public ResponseResult saveOrUpdateLesson(@RequestBody CourseLesson lesson) {
        try {
            courseContentService.saveLesson(lesson);
            return new ResponseResult(true, 200, "响应成功", null);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
