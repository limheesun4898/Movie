package com.example.movie_moa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.movie_moa.R;
import com.example.movie_moa.adapter.MoreAdapter;
import com.example.movie_moa.dataModel.MainItem;
import com.example.movie_moa.parser.Tab1Parser;
import com.example.movie_moa.util.Util;

import java.util.ArrayList;

public class PickMovieActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context = this;

    private static final String TYPE_MORE = "more";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_movie);

        if (!Util.isNetworkConnected(context)) {
            Util.AlertDailog(context);
        } else {
            Tab1Parser parser = new Tab1Parser(context, PickMovieActivity.this, TYPE_MORE);
            parser.execute();

            recyclerView = findViewById(R.id.pick_movie_Recyclerview);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            recyclerView.setLayoutManager(manager);
        }


        ImageView btn_close = findViewById(R.id.btn_close);
        btn_close.setOnClickListener(v -> onBackPressed());

    }

    //영화 선택 리스트 받아오기
    public void getParserList(ArrayList<MainItem> list) {
        MoreAdapter adapter = new MoreAdapter(context, list, "pickMovie", listener);
        recyclerView.setAdapter(adapter);

    }

    //선택한 영화 제목 movieticketing으로 넘기기
    private MoreAdapter.setFindMovieListener listener = new MoreAdapter.setFindMovieListener() {
        @Override
        public void setFindMovieTitle(String title) {
            Intent intent = new Intent();
            intent.putExtra("title", title);
            setResult(RESULT_OK, intent);
            finish();
        }
    };


}
