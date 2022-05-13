package com.song.dao;

import com.song.domain.Course;
import com.song.domain.CourseLesson;
import com.song.domain.CourseSection;

import java.util.Date;
import java.util.List;

public interface CourseContentMapper {
    /**
     * 查询课程下的章节与课时信息
     * */
    public List<CourseSection> findSectionAndLessonByCourseId(int courseId);

    /**
     * 回显章节对应的课程信息
     * */
    public Course findCourseByCourseId(int courseId);

    /**
     * 新增章节
     * */
    public void saveSection(CourseSection section);

    /**
     * 修改章节
     * */
    public void updateSection(CourseSection section);

    /**
     * 修改章节状态
     * */
    public void updateSectionStatus(CourseSection section);

    /**
     * 新增课时
     */
    public void saveLesson(CourseLesson lesson);
}
