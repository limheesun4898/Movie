package com.example.movie_moa.data;


public class MainItem {
    int number;
    String title;
    String preview; // 관람이용가
    String bookingRate; //예매율
    String openingDay;  // 개봉일
    String poster_url;
    String detail_url;

    public MainItem(int number, String title, String preview, String bookingRate, String openingDay, String poster_url, String detail_url) {
        this.number = number;
        this.title = title;
        this.preview = preview;
        this.bookingRate = bookingRate;
        this.openingDay = openingDay;
        this.poster_url = poster_url;
        this.detail_url = detail_url;
    }


    public int getNumber() {
        return number;
    }

    public String getTitle() {
        return title;
    }

    public String getPreview() {
        return preview;
    }

    public String getBookingRate() {
        return bookingRate;
    }

    public String getOpeningDay() {
        return openingDay;
    }

    public String getPoster_url() {
        return poster_url;
    }

    public String getDetail_url() {
        return detail_url;
    }

}
