package com.example.test.service.impl;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.example.test.service.UserService;
import com.example.test.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Slf4j
class UserServiceImplTestList {

    @Autowired
    private UserService userService;

    @Test
    void getUserVo() {
        for (int i = 0; i < 10; i++) {
            UserVO userVo = userService.getUserVo(1);
            log.info("获取1：{}", userVo.toString());
        }

        for (int i = 0; i < 10; i++) {
            UserVO userVo = userService.getUserVo("name");
            log.info("获取2：{}", userVo.toString());
        }
    }

    @Test
    void testGetUserVo() {
        String str = " {\\\"name\\\":\\\"spy\\\",\\\"id\\\":\\\"123456\\\"}";
        System.out.println("str = " + str);
    }
}