import bean.Account;
import config.BaseConfig;
import dao.TestDao;
import org.junit.Test;
import org.junit.validator.PublicClassValidator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class MyTest {

    @Test
    public void test1(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(BaseConfig.class);
        TestDao testDao = ac.getBean("testDao", TestDao.class);
        System.out.println(testDao.findAll());

    }
}
