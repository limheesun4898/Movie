package com.example.movie_moa.findTheather;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_moa.R;
import com.example.movie_moa.findTheather.download.TwoAreaListDownloader;

import java.util.ArrayList;

public class TwoAreaFragment extends Fragment {
    String sWideareaCd;
    Context context;
    ArrayList<AreaTheatherItem> areaTheatherItems = new ArrayList<>();
    RecyclerView recyclerView;
    public static final String TAG_FRAGMENT = "twoFragment";

    // 첫번째 코드
    public void setNm(String sWideareaCd) {
        this.sWideareaCd = sWideareaCd;
    }

    // 첫번째 선택하고 다음 list 결과
    public void getTwoAreaListResult(ArrayList<AreaTheatherItem> list) {
        areaTheatherItems = list;

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        AreaAdapter areaAdapter = new AreaAdapter(getActivity(), areaTheatherItems, TAG_FRAGMENT, sWideareaCd);
        recyclerView.setAdapter(areaAdapter);
    }


    public TwoAreaFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_two_area, container, false);

        recyclerView = view.findViewById(R.id.TwofindTheather_reccyclerview);
        context = container.getContext();

        TwoAreaListDownloader downloader = new TwoAreaListDownloader(context, sWideareaCd, TwoAreaFragment.this);
        downloader.execute();

        return view;
    }

}
