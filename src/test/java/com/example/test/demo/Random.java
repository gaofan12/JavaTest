package com.example.test.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author gaofan
 * @version 1.0
 * @date 2022/3/10 17:22
 **/
@Slf4j
public class Random {


    public static void main(String[] args) {
        // 共享 ThreadLocalRandom
        ThreadLocalRandom threadLocalRandom = ThreadLocalRandom.current();
        log.info("aaa:" + Thread.currentThread().getName() + threadLocalRandom.toString());
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                //ThreadLocalRandom threadLocalRandom2 = ThreadLocalRandom.current();
                // log.info("bbb:" + Thread.currentThread().getName() + threadLocalRandom2.toString());
                log.info(Thread.currentThread().getName() + ":" + threadLocalRandom.nextInt(10));
                //  log.info(Thread.currentThread().getName() + ":" + threadLocalRandom.nextInt(10));
                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            log.error("报错", e);
        }

    }


}
