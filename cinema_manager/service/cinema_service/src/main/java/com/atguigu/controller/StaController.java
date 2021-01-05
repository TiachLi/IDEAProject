package com.atguigu.controller;

import com.atguigu.commonutils.R;
import com.atguigu.service.StaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/cinema/sta/")
public class StaController {
    @Resource
    StaService staService;

    @GetMapping("getTicketSales/{movieName}")
    public R getTicketSales(@PathVariable("movieName") String movieName) {
        Integer ticketSales = staService.getTicketSales(movieName);
        if (ticketSales==null){
            ticketSales=0;
        }
        return R.ok().data("ticketSales",ticketSales);
    }

    @GetMapping("getFinalTicketPrice/{begin}/{end}")
    public R getTicketSales(@PathVariable("begin") String begin,@PathVariable("end") String end) {
        Integer finalTicketPrice = staService.getFinalTicketPrice(begin, end);
        return R.ok().data("finalTicketPrice",finalTicketPrice);
    }
}
