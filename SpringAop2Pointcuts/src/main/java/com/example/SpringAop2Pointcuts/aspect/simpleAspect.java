package com.example.SpringAop2Pointcuts.aspect;

import com.example.SpringAop2Pointcuts.annotation.TrackExecutionTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class simpleAspect {
    private static final Logger log = LoggerFactory.getLogger(simpleAspect.class);

//    @Before("@annotation(jdk.jfr.Timestamp)")
//    public void logBeforeMethod() {
//        System.out.println("Method Intercepted");
//    }

    @Around("@annotation(trackExecutionTime)")
    public Object MeasureExecutionTime(ProceedingJoinPoint joinPoint,
                                       TrackExecutionTime trackExecutionTime) throws Throwable {

        long startTime = System.currentTimeMillis();

        try {
            return joinPoint.proceed();
        }finally {

            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;

            String operation = trackExecutionTime.operation();

            if(operation.isBlank()){
                operation = joinPoint.getSignature().getName();
            }

            long warningThreshold = trackExecutionTime.warnAfter();

            if(duration >= warningThreshold){
                System.out.println("Slow Operation alert" + "Time taken by " + operation + " is " + duration);
            }else {
                System.out.println("Time taken by " + operation + " is " + duration);
            }
        }
    }
}
