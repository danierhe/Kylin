package com.example.demo.students.utils;

import commons.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author DanierHe
 * @description
 * @date 2020-02-22-02-22 23:26
 */
@Component
public class StudentSingScheduler {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

   /* @Scheduled(fixedRate = 2000)
    public void testTasks(){
        System.out.println("定时任务。2秒一次"+ DateUtils.getYmdhms());
    }*/
}
