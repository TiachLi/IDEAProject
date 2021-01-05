package com.atguigu.entity;

import lombok.Data;

@Data
public class Ticket {
    private int ticketId;//小票id，票据
    private String userId;//用户id
    private String playListId;//场次id
    private String seatId;//座位id
    private float finalPrice;//最终票价
    private int isPaid;//是否支付
    private String customerPhone;//顾客手机号
    private String sellTime;//卖出的时间
}
