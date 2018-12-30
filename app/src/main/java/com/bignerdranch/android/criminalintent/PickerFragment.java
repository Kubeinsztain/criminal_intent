package com.bignerdranch.android.criminalintent;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.DialogFragment;

import java.util.Date;

public class PickerFragment extends DialogFragment {

    public static final String ARG_DATE = "date";
    public static final String EXTRA_DATE =
            "com.bignerdranch.android.criminalintent.date";


    public void sendResult(int resultCode, Date date) {

        Intent intent = new Intent();
        intent.putExtra(EXTRA_DATE, date);

        if (getTargetFragment() == null) {
            getActivity()
                    .setResult(Activity.RESULT_OK, intent);
        } else {
            getTargetFragment()
                    .onActivityResult(getTargetRequestCode(), resultCode, intent);
        }
    }
}
