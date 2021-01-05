import com.dao.AccountDao;
import com.domain.Account;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class mybatis {
    private InputStream in;
    private SqlSession sqlSession;
    private AccountDao accountDao;
    private SqlSessionFactory factory;
    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in= Resources.getResourceAsStream("MybatisConfig.xml");
        //2.获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //3.获取SqlSessionFactory
        SqlSessionFactory factory = builder.build(in);
        //4.get SqlSession
        sqlSession = factory.openSession(true);
        //get accountDao
        accountDao = sqlSession.getMapper(AccountDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        //提交事务
        // sqlSession.commit();
        //6.释放资源
        sqlSession.close();
        in.close();
    }

    @Test
    public void findAllTest(){
        List<Account> all = accountDao.findAll();
        System.out.println(all);
    }
    @Test
    public void findOneTest(){
        Account aaa = accountDao.findAccountByName("aaa");
        System.out.println(aaa);
    }


}
