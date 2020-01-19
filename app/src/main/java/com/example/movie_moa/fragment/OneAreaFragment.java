package com.example.movie_moa.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_moa.R;
import com.example.movie_moa.adapter.AreaAdapter;
import com.example.movie_moa.dataModel.AreaTheatherItem;

import java.util.ArrayList;

public class OneAreaFragment extends Fragment {
    public static final String TAG_FRAGMENT = "oneFragment";
    RecyclerView recyclerView;
    ArrayList<AreaTheatherItem> areaTheatherItems = new ArrayList<AreaTheatherItem>() {
        {
            add(new AreaTheatherItem("0105001", "서울"));
            add(new AreaTheatherItem("0105002", "경기"));
            add(new AreaTheatherItem("0105003", "강원"));
            add(new AreaTheatherItem("0105004", "충북"));
            add(new AreaTheatherItem("0105005", "충남"));
            add(new AreaTheatherItem("0105006", "경북"));
            add(new AreaTheatherItem("0105007", "경남"));
            add(new AreaTheatherItem("0105008", "전북"));
            add(new AreaTheatherItem("0105009", "전남"));
            add(new AreaTheatherItem("0105010", "제주"));
            add(new AreaTheatherItem("0105011", "부산"));
            add(new AreaTheatherItem("0105012", "대구"));
            add(new AreaTheatherItem("0105013", "대전"));
            add(new AreaTheatherItem("0105014", "울산"));
            add(new AreaTheatherItem("0105015", "인천"));
            add(new AreaTheatherItem("0105016", "광주"));
            add(new AreaTheatherItem("0105017", "세종"));
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one_area, container, false);

        recyclerView = view.findViewById(R.id.OnefindTheather_reccyclerview);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), 2);

        recyclerView.setLayoutManager(gridLayoutManager);

        AreaAdapter areaAdapter = new AreaAdapter(getActivity(), areaTheatherItems, TAG_FRAGMENT);
        recyclerView.setAdapter(areaAdapter);

        return view;
    }

}
