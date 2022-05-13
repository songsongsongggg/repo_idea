package com.song.service;


import com.song.domain.Course;
import com.song.domain.CourseVO;

import java.util.List;

public interface CourseService {

    /**
     * 多条件查询课程列表
     * */
    public List<Course> findCourseByConditioin(CourseVO courseVO);

    /**
     * 保存课程信息
     * */
    public void saveCourseOrTeacher(CourseVO courseVO);

    /**
     * 根据id获取课程信息
     * */
    public CourseVO findCourseById(Integer id);

    /**
     * 修改课程信息
     * */
    public void updateCourseOrTeacher(CourseVO courseVO);

    /**
     * 修改课程状态
     * */
    public void updateCourseStatus(int id,int status);

}
