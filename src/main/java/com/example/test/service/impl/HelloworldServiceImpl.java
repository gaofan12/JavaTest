package com.example.test.service.impl;

import com.example.test.service.HelloworldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/3/7 11:48
 **/
@Slf4j
@Service
public class HelloworldServiceImpl implements HelloworldService {
    @Override
    public void sayHello() {
        log.info("hello-------");
    }
}
