package com.example.movie_moa.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_moa.R;
import com.example.movie_moa.dataModel.MovieTimeItem;
import com.example.movie_moa.dataModel.TimeItem;

import java.util.ArrayList;

public class MovieTimeResultAdapter extends RecyclerView.Adapter<MovieTimeResultAdapter.ViewHolder> {
    ArrayList<MovieTimeItem> movieTimeItems;
    Context context;

    public MovieTimeResultAdapter(Context context, ArrayList<MovieTimeItem> movieTimeItems) {
        this.movieTimeItems = movieTimeItems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_recyclerview, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MovieTimeItem movieTimeItem = movieTimeItems.get(position);

        holder.tv_theater.setText(movieTimeItem.getTheater());

        ArrayList timeItem = movieTimeItems.get(position).getAllItemsInSection();

        TimeResultAdapter adapter = new TimeResultAdapter(context, timeItem);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context));
        holder.recyclerView.setAdapter(adapter);
    }

    @Override
    public int getItemCount() {
        return movieTimeItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_theater;
        RecyclerView recyclerView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_theater = itemView.findViewById(R.id.tv_theater);
            recyclerView = itemView.findViewById(R.id.item_result_time_recyclerview);


        }
    }
}
