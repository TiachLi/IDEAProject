package com.atguigu.mapper;

import com.atguigu.entity.Hall;
import com.atguigu.entity.vo.HallVo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface HallMapper {
    //插入影厅
    @Insert("insert into hall values(#{hallId},#{hallName},#{hallLevel},#{seatCount})")
    void insertHall(Hall hall);
    //查询影厅总量
    @Select("select count(*) from hall")
    int selectCountAll();
    //根据id删除影厅信息;
    @Delete("delete from hall where hall_id=#{hallIdInt}")
    void deleteHall(Integer hallIdInt);
    //根据id查询
    @Select("select * from hall where hall_id=#{hallId}")
    Hall selectById(int hallId);
    //更新影厅信息，根据id
    @Update("update hall set hall_name=#{hallName},hall_level=#{hallLevel},seat_count=#{seatCount} " +
            "where hall_id=#{hallId}")
    void updateById(Hall hall);
    //获取全部影厅信息
    @Select("select * from hall")
    List<Hall> selectAllList();
    //更新座位数
    @Update("update hall set seat_count=#{SeatCount} where hall_name=#{hallName}")
    void updateSeatCountByName(@Param("SeatCount") int SeatCount, @Param("hallName") String hallName);
    //根据影厅名查询
    @Select("select * from hall where hall_name=#{hallName}")
    Hall selectByName(String hallName);
    //查询所有影厅名
    @Select("select hall_name from hall")
    List<String> selectAllHallName();
    //条件查询影厅
    @SelectProvider(type = com.atguigu.helpler.HallSqlHelper.class,method = "getHallsByVo")
    List<Hall> selectWithCondition(@Param("hallVo") HallVo hallVo, @Param("begin") int begin, @Param("end") int limit);
    //条件查询总量
    @SelectProvider(type = com.atguigu.helpler.HallSqlHelper.class,method = "getHallCountsByVo")
    int selectCountWithCondition(@Param("hallVo") HallVo hallVo);
}
