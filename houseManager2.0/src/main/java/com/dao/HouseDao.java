package com.dao;

import com.domain.House;
import com.domain.User;
import com.sql.sqlCondition;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HouseDao {

    @Select("select * from houses")
    List<House> findAllHouses();

    int findTotalCountsWithCondition(House house);
    List<House> findByPageWithCondition(@Param("house") House house,@Param("begin") int begin,@Param("end") int end);

    @Select("select * from houses where houseTel=#{houseTel}")
    House findByTel(String houseTel);
    //根据房源名查询房源
    @Select("select * from houses where houseName=#{houseName}")
    House findByName(String houseName);

    //添加新的房源信息
    @Insert("insert into houses (houseName," +
            "housePrice,houseAddress,houseType," +
            "houseTel) values(#{houseName},#{housePrice},#{houseAddress}," +
            "#{houseType},#{houseTel})")
    void addHouse(House house);

    @Delete("delete from houses where houseId=#{houId}")
    void  deleteHouseById(int id);

    @Select("select * from houses where houseId=#{houseId}")
     House  findOneById(int houseId);
     //更新房源信息
    @Update("update houses set houseName=#{houseName} ,housePrice=#{housePrice}," +
            " houseAddress=#{houseAddress}, houseType=#{houseType}," +
            " houseTel=#{houseTel} , subTel=#{subTel} where houseId=#{houseId}")
    void updateHouse(House house);



}
