package test.java;

import dao.IAccountDao;
import dao.IUserDao;
import domain.Account;
import domain.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.Utils;

import java.io.InputStream;
import java.util.List;

public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;

    private IUserDao userDao;

    private IAccountDao iAccountDao;

    @Before
    public void init(){
        in=Utils.load();
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession= factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
       iAccountDao = sqlSession.getMapper(IAccountDao.class);
    }

    @After
    public void destroy(){
    sqlSession.commit();
    sqlSession.close();
    }


    @Test
    public void findAllTest(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

   @Test
    public void findAWithU_Test() {
       List<Account> allAccount = iAccountDao.findAllAccount();

       for (Account account : allAccount) {
           System.out.println(" =单个用户= ");
           System.out.println(account);
           System.out.println(account.getUser());
       }

   }
}
