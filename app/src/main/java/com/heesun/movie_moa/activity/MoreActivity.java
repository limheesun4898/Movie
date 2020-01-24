package com.heesun.movie_moa.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import com.heesun.movie_moa.R;
import com.heesun.movie_moa.fragment.MoreTab1Fragment;
import com.heesun.movie_moa.fragment.MoreTab2Fragment;
import com.heesun.movie_moa.util.Util;
import com.google.android.material.tabs.TabLayout;

public class MoreActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;

    private TabLayout moreTablayout;
    private MoreTab1Fragment moreTab1Fragment;
    private MoreTab2Fragment moreTab2Fragment;

    private static final String fragmentTag1 = "Tab1";
    private static final String fragmentTag2 = "Tab2";

    Intent intent;
    String tabPosition;

    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        if( !Util.isNetworkConnected(context) ){
            Util.AlertDailog(context);
        }else{
            init();
        }

    }

    public void init() {

        ImageButton btn_back = findViewById(R.id.btn_back);
        btn_back.setOnClickListener(v -> onBackPressed());

        moreTab1Fragment = new MoreTab1Fragment();
        moreTab2Fragment = new MoreTab2Fragment();

        fragmentManager = getSupportFragmentManager();
        transaction = fragmentManager.beginTransaction();

        moreTablayout = findViewById(R.id.more_TabLayout);
        moreTablayout.setOnTabSelectedListener(this);

        intent = getIntent();
        tabPosition = intent.getExtras().getString("tab");

        if (tabPosition.equals(fragmentTag1)) {

            transaction.replace(R.id.more_fragment_container, moreTab1Fragment, fragmentTag1).commitAllowingStateLoss();

        } else if (tabPosition.equals(fragmentTag2)) {
            int index = 1;
            TabLayout.Tab tab1 = moreTablayout.getTabAt(index);
            tab1.select();

            transaction.replace(R.id.more_fragment_container, moreTab2Fragment, fragmentTag2).commitAllowingStateLoss();

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
                    fragmentManager.beginTransaction().add(R.id.more_fragment_container, moreTab2Fragment, fragmentTag2).commit();

                if (fragmentManager.findFragmentByTag(fragmentTag1) != null) {
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag(fragmentTag1)).commit();
                }
                break;
        }
    }

}
