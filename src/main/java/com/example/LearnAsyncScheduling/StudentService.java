package com.example.LearnAsyncScheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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

            Student student = new Student(studentInfoService.getName().get(),
                    studentInfoService.getCollege().get(),
                    studentInfoService.getId().get());

            long end = System.currentTimeMillis();
            log.info("Ended in {}", end-start);
            return student;
        } catch (InterruptedException | ExecutionException e){
            throw new RuntimeException(e);
        }
    }
}
