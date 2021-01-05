package com.atguigu.entity.vo;

import lombok.Data;

import java.util.List;

@Data
public class TicketVo {
    List<String> selectedSeat;//选择的座位
    String userId;//工作人员id
    String playListId;//场次id
    String CustomerPhone;//顾客手机号
}
