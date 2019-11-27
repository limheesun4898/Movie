package com.example.movie_moa.parser;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.movie_moa.data.MainItem;
import com.example.movie_moa.activity.PickMovieActivity;
import com.example.movie_moa.fragment.MainTab1Fragment;
import com.example.movie_moa.fragment.MoreTab1Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Tab1Parser extends AsyncTask<Void, Void, ArrayList<MainItem>> {

    ArrayList<MainItem> tab1list = new ArrayList<>();
    Context context;
    Fragment fragment;
    String TYPE;
    Activity activity;
    private static final String TYPE_MAIN_TAB1 = "mainTab1";
    private static final String TYPE_MORE_TAB1 = "moreTab1";
    private static final String TYPE_MORE = "more";

    private ProgressDialog progressDialog;

    public Tab1Parser(Context context, Fragment fragment, String TAPE) {
        this.context = context;
        this.fragment = fragment;
        this.TYPE = TAPE;
    }

    public Tab1Parser(Context context, Activity activity, String TAPE) {
        this.context = context;
        this.activity = activity;
        this.TYPE = TAPE;
    }

    @Override
    protected void onPreExecute() {

        //진행다일로그 시작
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("잠시 기다려 주세요.");
        progressDialog.show();

        super.onPreExecute();
    }

    @Override
    protected ArrayList<MainItem> doInBackground(Void... voids) {
        try {
            //예매순 데이터
            Document doc1 = Jsoup.connect("https://movie.daum.net/premovie/released").get();
            Elements mElementDataSize1 = doc1.select("ul[class=list_movie #movie]").select("li");

            //탭1에서는 8개까지만 보이고, 제목, 포스터, 예매율만 보임
            if (TYPE.equals(TYPE_MAIN_TAB1)) {
                for (int i = 0; i < 9; i++) {
                    String title = mElementDataSize1.get(i).select("li div[class=info_tit] a").text();

                    String description = mElementDataSize1.get(i).select("span.info_state").text();
                    int index1 = description.indexOf("・");
                    String bookingRate = description.substring(index1 + 1);

                    String poster_url = mElementDataSize1.get(i).select("img").attr("src");
                    int index2 = poster_url.indexOf("=");
                    String posterResult = poster_url.substring(index2 + 1);

                    tab1list.add(new MainItem(title, bookingRate, posterResult));
                }
                //탭2에서는 20개까지 보이고, 더 자세한 내용을 크롤링
            } else if (TYPE.equals(TYPE_MORE_TAB1) || TYPE.equals(TYPE_MORE)) {
                int count = 1;

                for (Element element : mElementDataSize1) {

                    int number = count++;

                    String title = element.select("li div[class=info_tit] a").text();

                    String preview = element.select("li div[class=info_tit] em").text();

                    String description = element.select("span.info_state").text();
                    int index1 = description.indexOf("・");
                    String openingDay = description.substring(0, index1);
                    String bookingRate = description.substring(index1 + 1);

                    String poster_url = element.select("img").attr("src");
                    int index2 = poster_url.indexOf("=");
                    String result = poster_url.substring(index2 + 1);

                    String detail_url = element.select("a").attr("href");
                    detail_url = "https://movie.daum.net" + detail_url;

                    tab1list.add(new MainItem(number, title, preview, bookingRate, openingDay, result, detail_url));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab1list;
    }

    @Override
    protected void onPostExecute(ArrayList<MainItem> list) {
        progressDialog.dismiss();

        if (TYPE.equals(TYPE_MAIN_TAB1)) {
            ((MainTab1Fragment) fragment).getParserList(list);
        } else if (TYPE.equals(TYPE_MORE_TAB1)) {
            ((MoreTab1Fragment) fragment).getParserList(list);
        } else if (TYPE.equals(TYPE_MORE)) {
            ((PickMovieActivity) activity).getParserList(list);
        }

    }


}
