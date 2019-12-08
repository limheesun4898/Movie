package com.example.movie_moa.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_moa.R;
import com.example.movie_moa.adapter.MoreAdapter;
import com.example.movie_moa.dataModel.MainItem;
import com.example.movie_moa.parser.Tab2Parser;

import java.util.ArrayList;


public class MoreTab2Fragment extends Fragment {
    private static final String TYPE_MORE_TAB2 = "moreTab2";
    RecyclerView recyclerView;

    public MoreTab2Fragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Tab2Parser parser = new Tab2Parser(getActivity(), MoreTab2Fragment.this, TYPE_MORE_TAB2);
        parser.execute();
    }

    public void getParserList(ArrayList<MainItem> list) {

        MoreAdapter adapter = new MoreAdapter(getActivity(), list,"more2");
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_tab2, container, false);

        recyclerView = view.findViewById(R.id.more2_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);



        return view;
    }

}
