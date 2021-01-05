package com;


import com.config.Config;
import com.dao.AccountDao;
import com.domain.Account;
import com.service.AccountService;
import com.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountText {
    @Autowired
    private AccountService accountService;
    @Test
    public void testFindAll(){
       List<Account> allService = accountService.findAllService();
       for (Account account : allService) {
            System.out.println(account);
        }
    }
}
