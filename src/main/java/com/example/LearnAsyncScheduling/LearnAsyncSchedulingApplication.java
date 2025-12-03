package com.example.LearnAsyncScheduling;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.*;


@SpringBootApplication
@Slf4j
public class LearnAsyncSchedulingApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LearnAsyncSchedulingApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception{
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,
                6, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10),
//                will not show thread rejected exception
                (r, executor) -> {
                    log.info("Thread rejected... Retrying..");
                    try {
                        Thread.sleep(2000);
                    }catch (InterruptedException e){
                        throw new RuntimeException(e);
                    }
                    executor.submit(r); // start retry
                }

        );

//        Scheduled ThreadPool Executor
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(6, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                log.info("");
                return new Thread(r, "thread "+System.nanoTime());
            }
        });

        scheduledThreadPoolExecutor
                .schedule(new LongRunningTask("schedule task"),
                        4, TimeUnit.SECONDS);


        log.info("Starting main Thread -> {}", Thread.currentThread().getName());
//        Task
//         Future<String> res = threadPoolExecutor.submit(()-> {
//        if you want to return something from submit method using Future you can.

//      using for loop start task is print 16. why :-
//      bcz we have active task is 6 inside the thread pool and other 10 task are in ArrayQueue. So, once active task is going to complete the array task is going to work
        for (int i = 0; i < 20; i++) {
            threadPoolExecutor.submit(new LongRunningTask(i+" "));
            Thread.sleep(1000);
        }

        log.info("Starting main Thread -> {}", Thread.currentThread().getName());

    }

}
