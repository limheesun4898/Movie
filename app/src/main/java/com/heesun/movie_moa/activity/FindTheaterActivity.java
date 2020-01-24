package com.heesun.movie_moa.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.heesun.movie_moa.R;
import com.heesun.movie_moa.fragment.OneAreaFragment;
import com.heesun.movie_moa.util.Util;

public class FindTheaterActivity extends AppCompatActivity {
    private OneAreaFragment oneAreaFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    Context context = this;
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_theater);

        if (!Util.isNetworkConnected(context)) {
            Util.AlertDailog(context);
        } else {
            init();
        }

    }

    public void init() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setTitle("");

        oneAreaFragment = new OneAreaFragment();

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.findTheather_container, oneAreaFragment).commitAllowingStateLoss();

        ImageView btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(v -> onBackPressed());

    }


}
