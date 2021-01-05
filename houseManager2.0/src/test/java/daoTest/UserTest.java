package daoTest;

import com.dao.UserDao;
import com.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserTest {

    @Autowired
    UserDao userDao;

    @Test
    public void test1(){
        User user = new User();
        user.setUserName("1");
        List<User> byPageWithCondition = userDao.findByPageWithCondition(1, 5, user);
        System.out.println(byPageWithCondition);
        int totalCountsWithCondition = userDao.findTotalCountsWithCondition(user);
        System.out.println(totalCountsWithCondition);
    }
}
