package com.atguigu.service.impl;

import com.atguigu.mapper.StaMapper;
import com.atguigu.service.StaService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("staService")
public class StaServiceImpl implements StaService {
    @Resource
    StaMapper staMapper;

    //获取票房
    @Override
    public Integer getTicketSales(String movieName) {
        return staMapper.getTicketSales(movieName);
    }
    //获取营业额
    @Override
    public Integer getFinalTicketPrice(String begin, String end) {
        return staMapper.getTotalTicketPrice(begin,end);
    }
}
