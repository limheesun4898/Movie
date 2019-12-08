package com.example.movie_moa.dataModel;

import android.os.Parcel;
import android.os.Parcelable;


public class MainItem implements Parcelable {
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

    public MainItem(String title,String bookingRate, String openingDay, String poster_url) {
        this.title = title;
        this.bookingRate = bookingRate;
        this.openingDay = openingDay;
        this.poster_url = poster_url;
    }

    public MainItem(String title,String bookingRate, String poster_url) {
        this.title = title;
        this.bookingRate = bookingRate;
        this.poster_url = poster_url;
    }


    protected MainItem(Parcel in) {
        number = in.readInt();
        title = in.readString();
        preview = in.readString();
        bookingRate = in.readString();
        openingDay = in.readString();
        poster_url = in.readString();
        detail_url = in.readString();
    }

    public static final Creator<MainItem> CREATOR = new Creator<MainItem>() {
        @Override
        public MainItem createFromParcel(Parcel in) {
            return new MainItem(in);
        }

        @Override
        public MainItem[] newArray(int size) {
            return new MainItem[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(title);
        dest.writeString(preview);
        dest.writeString(bookingRate);
        dest.writeString(openingDay);
        dest.writeString(poster_url);
        dest.writeString(detail_url);
    }

}
