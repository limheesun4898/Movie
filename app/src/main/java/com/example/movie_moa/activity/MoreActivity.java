package com.example.movie_moa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.TableLayout;

import com.example.movie_moa.R;
import com.example.movie_moa.adapter.MainPagerAdapter;
import com.example.movie_moa.adapter.MorePagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MoreActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private TabLayout moreTablayout;
    private ViewPager morePager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

    }

    public void init() {
        morePager = findViewById(R.id.more_viewpager);
        moreTablayout = findViewById(R.id.more_TabLayout);
        MorePagerAdapter adapter = new MorePagerAdapter(getSupportFragmentManager(), moreTablayout.getTabCount());
        morePager.setAdapter(adapter);
        morePager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(moreTablayout));
        moreTablayout.setupWithViewPager(morePager);
        moreTablayout.setOnTabSelectedListener(this);
    }


    //tablayout ontabselectedlistener
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        morePager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
