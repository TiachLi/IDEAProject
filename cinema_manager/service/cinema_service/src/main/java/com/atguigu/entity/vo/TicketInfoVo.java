package com.atguigu.entity.vo;

import lombok.Data;

@Data
public class TicketInfoVo {
    String ticketId;//影票id
    String movieName;//电影名
    String hallName;//影厅名
    String seatLine;//座位行号
    String seatCow;//座位列号
    String playTime;//放映时间
    String customerPhone;//顾客手机号
    String finalPrice;//最终价格
    String isPaid;//最终价格
    String ticketPrice;//原本票价
}
