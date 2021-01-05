package com.li.service;

import com.li.dao.UserDao;
import com.li.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
@Service
@Transactional
public class UserService {
    @Resource
    UserDao userDao;
    public List<User> findAll(){
        return userDao.findAll();
    }
}
