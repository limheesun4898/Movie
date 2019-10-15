package com.example.movie_moa.adapter;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie_moa.R;
import com.example.movie_moa.data.MainItem;

import java.util.ArrayList;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder> {
    Context context;
    ArrayList<MainItem> list;
    int tab;

    public MainRecyclerAdapter(Context context, ArrayList<MainItem> list, int tab) {
        this.context = context;
        this.list = list;
        this.tab = tab;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_recyclerview_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) holder.itemView.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        width = (int) (width/2.5);
        int height = (int) (width*1.5);

        holder.img_poster.getLayoutParams().width = width;
        holder.img_poster.getLayoutParams().height = height;
        holder.itemView.getLayoutParams().width = width;

        holder.itemView.requestLayout(); // 변경 사항 적용

        MainItem item = list.get(position);

        holder.tv_title.setText(item.getTitle());
        if(tab == 1){
            holder.tv_bookingRate.setText(item.getBookingRate());
        } else if (tab == 2) {
            holder.tv_bookingRate.setText(item.getOpeningDay());
        }
        Glide.with(context).load(item.getPoster_url())
                .into(holder.img_poster);

    }

    @Override
    public int getItemCount() {
        return 7;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_bookingRate;
        ImageView img_poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_bookingRate = itemView.findViewById(R.id.tv_bookingRate);
            img_poster = itemView.findViewById(R.id.img_poster);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        MainItem item = list.get(pos);
                        Toast.makeText(context, item.getDetail_url(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
