package com.example.test.controller;

import com.example.test.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/3/3 14:18
 **/
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @PostMapping("/addWWW")
    public UserVO addUser1(@ModelAttribute UserVO userVo) {
        log.info("www-url-encode:{}", userVo.toString());
        return userVo;
    }

    @PostMapping("/addFormData1")
    public UserVO addUser2(UserVO userVo) {
        log.info("addFormData1:{}", userVo.toString());
        return userVo;
    }

    @PostMapping("/addFormData2")
    public UserVO addUse3(@RequestParam("id") Integer id, @RequestParam String name) {
        UserVO userVo = new UserVO();
        userVo.setId(id);
        userVo.setName(name);
        log.info("addFormData2:{}", userVo.toString());
        return userVo;
    }

    @PostMapping("/addJson")
    public UserVO addUser4(@RequestBody UserVO userVo) {
        log.info("addJson:{}", userVo.toString());
        return userVo;
    }
}
