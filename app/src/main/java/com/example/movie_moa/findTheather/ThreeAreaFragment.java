package com.example.movie_moa.findTheather;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_moa.R;
import com.example.movie_moa.findTheather.download.ThreeAreaListDownloader;

import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;

public class ThreeAreaFragment extends Fragment {
    String sWideareaCd, sBasareaCd;
    Context context;
    AreaAdapter areaAdapter;

    ArrayList<AreaTheatherItem> areaTheatherItems = new ArrayList<>();
    RecyclerView recyclerView;
    public static final String TAG_FRAGMENT = "threeFragment";

    public ThreeAreaFragment() {
        // Required empty public constructor
    }

    public void setNm(String sWideareaCd, String sBasareaCd) {
        this.sWideareaCd = sWideareaCd;
        this.sBasareaCd = sBasareaCd;
    }

    // 첫번째 선택하고 다음 list 결과
    public void getTwoAreaListResult(ArrayList<AreaTheatherItem> list) {
        areaTheatherItems = list;

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        areaAdapter = new AreaAdapter(getActivity(), areaTheatherItems, TAG_FRAGMENT);
        recyclerView.setAdapter(areaAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three_area, container, false);

        recyclerView = view.findViewById(R.id.ThreefindTheather_reccyclerview);
        context = container.getContext();

        ThreeAreaListDownloader downloader = new ThreeAreaListDownloader(context, sWideareaCd, sBasareaCd, ThreeAreaFragment.this);
        downloader.execute();

        return view;
    }


}