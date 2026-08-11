package com.example.LearnAsyncScheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class mySchedule {

    @Scheduled(fixedRate = 1000)
    public void myTask() {
        log.info("Task executed");
    }
}
