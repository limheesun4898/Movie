package com.example.movie_moa.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie_moa.R;
import com.example.movie_moa.data.MainItem;

import java.util.ArrayList;

public class MoreAdapter extends RecyclerView.Adapter<MoreAdapter.ViewHolder> {

    ArrayList<MainItem> list;
    Context context;

    public MoreAdapter(Context context, ArrayList<MainItem> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.more_recyclerview_item, parent, false);
        MoreAdapter.ViewHolder viewHolder = new MoreAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DisplayMetrics displayMetrics = new DisplayMetrics();

        ((Activity) holder.itemView.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        width = (int) (width / 4);
        int height = (int) (width * 1.5);

        holder.img_poster.getLayoutParams().width = width;
        holder.img_poster.getLayoutParams().height = height;

        holder.itemView.requestLayout(); // 변경 사항 적용

        MainItem item = list.get(position);

        holder.tv_title.setText(item.getNumber()+". "+item.getTitle());
        holder.tv_preview.setText(item.getPreview());
        holder.tv_openingDay.setText(item.getOpeningDay());
        holder.tv_bookingRate.setText(item.getBookingRate());

        Glide.with(context).load(item.getPoster_url())
                .into(holder.img_poster);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_preview, tv_bookingRate, tv_openingDay;
        ImageView img_poster;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_preview = itemView.findViewById(R.id.tv_preview);
            tv_bookingRate = itemView.findViewById(R.id.tv_bookingRate);
            tv_openingDay = itemView.findViewById(R.id.tv_openingDay);
            img_poster = itemView.findViewById(R.id.img_poster);

        }
    }

}
