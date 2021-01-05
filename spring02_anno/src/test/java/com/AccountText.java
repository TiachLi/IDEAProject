package com;


import com.config.SpringConfig;
import com.domain.Account;
import com.service.AccountService;
import com.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringConfig.class)
public class AccountText {
    @Autowired
    private AccountService accountService;
    @Test
    public void testFindAll(){
     /*  ApplicationContext as =new AnnotationConfigApplicationContext(SpringConfig.class);
        AccountService accountService = (AccountService) as.getBean("accountService");*/
        List<Account> allService = accountService.findAllService();
       for (Account account : allService) {
            System.out.println(account);
        }
    }
}
