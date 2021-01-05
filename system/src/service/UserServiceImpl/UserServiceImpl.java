package service.UserServiceImpl;

import bean.Admin;
import bean.PageBean;
import bean.User;
import dao.UserDao;
import dao.UserDaoImpl.UserDaoImpl;
import service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {

   private UserDao userDao =new UserDaoImpl();
    @Override
    public List<User> findAll() {
        //调用dao层
        return userDao.findALL();
    }

    @Override
    public Admin login(Admin loginAdmin) {
        return userDao.findAdmin(loginAdmin);
    }
    public  void add(User user){
        userDao.add(user);
    }

    @Override
    public void delUser(String id) {
        userDao.delUser(Integer.parseInt(id));
    }
    //通过ID寻找指定用户
    @Override
    public User findByID(String id) {

        return userDao.findByID(Integer.parseInt(id));

    }
    //更新用户信息
    @Override
    public void updateUser(User user) {
        userDao.updateUser(user);
    }
    //删除选中
    @Override
    public void delSelected(String[] uids) {
        for (String uid : uids) {
            userDao.delUser(Integer.parseInt(uid));
        }
    }
    //分页显示
    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        if(currentPage <=0) {
            currentPage = 1;
        }
        //1.创建空的PageBean对象
        PageBean<User> pb = new PageBean<User>();
        //2.设置参数
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数
        int totalCount = userDao.findTotalCount(condition);
        pb.setTotalCount(totalCount);
        //4.调用dao查询List集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> list = userDao.findByPage(start,rows,condition);
        pb.setList(list);

        //5.计算总页码
        int totalPage = (totalCount % rows)  == 0 ? totalCount/rows : (totalCount/rows) + 1;
        pb.setTotalPage(totalPage);


        return pb;
    }
}
