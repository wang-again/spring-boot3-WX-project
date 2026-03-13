package com.wangagain.wx.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class submitAspect {
    @Around("execution(* com.wangagain.wx.service.impl.miniprogram..*(..))")
    public Object submit(ProceedingJoinPoint jp) throws Throwable {
            Object[] args = jp.getArgs();
            String methodName = jp.getSignature().getName();
            System.out.println("提交"+methodName+"前：" + Arrays.toString(args));
            Object proceed = jp.proceed(args);
            System.out.println("提交"+methodName+"结果：" + proceed);
            return proceed;
    }
}
