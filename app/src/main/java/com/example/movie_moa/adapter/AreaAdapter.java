package com.example.movie_moa.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckedTextView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_moa.R;
import com.example.movie_moa.activity.FindTheaterActivity;
import com.example.movie_moa.dataModel.AreaTheatherItem;
import com.example.movie_moa.fragment.ThreeAreaFragment;
import com.example.movie_moa.fragment.TwoAreaFragment;

import java.util.ArrayList;
import java.util.List;

public class AreaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
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
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        this.context = parent.getContext();

        if (TAG.equals(TAG_ONE) || TAG.equals(TAG_TWO)) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_area_recyclerview, parent, false);
            return new AreaViewHolder(view);

        } else if (TAG.equals(TAG_THREE)) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_check_area_recyclerview, parent, false);
            return new CheckViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if (holder instanceof AreaViewHolder) {
            AreaTheatherItem item = areaTheatherItems.get(position);

            AreaAdapter.AreaViewHolder areaViewHolder = (AreaAdapter.AreaViewHolder) holder;

            areaViewHolder.tv_area.setText(item.getCdNm());

            areaViewHolder.layout.setOnClickListener((View v) -> {
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

                }
            });

        } else if (holder instanceof CheckViewHolder) {
            AreaAdapter.CheckViewHolder checkViewHolder = (AreaAdapter.CheckViewHolder) holder;
            AreaTheatherItem item = areaTheatherItems.get(position);

            checkViewHolder.tv_checkArea.setText(item.getCdNm());

            checkViewHolder.tv_checkArea.setOnClickListener((View v) -> {

                Boolean value = checkViewHolder.tv_checkArea.isChecked();

                if (value) {
                    // set check mark drawable and set checked property to false

                    checkViewHolder.tv_checkArea.setCheckMarkDrawable(null);
                    checkViewHolder.tv_checkArea.setChecked(false);

                    item.setSelected(false);

//                    Toast.makeText(context, "un-Checked", Toast.LENGTH_LONG).show();
                } else {
                    // set check mark drawable and set checked property to true

                    checkViewHolder.tv_checkArea.setCheckMarkDrawable(R.drawable.ic_check_black_24dp);
                    checkViewHolder.tv_checkArea.setChecked(true);

                    item.setSelected(true);

//                    Toast.makeText(context, "Checked", Toast.LENGTH_LONG).show();
                }


            });

        }

    }

    @Override
    public int getItemCount() {
        return areaTheatherItems.size();
    }

    public class CheckViewHolder extends RecyclerView.ViewHolder {
        CheckedTextView tv_checkArea;

        public CheckViewHolder(final View itemView) {
            super(itemView);
            tv_checkArea = itemView.findViewById(R.id.tv_checkarea);

        }

    }

    public class AreaViewHolder extends RecyclerView.ViewHolder {
        TextView tv_area;
        LinearLayout layout;

        public AreaViewHolder(@NonNull final View itemView) {
            super(itemView);
            tv_area = itemView.findViewById(R.id.tv_area);
            layout = itemView.findViewById(R.id.layout);

        }

    }

    //선택된 영화관 넘겨주기
    public ArrayList<AreaTheatherItem> getCheckTheatherList() {
        ArrayList<AreaTheatherItem> list = new ArrayList<>();

        for (int i = 0; i < areaTheatherItems.size(); i++) {
            AreaTheatherItem item = areaTheatherItems.get(i);
            if (item.isSelected == true) {
                list.add(new AreaTheatherItem(item.getCd(), item.getCdNm()));
            }

        }

        return list;
    }
}
