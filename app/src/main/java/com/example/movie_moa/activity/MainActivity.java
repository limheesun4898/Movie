package com.example.movie_moa.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.movie_moa.R;
import com.example.movie_moa.fragment.MainTab1Fragment;
import com.example.movie_moa.fragment.MainTab2Fragment;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    private TabLayout tabLayout;
    private MainTab1Fragment mainTab1Fragment;
    private MainTab2Fragment mainTab2Fragment;
    private FragmentManager fragmentManager;
    private FragmentTransaction transaction;
    String fragmentTag = "Tab1";

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

        transaction.replace(R.id.main_fragment_container, mainTab1Fragment, fragmentTag).commitAllowingStateLoss();

        tabLayout = findViewById(R.id.tablayout);
        tabLayout.setOnTabSelectedListener(this);
        tabLayout.addTab(tabLayout.newTab().setText(" "));
        tabLayout.addTab(tabLayout.newTab().setText(" "));
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
                if (fragmentManager.findFragmentByTag("Tab1") != null) {
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Tab1")).commit();
                } else
                    fragmentManager.beginTransaction().add(R.id.main_fragment_container, mainTab1Fragment, "Tab1").commit();

                if (fragmentManager.findFragmentByTag("Tab2") != null) {
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Tab2")).commit();
                }
                break;

            case 1:

                if (fragmentManager.findFragmentByTag("Tab2") != null) {
                    fragmentManager.beginTransaction().show(fragmentManager.findFragmentByTag("Tab2")).commit();
                } else
                    fragmentManager.beginTransaction().add(R.id.main_fragment_container, mainTab2Fragment, "Tab2").commit();

                if (fragmentManager.findFragmentByTag("Tab1") != null) {
                    fragmentManager.beginTransaction().hide(fragmentManager.findFragmentByTag("Tab1")).commit();
                }
                break;
        }
    }


}
