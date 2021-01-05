package com.atguigu.service.impl;

import com.atguigu.entity.PlayList;
import com.atguigu.mapper.PlayListMapper;
import com.atguigu.service.PlayListService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.SimpleFormatter;

@Service("playListService")
public class PlayListServiceImpl implements PlayListService {
    @Resource
    PlayListMapper playListMapper;
    //添加场次信息
    @Override
    public void savePlayList(PlayList playList) {
        playListMapper.insertPlayList(playList);
    }
    //根据影厅名和放映时间
    @Override
    public PlayList getPlayListByHallNameAndTime(String hallName, String playListTime) {
        return playListMapper.selectByHallNameAndTime(hallName,playListTime);
    }
    //删除场次信息
    @Override
    public void removePlayListById(String playListIdInt) {
        playListMapper.deleteByPlayListId(playListIdInt);
    }
    //获取场次信息
    @Override
    public PlayList getPlayListById(String playListId) {
        return playListMapper.selectOneById(playListId);
    }
    //更新场次信息
    @Override
    public void updatePlayListById(PlayList playList) {
        playListMapper.updatePlayList(playList);
    }
    //查询到目前为止某一电影的场次
    @Override
    public Map<String, Object> getByMNameTNow(String movieName, Integer currentInt, Integer limitInt) {
        Date currentTime = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String formatDate = simpleDateFormat.format(currentTime);
        List<PlayList> playLists=playListMapper.selectByMovieNameTillNow(movieName,formatDate,(currentInt-1)*limitInt,limitInt);
        Integer total=playListMapper.selectCByMovieNameTillNow(movieName,formatDate);
        HashMap<String, Object> finalMap = new HashMap<>();
        finalMap.put("playLists",playLists);
        finalMap.put("total",total);
        return finalMap;
    }
}
