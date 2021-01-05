import Utils.load;
import dao.IUserDao;
import domain.QueryVo;
import domain.User;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class MybatisTest {
    private InputStream in;
    private SqlSession sqlSession;
    private IUserDao userDao;
    @Before
    public void init(){
        in=load.getAsInputStream();
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
        sqlSession= factory.openSession();
        userDao = sqlSession.getMapper(IUserDao.class);
    }
    @Test
    public void findAll(){
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }

    @Test
    public  void save(){
        User user = new User();
        user.setUserName("test");

        System.out.println("保存");
    }

    @Test
    public void delTest(){
        userDao.deleteUser(79);
    }
    @Test
    public void findByIdTest(){
        User daoById = userDao.findById(80);
        System.out.println(daoById);
    }

    @Test
    public void findByNameTest(){
        List<User> byName = userDao.findByName("%王%");
        System.out.println(byName);

    }
    @Test
    public void findTotalTest(){
        int total = userDao.findTotal();
        System.out.println(total);
    }

    @Test
    public void findUserByVoTest(){
        QueryVo vo = new QueryVo();
        User user = new User();
        user.setUserName("%王%");
        vo.setUser(user);
        List<User> userByVo = userDao.findUserByVo(vo);
        for (User user1 : userByVo) {
            System.out.println(user1);
        }
    }

    @Test
    public void  updateTest(){
        User user = new User();
        user.setUserId(103);
        user.setUserName("mybastis update user");
        user.setUserAddress("北京市顺义区");
        user.setUserSex("女");
        user.setUserBirthday(new Date());

        //5.执行保存方法
        userDao.updateUser(user);
    }

    @After
    public void destroy() throws IOException {
        sqlSession.commit();
        sqlSession.close();
        in.close();
    }


}
