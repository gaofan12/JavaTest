package com.example.test.service.impl;

import com.example.test.mapper.TeacherMapper;
import com.example.test.model.entity.TeacherDO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherControllerImpMockitolTestList {

    @Mock
    private TeacherMapper mockTeacherMapper;

    @InjectMocks
    private TeacherServiceImpl teacherControllerImplUnderTest;

    @Test
    void testFindById() {
        // Setup
        final TeacherDO expectedResult = new TeacherDO();
        expectedResult.setId(0);
        expectedResult.setName("name");
        expectedResult.setClassName("className");

        // Configure TeacherMapper.selectById(...).
        final TeacherDO teacherDo = new TeacherDO();
        teacherDo.setId(0);
        teacherDo.setName("name");
        teacherDo.setClassName("className");
        when(mockTeacherMapper.selectById(0)).thenReturn(teacherDo);

        // Run the test
        final TeacherDO result = teacherControllerImplUnderTest.findById(0);

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

        when(mockTeacherMapper.insert(new TeacherDO())).thenReturn(0);

        // Run the test
        final int result = teacherControllerImplUnderTest.insertTeacher(teacherDo);

        // Verify the results
        assertThat(result).isEqualTo(0);
    }
}
