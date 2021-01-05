package com.atguigu.service;

import com.atguigu.entity.Seat;
import com.atguigu.entity.vo.SeatVo;
import com.atguigu.entity.vo.SelectedSeatVo;

import java.util.List;
import java.util.Map;

public interface SeatService {
    //添加座位信息
    void addSeats(SeatVo seatVo);
    //更新座位信息
    void updateSeats(SeatVo seatVo);
    //获取影厅全部座位信息
    Map<Integer, List<Seat>> getAllSeats(String hallName );
    //获取某一场次全部的座位信息
    Map<Integer, List<SelectedSeatVo>> getAllSeatsWithHallName(String playListId, String hallName);
    //设置座位为不可用
    void setSeatDisable(List<String> ids);
}
