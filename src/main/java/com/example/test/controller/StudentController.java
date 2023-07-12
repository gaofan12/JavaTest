package com.example.test.controller;

import com.example.test.model.entity.StudentDO;
import com.example.test.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * (student)表控制层
 *
 * @author xxxxx
 */
@RestController
@RequestMapping("/student")
public class StudentController {
    /**
     * 服务对象
     */
    @Resource
    private StudentService studentService;

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("selectOne")
    public StudentDO selectOne(Integer id) {
        return studentService.selectByPrimaryKey(id);
    }

}
