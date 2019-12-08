package com.example.movie_moa.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.ArrayList;

public class Util {


    public static boolean isNetworkConnected(Context context) {
        boolean isConnected = false;

        ConnectivityManager manager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobile = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        if (mobile.isConnected() || wifi.isConnected()) {
            isConnected = true;
        } else {
            isConnected = false;
        }
        return isConnected;
    }

    public static void AlertDailog(Context context) {
        new AlertDialog.Builder(context)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("네트워크 연결 오류").setMessage("네트워크 연결 상태 확인 후 다시 시도해 주십시요.")
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ((Activity)context).finish();
                    }
                }).show();

    }

    //1010 , 1350 이렇게 들어온 시간표를 -> 10:10 | 13:50으로 바꿔줌
    public static String setshowTimeSort(String[] timetable, String todayTime) {
        ArrayList<String> table = new ArrayList<String>();

        for (int h = 0; h < timetable.length; h++) {
            //선택 시간 이후 시간표만 모아두기
            if (!todayTime.equals("0")) {
                if (Integer.parseInt(todayTime) <= Integer.parseInt(timetable[h])) {
                    String hour = timetable[h].substring(0, 2);
                    String minute = timetable[h].substring(2);

                    table.add(hour + ":" + minute);
                }
            } else {
                String hour = timetable[h].substring(0, 2);
                String minute = timetable[h].substring(2);

                table.add(hour + ":" + minute);
            }

        }

        String delimiter = " | ";

        StringBuilder arTostr = new StringBuilder();
        if (table.size() > 0) {
            arTostr.append(table.get(0));
            for (int i = 1; i < table.size(); i++) {
                arTostr.append(delimiter);
                arTostr.append(table.get(i));
            }
        }
        return arTostr.toString();
    }

}
