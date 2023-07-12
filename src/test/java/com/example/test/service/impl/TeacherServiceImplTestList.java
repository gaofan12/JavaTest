package com.example.test.service.impl;

import com.baomidou.mybatisplus.test.autoconfigure.MybatisPlusTest;
import com.example.test.model.entity.TeacherDO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@MybatisPlusTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TeacherServiceImplTestList {

    @InjectMocks
    private TeacherServiceImpl teacherServiceImplUnderTest;

    @Test
    void testFindById() {
        // Setup
        final TeacherDO expectedResult = new TeacherDO();
        expectedResult.setId(0);
        expectedResult.setName("name");
        expectedResult.setClassName("className");

        // Run the test
        final TeacherDO result = teacherServiceImplUnderTest.findById(0);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testInsertTeacher() {
        // Setup
        final TeacherDO teacherDo = new TeacherDO();
        teacherDo.setId(0);
        teacherDo.setName("name");
        teacherDo.setClassName("className");

        // Run the test
        final int result = teacherServiceImplUnderTest.insertTeacher(teacherDo);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }
}
