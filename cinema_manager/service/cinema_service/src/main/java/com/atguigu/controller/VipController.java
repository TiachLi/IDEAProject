package com.atguigu.controller;

import com.atguigu.commonutils.R;
import com.atguigu.entity.Vip;
import com.atguigu.entity.vo.VipVo;
import com.atguigu.service.VipService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cinema/vip/")
public class VipController {

    @Resource(name = "vipService")
    VipService vipService;
    //添加会员信息
    @PostMapping("addVip")
    public R addVip(@RequestBody Vip vip){
        Vip vipVo= vipService.getByVipPhone(vip.getVipPhone());
        if (vipVo!=null){
            return R.error().message("会员号重复");
        }
        vipService.saveVip(vip);
        return R.ok();
    }
    //根据条件获取会员信息
    @PostMapping("getVips/{current}/{limit}")
    public R getVips(@RequestBody(required = false) VipVo vipVo,
                       @PathVariable String current, @PathVariable String limit){
        Integer currentInt = Integer.valueOf(current);
        Integer limitInt = Integer.valueOf(limit);
        Map<String,Object> results= vipService.getVipWithCondition(vipVo,currentInt,limitInt);
        return R.ok().data("vips",results.get("vips")).data("total",results.get("total"));
    }
    //删除会员信息
    @DeleteMapping("removeVip/{vipId}")
    public R removeVip(@PathVariable String vipId){
        vipService.removeVipById(vipId);
        return R.ok();
    }
    //根据id获取会员信息
    @GetMapping("getVip/{vipId}")
    public R getVip(@PathVariable String vipId){
        Vip vip =vipService.getVipById(vipId);
        return R.ok().data("vip",vip);
    }
    //根据id更新
    @PostMapping("updateVip")
    public R updateVip(@RequestBody Vip vip){
        Vip vipVo= vipService.getByVipPhone(vip.getVipPhone());
        if (vipVo!=null&&vipVo.getVipId()!=vip.getVipId()){
            return R.error().message("会员号重复");
        }
        vipService.updateVipById(vip);
        return R.ok();
    }
    //获取所有影
    @GetMapping("getAllVip")
    public R getAllVipName(){
        List<Vip> vipNameList =vipService.getAllVips();
        return R.ok().data("vipList",vipNameList);
    }
}
