package com.example.movie_moa.findTheather.download;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import androidx.fragment.app.Fragment;

import com.example.movie_moa.findTheather.AreaTheatherItem;
import com.example.movie_moa.findTheather.TwoAreaFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class TwoAreaListDownloader extends AsyncTask<Void, Void, ArrayList<AreaTheatherItem>> {
    ArrayList<AreaTheatherItem> areaTheatherItems = new ArrayList<>();
    Context context;
    String sWideareaCd;
    StringBuffer buffer;
    Fragment fragment;

    public TwoAreaListDownloader(Context context, String sWideareaCd, Fragment fragment) {
        this.context = context;
        this.sWideareaCd = sWideareaCd;
        this.fragment = fragment;
    }

    private ProgressDialog progressDialog;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        //진행다일로그 시작
        progressDialog = new ProgressDialog(context);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("잠시 기다려 주세요.");
        progressDialog.show();

    }

    @Override
    protected ArrayList<AreaTheatherItem> doInBackground(Void... voids) {

        HttpURLConnection urlConnection = null;

        try {
            String body = "sWideareaCd=" + sWideareaCd;
            URL url = new URL("http://www.kobis.or.kr/kobis/business/mast/thea/findBasareaCdList.do");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true); //서버로부터 응답 메시지를 받는
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //요청 헤더 부
            OutputStream os = urlConnection.getOutputStream();
            os.write(body.getBytes());
            os.flush();
            os.close();

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
                buffer = new StringBuffer();

                String line = "";

                String cdValue;
                String cdNmValule;

                while ((line = in.readLine()) != null) {
                    buffer.append(line);
                }

                JSONArray jsonArray = new JSONObject(String.valueOf(buffer)).getJSONArray("basareaCdList");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject object = jsonArray.getJSONObject(i);
                    cdValue = object.getString("cd");
                    cdNmValule = object.getString("cdNm");
                    areaTheatherItems.add(new AreaTheatherItem(cdValue, cdNmValule));
                }

                in.close();


            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return areaTheatherItems;
    }

    @Override
    protected void onPostExecute(ArrayList<AreaTheatherItem> areaTheaterItems) {

        progressDialog.dismiss();

        ((TwoAreaFragment)fragment).getTwoAreaListResult(areaTheaterItems);

    }

}
