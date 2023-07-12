package com.example.test.demo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/3/10 14:34
 **/
@Data
@Slf4j
public class Student {

    //构造代码块
    {
        log.info("Student构造代码块");
    }
    //静态代码块
    static {
        log.info("Student静态代码块");
    }

    Student() {
        log.info("Student构造方法初始化");
    }
}
