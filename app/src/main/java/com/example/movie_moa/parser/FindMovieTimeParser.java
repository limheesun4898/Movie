package com.example.movie_moa.parser;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.movie_moa.dataModel.MovieTimeItem;
import com.example.movie_moa.activity.MovieTicketingActivity;
import com.example.movie_moa.dataModel.TimeItem;
import com.example.movie_moa.util.Util;

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
    ArrayList<TimeItem> item = new ArrayList<>();

    String theaterNm, theaCd, showDt, movieTitle, todayTime;
    Context context;

    StringBuffer buffer;


    public FindMovieTimeParser(Context context, String theaCd, String showDt, String theaterNm, String movieTitle, String todayTime) {
        this.context = context;
        this.theaCd = theaCd;
        this.showDt = showDt;
        this.theaterNm = theaterNm;
        this.movieTitle = movieTitle;
        this.todayTime = todayTime;
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

                    // 영화 제목
                    movieNm = object.getString("movieNm");

                    if (movieNm.contains(movieTitle)) {
                        //스크린
                        scrnNm = object.getString("scrnNm");
                        //시간표
                        String[] timetable = object.getString("showTm").split(",");

                        showTm = Util.setshowTimeSort(timetable, todayTime);

                        item.add(new TimeItem(scrnNm, movieNm, showTm));
                    } else ;

                }
                movieTimeItems.add(new MovieTimeItem(theaterNm, item));

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
        ((MovieTicketingActivity) context).getParserList(movieTimeItems);
    }
}