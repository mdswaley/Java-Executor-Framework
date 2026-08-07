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
    public void run(String... args) throws Exception {

        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4,
                6, 2, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), // At a time we can execute
                // 16 task bcz max thread pool can be 6 and we have 10 size of array which carry task so 10 + 6 = 16

//                here we define rejection thread
                new RejectedExecutionHandler() {
                    @Override
                    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                        log.info("Thread is rejected...");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }


//                will not show thread rejected exception
//                (r, executor) -> {
//                    log.info("Thread rejected... Retrying..");
//                    try {
//                        Thread.sleep(2000);
//                    }catch (InterruptedException e){
//                        throw new RuntimeException(e);
//                    }
//                    executor.submit(r); // start retry
//                }

        );

//        log.info("Starting main thread -> {}", Thread.currentThread().getName());

//        threadPoolExecutor.submit(() -> {
//            log.info("starting task -> {}", Thread.currentThread().getName());
//            try {
//                Thread.sleep(4000);
//            }catch (InterruptedException e){
//                throw new RuntimeException(e);
//            }
//            log.info("Ending task -> {}", Thread.currentThread().getName());
//        });


//        threadPoolExecutor.submit(new LongRunningTask("Hello Swaley"));

//      when we run with this config after 16 task started and ended the size of max thread pool reach so some how rest of task not assigned
//        for (int i = 0; i < 100; i++) {
//            threadPoolExecutor.submit(new LongRunningTask(i + " "));
//        }

//        log.info("Ending main thread -> {}", Thread.currentThread().getName());

        /*
Task Submission Flow:

Tasks 1 - 4:
    -> Assigned immediately to the 4 core threads.

Tasks 5 - 14:
    -> Core threads are busy, so these tasks are placed into the queue.

Tasks 15 - 16:
    -> Queue is full, so the executor creates 2 additional (non-core) threads.

Task 17 onwards:
    -> Core threads are busy.
    -> Queue is full.
    -> Maximum thread count (6) has been reached.
    -> Therefore, tasks are rejected by the default AbortPolicy,
       which throws RejectedExecutionException.

When the running tasks finish:
    -> The queued tasks (5-14) are executed.
    -> Rejected tasks (17-100) are NOT executed because they were never accepted.

After all tasks complete:
    -> The 2 non-core threads remain idle.
    -> If they stay idle for more than 2 seconds (keepAliveTime),
       they are terminated automatically.
    -> The 4 core threads remain alive by default.

To solve :- we can either increase number max pool size according to the input
            or we can use LinkedBlockingQueue<>()
            or we can go for RejectedExecution like after max Thread pool is full wait for some thread to release then assign again
*/

//        for (int i = 0; i < 20; i++) {
//            threadPoolExecutor.submit(new LongRunningTask(i + " "));
//        }


//        Scheduled ThreadPool Executor
//        we can schedule the task like after certain number of time a task should start
        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(6, new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                log.info("");
                return new Thread(r, "thread "+System.nanoTime());
            }
        });

        scheduledThreadPoolExecutor
                .schedule(new LongRunningTask("schedule task"),
                        4, TimeUnit.SECONDS); // here after 4 sec we start task

    }

}









/*


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
        */
