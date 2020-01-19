package com.example.movie_moa.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_moa.R;
import com.example.movie_moa.adapter.MovieTimeResultAdapter;
import com.example.movie_moa.dataModel.AreaTheatherItem;
import com.example.movie_moa.dataModel.MovieTimeItem;
import com.example.movie_moa.dialogFragment.PickDateDialogFragment;
import com.example.movie_moa.fragment.MainTab1Fragment;
import com.example.movie_moa.fragment.MainTab2Fragment;
import com.example.movie_moa.fragment.PickTheaterFragment;
import com.example.movie_moa.fragment.ResultShowTimeFragment;
import com.example.movie_moa.parser.FindMovieTimeParser;
import com.example.movie_moa.util.Util;

import org.jsoup.helper.StringUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieTicketingActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_theater, tv_date, tv_title;

    SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd"); // 현재 날짜 - 변수
    SimpleDateFormat format2 = new SimpleDateFormat("MM월\ndd일"); // 현재 날짜 - 표시용
    SimpleDateFormat format3 = new SimpleDateFormat("HHmm"); //현재 시간
    Date time = new Date();

    String movieTitle; // 영화 제목
    String showDt = format1.format(time); // 오늘 날짜 - 선택하면 선택 날짜로 바
    String todayTime = format3.format(time); // 선택 시간낌
    String text_showDt = format2.format(time);


    public static final int FIND_THEATER = 1; // 영화관 요청
    public static final int FIND_MOVIE = 2; // 영화 선택 요청

    RecyclerView recyclerView;
    Context context = this;
    ArrayList<AreaTheatherItem> theaterCheckList = new ArrayList<>(); //영화관 선택 리스트

    private PickTheaterFragment pickTheaterFragment;
    private ResultShowTimeFragment resultShowTimeFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_ticketing);

        Intent intent = getIntent();
        movieTitle = intent.getStringExtra("title");

        if (!Util.isNetworkConnected(context)) {
            Util.AlertDailog(context);
        } else {
            init();
        }

    }

    public void init() {
        tv_theater = findViewById(R.id.tv_theater);
        tv_date = findViewById(R.id.tv_date);
        tv_title = findViewById(R.id.tv_title);

        tv_title.setText(movieTitle);
        tv_date.setText(text_showDt);


        pickTheaterFragment = new PickTheaterFragment();
        resultShowTimeFragment = new ResultShowTimeFragment();

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        //처음엔 영화관 선택 프래그먼트 띄워줌.
        transaction.replace(R.id.ticketing_fragment_container, pickTheaterFragment).commit();

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
                Click_movie();

                break;
            case R.id.layout_day:
                DialogFragment newFragment = new PickDateDialogFragment(mDataPickListener);
                newFragment.show(getSupportFragmentManager(), "timePicker");

                break;

//            case R.id.btn_result:
//                FindMovieTimeParser parser = new FindMovieTimeParser(context, movieTitle, showDt, todayTime, theaterCheckList);
//                parser.execute();
//
//                break;
        }

    }

    public void Click_movie() {
        Intent intent = new Intent(MovieTicketingActivity.this, FindTheaterActivity.class);
        startActivityForResult(intent, FIND_THEATER);
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.ticketing_fragment_container, fragment).commit();
    }

    //날짜 다이얼로그에서 선택한 날짜 가져오기
    private PickDateDialogFragment.setListener mDataPickListener = new PickDateDialogFragment.setListener() {
        @Override
        public void setSelectedDateListener(String selectedDate, String textDate, String time) {
            showDt = selectedDate;
            tv_date.setText(textDate);
            todayTime = time;

            if (theaterCheckList.size() == 0) {
                Log.d("debug", "onActivityResult: " + "선택 영화관 없");
            } else {
               setParserList();
            }
        }
    };

    //파싱 최종 리스트
    public void getParserList(ArrayList<MovieTimeItem> list) {
        Log.d("debug", "getParserList: " + list.toString());
        MovieTimeResultAdapter adapter = new MovieTimeResultAdapter(context, list);
        recyclerView.setAdapter(adapter);
    }

    public void setParserList(){
        FindMovieTimeParser parser = new FindMovieTimeParser(context, movieTitle, showDt, todayTime, theaterCheckList, resultShowTimeFragment);
        parser.execute();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FIND_MOVIE:
                if (resultCode == RESULT_OK) {
                    movieTitle = data.getStringExtra("title");
                    tv_title.setText(movieTitle);

                    if (theaterCheckList.size() == 0) {
                        Log.d("debug", "onActivityResult: " + "선택 영화관 없");
                    } else {
                       setParserList();
                    }
                }
                break;
            case FIND_THEATER:
                if (resultCode == RESULT_OK) {
                    theaterCheckList = data.getParcelableArrayListExtra("checklist");

                    List<String> list = new ArrayList<>();

                    for (int i = 0; i < theaterCheckList.size(); i++) {
                        list.add(theaterCheckList.get(i).getCdNm());
                    }

                    String theater = StringUtil.join(list, ", ");
                    tv_theater.setText(theater);

                    replaceFragment(resultShowTimeFragment);

                    setParserList();

                }
                break;

        }

    }

}
