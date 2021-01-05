package test;

import dao.UserDao;
import org.junit.Test;
import User.user;

public class UserDaoTest {

@Test
 public void daotets(){
    user loginuser = new user();
    loginuser.setUsername("zhangsan");
    loginuser.setPassword("123");


    UserDao dao = new UserDao();
    user users = dao.login(loginuser);

    System.out.println(users);
}

}
