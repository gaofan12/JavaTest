package com.example.test.mapper;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.example.test.model.entity.TeacherDO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/4/28 10:46
 **/
@Slf4j
@ExtendWith(SpringExtension.class)
@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DisplayName("测试mybatis的mapper")
public class TeacherMapperTestList {

    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    @DisplayName("增删改查测试")
    //@Rollback(value = false)
    public void TestCurd() {
        // Setup
        final TeacherDO expectedResult = new TeacherDO();
        //expectedResult.setId(0);
        expectedResult.setName("name");
        expectedResult.setClassName("className");

        // Configure TeacherMapper.selectById(...).
        int insert = teacherMapper.insert(expectedResult);
        log.info("插入成功或失败：{}", insert);
        log.info("主键id为：{}", expectedResult.getId());
        // Run the test
        final TeacherDO result = teacherMapper.selectById(expectedResult.getId());

        // Verify the results
        /*   assertThat(result).isEqualTo(expectedResult);*/
        // result.setName("111");
        Assertions.assertEquals(expectedResult, result, "测试增查");
    }

}
