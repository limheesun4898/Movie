package com.heesun.movie_moa.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.heesun.movie_moa.R;
import com.heesun.movie_moa.adapter.AreaAdapter;
import com.heesun.movie_moa.dataModel.AreaTheatherItem;

import java.util.ArrayList;

public class OneAreaFragment extends Fragment {
    public static final String TAG_FRAGMENT = "oneFragment";
    RecyclerView recyclerView;

    ArrayList<AreaTheatherItem> areaTheatherItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_one_area, container, false);

        Bundle bundle = this.getArguments();
        areaTheatherItems = bundle.getParcelableArrayList("area");

        recyclerView = view.findViewById(R.id.OnefindTheather_reccyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        recyclerView.setLayoutManager(linearLayoutManager);

        AreaAdapter areaAdapter = new AreaAdapter(getActivity(), areaTheatherItems, TAG_FRAGMENT);
        recyclerView.setAdapter(areaAdapter);

        return view;
    }

}
