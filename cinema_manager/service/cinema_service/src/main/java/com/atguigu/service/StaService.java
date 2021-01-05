package com.atguigu.service;

public interface StaService {

    //根据影片获取票房
    Integer getTicketSales(String movieName);
    //根据时间获取营业额
    Integer getFinalTicketPrice(String begin,String end);
}
