package com.example.movie_moa.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.movie_moa.R;
import com.example.movie_moa.fragment.OneAreaFragment;
import com.example.movie_moa.util.Util;

public class FindTheaterActivity extends AppCompatActivity {
    private OneAreaFragment oneAreaFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_theather);

        if( !Util.isNetworkConnected(context) ){
            Util.AlertDailog(context);
        }else{
            init();
        }

    }

    public void init() {
        oneAreaFragment = new OneAreaFragment();

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.findTheather_container, oneAreaFragment).commitAllowingStateLoss();

        ImageView btn_back = findViewById(R.id.btn_back);

        btn_back.setOnClickListener(v-> onBackPressed());
    }


}
