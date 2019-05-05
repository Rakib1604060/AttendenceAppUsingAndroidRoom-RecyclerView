package com.ourcuet.attendtoday;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;

import java.util.Calendar;

public class DatePicker_Fragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Calendar c=Calendar.getInstance();
        int month=c.get(Calendar.MONTH);
        int year=c.get(Calendar.YEAR);
        int day=c.get(Calendar.DAY_OF_MONTH);
        return  new DatePickerDialog(getActivity(),this,year,month,day );
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        Fragment_TakeAttendence.setDatenow(i,i1,i2);
        //Fragment_ViewReport.setDatenow(i,i1,i2);
    }
}
