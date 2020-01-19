package com.example.movie_moa.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.movie_moa.R;
import com.example.movie_moa.activity.MoreActivity;
import com.example.movie_moa.dataModel.MainItem;
import com.example.movie_moa.activity.MovieTicketingActivity;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    ArrayList<MainItem> list;
    int tab;
    private static final int TYPE_TAB = -1;
    private static final int TYPE_ADD = 0;

    private static final String fragmentTag1 = "Tab1";
    private static final String fragmentTag2 = "Tab2";

    public MainAdapter(Context context, ArrayList<MainItem> list, int tab) {
        this.context = context;
        this.list = list;
        this.tab = tab;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        this.context = parent.getContext();

        if (viewType == TYPE_TAB) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main1_recyclerview, parent, false);
            return new TabViewHolder(view);

        } else if (viewType == TYPE_ADD) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_add, parent, false);
            return new AddViewHolder(view);

        }

        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        // 영화 정보 띄우기
        if (holder instanceof TabViewHolder) {
            DisplayMetrics displayMetrics = new DisplayMetrics();

            ((Activity) holder.itemView.getContext()).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int width = displayMetrics.widthPixels;
            width = (int) (width / 2.5);
            int height = (int) (width * 1.5);

            TabViewHolder tabViewHolder = (TabViewHolder) holder;

            tabViewHolder.img_poster.getLayoutParams().width = width;
            tabViewHolder.img_poster.getLayoutParams().height = height;
            tabViewHolder.itemView.getLayoutParams().width = width;

            tabViewHolder.itemView.requestLayout(); // 변경 사항 적용

            MainItem item = list.get(position);

            Glide.with(context)
                    .load(item.getPoster_url())
                    .override(600,400)
                    .into(tabViewHolder.img_poster);

            tabViewHolder.tv_title.setText(item.getTitle());
            if (tab == 1) {
                tabViewHolder.tv_bookingRate.setText(item.getBookingRate());

                tabViewHolder.btn_movieTicket.setOnClickListener((View v) -> {
                    Intent intent = new Intent(context, MovieTicketingActivity.class);
                    intent.putExtra("title", item.getTitle());
                    context.startActivity(intent);
                });
            } else if (tab == 2) {
                tabViewHolder.tv_bookingRate.setText(item.getOpeningDay());
                tabViewHolder.btn_movieTicket.setVisibility(View.INVISIBLE);
            }


            // 더보기 버튼 나타내기
        } else if (holder instanceof AddViewHolder) {

            AddViewHolder addViewHolder = (AddViewHolder) holder;

            addViewHolder.btn.setOnClickListener((View v) -> {

                Intent intent = new Intent(context, MoreActivity.class);

                if (tab == 1) {
                    intent.putExtra("tab", fragmentTag1);
                } else if (tab == 2) {
                    intent.putExtra("tab", fragmentTag2);
                }
                context.startActivity(intent);
            });

        }

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 7) {
            return TYPE_ADD;
        }
        return TYPE_TAB;
    }

    public class TabViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_bookingRate;
        ImageView img_poster;
        TextView btn_movieTicket;

        public TabViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_bookingRate = itemView.findViewById(R.id.tv_bookingRate);
            img_poster = itemView.findViewById(R.id.img_poster);
            btn_movieTicket = itemView.findViewById(R.id.btn_movieticket);

        }
    }

    public class AddViewHolder extends RecyclerView.ViewHolder {
        ImageButton btn;

        public AddViewHolder(@NonNull View itemView) {
            super(itemView);
            btn = itemView.findViewById(R.id.btn);

        }
    }


}
