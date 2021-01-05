package com.atguigu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Hall implements Serializable {

    private int hallId;//影厅id
    private String hallName;//影厅名
    private String hallLevel;//等级
    private String seatCount;//座位数
}
