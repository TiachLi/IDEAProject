import dao.IUserDao;
import domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.InputStream;
import java.util.List;

public class IUserDaoTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao iUserDao;
    SqlSessionFactory factory;

    @Before//用于在测试方法执行之前执行
    public void init()throws Exception{
        //1.读取配置文件，生成字节输入流
        in= Resources.getResourceAsStream("SqlConfig.xml");
        //2.获取SqlSessionFactory
        factory = new SqlSessionFactoryBuilder().build(in);
        //3.获取SqlSession对象
        sqlSession = factory.openSession(true);
        //4.获取dao的代理对象
        iUserDao = sqlSession.getMapper(IUserDao.class);
    }

    @After//用于在测试方法执行之后执行
    public void destroy()throws Exception{
        //提交事务
        //sqlSession.commit();
        //6.释放资源
        //sqlSession.close();
        in.close();
    }
    @Test
    public void findAllTest(){
        List<User> users1 = iUserDao.findAll();
        for (User user : users1) {
            System.out.println(user);
        }
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
