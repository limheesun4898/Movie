package com.example.movie_moa.findTheather;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.DialogFragment;

import com.example.movie_moa.movieticketing.MovieTicketingActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatePickDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    Calendar mCalendar = Calendar.getInstance(Locale.KOREA);
    Date date = new Date(mCalendar.getTimeInMillis());

    //변수용 날짜
    String selectdateFormat = "yyyyMMdd";
    SimpleDateFormat selectdateFormatter = new SimpleDateFormat(selectdateFormat);

    //텍스트용 날짜
    String textdateFormat = "MM월\ndd";
    SimpleDateFormat textdateFormatter = new SimpleDateFormat(textdateFormat);

    String selectedDate, textDate, selectedTime;

    setListener listener;

    public interface setListener{
        void setSelectedDateListenser(String selectedDate);
    }

    public void setSelectedDataListener(setListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        //기본 달력 띄우기
        int defaultYear = mCalendar.get(Calendar.YEAR);
        int defaultMonth = mCalendar.get(Calendar.MONTH);
        int defaultDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), this, defaultYear, defaultMonth, defaultDay);

        //당일부터 3일까지만 선택 가능
        DatePicker mDatePicker = datePickerDialog.getDatePicker();
        mDatePicker.setMinDate(mCalendar.getTimeInMillis());
        Calendar after3days = Calendar.getInstance(Locale.KOREA);
        after3days.add(Calendar.DATE, 3);
        mDatePicker.setMaxDate(after3days.getTimeInMillis());

        return datePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        mCalendar.set(year, month, dayOfMonth);
        selectedDate = selectdateFormatter.format(mCalendar.getTimeInMillis());
        textDate = textdateFormatter.format(mCalendar.getTimeInMillis());

        String hourFormat = "HH";
        SimpleDateFormat hourFormatter = new SimpleDateFormat(hourFormat);
        final String hour = hourFormatter.format(date);//현재 시 담기

        String minuteFormat = "mm";
        SimpleDateFormat minuteFormatter = new SimpleDateFormat(minuteFormat);
        final String minute = minuteFormatter.format(date);//현재 분 담기

        listener.setSelectedDateListenser(selectedDate);
    }

    public String setSelectDate() {
        return selectedDate;
    }

    public String setTextDate() {
        return textDate;
    }


}
