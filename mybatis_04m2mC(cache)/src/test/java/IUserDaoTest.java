import dao.IAccountDao;
import dao.IUserDao;
import domain.Account;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class IUserDaoTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao iUserDao;
    private IAccountDao iAccountDao;
    SqlSessionFactory factory;


    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in= Resources.getResourceAsStream("SqlConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        iUserDao = sqlSession.getMapper(IUserDao.class);
        iAccountDao=sqlSession.getMapper(IAccountDao.class);
    }


    public void destroy()throws Exception{
        //提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }
    @Test
    public void findAllTest() {
/*        List<User> users1 = iUserDao.findAll();
        for (User user : users1) {
            System.out.println(user);
        }
        System.out.println("第一次查询结束");
        List<User>users2= iUserDao.findAll();
        for (User user : users2) {
            System.out.println(user);
        }
        System.out.println("第二次查询结束");
        User user =new User();
        user.setUsername("test");
        SqlSession sqlSession1 = factory.openSession();
        IUserDao iUserDao1 = sqlSession1.getMapper(IUserDao.class);
        iUserDao1.addUser(user);
        System.out.println("第一次插入结束");
        sqlSession1.commit(false);
        sqlSession1.close();
        List<User> all = iUserDao.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
        User user1 =new User();
        user1.setUsername("test1");
        iUserDao.addUser(user1);
        System.out.println("第二次插入结束");
        List<User> all1 = iUserDao.findAll();
        for (User user2 : all1) {
            System.out.println(user2);
        }
        System.out.println("第三次查询结束");*/
    }
    @Test
    public void findAllTest1() throws IOException {
        //1.读取配置文件，生成字节输入流
        in= Resources.getResourceAsStream("SqlConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        SqlSession sqlSession1 = factory.openSession();
        IUserDao iUserDao1 = sqlSession1.getMapper(IUserDao.class);
        List<User> users1 = iUserDao1.findAll();
        for (User user : users1) {
            System.out.println(user);
        }
        System.out.println("第一次查询结束");

/*        SqlSession sqlSession2 = factory.openSession();
        IUserDao iUserDao2 = sqlSession2.getMapper(IUserDao.class);*/
/*        User user =new User();
        user.setUsername("test1");
        iUserDao1.addUser(user);*/
        Account account = new Account();
        account.setMoney(200.0);
        IAccountDao mapper = sqlSession1.getMapper(IAccountDao.class);
        mapper.addAccount(account);
        System.out.println("第一次插入结束");
        List<User> all = iUserDao1.findAll();
        for (User user1 : all) {
            System.out.println(user1);
        }
        System.out.println("第二次查询结束");
    }


  /*  @Test
    public void findByIdTest(){
        iUserDao.findById(41);
        sqlSession.commit();
        sqlSession.close();
        SqlSession sqlSession1 = factory.openSession();
        IUserDao iUserDao1 = sqlSession1.getMapper(IUserDao.class);
        iUserDao1.findById(41);
        sqlSession1.commit();
        sqlSession1.close();
    }*/
}
