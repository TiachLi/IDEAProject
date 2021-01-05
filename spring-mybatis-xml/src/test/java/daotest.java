import mapper.UserDao;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class daotest {


    @Test
    public void test1(){
        ApplicationContext ac =new ClassPathXmlApplicationContext("applicationContext.xml");
        UserDao userDao = ac.getBean("userDao", UserDao.class);
        int totalCounts = userDao.findTotalCounts();
        System.out.println(totalCounts);
    }
}
