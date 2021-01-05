package com.factory;

import com.service.IAccountService;
import com.utils.TransactionManager;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于管理通知
 */
@Aspect
@EnableAspectJAutoProxy
public class openManager {
    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    @Pointcut("execution(* com.service.impl.*.*(..))")
    public void pt1(){}
    /*
    //before
    @Before("pt1()")
    public void before(){
        System.out.println("before");
        txManager.beginTransaction();
    }

    //after-returning
    @AfterReturning("pt1()")
    public void afterReturning(){
        System.out.println("after returning");
        txManager.commit();
    }
    //after-throwing
    @AfterThrowing("pt1()")
    public void afterThrowing(){
        System.out.println("after throwing");
        txManager.rollback();
    }

    //after
    @After("pt1()")
    public void after(){
        System.out.println("after");
        txManager.release();
    }*/
    @Around("pt1()")
    public Object around(ProceedingJoinPoint pjp){
        Object rtValue=null;
        try {
            Object[] args=pjp.getArgs();
            txManager.beginTransaction();
            System.out.println("before");
            rtValue=pjp.proceed(args);
            System.out.println("after returning");
            txManager.commit();
            return rtValue;
        }catch (Throwable t){
            System.out.println("after throwing");
            txManager.rollback();
            throw new RuntimeException(t);
        }finally {
            System.out.println("after");
            txManager.release();
        }
    }
}
