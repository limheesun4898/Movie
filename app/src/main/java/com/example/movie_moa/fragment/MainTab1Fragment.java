package com.example.movie_moa.fragment;

import android.app.ProgressDialog;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_moa.R;
import com.example.movie_moa.adapter.MainRecyclerAdapter;
import com.example.movie_moa.data.MainItem;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainTab1Fragment extends Fragment {
    ArrayList<MainItem> tab1list = new ArrayList<>();
    RecyclerView recyclerView;

    public MainTab1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new showMovie().execute();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_tab1, container, false);

        recyclerView = view.findViewById(R.id.tab1_recyclerview);

        LinearLayoutManager horizonalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false);

        recyclerView.setLayoutManager(horizonalLayoutManager);

        return view;
    }

    public class showMovie extends AsyncTask<Void, Void, Void> {
        //진행바표시
        private ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //진행다일로그 시작
            progressDialog = new ProgressDialog(getContext());
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("잠시 기다려 주세요.");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                //예매순 데이터
                Document doc1 = Jsoup.connect("https://movie.daum.net/premovie/released").get();
                Elements mElementDataSize1 = doc1.select("ul[class=list_movie #movie]").select("li");
                int count = 1;

                for (Element element : mElementDataSize1) {

                    int number = count++;

                    String title = element.select("li div[class=info_tit] a").text();

                    String preview = element.select("li div[class=info_tit] em").text();

                    String description = element.select("span.info_state").text();
                    int index1 = description.indexOf("・");
                    String openingDay = description.substring(0,index1);
                    String bookingRate = description.substring(index1 + 1);

                    String poster_url = element.select("img").attr("src");
                    int index2 = poster_url.indexOf("=");
                    String result = poster_url.substring(index2 + 1);

                    String detail_url = element.select("a").attr("href");
                    detail_url = "https://movie.daum.net" + detail_url;

                    tab1list.add(new MainItem(number,title,preview ,bookingRate,openingDay, result, detail_url));
//                    Log.d("debug", "number : "+number+" title : "+title+" preview : "+preview+" bookingRate : "+bookingRate+
//                            " openingDay : "+openingDay+ " poster_url : " +result+" detail_url : "+detail_url);
  }

            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            MainRecyclerAdapter adapter = new MainRecyclerAdapter(getActivity(), tab1list, 1);
            recyclerView.setAdapter(adapter);

            progressDialog.dismiss();
        }
    }


}
