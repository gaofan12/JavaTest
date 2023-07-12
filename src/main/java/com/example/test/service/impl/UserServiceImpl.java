package com.example.test.service.impl;

import com.example.test.service.UserService;
import com.example.test.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/3/4 16:22
 **/
@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Override
    @Cacheable(cacheNames = "test-redis-cache")
    public UserVO getUserVo(Integer id) {
        UserVO userVo = new UserVO();
        userVo.setId(id);
        userVo.setName("user-cache");
        log.info("进入缓存1：{}", userVo);
        return userVo;
    }

    @Override
    @Cacheable(cacheNames = "test-redis-cache")
    public UserVO getUserVo(String name) {
        UserVO userVo = new UserVO();
        userVo.setId(12);
        userVo.setName(name);
        log.info("进入缓存2：{}", userVo);
        return userVo;
    }
}
