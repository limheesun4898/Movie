package com.heesun.movie_moa.dataModel;


import java.util.ArrayList;

public class MovieTimeItem {

    String theater;

    @Override
    public String toString() {
        return "MovieTimeItem{" +
                "theater='" + theater + '\'' +
                ", info=" + info +
                '}';
    }

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

    public ArrayList<TimeItem> getInfo() {
        return info;
    }

    public void setInfo(ArrayList<TimeItem> info) {
        this.info = info;
    }

    public ArrayList<TimeItem> getAllItemsInSection() {
        return info;
    }

}
