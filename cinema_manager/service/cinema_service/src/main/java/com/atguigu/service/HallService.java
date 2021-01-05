package com.atguigu.service;

import com.atguigu.entity.Hall;
import com.atguigu.entity.vo.HallVo;

import java.util.List;
import java.util.Map;

public interface HallService {

    //添加影片信息
    void saveHall(Hall hall);
    //根据条件查询
    Map<String, Object> getHallWithCondition(HallVo hallVo, int current, int limit);
    //根据id删除
    void removeHallById(Integer hallIdInt);
    //根据id获取信息
    Hall getHallById(int hallId);
    //根据id更新
    void  updateHallById(Hall hall);
    //获取全部影厅列表
    List<Hall> getHallAllList();
    //根据影厅名获取信息
    Hall getHallByName(String hallName);
    //获取全部影厅名信息
    List<String> getAllHallNameList();
}
