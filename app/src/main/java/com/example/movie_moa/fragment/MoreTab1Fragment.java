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

import com.example.movie_moa.R;
import com.example.movie_moa.data.MainItem;

import java.util.ArrayList;

public class MoreTab1Fragment extends Fragment {
    ArrayList<MainItem> list = new ArrayList<>();

    public MoreTab1Fragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
//            list = bundle.getParcelableArrayList("arraylist");
            int test = getArguments().getInt("test");

            Log.d("debug", "onCreate: "+test);

        }else Log.d("debug", "onCreate: no");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more_tab1, container, false);


        return view;
    }

}