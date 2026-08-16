package com.example.LearnAsyncScheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class mySchedule {

//    @Scheduled(fixedRate = 1000) // Not concurrent (only use one thread)
//    public void myTask1() {
//        log.info("Scheduler1 started..{}", Thread.currentThread().getName());
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        log.info("Scheduler1 Ended..{}", Thread.currentThread().getName());
//    }
//
//    @Scheduled(fixedRate = 1000)
//    public void myTask2() {
//        log.info("Scheduler2 started..{}", Thread.currentThread().getName());
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        log.info("Scheduler2 Ended..{}", Thread.currentThread().getName());
//    }

//    @Scheduled(fixedDelay = 2000, initialDelay = 10000) // wait for myTask3 to complete first then wait for 2s
////    initialDelay means after 10s that method is going to start for first time then every 2s + 1s inside method will execute
//    public void myTask3() {
//        // Task starts
//        log.info("Scheduler started..{}", Thread.currentThread().getName());
//
//        try {
//            // Task takes 1 second to complete
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            Thread.currentThread().interrupt();
//        }
//
//        // After the method finishes, Spring waits 2 seconds
//        // before starting myTask3() again.
//    }

// work of cron
// cron = "sec min hours DayOfMonths Months dayOfWeek"
// @Scheduled(cron = "*/5 * * * * *") // this will run every 5sec
//public void myTask1() {
//    log.info("Scheduler1 started..{}", Thread.currentThread().getName());
//    try {
//        Thread.sleep(1000);
//    } catch (InterruptedException e) {
//        throw new RuntimeException(e);
//    }
//    log.info("Scheduler1 Ended..{}", Thread.currentThread().getName());
//}

//    @Scheduled(fixedRate = 200)
//    @Async("jobExecutor")
//    public void myTask1() {
//        log.info("Scheduler1 started..{}", Thread.currentThread().getName());
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        log.info("Scheduler1 Ended..{}", Thread.currentThread().getName());
//    }


}
