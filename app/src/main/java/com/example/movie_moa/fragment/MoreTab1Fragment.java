package com.example.movie_moa.fragment;

import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_moa.R;
import com.example.movie_moa.activity.MoreActivity;
import com.example.movie_moa.adapter.MoreAdapter;
import com.example.movie_moa.data.MainItem;

import java.util.ArrayList;

public class MoreTab1Fragment extends Fragment {
    ArrayList<MainItem> morelist1 = new ArrayList<>();
    public static final String LIST = "list";
    RecyclerView recyclerView;

    public MoreTab1Fragment() {

    }

    public static MoreTab1Fragment newInstance(ArrayList<MainItem> list) {
        Bundle args = new Bundle();
        MoreTab1Fragment fragment = new MoreTab1Fragment();
        args.putParcelableArrayList(LIST, list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) {
            morelist1 = getArguments().getParcelableArrayList(LIST);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_tab1, container, false);

        recyclerView = view.findViewById(R.id.more1_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        MoreAdapter adapter = new MoreAdapter(getActivity(), morelist1);
        recyclerView.setAdapter(adapter);

        return view;
    }

}