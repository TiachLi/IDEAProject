package com.atguigu.mapper;

import com.atguigu.entity.Movie;
import com.atguigu.entity.vo.MovieVo;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface MovieMapper {
    //插入电影
    @Insert("insert into movie values(#{movieId},#{movieName},#{movieType},#{movieIntroduction},#{movieCover})")
    void insertMovie(Movie movie);
    //查询影片总量
    @Select("select count(*) from movie")
    int selectCountAll();
    //删除影片
    @Delete("delete from movie where movie_id=#{movieIdInt}")
    void deleteMovie(String movieIdInt);
    //根据id查询
    @Select("select * from movie where movie_id=#{movieId}")
    Movie selectById(String movieId);
    //更新影片信息，根据id
    @Update("update movie set movie_name=#{movieName},movie_type=#{movieType},movie_introduction=#{movieIntroduction} " +
            "where movie_id=#{movieId}")
    void updateById(Movie movie);
    //获取所有影片名
    @Select("select movie_name from movie")
    List<String> selectAllMovieName();
    //条件查询影片
    @SelectProvider(type = com.atguigu.helpler.MovieSqlHelper.class,method = "getMoviesByVo")
    List<Movie> selectWithCondition(@Param("movieVo") MovieVo movieVo, @Param("begin") int begin,@Param("end") int limit);
    //条件查询影片数量
    @SelectProvider(type = com.atguigu.helpler.MovieSqlHelper.class,method = "getMovieCountsByVo")
    Integer selectCountWithCondition(@Param("movieVo") MovieVo movieVo);
}
