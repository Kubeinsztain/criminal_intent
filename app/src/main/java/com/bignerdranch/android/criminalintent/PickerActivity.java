package com.bignerdranch.android.criminalintent;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.Date;

public class PickerActivity extends SingleFragmentActivity {

    private static final String EXTRA_PICKER_TYPE =
            "com.bignerdranch.android.criminalintent.picker_type";
    private static final int TYPE_DATE = 0;
    private static final int TYPE_TIME = 1;

    @Override
    protected Fragment createFragment() {
        Date date = (Date) getIntent().getSerializableExtra(PickerFragment.EXTRA_DATE);
        int pickerType = getIntent().getIntExtra(EXTRA_PICKER_TYPE, 0);
        if (pickerType == 1) {
            return TimePickerFragment.newInstance(date);
        }
        return DatePickerFragment.newInstance(date);
    }

    public static Intent newIntentDateFragment(Context packageContext, int type ,Date date) {
        Intent intent = newIntent(packageContext, date);
        intent.putExtra(EXTRA_PICKER_TYPE, TYPE_DATE);
        return intent;
    }

    public static Intent newIntentTimeFragment(Context packageContext, int type ,Date date) {
        Intent intent = newIntent(packageContext, date);
        intent.putExtra(EXTRA_PICKER_TYPE, TYPE_TIME);
        return intent;
    }

    private static Intent newIntent(Context packageContext, Date date) {
        Intent intent = new Intent(packageContext, PickerActivity.class);
        intent.putExtra(PickerFragment.EXTRA_DATE, date);
        return intent;
    }
}
