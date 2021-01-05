package com.atguigu.service.impl;

import com.atguigu.entity.Movie;
import com.atguigu.entity.vo.MovieVo;
import com.atguigu.mapper.MovieMapper;
import com.atguigu.service.MovieService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("movieService")
public class MovieServiceImpl implements MovieService {
    @Resource
    MovieMapper movieMapper;
    //添加影片信息
    @Override
    public void saveMovie(Movie movie) {
        movieMapper.insertMovie(movie);
    }
    //根据条件查询影片信息
    @Override
    public Map<String, Object> getMovieWithCondition(MovieVo movieVo, int current, int limit) {
        //影片信息
        List<Movie> movies=movieMapper.selectWithCondition(movieVo,(current-1)*limit,limit);
        //影片总量
        int total = movieMapper.selectCountWithCondition(movieVo);
        HashMap<String, Object> result = new HashMap<>();
        result.put("movies",movies);
        result.put("total",total);
        return result;
    }
    //删除影片
    @Override
    public void removeMovieById(String movieIdInt) {
        movieMapper.deleteMovie(movieIdInt);
    }

    //根据id获取影片信息
    @Override
    public Movie getMovieById(String movieId) {
        return  movieMapper.selectById(movieId);
    }
    //根据id更新
    @Override
    public void updateMovieById(Movie movie) {
        movieMapper.updateById(movie);
    }
    //获取所有影片名
    @Override
    public List<String> getAllMovieNames() {
        return movieMapper.selectAllMovieName();
    }
}
