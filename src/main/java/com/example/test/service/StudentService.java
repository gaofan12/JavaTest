package com.example.test.service;

import com.example.test.model.entity.StudentDO;
import com.baomidou.mybatisplus.extension.service.IService;

public interface StudentService {


    StudentDO selectByPrimaryKey(Integer id);
}



