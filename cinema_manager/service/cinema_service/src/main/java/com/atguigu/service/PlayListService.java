package com.atguigu.service;

import com.atguigu.entity.PlayList;

import java.util.List;
import java.util.Map;

public interface PlayListService {
    //添加场次信息
    void savePlayList(PlayList playList);
    //根据id删除
    void removePlayListById(String playListIdInt);
    //根据id获取信息
    PlayList getPlayListById(String playListId);
    //根据id更新
    void  updatePlayListById(PlayList playList);
    //根据影厅名和放映时间获取场次
    PlayList getPlayListByHallNameAndTime (String hallName,String playListTime);
    //查询到目前为止某一电影的场次
    Map<String, Object> getByMNameTNow(String movieName, Integer currentInt, Integer limitInt);
}
