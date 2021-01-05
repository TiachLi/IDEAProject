package com.service.impl;

import com.dao.UserDao;
import com.domain.ResultInfo;
import com.domain.User;
import com.domain.UserPageBean;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    private UserDao userDao;
    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public List<User> findAllUser() {
        return userDao.findAllUser();
    }


    @Override
    public UserPageBean pageQueryWithCondition(int currentPage,int pageSize,User user) {
        UserPageBean userPageBean =new UserPageBean();
        //根据用户输入的查询条件，获得相应客户信息
        List<User> users=userDao.findByPageWithCondition((currentPage-1)*pageSize,pageSize,user);
        //根据用户输入的查询条件，查询数据库，获得总条数
        int totalCounts=userDao.findTotalCountsWithCondition(user);
        int totalPages=totalCounts%pageSize==0?totalCounts/pageSize:totalCounts/pageSize+1;
        //设置每页要显示的内容
        userPageBean.setCurrentPage(currentPage);
        userPageBean.setPageSize(pageSize);
        userPageBean.setTotalCount(totalCounts);
        userPageBean.setUsers(users);
        userPageBean.setTotalPage(totalPages);
        //返回数据
        return userPageBean;
    }

    @Override
    public ResultInfo addUser(User user) {
        ResultInfo info =new ResultInfo();
        //根据手机号查询数据库
        User byTel = userDao.findByTel(user.getUserTel());
        //查询到数据则返回手机号重复错误
        if (byTel!=null){
            info.setFlag(false);
            info.setErrorMsg("手机号重复");
            return info;
        }
        //验证通过执行添加操作
        userDao.addUser(user);
        info.setFlag(true);
        return info;
    }
    @Override
    public  void  deleteUser(int userId){
        userDao.deleteUserById(userId);
    }


    @Override
    public User findOneById(int id) {
        return userDao.findOneById(id);
    }

    @Override
    public ResultInfo updateUser(User user) {
        ResultInfo info =new ResultInfo();
        //根据手机号查询数据库
        User byTel = userDao.findByTel(user.getUserTel());
        if (byTel!=null&&byTel.getUserId()!=user.getUserId()){
            //如果要修改的客户id和当前手机号的客户id不同
            if (byTel.getUserTel().equals(user.getUserTel())){
                //如果手机号相同
                info.setFlag(false);
                info.setErrorMsg("手机号重复");
                return info;
            }
        }
        //验证通过，调用dao
        info.setFlag(true);
        userDao.updateUser(user);
        return  info;
    }

    @Override
    public User findOneByTel(String tel) {
        return userDao.findByTel(tel);
    }
}
