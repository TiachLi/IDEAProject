package com.atguigu.controller;

import com.atguigu.commonutils.R;
import com.atguigu.entity.Hall;
import com.atguigu.entity.vo.HallVo;
import com.atguigu.service.HallService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cinema/hall/")
public class HallController {
    @Resource(name = "hallService")
    HallService hallService;
    //添加影厅基本信息
    @PostMapping("addHall")
    public R addHall(@RequestBody Hall hall){
        Hall hallByName = hallService.getHallByName(hall.getHallName());
        if (hallByName!=null){
            return R.error().code(20001).message("影厅名重复");
        }
        //默认座位数为0
        hall.setSeatCount("0");
        hallService.saveHall(hall);
        return R.ok();
    }
    //根据条件获取影片信息
    @PostMapping("getHalls/{current}/{limit}")
    public R getHalls(@RequestBody(required = false) HallVo hallVo,
                       @PathVariable String current, @PathVariable String limit){
        Integer currentInt = Integer.valueOf(current);
        Integer limitInt = Integer.valueOf(limit);
        Map<String,Object> results= hallService.getHallWithCondition(hallVo,currentInt,limitInt);
        return R.ok().data("halls",results.get("halls")).data("total",results.get("total"));
    }
    //删除影片信息
    @DeleteMapping("removeHall/{hallId}")
    public R removeHall(@PathVariable String hallId){
        Integer hallIdInt = Integer.valueOf(hallId);
        hallService.removeHallById(hallIdInt);
        return R.ok();
    }
    //根据id获取影片信息
    @GetMapping("getHall/{hallId}")
    public R getHall(@PathVariable String hallId){
        Integer hallIdInt = Integer.valueOf(hallId);
        Hall hall =hallService.getHallById(hallIdInt);
        return R.ok().data("hall",hall);
    }
    //根据id更新
    @PostMapping("updateHall")
    public R updateHall(@RequestBody Hall hall){
        hallService.updateHallById(hall);
        return R.ok();
    }
    //获取全部影厅信息
    @GetMapping("getAllHallList")
    public R getAllHallList(){
        List<Hall> hallList =hallService.getHallAllList();
        return R.ok().data("hallList",hallList);
    }
    //获取全部影厅名信息
    @GetMapping("getAllHallNameList")
    public R getAllHallNameList(){
        List<String> hallNameList =hallService.getAllHallNameList();
        return R.ok().data("hallNameList",hallNameList);
    }
}
