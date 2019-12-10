package com.example.movie_moa.parser;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.movie_moa.dataModel.AreaTheatherItem;
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
    //최종 영화 시간표 리스트
    ArrayList<MovieTimeItem> movieTimeItems = new ArrayList<>();

    //선택된 영화관 리스트
    ArrayList<AreaTheatherItem> theaterCheckList = new ArrayList<>();

    String showDt, movieTitle, todayTime;
    Context context;

    StringBuffer buffer;

    public FindMovieTimeParser(Context context, String movieTitle, String showDt, String todayTime, ArrayList<AreaTheatherItem> theaterCheckList) {
        this.context = context;
        this.movieTitle = movieTitle;
        this.showDt = showDt;
        this.todayTime = todayTime;
        this.theaterCheckList = theaterCheckList;
    }

    @Override
    protected ArrayList<MovieTimeItem> doInBackground(Void... voids) {
        HttpURLConnection urlConnection = null;
        try {
            for (int i = 0; i < theaterCheckList.size(); i++) {
                String body = "theaCd=" + theaterCheckList.get(i).getCd() + "&showDt=" + showDt;

                Log.d("debug", "cdnumber: " + theaterCheckList.get(i).getCd());

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

                    //영화관별 시간표 리스트
                    ArrayList<TimeItem> item = new ArrayList<>();

                    while ((line = in.readLine()) != null) {
                        buffer.append(line);
                    }

                    JSONObject jsonObject = new JSONObject(buffer.toString());
                    JSONArray schedule = jsonObject.getJSONArray("schedule");

                    for (int j = 0; j < schedule.length(); j++) {

                        String scrnNm = null, movieNm = null, showTm = null;

                        JSONObject object = schedule.getJSONObject(j);

                        // 영화 제목
                        movieNm = object.getString("movieNm");

                        if (movieNm.contains(movieTitle)) { //선택한 영화제목과 비교
                            //스크린
                            scrnNm = object.getString("scrnNm");
                            //시간표
                            String[] timetable = object.getString("showTm").split(",");

                            showTm = Util.setshowTimeSort(timetable, todayTime);

                            item.add(new TimeItem(scrnNm, movieNm, showTm));

                        } else ;

                    }
                    movieTimeItems.add(new MovieTimeItem(theaterCheckList.get(i).getCdNm(), item));

                    Log.d("debug", "doInBackground: " + movieTimeItems.get(i).toString());

                    in.close();

                }
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