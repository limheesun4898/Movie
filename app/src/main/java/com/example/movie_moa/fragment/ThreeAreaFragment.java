package com.example.movie_moa.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.movie_moa.R;
import com.example.movie_moa.activity.FindTheaterActivity;
import com.example.movie_moa.adapter.AreaAdapter;
import com.example.movie_moa.dataModel.AreaTheatherItem;
import com.example.movie_moa.parser.ThreeAreaListParser;

import java.util.ArrayList;

public class ThreeAreaFragment extends Fragment {
    String sWideareaCd, sBasareaCd;
    Context context;
    AreaAdapter areaAdapter;

    RecyclerView recyclerView;
    public static final String TAG_FRAGMENT = "threeFragment";

    public ThreeAreaFragment() {
        // Required empty public constructor
    }

    public void setNm(String sWideareaCd, String sBasareaCd) {
        this.sWideareaCd = sWideareaCd;
        this.sBasareaCd = sBasareaCd;
    }

    // 첫번째 선택하고 다음 list 결과
    public void getTwoAreaListResult(ArrayList<AreaTheatherItem> list) {

        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        areaAdapter = new AreaAdapter(getActivity(), list, TAG_FRAGMENT);
        recyclerView.setAdapter(areaAdapter);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_three_area, container, false);

        setHasOptionsMenu(true);

        recyclerView = view.findViewById(R.id.ThreefindTheather_reccyclerview);
        context = container.getContext();

        ThreeAreaListParser parser = new ThreeAreaListParser(context, sWideareaCd, sBasareaCd, ThreeAreaFragment.this);
        parser.execute();

        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_find_theater, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.btn_check_complete:
                ArrayList<AreaTheatherItem> list = areaAdapter.getCheckTheatherList();

                if (list.size() == 0 ){
                    Toast.makeText(context, "선택한 것이 없습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent();
                    intent.putParcelableArrayListExtra("checklist", list);
                    ((FindTheaterActivity) context).setResult(Activity.RESULT_OK, intent);
                    ((FindTheaterActivity) context).finish();
//
                }


                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }


}
