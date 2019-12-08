package com.example.movie_moa.dataModel;

public class TimeItem {
    String scrnNm;
    String movieNm;
    String showTm;

    public TimeItem(String scrnNm, String movieNm, String showTm) {
        this.scrnNm = scrnNm;
        this.movieNm = movieNm;
        this.showTm = showTm;
    }

    public String getScrnNm() {
        return scrnNm;
    }

    public void setScrnNm(String scrnNm) {
        this.scrnNm = scrnNm;
    }

    public String getMovieNm() {
        return movieNm;
    }

    public void setMovieNm(String movieNm) {
        this.movieNm = movieNm;
    }

    public String getShowTm() {
        return showTm;
    }

    public void setShowTm(String showTm) {
        this.showTm = showTm;
    }

}