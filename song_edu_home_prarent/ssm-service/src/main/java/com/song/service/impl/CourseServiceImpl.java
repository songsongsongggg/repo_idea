package com.song.service.impl;


import com.song.dao.CourseMapper;
import com.song.domain.Course;
import com.song.domain.CourseVO;
import com.song.domain.Teacher;
import com.song.service.CourseService;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    /**
     * 多条件查询课程列表 *
     *
     * @param courseVo
     */

    @Autowired
    private CourseMapper courseMapper;

    @Override
    public List<Course> findCourseByConditioin(CourseVO courseVO) {
        return courseMapper.findCourseByConditioin(courseVO);
    }

    /**
     * 保存课程信息 *
     *
     * @param courseVO
     */
    @Override
    public void saveCourseOrTeacher(CourseVO courseVO) {
        try {
            //封装课程信息
            Course course = new Course();
            BeanUtils.copyProperties(course, courseVO);

            //补全信息
            Date date = new Date();
            course.setCreateTime(date);
            course.setUpdateTime(date);

            //保存课程
            courseMapper.saveCourse(course);

            //获取新插入数据的id
            int id = course.getId();

            //封装讲师信息
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher, courseVO);

            //补全信息
            teacher.setCourseId(id);
            teacher.setCreateTime(date);
            teacher.setUpdateTime(date);

            //保存讲师信息
            courseMapper.saveTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 回显课程信息 （根据id查询相应的课程信息及关联的讲师信息）
     *
     * @param id
     */
    @Override
    public CourseVO findCourseById(Integer id) {
        return courseMapper.findCourseById(id);
    }

    /**
     * 修改课程信息
     *
     * @param courseVO
     */
    @Override
    public void updateCourseOrTeacher(CourseVO courseVO) {
        try {
            //封装课程信息
            Course course = new Course();
            BeanUtils.copyProperties(course, courseVO);

            //补全信息
            Date date = new Date();
            course.setUpdateTime(date);

            //保存课程
            courseMapper.updateCourse(course);

            //获取新插入数据的id
            int id = course.getId();

            //封装讲师信息
            Teacher teacher = new Teacher();
            BeanUtils.copyProperties(teacher, courseVO);

            //补全信息
            teacher.setCourseId(id);
            teacher.setUpdateTime(date);

            //保存讲师信息
            courseMapper.updateTeacher(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改课程状态
     *
     * @param id
     * @param status
     */
    @Override
    public void updateCourseStatus(int id, int status) {
        try {
            //封装数据
            Course course = new Course();
            course.setStatus(status);
            course.setId(id);
            course.setUpdateTime(new Date());
            //调用Dao
            courseMapper.updateCourseStatus(course);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
