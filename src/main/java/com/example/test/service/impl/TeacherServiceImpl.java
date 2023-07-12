package com.example.test.service.impl;

import com.example.test.mapper.TeacherMapper;
import com.example.test.model.entity.TeacherDO;
import com.example.test.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/4/22 15:55
 **/
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;


    @Override
    public TeacherDO findById(Integer id) {
        return teacherMapper.selectById(id);
    }

    @Override
    public int insertTeacher(TeacherDO teacherDo) {
        return teacherMapper.insert(teacherDo);
    }
}
