package com.atguigu.controller;

import com.atguigu.commonutils.R;
import com.atguigu.entity.PlayList;
import com.atguigu.service.PlayListService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;

@RestController
@RequestMapping("/cinema/playList/*")
public class PlayListController {

    @Resource(name = "playListService")
    PlayListService playListService;
    //添加
    @PostMapping("addPlayList")
    public R addPlayList(@RequestBody PlayList playList){
        //判断是否重复
        PlayList nameAndTime = playListService.getPlayListByHallNameAndTime(playList.getHallName(), playList.getPlayListTime());
        if (nameAndTime!=null) {
            return R.error().code(200001).message("影厅和时间重复");
        }
        playListService.savePlayList(playList);
        return R.ok();
    }
    //删除
    @DeleteMapping("removePlayList/{playListId}")
    public R deletePlayList(@PathVariable String playListId){
        playListService.removePlayListById(playListId);
        return R.ok();
    }
    //更新
    @PostMapping("updatePlayList")
    public R updatePlayList(@RequestBody PlayList playList){
        //判断是否重复
        PlayList nameAndTime = playListService.getPlayListByHallNameAndTime(playList.getHallName(), playList.getPlayListTime());
        if (nameAndTime!=null&& nameAndTime.getPlayListId()!=playList.getPlayListId()) {
            return R.error().code(200001).message("同一时间该影厅已有");
        }
        playListService.updatePlayListById(playList);
        return R.ok();
    }
    //查询单个
    @GetMapping("getPlayList/{playListId}")
    public R getOne(@PathVariable String playListId){
        PlayList playListById = playListService.getPlayListById(playListId);
        return R.ok().data("playList",playListById);
    }
    //获取场次列表
    @GetMapping("getPlayLists/{current}/{limit}")
    public R getPlayLists(String movieName,@PathVariable String current,@PathVariable String limit){
        Integer currentInt = Integer.valueOf(current);
        Integer limitInt = Integer.valueOf(limit);
        Map<String, Object> finalMap = playListService.getByMNameTNow(movieName, currentInt, limitInt);
        return R.ok().data("playLists",finalMap.get("playLists")).data("total",finalMap.get("total"));
    }
}
