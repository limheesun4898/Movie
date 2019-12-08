package com.example.movie_moa.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_moa.R;
import com.example.movie_moa.adapter.MovieTimeResultAdapter;
import com.example.movie_moa.dataModel.MovieTimeItem;
import com.example.movie_moa.dialogFragment.PickDateDialogFragment;
import com.example.movie_moa.parser.FindMovieTimeParser;
import com.example.movie_moa.util.Util;

import java.util.ArrayList;

public class MovieTicketingActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_theater, tv_date, tv_title;

    String movieTitle, showDt, theaCd, theaterNm, todayTime;

    public static final int FIND_THEATER = 1; // 영화관 요청
    public static final int FIND_MOVIE = 2; // 영화 선택 요청

    RecyclerView recyclerView;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_ticketing);

        Intent intent = getIntent();
        movieTitle = intent.getStringExtra("title");

        if( !Util.isNetworkConnected(context) ){
            Util.AlertDailog(context);
        }else{
            init();
        }

    }

    public void init() {
        tv_theater = findViewById(R.id.tv_theater);
        tv_date = findViewById(R.id.tv_date);
        tv_title = findViewById(R.id.tv_title);

        recyclerView = findViewById(R.id.result_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        tv_title.setText(movieTitle);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;

            case R.id.layout_movie:

                Intent intent1 = new Intent(MovieTicketingActivity.this, PickMovieActivity.class);
                startActivityForResult(intent1, FIND_MOVIE);

                break;

            case R.id.layout_theater:
                Intent intent = new Intent(MovieTicketingActivity.this, FindTheaterActivity.class);
                startActivityForResult(intent, FIND_THEATER);

                break;
            case R.id.layout_day:

                DialogFragment newFragment = new PickDateDialogFragment(mDataPickListener);
                newFragment.show(getSupportFragmentManager(), "timePicker");

                break;

            case R.id.btn_result:
                FindMovieTimeParser parser = new FindMovieTimeParser(context,theaCd, showDt, theaterNm, movieTitle, todayTime);
                parser.execute();

                break;
        }

    }

    //날짜 다이얼로그에서 선택한 날짜 가져오기
    private PickDateDialogFragment.setListener mDataPickListener = new PickDateDialogFragment.setListener() {
        @Override
        public void setSelectedDateListener(String selectedDate, String textDate, String time) {
            showDt = selectedDate;
            tv_date.setText(textDate);
            todayTime = time;
        }
    };

    //파싱 최종 리스트
    public void getParserList(ArrayList<MovieTimeItem> list) {
        Log.d("debug", "getParserList: " + list.toString());
        MovieTimeResultAdapter adapter = new MovieTimeResultAdapter(context, list);
        recyclerView.setAdapter(adapter);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FIND_MOVIE:
                if (resultCode == RESULT_OK) {
                    movieTitle = data.getStringExtra("title");
                    tv_title.setText(movieTitle);
                }
                break;
            case FIND_THEATER:
                if (resultCode == RESULT_OK) {
                    theaCd = data.getStringExtra("cd");
                    theaterNm = data.getStringExtra("name");
                    tv_theater.setText(theaterNm);

                }
                break;

        }

    }

}
