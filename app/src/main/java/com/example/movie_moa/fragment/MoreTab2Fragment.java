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
import com.example.movie_moa.data.MainItem;

import java.util.ArrayList;


public class MoreTab2Fragment extends Fragment {
    private ArrayList<MainItem> morelist2 = new ArrayList<>();
    public static final String LIST = "list";
    RecyclerView recyclerView;

    public MoreTab2Fragment() {

    }

    public static MoreTab2Fragment newInstance(ArrayList<MainItem> list) {

        Bundle args = new Bundle();
        MoreTab2Fragment fragment = new MoreTab2Fragment();
        args.putParcelableArrayList(LIST, list);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            morelist2 = getArguments().getParcelableArrayList(LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_tab2, container, false);

        recyclerView = view.findViewById(R.id.more2_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(manager);
        MoreAdapter adapter = new MoreAdapter(getActivity(), morelist2,"more2");
        recyclerView.setAdapter(adapter);

        return view;
    }

}
