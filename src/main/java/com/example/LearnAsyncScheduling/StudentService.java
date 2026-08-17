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
