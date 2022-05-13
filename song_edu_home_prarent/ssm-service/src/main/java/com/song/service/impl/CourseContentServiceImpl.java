package com.song.service.impl;

import com.song.dao.CourseContentMapper;
import com.song.domain.Course;
import com.song.domain.CourseLesson;
import com.song.domain.CourseSection;
import com.song.service.CourseContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class CourseContentServiceImpl implements CourseContentService {

    @Autowired
    private CourseContentMapper courseContentMapper;


    /**
     * 查询课程下的章节与课时信息
     *
     * @param courseId
     */
    @Override
    public List<CourseSection> findSectionAndLessonByCourseId(Integer courseId) {
        return courseContentMapper.findSectionAndLessonByCourseId(courseId);
    }

    /**
     * 回显章节对应的课程信息
     *
     * @param courseId
     */
    @Override
    public Course findCourseByCourseId(int courseId) {
        return courseContentMapper.findCourseByCourseId(courseId);
    }

    /**
     * 新增章节
     *
     * @param section
     */
    @Override
    public void saveSection(CourseSection section) {
        //补全信息
        Date date = new Date();
        section.setCreateTime(date);
        section.setUpdateTime(date);

        courseContentMapper.saveSection(section);
    }

    /**
     * 修改章节
     *
     * @param section
     */
    @Override
    public void updateSection(CourseSection section) {
        //补全信息
        Date date = new Date();
        section.setUpdateTime(date);

        courseContentMapper.updateSection(section);
    }

    /**
     * 修改章节状态
     *
     * @param id
     * @param status
     */
    @Override
    public void updateSectionStatus(Integer id,Integer status) {
        //封装数据
        CourseSection section = new CourseSection();
        section.setId(id);
        section.setStatus(status);
        //补全信息
        section.setUpdateTime(new Date());

        courseContentMapper.updateSectionStatus(section);
    }

    /**
     * 新增课时
     *
     * @param lesson
     */
    @Override
    public void saveLesson(CourseLesson lesson) {
        //补全信息
        Date date = new Date();
        lesson.setCreateTime(date);
        lesson.setUpdateTime(date);

        courseContentMapper.saveLesson(lesson);
    }
}
