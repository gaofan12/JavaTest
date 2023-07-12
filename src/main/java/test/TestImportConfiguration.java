package test;

import com.example.test.model.vo.UserVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/4/7 11:29
 **/
@Slf4j
@Configuration
public class TestImportConfiguration {

    static {
        log.info("11111");
    }

    @Bean
    public UserVO createUserVo() {
        log.info("注入");
        return new UserVO();
    }

}
