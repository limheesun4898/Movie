package com.example.movie_moa.dialogFragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class PickDateDialogFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    Calendar mCalendar = Calendar.getInstance(Locale.KOREA);
    Date date = new Date(mCalendar.getTimeInMillis());

    //변수용 날짜
    String selectdateFormat = "yyyyMMdd";
    SimpleDateFormat selectdateFormatter = new SimpleDateFormat(selectdateFormat);

    //현재 날짜
    String todayDate = selectdateFormatter.format(mCalendar.getTime());

    //텍스트용 날짜
    String textdateFormat = "MM월\ndd";
    SimpleDateFormat textdateFormatter = new SimpleDateFormat(textdateFormat);

    String selectedDate, textDate;

    setListener listener;

    public interface setListener {
        void setSelectedDateListener(String selectedDate, String textDate, String time);
    }

    public PickDateDialogFragment(setListener listener) {
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

        //현재 시,분
        String timeFormat = "HHmm";
        SimpleDateFormat timeFormatter = new SimpleDateFormat(timeFormat);
        String time = timeFormatter.format(date);

        //오늘 날짜와 선택 날짜가 같으면 -> 선택 시간 이후의 시간표만 보여줌
        if (todayDate.equals(selectedDate)) {
            listener.setSelectedDateListener(selectedDate, textDate, time);
        } // 다르면 -> 선택 날짜
        else {
            time = "0";
            listener.setSelectedDateListener(selectedDate, textDate, time);
        }

    }

}
