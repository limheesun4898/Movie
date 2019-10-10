package com.example.movie_moa.data;

public class MainItem {
    String title;
    String description;
    String poster_url;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public void setPoster_url(String poster_url) {
        this.poster_url = poster_url;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    String detail_url;


    public MainItem(String title, String description, String poster_url, String detail_url) {
        this.title = title;
        this.description = description;
        this.poster_url = poster_url;
        this.detail_url = detail_url;
    }
}
