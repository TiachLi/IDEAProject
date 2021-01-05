package com.atguigu.controller;

import com.atguigu.commonutils.R;
import com.atguigu.entity.Movie;
import com.atguigu.entity.vo.MovieVo;
import com.atguigu.service.MovieService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cinema/movie/")
public class MovieController {

    @Resource(name = "movieService")
    MovieService movieService;
    //添加影片信息
    @PostMapping("addMovie")
    public R addMovie(@RequestBody Movie movie){
       movieService.saveMovie(movie);
       return R.ok();
    }
    //根据条件获取影片信息
    @PostMapping("getMovies/{current}/{limit}")
    public R getMovies(@RequestBody(required = false) MovieVo movieVo,
                       @PathVariable String current,@PathVariable String limit){
        Integer currentInt = Integer.valueOf(current);
        Integer limitInt = Integer.valueOf(limit);
        Map<String,Object> results= movieService.getMovieWithCondition(movieVo,currentInt,limitInt);
        return R.ok().data("movies",results.get("movies")).data("total",results.get("total"));
    }
    //删除影片信息
    @DeleteMapping("removeMovie/{movieId}")
    public R removeMovie(@PathVariable String movieId){
        movieService.removeMovieById(movieId);
        return R.ok();
    }
    //根据id获取影片信息
    @GetMapping("getMovie/{movieId}")
    public R getMovie(@PathVariable String movieId){
        Movie movie =movieService.getMovieById(movieId);
        return R.ok().data("movie",movie);
    }
    //根据id更新
    @PostMapping("updateMovie")
    public R updateMovie(@RequestBody Movie movie){
        movieService.updateMovieById(movie);
        return R.ok();
    }
    //获取所有影片名
    @GetMapping("getAllMovieName")
    public R getAllMovieName(){
        List<String> movieNameList =movieService.getAllMovieNames();
        return R.ok().data("movieNameList",movieNameList);
    }
}
