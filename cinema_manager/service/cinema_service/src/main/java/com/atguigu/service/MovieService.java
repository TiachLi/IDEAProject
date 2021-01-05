package com.atguigu.service;

import com.atguigu.entity.Movie;
import com.atguigu.entity.vo.MovieVo;

import java.util.List;
import java.util.Map;

public interface MovieService {
    //添加影片信息
    void saveMovie(Movie movie);
    //根据条件查询
    Map<String, Object> getMovieWithCondition(MovieVo movieVo, int current, int limit);
    //根据id删除
    void removeMovieById(String movieIdInt);
    //根据id获取信息
    Movie getMovieById(String movieId);
    //根据id更新
    void  updateMovieById(Movie movie);
    //获取所有影片名
    List<String> getAllMovieNames();
}
