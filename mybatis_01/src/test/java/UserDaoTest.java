import mybatis.dao.IUserDao;
import mybatis.domain.Admin;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class UserDaoTest {

    @Test
    public void test1() throws IOException {
        InputStream inputStream = UserDaoTest.class.getClassLoader().getResourceAsStream("Sql.xml");
        //创建工厂构建这，构建者模式
        SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
        //创建工厂
        SqlSessionFactory factory = builder.build(inputStream);
        //创建SqlSession
        SqlSession sqlSession = factory.openSession();
        //使用工厂来创建dao对象
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        List<Admin> all = iUserDao.findAll();
        for (Admin admin : all) {
            System.out.println(admin);
        }
        sqlSession.close();
        inputStream.close();
    }

}
