package dao;

import bean.Admin;
import bean.User;

import java.util.List;
import java.util.Map;

 public interface UserDao {
    List<User> findALL();
    Admin findAdmin(Admin admin);
    void  add(User user);

    void delUser(int id);

    User findByID(int id);

    void updateUser(User user);

    int findTotalCount(Map<String,String[]> condition);

    List<User> findByPage(int start, int rows, Map<String, String[]> condition);
}
