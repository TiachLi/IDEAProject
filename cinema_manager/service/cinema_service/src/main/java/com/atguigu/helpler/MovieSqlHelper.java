package com.atguigu.helpler;

import com.atguigu.entity.vo.MovieVo;
import com.atguigu.mapper.MovieMapper;
import com.atguigu.service.impl.MovieServiceImpl;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class MovieSqlHelper {

    //按条件查询电影信息
    public String getMoviesByVo(MovieVo movieVo,int begin,int end){
        String sql =new SQL(){
            {
                SELECT("* ");
                FROM("movie ");
                if (!StringUtils.isEmpty(movieVo.getMovieName())){
                    WHERE(" movie_name like '%"+movieVo.getMovieName()+"%'");
                }
                if (!StringUtils.isEmpty(movieVo.getMovieType())){
                    WHERE(" movie_type like '%"+movieVo.getMovieType()+"%'");
                }
            }

        }.toString()+"limit "+ begin+","+end;
        return sql;
    }

    //按条件查询电影总量
    public String getMovieCountsByVo(MovieVo movieVo){
        String sql =new SQL(){
            {
                SELECT("count(*) ");
                FROM("movie ");
                if (!StringUtils.isEmpty(movieVo.getMovieName())){
                    WHERE(" movie_name like '%"+movieVo.getMovieName()+"%'");
                }
                if (!StringUtils.isEmpty(movieVo.getMovieType())){
                    WHERE(" movie_type like '%"+movieVo.getMovieType()+"%'");
                }
            }

        }.toString();
        return sql;
    }
}
