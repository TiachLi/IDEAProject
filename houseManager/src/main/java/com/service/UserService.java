package com.service;

import com.dao.UserDao;
import com.domain.User;

import java.util.List;

public interface UserService {

    public List<User> findAllUser();

}
