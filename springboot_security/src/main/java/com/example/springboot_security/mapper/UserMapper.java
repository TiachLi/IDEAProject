package com.example.springboot_security.mapper;

import com.example.springboot_security.bean.Admin;
import com.example.springboot_security.bean.Admin;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    @Select("select * from user where username=#{userName}")
    Admin getUserByUserName(String userName);
}
