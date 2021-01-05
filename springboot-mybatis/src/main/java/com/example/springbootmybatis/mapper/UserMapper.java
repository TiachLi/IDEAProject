package com.example.springbootmybatis.mapper;

import com.example.springbootmybatis.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {


    @Select("select * from user")
    @Results(
            @Result(column = "id",property = "uid")
    )
    List<User> findAll();

    @Options(useGeneratedKeys = true,keyProperty = "uid",keyColumn = "id")
    @Insert("insert into user(name) values(#{name})")
    int insertDept(User user);
}
