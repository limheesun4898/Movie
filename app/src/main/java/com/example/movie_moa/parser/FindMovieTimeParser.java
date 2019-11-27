package com.example.movie_moa.parser;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.movie_moa.data.MovieTimeItem;
import com.example.movie_moa.activity.MovieTicketingActivity;

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

public class FindMovieTimeParser extends AsyncTask<Void, Void, ArrayList<MovieTimeItem>> {
    ArrayList<MovieTimeItem> movieTimeItems = new ArrayList<>();

    Context context;

    String theaCd, showDt;
    StringBuffer buffer;

    public FindMovieTimeParser(String theaCd, String showDt, Context context) {
        this.theaCd = theaCd;
        this.showDt = showDt;
        this.context = context;
    }

    @Override
    protected ArrayList<MovieTimeItem> doInBackground(Void... voids) {
        HttpURLConnection urlConnection = null;
        try {
            String body = "theaCd=" + theaCd + "&showDt=" + showDt;

            Log.d("debug", "doInBackground: " + theaCd + "\\\\\\" + showDt);
            URL url = new URL("http://www.kobis.or.kr/kobis/business/mast/thea/findSchedule.do");
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true); //서버로부터 응답 메시지를 받는
            urlConnection.setDoOutput(true);
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded"); //요청 헤더 부분
            OutputStream os = urlConnection.getOutputStream();
            os.write(body.getBytes());
            os.flush();
            os.close();

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
                buffer = new StringBuffer();

                String line = "";

                String scrnNm, movieNm, showTm;

                while ((line = in.readLine()) != null) {
                    buffer.append(line);
                }

                JSONObject jsonObject = new JSONObject(buffer.toString());
                JSONArray schedule = jsonObject.getJSONArray("schedule");

                for (int i = 0; i < schedule.length(); i++) {
                    JSONObject object = schedule.getJSONObject(i);
                    scrnNm = object.getString("scrnNm");
                    movieNm = object.getString("movieNm");
                    showTm = object.getString("showTm");
                    movieTimeItems.add(new MovieTimeItem(scrnNm, movieNm, showTm));
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
        return movieTimeItems;
    }

    @Override
    protected void onPostExecute(ArrayList<MovieTimeItem> movieTimeItems) {
        ((MovieTicketingActivity)context).getParserList(movieTimeItems);
    }
}
