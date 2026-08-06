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


    }

}
