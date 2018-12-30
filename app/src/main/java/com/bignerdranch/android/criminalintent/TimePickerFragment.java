package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

public class TimePickerFragment extends PickerFragment {

    private TimePicker mTimePicker;
    private Button mOkButton;
    private Button mCancelButton;

    public static TimePickerFragment newInstance(Date date) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_DATE, date);

        TimePickerFragment fragment = new TimePickerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_time, container,false);

        final Date date = (Date) getArguments().getSerializable(ARG_DATE);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        mTimePicker = (TimePicker) view.findViewById(R.id.dialog_time_picker);
        mTimePicker.setIs24HourView(DateFormat.is24HourFormat(getActivity()));
        mTimePicker.setCurrentHour(hour);
        mTimePicker.setCurrentMinute(minute);

        mOkButton = (Button) view.findViewById(R.id.time_picker_ok);
        mOkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int hour = mTimePicker.getCurrentHour();
                int minute = mTimePicker.getCurrentMinute();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day, hour, minute);
                Date date = calendar.getTime();

                sendResult(Activity.RESULT_OK, date);
                if (getTargetFragment() == null) {
                    getActivity().finish();
                } else {
                    dismiss();
                }
            }
        });

        mCancelButton = (Button) view.findViewById(R.id.time_picker_cancel);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getTargetFragment() == null) {
                    getActivity().finish();
                } else {
                    dismiss();
                }
            }
        });

        return view;
    }
}
