package com.example.test.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/3/10 14:33
 **/
@Slf4j
public class Father {

    private Student student = new Student();

    //构造代码块
    {
        log.info("Father构造代码块");
    }
    //静态代码块
    static {
        log.info("Father静态代码块");
    }

    Father() {
        log.info("Father构造方法初始化");
    }
}
