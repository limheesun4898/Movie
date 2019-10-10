package com.example.movie_moa.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.movie_moa.fragment.MoreTab1Fragment;
import com.example.movie_moa.fragment.MoreTab2Fragment;

public class MorePagerAdapter extends FragmentStatePagerAdapter {
    private int tabCount;
    private String tabTitle[] = { "예매","상영 예정"};

    public MorePagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                MoreTab1Fragment moreTab1Fragment = new MoreTab1Fragment();
                return moreTab1Fragment;
            case 1:
                MoreTab2Fragment moreTab2Fragment = new MoreTab2Fragment();
                return moreTab2Fragment;
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
