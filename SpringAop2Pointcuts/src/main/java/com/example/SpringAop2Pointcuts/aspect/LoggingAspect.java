package com.example.SpringAop2Pointcuts.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {

//    @Before("execution( * com.example.SpringAop2Pointcuts.service.StudentService.*(..) )")
//    public void logBeforeMethod() {
//        System.out.println("Before Method Intercepted");
//    }

//    @Before("within(com.example.SpringAop2Pointcuts.service.StudentService)")
//    public void logBeforeMethod() {
//        System.out.println("Before Method Intercepted");
//    }

    @Before("bean(studentService)")
    public void logBeforeMethod() {
        System.out.println("Before Method Intercepted");
    }

//    @Before("execution( com.example.SpringAop2Pointcuts.dto.Student " +
//            "com.example.SpringAop2Pointcuts.service.StudentService.createStudent(com.example.SpringAop2Pointcuts.dto.Student) )")
//    public void logBeforeMethod2() {
//        System.out.println("Before Method2 Intercepted");
//    }
}
