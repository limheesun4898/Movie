package com.example.movie_moa.fragment;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_moa.R;
import com.example.movie_moa.adapter.MainRecyclerAdapter;
import com.example.movie_moa.data.MainItem;
import com.example.movie_moa.parser.Tab2Parser;

import java.io.IOException;
import java.util.ArrayList;

public class MainTab2Fragment extends Fragment {
    RecyclerView recyclerView;
    private static final String TYPE_MAIN_TAB2 = "mainTab2";


    public MainTab2Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Tab2Parser parser = new Tab2Parser(getActivity(), MainTab2Fragment.this, TYPE_MAIN_TAB2);
        parser.execute();

    }

    public void getParserList(ArrayList<MainItem> list) {

        MainRecyclerAdapter adapter = new MainRecyclerAdapter(getActivity(), list, 2);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_tab2, container, false);

        recyclerView = view.findViewById(R.id.tab2_recyclerview);
        LinearLayoutManager horizonalLayoutManager
                = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL, false);

        recyclerView.setLayoutManager(horizonalLayoutManager);

        return view;
    }


}
