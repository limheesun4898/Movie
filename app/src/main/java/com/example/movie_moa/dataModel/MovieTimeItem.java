package com.example.movie_moa.dataModel;

import java.util.ArrayList;
import java.util.List;

public class MovieTimeItem {

    String theater;

    ArrayList<TimeItem> info;

    public MovieTimeItem() {

    }

    public MovieTimeItem(String theater, ArrayList<TimeItem> info) {
        this.theater = theater;
        this.info = info;
    }

    public String getTheater() {
        return theater;
    }

    public void setTheater(String theater) {
        this.theater = theater;
    }

    public List<TimeItem> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<TimeItem> info) {
        this.info = info;
    }

    public ArrayList<TimeItem> getAllItemsInSection() {
        return info;
    }

//    String scrnNm;
//    String movieNm;
//    String showTm;
//
//
//    public MovieTimeItem(String scrnNm, String movieNm, String showTm) {
//        this.scrnNm = scrnNm;
//        this.movieNm = movieNm;
//        this.showTm = showTm;
//    }
//
//    public String getScrnNm() {
//        return scrnNm;
//    }
//
//    public void setScrnNm(String scrnNm) {
//        this.scrnNm = scrnNm;
//    }
//
//    public String getMovieNm() {
//        return movieNm;
//    }
//
//    public void setMovieNm(String movieNm) {
//        this.movieNm = movieNm;
//    }
//
//    public String getShowTm() {
//        return showTm;
//    }
//
//    public void setShowTm(String showTm) {
//        this.showTm = showTm;
//    }
//
//
//    @Override
//    public String toString() {
//        return "MovieTimeItem{" +
//                "scrnNm='" + scrnNm + '\'' +
//                ", movieNm='" + movieNm + '\'' +
//                ", showTm='" + showTm + '\'' +
//                '}';
//    }
}
