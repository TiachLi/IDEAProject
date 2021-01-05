package com.atguigu.entity.vo;

import lombok.Data;

@Data
public class SelectedSeatVo {
    private int seatId;//座位id
    private String hallName;//影厅名
    private int seatLine;//座位行号
    private int seatCow;//座位列号
    private int isUsable;//是否可用
    private Integer isSelected;//是否被选
}
