package com.github.dzieniu.libsysbe.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class MainLogger {

    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void restControllerMethods() {}

    @Around("restControllerMethods()")
    public Object logRestControllerMethods(ProceedingJoinPoint jp) throws Throwable {
        String methodName = jp.getSignature().getName();
        String args = "";
        for(Object arg : jp.getArgs()) args += arg;
        log.info("Executing method: " + jp.getSignature().getDeclaringType().getSimpleName() + "." + methodName + "(), with arguments: " + args);
        return jp.proceed();
    }
}
