package com.li.dao;

import com.li.domain.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserDao {

    List<User> findAll();
}
