package com.example.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@Slf4j
//@EnableTest
//@MapperScan("com.example.test.mapper")
public class TestApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(TestApplication.class, args);
        String[] beanDefinitionNames = run.getBeanDefinitionNames();
        //log.info(Arrays.toString(beanDefinitionNames));
/*        Object enableTest = run.getBeansOfType(TestImportConfiguration.class);
        TestImportConfiguration bean = run.getBean(TestImportConfiguration.class);*/
        // run.getBean(TestImportConfiguration.class);
       // log.info(enableTest.toString());

    }

}
