package com.example.test.service;

import com.example.test.model.entity.TeacherDO;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/4/22 15:54
 **/
public interface TeacherService {
    TeacherDO findById(Integer id);

    int insertTeacher(TeacherDO teacherDo);
}
