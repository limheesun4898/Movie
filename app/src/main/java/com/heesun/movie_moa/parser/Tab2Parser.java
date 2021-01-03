package com.heesun.movie_moa.parser;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.heesun.movie_moa.dataModel.MainItem;
import com.heesun.movie_moa.fragment.MainTab2Fragment;
import com.heesun.movie_moa.fragment.MoreTab2Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Tab2Parser extends AsyncTask<Void, Void, ArrayList<MainItem>> {

    ArrayList<MainItem> tab2List = new ArrayList<>();
    Context context;
    Fragment fragment;
    String TYPE;
    private static final String TYPE_MAIN_TAB2 = "mainTab2";
    private static final String TYPE_MORE_TAB2 = "moreTab2";
    private static final String TYPE_MORE = "more";


    public Tab2Parser(Context context, Fragment fragment, String TYPE) {
        this.context = context;
        this.fragment = fragment;
        this.TYPE = TYPE;
    }

    private ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //진행다일로그 시작
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("잠시 기다려 주세요.");
        progressDialog.show();

    }

    @Override
    protected ArrayList<MainItem> doInBackground(Void... voids) {

        try {
            //개봉예정 데이터
            Document doc2 = Jsoup.connect("https://movie.daum.net/premovie/scheduled").get();
            Elements mElementDataSize1 = doc2.select("ul[class=list_movie]").select("li");

            if (TYPE.equals(TYPE_MAIN_TAB2)) {
                for (int i = 0; i < 9; i++) {
                    String title = mElementDataSize1.get(i).select("li div[class=info_tit] a").text();

                    String openingDay = mElementDataSize1.get(i).select("span.info_state").text();

                    String bookingRate = "";

                    String poster_url = mElementDataSize1.get(i).select("img").attr("src");
                    int index = poster_url.indexOf("=");
                    String result = poster_url.substring(index + 1);

                    tab2List.add(new MainItem( title,  bookingRate, openingDay, result));
                }
            } else if (TYPE.equals(TYPE_MORE_TAB2) || TYPE.equals(TYPE_MORE)) {
                int count = 1;

                for (Element element : mElementDataSize1) {
                    int number = count++;

                    String title = element.select("li div[class=info_tit] a").text();

                    String preview = element.select("li div[class=info_tit] em").text();

                    String openingDay = element.select("span.info_state").text();

                    String bookingRate = "";

                    String poster_url = element.select("img").attr("src");
                    int index = poster_url.indexOf("=");
                    String result = poster_url.substring(index + 1);

                    String detail_url = element.select("a").attr("href");
                    detail_url = "https://movie.daum.net" + detail_url;

                    tab2List.add(new MainItem(number, title, preview, bookingRate, openingDay, result, detail_url));
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return tab2List;
    }

    @Override
    protected void onPostExecute(ArrayList<MainItem> list) {

        progressDialog.dismiss();


        if (TYPE.equals(TYPE_MAIN_TAB2)) {
            ((MainTab2Fragment) fragment).getParserList(list);
        } else if (TYPE.equals(TYPE_MORE_TAB2)) {
            ((MoreTab2Fragment)fragment).getParserList(list);
        } else if (TYPE.equals(TYPE_MORE)) {

        }
    }
}
