package com.example.test.service.impl;

import com.example.test.mapper.StudentMapper;
import com.example.test.model.entity.StudentDO;
import com.example.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;


    @Override
    public StudentDO selectByPrimaryKey(Integer id) {
        return studentMapper.selectById(id);
    }
}



