package com.example.movie_moa.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.movie_moa.R;
import com.example.movie_moa.data.MainItem;
import com.example.movie_moa.fragment.MainTab1Fragment;
import com.example.movie_moa.fragment.MainTab2Fragment;
import com.example.movie_moa.movieticketing.MovieTicketingActivity;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private MainTab1Fragment mainTab1Fragment;
    private MainTab2Fragment mainTab2Fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private static final String fragmentTag1 = "Tab1";
    private static final String fragmentTag2 = "Tab2";

    private int page_number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();

    }

    public void init() {
        mainTab1Fragment = new MainTab1Fragment();
        mainTab2Fragment = new MainTab2Fragment();

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        transaction.replace(R.id.main_fragment_container, mainTab1Fragment, fragmentTag1).commitAllowingStateLoss();

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setOnTabSelectedListener(this);

        Button btn = findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, MovieTicketingActivity.class));

            }
        });
    }

    //tablayout ontabselectedlistener
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        int position = tab.getPosition();
        callFragment(position);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    private void callFragment(int position) {

        transaction = getSupportFragmentManager().beginTransaction();

//      show / hide 통해서 fragment replace 할때 oncreate 다시 작동하는 것을 막음.
        switch (position) {
            case 0:
                if (fragmentManager.findFragmentByTag(fragmentTag1) != null) {
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(fragmentTag1)).commit();
                } else
                    fragmentManager.beginTransaction().add(R.id.main_fragment_container, mainTab1Fragment, fragmentTag1).commit();

                if (fragmentManager.findFragmentByTag(fragmentTag2) != null) {
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(fragmentTag2)).commit();
                }
                page_number = 0;
                break;

            case 1:

                if (fragmentManager.findFragmentByTag(fragmentTag2) != null) {
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(fragmentTag2)).commit();
                } else
                    fragmentManager.beginTransaction().add(R.id.main_fragment_container, mainTab2Fragment, fragmentTag2).commit();

                if (fragmentManager.findFragmentByTag(fragmentTag1) != null) {
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(fragmentTag1)).commit();
                }
                page_number = 1;
                break;
        }
    }


    public void btn_moreActivity(View view) {
        Intent intent = new Intent(MainActivity.this, MoreActivity.class);
        if (page_number == 0) {
            intent.putExtra("tab", fragmentTag1);
            intent.putParcelableArrayListExtra("movies1", mainTab1Fragment.getMovieList());
        } else if (page_number == 1) {
            intent.putExtra("tab", fragmentTag2);
            intent.putParcelableArrayListExtra("movies2", mainTab2Fragment.getMovieList());
        }

        startActivity(intent);
    }

}
