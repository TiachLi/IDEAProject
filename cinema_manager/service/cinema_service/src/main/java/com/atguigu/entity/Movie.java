package com.atguigu.entity;

import lombok.Data;

import java.io.Serializable;

public class Movie implements Serializable {

    private int movieId;//影片id
    private String movieName;//影片名
    private String movieType;//影片类型
    private String movieIntroduction;//影片介绍
    private String movieCover;//封面

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieType() {
        return movieType;
    }

    public void setMovieType(String movieType) {
        this.movieType = movieType;
    }

    public String getMovieIntroduction() {
        return movieIntroduction;
    }

    public void setMovieIntroduction(String movieIntroduction) {
        this.movieIntroduction = movieIntroduction;
    }

    public String getMovieCover() {
        return movieCover;
    }

    public void setMovieCover(String movieCover) {
        this.movieCover = movieCover;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "movieId=" + movieId +
                ", movieName='" + movieName + '\'' +
                ", movieType='" + movieType + '\'' +
                ", movieIntroduction='" + movieIntroduction + '\'' +
                ", movieCover='" + movieCover + '\'' +
                '}';
    }
}
