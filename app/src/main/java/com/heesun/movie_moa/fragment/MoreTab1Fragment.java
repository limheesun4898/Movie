package com.heesun.movie_moa.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.heesun.movie_moa.R;
import com.heesun.movie_moa.adapter.MoreAdapter;
import com.heesun.movie_moa.dataModel.MainItem;
import com.heesun.movie_moa.parser.Tab1Parser;

import java.util.ArrayList;

public class MoreTab1Fragment extends Fragment {
    ArrayList<MainItem> morelist1 = new ArrayList<>();
    private static final String TYPE_M0RE_TAB1 = "moreTab1";

    RecyclerView recyclerView;

    public MoreTab1Fragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Tab1Parser parser = new Tab1Parser(getActivity(), MoreTab1Fragment.this, TYPE_M0RE_TAB1);
        parser.execute();
    }

    public void getParserList(ArrayList<MainItem> list) {
        morelist1 = list;

        MoreAdapter adapter = new MoreAdapter(getActivity(), morelist1,"more1");
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_tab1, container, false);

        recyclerView = view.findViewById(R.id.more1_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        MoreAdapter adapter = new MoreAdapter(getActivity(), morelist1,"more1");
        recyclerView.setAdapter(adapter);

        return view;
    }

}