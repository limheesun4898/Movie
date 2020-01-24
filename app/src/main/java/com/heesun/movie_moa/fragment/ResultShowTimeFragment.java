package com.heesun.movie_moa.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.heesun.movie_moa.R;
import com.heesun.movie_moa.adapter.MovieTimeResultAdapter;
import com.heesun.movie_moa.dataModel.MovieTimeItem;

import java.util.ArrayList;

public class ResultShowTimeFragment extends Fragment {
    Context context = getActivity();
    RecyclerView recyclerView;

    public ResultShowTimeFragment() {
        // Required empty public constructor
    }

    public void getParserList(ArrayList<MovieTimeItem> list) {

        MovieTimeResultAdapter adapter = new MovieTimeResultAdapter(context, list);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result_show_time, container, false);

        recyclerView = view.findViewById(R.id.result_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        recyclerView.setLayoutManager(manager);

        return view;

    }

}
