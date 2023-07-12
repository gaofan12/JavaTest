package com.example.test.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.test.model.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.Charset;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

//@ExtendWith(SpringExtension.class)
@WebMvcTest({UserController.class})
//@MybatisPlusTest
//@MapperScan("com.example.test.mapper")
//@AutoConfigureMockMvc
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserControllerTestList {
    //https://zhuanlan.zhihu.com/p/64196108

    //防止加载到主类上的mybatis注解
    /*@SpringBootApplication(scanBasePackages = {"com.example.test.controller"})
    static class InnerConfig {}*/

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAddUser1() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/user/addWWW")
                        .param("id", "1")
                        .param("name", "高1")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();
        UserVO userVo = new UserVO();
        userVo.setId(1);
        userVo.setName("高1");
        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(JSONObject.parseObject(
                response.getContentAsString(Charset.defaultCharset()), UserVO.class)).isEqualTo(userVo);
    }

    @Test
    void testAddUser2() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/user/addFormData1")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testAddUse3() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/user/addFormData2")
                        .param("id", "0")
                        .param("name", "name")
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }

    @Test
    void testAddUser4() throws Exception {
        // Setup
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/user/addJson")
                        .content("content").contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
