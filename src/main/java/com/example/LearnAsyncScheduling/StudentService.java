package com.example.LearnAsyncScheduling;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentInfoService studentInfoService;

    public Student getStudentInfo(){
        try{
            long start = System.currentTimeMillis();
            log.info("Starting now...{}", Thread.currentThread().getName());

            Student student = new Student(studentInfoService.getName(),
                    studentInfoService.getCollege(),
                    studentInfoService.getId());

            long end = System.currentTimeMillis();
            log.info("Ended in {}", end-start);
            return student;
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}
