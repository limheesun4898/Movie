package com.example.movie_moa.movieticketing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import com.example.movie_moa.R;
import com.example.movie_moa.findTheather.DatePickDialogFragment;
import com.example.movie_moa.findTheather.FindTheaterActivity;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Locale;

public class MovieTicketingActivity extends AppCompatActivity implements View.OnClickListener, DatePickDialogFragment.setListener {
    TextView tv_theater;

    String selectDate, textDate;

    public static final int FIND_THEATER = 1; // 영화관 요청

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_ticketing);

        init();

    }

    public void init() {
        tv_theater = findViewById(R.id.tv_theater);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_movie:

                break;

            case R.id.layout_theater:
                Intent intent = new Intent(MovieTicketingActivity.this, FindTheaterActivity.class);
                startActivityForResult(intent, FIND_THEATER);

                break;
            case R.id.layout_day:
                // setDate();
                DialogFragment newFragment = new DatePickDialogFragment();
                newFragment.show(getSupportFragmentManager(), "timePicker");

                DatePickDialogFragment fragment = new DatePickDialogFragment();
                selectDate = fragment.setSelectDate();
                textDate = fragment.setTextDate();

                Log.d("debug", "onClick: "+selectDate + "d"+textDate);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case FIND_THEATER:
                if (resultCode == RESULT_OK) {
                    String cd = data.getStringExtra("cd");
                    String name = data.getStringExtra("name");
                    tv_theater.setText(name);
                }
                break;

        }

    }

    public void setSelectDate(String selectDate) {
        this.selectDate = selectDate;
    }

    public void setTextDate(String textDate) {
        this.textDate = textDate;
    }

    @Override
    public void setSelectedDateListenser(String selectedDate) {
        Log.d("debug", "setSelectedDateListenser: "+selectedDate);
    }
}
