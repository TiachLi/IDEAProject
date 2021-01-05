package com.dao;

import com.domain.User;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import com.sql.sqlCondition;
import java.util.List;

@Repository
public interface UserDao {
    //
    @Select("select * from book")
    List<User> findAllUser();

    int findTotalCountsWithCondition(User user);

    List<User> findByPageWithCondition(@Param("begin") int begin,@Param("end") int end,@Param("user") User user);

    //根据手机号查询客户信息
    @Select("select * from user where userTel=#{userTel}")
    User findByTel(String userTel);

    @Insert("insert into book(userName," +
            "userEmail,userTel,userGender," +
            "userAge,userAddress) values(#{userName},#{userEmail},#{userTel}," +
            "#{userGender},#{userAge},#{userAddress})")
    void addUser(User user);

    @Delete("delete from book where userId=#{userId}")
    void  deleteUserById(int id);

    @Select("select * from book where userId=#{userId}")
     User  findOneById(int userId);

    @Select("select * from book where userTel=#{tel}")
    User  findOneByTel(String tel);

    //更新客户信息
    @Update("update book set userName=#{userName} ,userEmail=#{userEmail}," +
            " userTel=#{userTel}, userGender=#{userGender}," +
            " userAge=#{userAge}, userAddress=#{userAddress} where userId=#{userId}")
    void updateUser(User user);

}
