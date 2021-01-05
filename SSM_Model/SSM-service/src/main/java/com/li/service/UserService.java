package com.li.service;

import com.li.dao.UserDao;
import com.li.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    public List<User> findAll(){
        return userDao.findAll();
    }
}
