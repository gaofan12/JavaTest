package com.example.test.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/4/22 15:37
 **/
@Data
@TableName("teacher")
public class TeacherDO {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String name;

    private String className;
}
