package com.atguigu.mapper;

import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.mapping.StatementType;

public interface StaMapper {
    //根据影片名获取票房
    @Select("call selectMovieTotalTicketPrice (#{movieName})")
    @Options(statementType= StatementType.CALLABLE)
    Integer getTicketSales(@Param("movieName") String movieName);

    //根据时间获取营业额
    @Select("call selectTotalFinalPrice (#{begin},#{end})")
    Integer getTotalTicketPrice(@Param("begin") String begin,@Param("end") String end);
}
