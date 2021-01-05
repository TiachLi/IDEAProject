package service;

import bean.Admin;
import bean.PageBean;
import bean.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //显示所有用户
    public List<User> findAll();
    //管理员登陆
    Admin login(Admin loginAdmin);
    //添加用户
    void  add(User user);
    //删除用户
    void delUser(String id);
    //通过ID查找用户
    User findByID(String id);
    //更新用户信息
    void updateUser(User user);
    //删除选中
    void delSelected(String[] uids);
    //分页
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
