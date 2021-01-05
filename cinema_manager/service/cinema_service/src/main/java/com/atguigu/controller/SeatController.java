package com.atguigu.controller;

import com.atguigu.commonutils.R;
import com.atguigu.entity.Seat;
import com.atguigu.entity.vo.SeatVo;
import com.atguigu.entity.vo.SelectedSeatVo;
import com.atguigu.service.SeatService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cinema/seat/")
public class SeatController {

    @Resource(name = "seatService")
    SeatService seatService;

    //添加座位信息,包括默认可用
    @PostMapping("addSeats")
    public R addSeats(@RequestBody SeatVo seatVo){
        seatService.addSeats(seatVo);
        return R.ok();
    }
    //设置不可用的状态
    @PostMapping("setIsUsable")
    public R setIsUsable(@RequestBody List<String> ids){
        System.out.println(ids);
        seatService.setSeatDisable(ids);
        return R.ok();
    }
    //更新座位信息
    @PostMapping("updateSeats")
    public R updateSeats(@RequestBody SeatVo seatVo){
        seatService.updateSeats(seatVo);
        return R.ok();
    }
    //获取某影厅全部座位信息
    @GetMapping("getAllSeats/{hallName}")
    public R getAllSeats(@PathVariable String hallName){
        Map<Integer, List<Seat>> allSeats = seatService.getAllSeats(hallName);
        return R.ok().data("seatList",allSeats);
    }


    //获取某一厅在某场次全部座位信息
    //返回一个Map 键为行号，值为这一行对应的座位
    @GetMapping("getAllSeatsWithPl/{playListId}/{hallName}")
    public R getAllSeats(@PathVariable String playListId, @PathVariable String hallName){
        Map<Integer, List<SelectedSeatVo>> seatList =seatService.getAllSeatsWithHallName(playListId,hallName);
        return R.ok().data("seatList",seatList);
    }
}
