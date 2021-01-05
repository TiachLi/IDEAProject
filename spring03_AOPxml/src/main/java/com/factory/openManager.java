package com.factory;

import com.service.IAccountService;
import com.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于管理通知
 */
public class openManager {


    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }

    //before
    public void before(){
        System.out.println("before");
        txManager.beginTransaction();
    }

    //after-returning
    public void afterReturning(){
        System.out.println("after returning");
        txManager.commit();
    }
    //after-throwing
    public void afterThrowing(){
        System.out.println("after throwing");
        txManager.rollback();
    }

    //after
    public void after(){
        System.out.println("after");
        txManager.release();
    }
}
