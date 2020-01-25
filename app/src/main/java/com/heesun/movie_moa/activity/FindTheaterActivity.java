package com.heesun.movie_moa.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.heesun.movie_moa.R;
import com.heesun.movie_moa.dataModel.AreaTheatherItem;
import com.heesun.movie_moa.fragment.OneAreaFragment;
import com.heesun.movie_moa.util.Util;

import java.util.ArrayList;

public class FindTheaterActivity extends AppCompatActivity {
    private OneAreaFragment oneAreaFragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    Context context = this;
    Toolbar toolbar;

    ArrayList<AreaTheatherItem> areaTheatherItems = new ArrayList<>(); // 지역 선택 리스트
    String[] area_codelist = getResources().getStringArray(R.array.area_code);
    String[] area_citylist = getResources().getStringArray(R.array.area_city);

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

        for (int i = 0; i < area_citylist.length; i++) {
            String area_code = area_codelist[i];
            String area_city = area_citylist[i];
            areaTheatherItems.add(new AreaTheatherItem(area_code, area_city));
        }

        oneAreaFragment = new OneAreaFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("area",areaTheatherItems);
        oneAreaFragment.setArguments(bundle);

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.findTheather_container, oneAreaFragment).commitAllowingStateLoss();

        ImageView btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(v -> onBackPressed());

    }


}
