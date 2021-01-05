package com.factory;

import com.service.IAccountService;
import com.utils.TransactionManager;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 用于创建Service的代理对象的工厂
 */
public class BeanFactory {

    private IAccountService accountService;

    private TransactionManager txManager;

    public void setTxManager(TransactionManager txManager) {
        this.txManager = txManager;
    }


    public final void setAccountService(IAccountService accountService) {
        this.accountService = accountService;
    }

    /**
     * 获取Service代理对象
     * @return
     */
    public IAccountService getAccountService() {
        return (IAccountService) Proxy.newProxyInstance(accountService.getClass().getClassLoader(),
                accountService.getClass().getInterfaces(), new InvocationHandler() {
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        System.out.println("增强方法");
                        if("test".equals(method.getName())){
                            return method.invoke(accountService,args);
                        }

                        try {
                            txManager.beginTransaction();
                            Object rtValue=null;
                            rtValue = method.invoke(accountService, args);
                            txManager.commit();
                            return rtValue;
                        }catch (Exception e){
                            txManager.rollback();
                            throw new RuntimeException(e);
                        }finally {
                            txManager.release();
                        }
                    }
                });
    }
}
