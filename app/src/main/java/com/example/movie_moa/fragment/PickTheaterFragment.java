package com.example.movie_moa.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;

import com.example.movie_moa.R;
import com.example.movie_moa.activity.FindTheaterActivity;
import com.example.movie_moa.activity.MovieTicketingActivity;

import static com.example.movie_moa.activity.MovieTicketingActivity.FIND_THEATER;


public class PickTheaterFragment extends Fragment {
    Context context = getActivity();

    public PickTheaterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_pick_theater, container, false);

        LinearLayout layout_pick_theater;
        layout_pick_theater = view.findViewById(R.id.layout_pick_theater);

        layout_pick_theater.setOnClickListener((View v)->{
            ((MovieTicketingActivity)getActivity()).Click_movie();
        });

        return view;
    }

}
