package com.example.movie_moa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.movie_moa.R;
import com.example.movie_moa.data.MainItem;
import com.example.movie_moa.fragment.MainTab1Fragment;
import com.example.movie_moa.fragment.MainTab2Fragment;
import com.example.movie_moa.fragment.MoreTab1Fragment;
import com.example.movie_moa.fragment.MoreTab2Fragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MoreActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener, View.OnClickListener {
    private TabLayout moreTablayout;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    private MoreTab1Fragment moreTab1Fragment;
    private MoreTab2Fragment moreTab2Fragment;

    ArrayList<MainItem> moreList1 = new ArrayList<>();
    ArrayList<MainItem> moreList2 = new ArrayList<>();

    private static final String fragmentTag1 = "Tab1";
    private static final String fragmentTag2 = "Tab2";

    Intent intent;
    String tabPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        init();
    }

    public void init() {

        ImageButton btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(this);

        moreTab1Fragment = new MoreTab1Fragment();
        moreTab2Fragment = new MoreTab2Fragment();

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        moreTablayout = findViewById(R.id.more_TabLayout);
        moreTablayout.setOnTabSelectedListener(this);

        intent = getIntent();
        tabPosition = intent.getExtras().getString("tab");

        moreList1 = intent.getParcelableArrayListExtra("movies1");
        moreList2 = intent.getParcelableArrayListExtra("movies2");

        if (tabPosition.equals(fragmentTag1)) {

            transaction.replace(R.id.more_fragment_container, MoreTab1Fragment.newInstance(moreList1), fragmentTag1).commitAllowingStateLoss();


        } else if (tabPosition.equals(fragmentTag2)) {
            int index = 1;
            TabLayout.Tab tab1 = moreTablayout.getTabAt(index);
            tab1.select();

//            transaction.replace(R.id.more_fragment_container, MoreTab2Fragment.newInstance(moreList2), fragmentTag2).commitAllowingStateLoss();
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_back:
                onBackPressed();
                break;
        }
    }

    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return MoreTab1Fragment.newInstance(moreList1);
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return MoreTab2Fragment.newInstance(moreList2);
            default:
                return null;
        }
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

//        show / hide 통해서 fragment replace 할때 oncreate 다시 작동하는 것을 막음.
        switch (position) {
            case 0:
                if (fragmentManager.findFragmentByTag(fragmentTag1) != null) {
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(fragmentTag1)).commit();
                } else
                    fragmentManager.beginTransaction().add(R.id.more_fragment_container, moreTab1Fragment, fragmentTag1).commit();

                if (fragmentManager.findFragmentByTag(fragmentTag2) != null) {
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(fragmentTag2)).commit();
                }
                break;

            case 1:
                if (fragmentManager.findFragmentByTag(fragmentTag2) != null) {
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag(fragmentTag2)).commit();
                } else
                    fragmentManager.beginTransaction().add(R.id.more_fragment_container, moreTab1Fragment, fragmentTag2).commit();

                if (fragmentManager.findFragmentByTag(fragmentTag1) != null) {
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(fragmentTag1)).commit();
                }
                break;
        }
    }

}
