package com.example.movie_moa.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Point;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_moa.R;
import com.example.movie_moa.activity.MoreActivity;
import com.example.movie_moa.adapter.MainRecyclerAdapter;
import com.example.movie_moa.data.AreaTheatherItem;
import com.example.movie_moa.data.MainItem;
import com.example.movie_moa.findTheather.AreaAdapter;
import com.example.movie_moa.parser.Tab1Parser;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainTab1Fragment extends Fragment {
    RecyclerView recyclerView;
    private static final String TYPE_MAIN_TAB1 = "mainTab1";

    public MainTab1Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Tab1Parser parser = new Tab1Parser(getActivity(), MainTab1Fragment.this, TYPE_MAIN_TAB1);
        parser.execute();
//        new showMovie().execute();
    }

    public void getParserList(ArrayList<MainItem> list) {
        MainRecyclerAdapter adapter = new MainRecyclerAdapter(getActivity(), list, 1);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_tab1, container, false);

        recyclerView = view.findViewById(R.id.tab1_recyclerview);

        LinearLayoutManager horizonalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false);

        recyclerView.setLayoutManager(horizonalLayoutManager);

        return view;
    }



}
