package com.example.test.config;

import com.example.test.model.vo.UserVO;
import org.springframework.context.annotation.Import;
import test.TestImportConfiguration;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/4/7 11:29
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({TestImportConfiguration.class, UserVO.class})
public @interface EnableTest {
}
