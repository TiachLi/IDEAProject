package com.service.impl;

import com.dao.impl.AccountDaoImpl;
import com.domain.Account;
import com.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;
import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountDaoImpl accountDao;

    public void init() {
        System.out.println("init");
    }

    public List<Account> findAllService(){
        return accountDao.findAll();
    }
}
