package com.example.test.controller;

import com.example.test.model.entity.TeacherDO;
import com.example.test.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/4/22 16:41
 **/
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @PostMapping("/insert")
    public int insert(TeacherDO teacherDO) {
        return teacherService.insertTeacher(teacherDO);
    }

    @GetMapping("/find")
    public TeacherDO find(Integer id) {
        return teacherService.findById(id);
    }

}
