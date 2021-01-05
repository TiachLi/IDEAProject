package com.atguigu.helpler;

import com.atguigu.entity.vo.HallVo;
import com.atguigu.entity.vo.MovieVo;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.StringUtils;

public class HallSqlHelper {
    //按条件查询影厅信息
    public String getHallsByVo(HallVo hallVo, int begin, int end){
        String sql =new SQL(){
            {
                SELECT("* ");
                FROM("hall ");
                if (!StringUtils.isEmpty(hallVo.getHallName())){
                    WHERE(" hall_name like '%"+hallVo.getHallName()+"%'");
                }
                if (!StringUtils.isEmpty(hallVo.getHallLevel())){
                    WHERE(" hall_level like '%"+hallVo.getHallLevel()+"%'");
                }
            }

        }.toString()+"limit "+ begin+","+end;
        return sql;
    }

    //按条件查询影厅信息
    public String getHallCountsByVo(HallVo hallVo){
        String sql =new SQL(){
            {
                SELECT(" count(*) ");
                FROM("hall ");
                if (!StringUtils.isEmpty(hallVo.getHallName())){
                    WHERE(" hall_name like '%"+hallVo.getHallName()+"%'");
                }
                if (!StringUtils.isEmpty(hallVo.getHallLevel())){
                    WHERE(" hall_level like '%"+hallVo.getHallLevel()+"%'");
                }
            }

        }.toString();
        return sql;
    }
}
