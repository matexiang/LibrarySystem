package com.lxy.book.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@Aspect
public class TimeRecordAspect {
    @Around("execution(* com.bite.book.controller.*.*(..))")
    public Object timeRecord(ProceedingJoinPoint joinPoint) throws Throwable {
        //记录方法耗时

        //开始时间
        long start = System.currentTimeMillis();
        //执行原始方法
        Object result = joinPoint.proceed();
        //记录耗时时间
        log.info(joinPoint.getSignature()+"耗时" +(System.currentTimeMillis()-start)+"ms");
        return result;
    }
}
