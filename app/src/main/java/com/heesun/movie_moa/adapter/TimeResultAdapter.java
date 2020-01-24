package com.heesun.movie_moa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.heesun.movie_moa.R;
import com.heesun.movie_moa.dataModel.TimeItem;

import java.util.ArrayList;

public class TimeResultAdapter extends RecyclerView.Adapter<TimeResultAdapter.ViewHolder> {
    ArrayList<TimeItem> timeItems;
    Context context;


    public TimeResultAdapter(Context context, ArrayList<TimeItem> timeItem) {
        this.context = context;
        this.timeItems = timeItem;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_time_recyclerview, parent, false);
        return new TimeResultAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        TimeItem item = timeItems.get(position);

        holder.tv_title.setText(item.getMovieNm());
        holder.tv_scrn.setText(item.getScrnNm());
        holder.tv_showTm.setText(item.getShowTm());

    }

    @Override
    public int getItemCount() {
        return timeItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_scrn, tv_showTm;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_scrn = itemView.findViewById(R.id.tv_scrn);
            tv_showTm = itemView.findViewById(R.id.tv_showTm);
        }
    }
}
