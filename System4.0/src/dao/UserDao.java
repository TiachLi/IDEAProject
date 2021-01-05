package dao;

import JDBCUtils.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import User.user;

public class UserDao {
    //创建JdbcTemplate对象
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDatasource());
    public user login(user loginUser) {
  try {
      //编写sql语句
      String sql = "SELECT * From users WHERE username=? and password=?";
      System.out.println(loginUser.getUsername() + loginUser.getPassword());
      //调用template方法查询
      user user1 = template.queryForObject(sql, new BeanPropertyRowMapper<>(user.class), loginUser.getUsername(), loginUser.getPassword());
      System.out.println(user1);
      return user1;
  }
    catch (
    DataAccessException e) {
        e.printStackTrace();//记录日志
        return null;
    }
    }
    public user register(String username,String password){
        String sql ="INSERT into users(username,password) select ?,?FROM DUAL WHERE  not EXISTS (SELECT * FROM users WHERE username=?)";
        template.update(sql,username,password,username);
        user temp = new user(username,password);
        UserDao  tempDao= new UserDao();
        user login = tempDao.login(temp);
        return login;
    }

}


