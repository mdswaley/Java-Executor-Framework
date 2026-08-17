package com.example.LearnAsyncScheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class StudentInfoService {

    @Async
    public CompletableFuture<String> getName() throws InterruptedException {
        log.info("Getting the name..{}", Thread.currentThread().getName());
        Thread.sleep(2000);
        log.info("Returning name");

        return CompletableFuture.completedFuture("MD");
    }

    @Async
    public CompletableFuture<String> getId() throws InterruptedException{
        log.info("Getting the Id..{}", Thread.currentThread().getName());
        Thread.sleep(2000);
        log.info("Returning Id");

        return CompletableFuture.completedFuture("49539749");
    }

    @Async
    public CompletableFuture<String> getCollege() throws InterruptedException{
        log.info("Getting the College..{}", Thread.currentThread().getName());
        Thread.sleep(2000);
        log.info("Returning College");

        return CompletableFuture.completedFuture("CUTM");
    }
}
