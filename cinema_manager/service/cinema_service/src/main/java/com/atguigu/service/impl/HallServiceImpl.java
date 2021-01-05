package com.atguigu.service.impl;

import com.atguigu.entity.Hall;
import com.atguigu.entity.vo.HallVo;
import com.atguigu.mapper.HallMapper;
import com.atguigu.service.HallService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("hallService")
public class HallServiceImpl implements HallService {
    @Resource
    HallMapper hallMapper;
    //添加影片信息
    @Override
    public void saveHall(Hall hall) {
        hallMapper.insertHall(hall);
    }
    //根据条件查询影片信息
    @Override
    public Map<String, Object> getHallWithCondition(HallVo hallVo, int current, int limit) {

        List<Hall> halls=hallMapper.selectWithCondition(hallVo,(current-1)*limit,limit);
        int total = hallMapper.selectCountWithCondition(hallVo);
        HashMap<String, Object> result = new HashMap<>();
        result.put("halls",halls);
        result.put("total",total);
        return result;
    }
    //删除影片
    @Override
    public void removeHallById(Integer hallIdInt) {
        hallMapper.deleteHall(hallIdInt);
    }

    //根据id获取影片信息
    @Override
    public Hall getHallById(int hallId) {
        return  hallMapper.selectById(hallId);
    }
    //根据id更新
    @Override
    public void updateHallById(Hall hall) {
        hallMapper.updateById(hall);
    }
    //获取全部影厅列表
    @Override
    public List<Hall> getHallAllList() {
        return hallMapper.selectAllList();
    }
    //根据影厅名查询
    @Override
    public Hall getHallByName(String hallName) {
        return hallMapper.selectByName(hallName);
    }
    //获取全部影厅名信息

    @Override
    public List<String> getAllHallNameList() {
        return hallMapper.selectAllHallName();
    }
}
