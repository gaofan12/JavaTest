package com.example.test.service;

import com.example.test.model.vo.UserVO;
import org.springframework.stereotype.Service;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/3/4 16:19
 **/
@Service
public interface UserService {

    UserVO getUserVo(Integer id);

    UserVO getUserVo(String name);
}
