package com.example.test.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/3/10 14:36
 **/
@Slf4j
public class Son extends Father {

    private Student student = new Student();

    //构造代码块
    {
        log.info("Son构造代码块");
    }
    //静态代码块
    static {
        log.info("Son静态代码块");
    }

    Son() {
        log.info("Son构造方法初始化");
    }


}
