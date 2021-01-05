package com;


import com.dao.AccountDao;
import com.domain.Account;
import com.service.AccountService;
import com.service.impl.AccountServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
@ComponentScan("com")
public class AccountText {
    @Test
    public void testFindAll(){
        ApplicationContext ac =new ClassPathXmlApplicationContext("bean.xml");
        AccountServiceImpl bean = ac.getBean(AccountServiceImpl.class);
/*        AccountService bean = ac.getBean(AccountService.class);*/
        bean.findAllService();
        System.out.println(bean.getClass());
    }
}
