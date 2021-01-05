package com.example.springbootauto.auto;

import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AutoService {
    public static void main(String[] args) {
        Map<String,Object> data =new HashMap<>();
        data.put("businessType","epmpics");
        JSONObject jsonObject = new JSONObject(data);
        System.out.println(jsonObject.toString());

    }
}
