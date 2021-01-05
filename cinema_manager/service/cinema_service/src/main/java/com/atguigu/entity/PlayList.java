package com.atguigu.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class PlayList implements Serializable {

    private int playListId;//场次id
    private String playListTime;//放映时间
    private String hallName;//影厅名
    private String movieName;//电影名
    private String ticketPrice;//票价

}
