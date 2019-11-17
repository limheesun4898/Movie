package com.example.movie_moa.findTheather;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.example.movie_moa.R;
import com.example.movie_moa.adapter.MoreAdapter;
import com.example.movie_moa.data.MainItem;
import com.example.movie_moa.movieticketing.MovieTicketingActivity;

import java.util.ArrayList;

public class PickMovieActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context;

    ArrayList<MainItem> moiveList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pick_movie);

        Intent intent = getIntent();
        moiveList = intent.getParcelableArrayListExtra("movieList");

        recyclerView = findViewById(R.id.pick_movie_Recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);
        MoreAdapter adapter = new MoreAdapter(getApplicationContext(),moiveList,"pickMovie", listener);
        recyclerView.setAdapter(adapter);

        ImageButton btn_close = findViewById(R.id.btn_close);
        btn_close.setOnClickListener(v -> onBackPressed());

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
