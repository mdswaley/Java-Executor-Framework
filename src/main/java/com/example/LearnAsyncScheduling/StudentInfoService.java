package com.example.LearnAsyncScheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class StudentInfoService {
    public String getName() throws InterruptedException {
        log.info("Getting the name..{}", Thread.currentThread().getName());
        Thread.sleep(2000);
        log.info("Returning name");

        return "MD";
    }

    public String getId() throws InterruptedException{
        log.info("Getting the Id..{}", Thread.currentThread().getName());
        Thread.sleep(2000);
        log.info("Returning Id");

        return "947937342";
    }

    public String getCollege() throws InterruptedException{
        log.info("Getting the College..{}", Thread.currentThread().getName());
        Thread.sleep(2000);
        log.info("Returning College");

        return "CUTM";
    }
}
