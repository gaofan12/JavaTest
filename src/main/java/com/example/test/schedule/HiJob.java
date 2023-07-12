package com.example.test.schedule;

import com.example.test.service.HelloworldService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/3/7 11:38
 **/
@Component
public class HiJob extends QuartzJobBean {
    @Autowired
    HelloworldService myService;
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        myService.sayHello();
        System.out.println("    Hi! :" + jobExecutionContext.getJobDetail().getKey());
    }
}