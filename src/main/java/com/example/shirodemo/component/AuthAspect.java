package com.example.shirodemo.component;

import org.apache.shiro.authz.UnauthenticatedException;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component // 注册到Spring容器，必须加入这个注解
@Aspect // 该注解标示该类为切面类，切面是由通知和切点组成的。
public class AuthAspect {

//    @Pointcut("execution(* com.example.shirodemo.service.*.*(..))) && @annotation(Auth)")
//    public void authAspect(){}
//
//    @Around("authAspect()")
//    public Object aroundProcess(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
//        Object[] args = proceedingJoinPoint.getArgs();
//        System.out.println("进入AOP!!");
//        Object arg = args[0];
//        int id = (int)arg;
//        if(id != 1)
//            throw new UnauthenticatedException("未经登录，无法访问");
//        return proceedingJoinPoint.proceed(args);
//    }

}
