package com.song.dao;

import com.song.domain.Course;
import com.song.domain.CourseVO;
import com.song.domain.Teacher;

import java.util.List;

public interface CourseMapper {
    /**
     * 多条件课程列表查询
     * */
    public List<Course> findCourseByConditioin(CourseVO courseVO);

    /**
     * 保存课程信息
     * */
    public int saveCourse(Course course);

    /**
     * 保存讲师信息
     * */
    public void saveTeacher(Teacher teacher);

    /**
     * 回显课程信息 （根据id查询相应的课程信息及关联的讲师信息）
     * */
    public CourseVO findCourseById(int id);

    /**
     * 修改课程信息
     * */
    public void updateCourse(Course course);
    /**
     * 修改讲师信息
     * */
    public void updateTeacher(Teacher teacher);


    /**
     * 修改课程状态
     * */
    public void updateCourseStatus(Course course);
}
