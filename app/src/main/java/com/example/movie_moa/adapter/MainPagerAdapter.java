package com.example.movie_moa.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.movie_moa.R;
import com.example.movie_moa.fragment.MainTab1Fragment;
import com.example.movie_moa.fragment.MainTab2Fragment;

public class MainPagerAdapter extends FragmentStatePagerAdapter {

    private int tabCount;
    private String tabTitle[] = { "예매순","상영 예정"};

    public MainPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MainTab1Fragment mainTab1Fragment = new MainTab1Fragment();
                return mainTab1Fragment;
            case 1:
                MainTab2Fragment mainTab2Fragment = new MainTab2Fragment();
                return mainTab2Fragment;
            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitle[position];
    }
}
