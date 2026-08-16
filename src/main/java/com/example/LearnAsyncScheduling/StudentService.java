package com.example.LearnAsyncScheduling;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentInfoService studentInfoService;
}
