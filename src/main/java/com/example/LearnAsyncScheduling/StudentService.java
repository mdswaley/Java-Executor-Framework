package com.example.LearnAsyncScheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentInfoService studentInfoService;

    public Student getStudentInfo(){
        try{
            long start = System.currentTimeMillis();
            log.info("Starting now...{}", Thread.currentThread().getName());

            CompletableFuture<String> nameFuture = studentInfoService.getName();
            CompletableFuture<String> collegeFuture = studentInfoService.getCollege();
            CompletableFuture<String> idFuture = studentInfoService.getId();

            CompletableFuture.allOf(nameFuture, collegeFuture, idFuture).join(); // all are going to run parallel and take 2 sec

            Student student = new Student(nameFuture.get(),
                    collegeFuture.get(),
                    idFuture.get());

            long end = System.currentTimeMillis();
            log.info("Ended in {}", end-start);
            return student;
        } catch (InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }
    }
}

/*

Spring Boot Thread Safety

Spring Beans default to Singleton scope, which means that there will only ever be one
instance of them while your application is running. This means that all of your
Controllers, Services, Repositories, etc code will be shared by multiple threads,

which means they will be shared by multiple user requests running at the same time.

• You should be designing all of these components to be stateless.
• User state should only exist in parameters, and inside functions.
• It's totally fine for your Repository class to store state about the database url,
username, and password. These values are not user-specific.

*/
