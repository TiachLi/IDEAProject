package Utils;

import dao.IUserDao;
import domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MybatisUtils {
    public void findAll() throws IOException {

        //加载配置文件
        InputStream resourceAsStream = MybatisUtils.class.getClassLoader().getResourceAsStream("sqlconfig.xml");
        //创建 builder
        SqlSessionFactoryBuilder builder =new SqlSessionFactoryBuilder();
        //创建factory
        SqlSessionFactory factory = builder.build(resourceAsStream);
        //创建session
        SqlSession sqlSession = factory.openSession();
        //创建dao的代理对象
        IUserDao iUserDao = sqlSession.getMapper(IUserDao.class);
        List<User> all = iUserDao.findAll();

        for (User user : all) {
            System.out.println(user);
        }

        sqlSession.close();
        resourceAsStream.close();
    }
}
