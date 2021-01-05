package com.dao;

import com.domain.Admin;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminDao {

    //增添管理员信息
    void addAdmin(Admin admin);

    //根据管理员用户名查询管理员信息
    Admin findOneByName(@Param("adminName") String adminName);

    //根据手机号查询管理员
    Admin findOneByTel(String tel);

/*
    @Select("select * from admin where adminName=#{name} and adminPassword=#{password}")
    Admin findOneByNameAndPassword(@Param("name") String name,@Param("password") String password);*/
}
