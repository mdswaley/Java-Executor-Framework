package com.example.LearnAsyncScheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MyController {

    private final ThreadPoolTaskExecutor threadPoolTaskExecutor;

    @GetMapping("/hello")
    public String getData(){
        log.info("Starting.. {}", Thread.currentThread().getName());

        threadPoolTaskExecutor.execute(()->{ // this will in background. suppose you perform some create user profile
            log.info("Middle.. {}", Thread.currentThread().getName());
        });

        log.info("Ended.. {}", Thread.currentThread().getName()); // here user can sign up but profile is still created on middle
        return "Hello";
    }
}

// output become :- start -> end -> middle

/* Default configuration for tomcat:
    -> Maximum Threads : 200
    -> Minimum Idle Thread : 10
    -> Queue Size : Unbounded by default (can lead to memory issues if too many requests arrive).


    you can customize this via application.properties:
    server.tomcat.threads.max=200
    server.tomcat.threads.min-spare=10
    server.tomcat.accept-count=100 // When the queue exceeds this limit, new requests are rejected
    with a 503 service unavailable.
 */
