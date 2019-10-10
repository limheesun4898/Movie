package com.example.movie_moa.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.movie_moa.R;
import com.example.movie_moa.adapter.MainPagerAdapter;
import com.example.movie_moa.data.MainItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout mainTablayout;
    private ViewPager mainpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
    }

    public void init() {
        mainpager = findViewById(R.id.main_viewpager);
        mainTablayout = findViewById(R.id.main_tablayout);
        MainPagerAdapter adapter = new MainPagerAdapter(getSupportFragmentManager(), mainTablayout.getTabCount());
        mainpager.setAdapter(adapter);
        mainpager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mainTablayout));
        mainTablayout.setupWithViewPager(mainpager);
        mainTablayout.setOnTabSelectedListener(this);

    }

    //tablayout ontabselectedlistener
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mainpager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
