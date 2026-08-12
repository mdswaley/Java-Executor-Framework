package com.example.LearnAsyncScheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@ConditionalOnProperty(
        name = {"scheduler.myTask1.enabled", "scheduler.myTask2.enabled"},
        havingValue = "true"
)
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

    @Scheduled(fixedDelay = 2000)
    public void myTask3() {
        log.info("Scheduler started..{}", Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
