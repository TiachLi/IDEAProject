import dao.IAccountDao;
import domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class IAccountTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IAccountDao accountDao;
    private SqlSessionFactory factory;
    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in=Resources.getResourceAsStream("SqlConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        accountDao = sqlSession.getMapper(IAccountDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        //提交事务
        sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }
    //测试延缓查询
    @Test
    public void findAllTest(){
        List<Account> accounts = accountDao.findAll();
        for (Account account : accounts) {
            System.out.println(account.getId());
            System.out.println(account.getUser());
        }
        sqlSession.commit();
    }
    //测试二级缓存
   /* @Test
    public void findById(){
        List<Account> accounts1 = accountDao.findByUid(41);
        sqlSession.close();
        SqlSession sqlSession1 = factory.openSession();
        IAccountDao accountDao1 = sqlSession1.getMapper(IAccountDao.class);
        List<Account> accounts2 = accountDao1.findByUid(41);
        sqlSession1.close();
        System.out.println(accounts1==accounts2);
    }*/


}
