package com.example.LearnAsyncScheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class mySchedule {

    @Scheduled(fixedRate = 1000) // Not concurrent (only use one thread)
    public void myTask1() {
        log.info("Scheduler1 started..{}", Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Scheduler1 Ended..{}", Thread.currentThread().getName());
    }

    @Scheduled(fixedRate = 1000)
    public void myTask2() {
        log.info("Scheduler2 started..{}", Thread.currentThread().getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Scheduler2 Ended..{}", Thread.currentThread().getName());
    }


}
