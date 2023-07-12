package com.example.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.test.model.entity.TeacherDO;
import com.example.test.service.TeacherService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@DisplayName("测试web")
@WebMvcTest(TeacherController.class)
class TeacherControllerTestList {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeacherService mockTeacherService;

    @Test
    void testInsert() throws Exception {
        // Setup
        when(mockTeacherService.insertTeacher(new TeacherDO())).thenReturn(0);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/teacher/insert")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    @DisplayName("测试testFind")
    void testFind() throws Exception {
        // Setup
        // Configure TeacherService.findById(...).
        final TeacherDO teacherDO = new TeacherDO();
        teacherDO.setId(0);
        teacherDO.setName("name");
        teacherDO.setClassName("className");
        when(mockTeacherService.findById(0)).thenReturn(teacherDO);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/teacher/find")
                        .param("id", "0")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        TeacherDO teacherDO1 = JSONObject.parseObject(response.getContentAsString(Charset.defaultCharset()), TeacherDO.class);

        assertThat(teacherDO1).isEqualTo(teacherDO);
    }
}
