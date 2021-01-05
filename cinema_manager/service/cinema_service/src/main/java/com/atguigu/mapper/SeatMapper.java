package com.atguigu.mapper;

import com.atguigu.entity.Seat;
import com.atguigu.entity.vo.SelectedSeatVo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

public interface SeatMapper {
    //添加座位信息
    @Insert("insert into seat values(#{seatId},#{hallName},#{seatLine},#{seatCow},#{isUsable})")
    void insertSeat(Seat seat);
    //设置座位为不可用
    @Update("update seat set is_usable=0 where seat_id=#{seatId}")
    void setSeatDisable(String seatId);
    //根据影厅名删除座位信息
    @Delete("delete from seat where hall_name=#{hallName}")
    void deleteByHallName(String hallName);
    //根据影厅名查询
    @Select("select * from seat where hall_name=#{hallName}")
    List<Seat> selectByHallName(String hallName);

    @Select("call selectSeat (#{playListId},#{hallName})")
    @Options(statementType=StatementType.CALLABLE)
    List<SelectedSeatVo> selectSelectedSeat(@Param("playListId") String playListId,@Param("hallName") String hallName);
}
