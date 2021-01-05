package com.atguigu.mapper;

import com.atguigu.entity.PlayList;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface PlayListMapper {
    //添加场次信息
    @Insert("insert into play_list values(#{playListId},#{playListTime},#{hallName},#{movieName},#{ticketPrice})")
    void insertPlayList(PlayList playList);
    //更新场次信息
    @Update("update play_list set playList_time=#{playListTime},hall_name=#{hallName},movie_name=#{movieName},ticket_price=#{ticketPrice} where playList_id=#{playListId}")
    void updatePlayList(PlayList playList);
    //根据删除场次信息
    @Delete("delete from play_list where playList_id=#{playListId}")
    void deleteByPlayListId(String playListId);
    //获取场次信息
    @Select("select * from play_list where playList_Id = #{playListId}")
    PlayList selectOneById(String playListId);
    //获取全部场次信息
    @Select("select * from play_list")
    List<PlayList> selectAllPlayLists();
    //根据影厅名查询
    @Select("select * from play_list where hall_name=#{hallName}")
    List<PlayList> selectByHallName(String hallName);
    //查询票价
    @Select("select ticket_price from play_list where playList_id=#{playListId}")
    float selectTicketPrice(String playListId);
    //判断是否重复
    @Select("select * from play_list where playList_time=#{playListTime} and hall_name=#{hallName}")
    PlayList selectByHallNameAndTime(String hallName, String playListTime);
    //查询总量
    @Select("select count(*) from play_list")
    Integer selectCountAll();
    @Select("select * from play_list where movie_name=#{movieName} and playList_time>#{date} limit #{begin},#{end}")
    List<PlayList> selectByMovieNameTillNow(@Param("movieName") String movieName,@Param("date") String date, @Param("begin") int begin, @Param("end") Integer end);
    @Select("select count(*) from play_list where movie_name=#{movieName} and playList_time>#{date}")
    Integer selectCByMovieNameTillNow(@Param("movieName") String movieName,@Param("date") String date);
}
