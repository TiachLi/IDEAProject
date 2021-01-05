package com.atguigu.service;

import com.atguigu.entity.Vip;
import com.atguigu.entity.vo.VipVo;

import java.util.List;
import java.util.Map;

public interface VipService {
    //添加会员信息
    void saveVip(Vip vip);
    //根据条件查询
    Map<String, Object> getVipWithCondition(VipVo vipVo, int current, int limit);
    //根据id删除
    void removeVipById(String vipIdInt);
    //根据id获取信息
    Vip getVipById(String vipId);
    //根据id更新
    void  updateVipById(Vip vip);
    //获取所有会员
    List<Vip> getAllVips();
    //根据手机号获取会员信息
    Vip getByVipPhone(String vipPhone);
}
