package com.atguigu.service.impl;

import com.atguigu.entity.Vip;
import com.atguigu.entity.vo.VipVo;
import com.atguigu.mapper.VipMapper;
import com.atguigu.service.VipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("vipService")
public class VipServiceImpl implements VipService {
    @Resource
    VipMapper vipMapper;
    @Override
    public void saveVip(Vip vip) {
        vipMapper.insertVip(vip);
    }

    @Override
    public Map<String, Object> getVipWithCondition(VipVo vipVo, int current, int limit) {
        List<Vip> vips = vipMapper.selectWithCondition(vipVo, (current - 1) * limit, limit);
        Integer total = vipMapper.selectCountWithCondition(vipVo);
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("vips",vips);
        resultMap.put("total",total);
        return resultMap;
    }

    @Override
    public void removeVipById(String vipIdInt) {
        vipMapper.deleteByVipId(vipIdInt);
    }

    @Override
    public Vip getVipById(String vipId) {
        return  vipMapper.selectByVipId(vipId);
    }

    @Override
    public void updateVipById(Vip vip) {
        vipMapper.updateVip(vip);
    }

    @Override
    public List<Vip> getAllVips() {
        return vipMapper.getAllVips();
    }

    @Override
    public Vip getByVipPhone(String vipPhone) {

        return vipMapper.selectByVipPhone(vipPhone);
    }
}
