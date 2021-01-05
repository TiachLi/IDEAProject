package com.atguigu.aclservice.service;

import com.alibaba.fastjson.JSONObject;

import java.util.List;
import java.util.Map;

public interface IndexService {

    /**
     * 根据用户id获取用户登录信息
     * @param userId
     * @return
     */
    Map<String, Object> getUserInfo(String userId);

    /**
     * 根据用户id获取动态菜单
     */
    List<JSONObject> getMenu(String userId);

}
