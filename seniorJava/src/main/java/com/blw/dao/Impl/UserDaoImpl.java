package com.blw.dao.Impl;

import com.blw.Utils.DBUtils;
import com.blw.dao.UserDao;
import com.blw.entity.User;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {

    DBUtils dbUtils;

    @Override
    public void selectUsers() throws SQLException {
        DBUtils.getConnection();
        ResultSet resultSet = dbUtils.executeQuery("select * from user", null);
        while (resultSet.next()){
            User user = new User();
            int userId = resultSet.getInt(1);
            user.setUserId(userId);
        }
    }
}
