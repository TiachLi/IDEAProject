package com.service;

import com.domain.ResultInfo;
import com.domain.User;
import com.domain.UserPageBean;

import java.util.List;

public interface UserService {

     List<User> findAllUser();
     UserPageBean pageQueryWithCondition(int currentPage,int pageSize,User user);
    ResultInfo addUser(User user);

    void  deleteUser(int userId);

    User findOneById(int id);

    User findOneByTel(String tel);

    ResultInfo updateUser(User user);
}
