package com.atguigu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Seat implements Serializable {
    private int seatId;//座位id
    private String hallName;//影厅名
    private int seatLine;//座位行号
    private int seatCow;//座位列号
    private int isUsable;//是否可用
}
