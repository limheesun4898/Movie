package com.example.movie_moa.findTheather;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_moa.R;
import com.example.movie_moa.data.AreaTheatherItem;

import java.util.ArrayList;

public class AreaAdapter extends RecyclerView.Adapter<AreaAdapter.ViewHolder> {
    Context context;
    String sWideareaCd;
    ArrayList<AreaTheatherItem> areaTheatherItems;
    String TAG;

    public static final String TAG_ONE = "oneFragment";
    public static final String TAG_TWO = "twoFragment";
    public static final String TAG_THREE = "threeFragment";

    public AreaAdapter(Context context, ArrayList<AreaTheatherItem> areaTheatherItems, String TAG) {
        this.context = context;
        this.areaTheatherItems = areaTheatherItems;
        this.TAG = TAG;
    }

    public AreaAdapter(Context context, ArrayList<AreaTheatherItem> areaTheatherItems, String TAG, String sWideareaCd) {
        this.context = context;
        this.areaTheatherItems = areaTheatherItems;
        this.TAG = TAG;
        this.sWideareaCd = sWideareaCd;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_onearea_recyclerview, parent, false);
        AreaAdapter.ViewHolder viewHolder = new AreaAdapter.ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AreaTheatherItem item = areaTheatherItems.get(position);

        holder.tv_area.setText(item.getCdNm());
    }

    @Override
    public int getItemCount() {
        return areaTheatherItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_area;
        LinearLayout layout;

        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            tv_area = itemView.findViewById(R.id.tv_area);
            layout = itemView.findViewById(R.id.layout);

            layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {

                        AreaTheatherItem item = areaTheatherItems.get(pos);

                        if (TAG.equals(TAG_ONE)) {
                            TwoAreaFragment fragment = new TwoAreaFragment();
                            fragment.setNm(item.getCd());
                            FragmentTransaction trans = ((FindTheaterActivity) context).getSupportFragmentManager().beginTransaction();
                            trans.replace(R.id.findTheather_container, fragment);
                            trans.commit();
                        } else if (TAG.equals(TAG_TWO)) {
                            ThreeAreaFragment fragment = new ThreeAreaFragment();
                            fragment.setNm(sWideareaCd, item.getCd());
                            FragmentTransaction trans = ((FindTheaterActivity) context).getSupportFragmentManager().beginTransaction();
                            trans.replace(R.id.findTheather_container, fragment);
                            trans.commit();

                        } else if (TAG.equals(TAG_THREE)) {

                            Intent intent = new Intent();
                            intent.putExtra("cd", item.getCd());
                            intent.putExtra("name", item.getCdNm());
                            ((FindTheaterActivity)context).setResult(Activity.RESULT_OK, intent);
                            ((FindTheaterActivity)context).finish();

                        }

                    }

                }
            });

        }

    }
}
