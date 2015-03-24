package edu.uscupstate.reportcrime;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;

import java.util.Date;

/**
 * Created by christophe on 3/19/15.
 */
public class DatePickerFragment extends DialogFragment {
    public static final String EXTRA_DATE = "criminalReport.DATE";
    Date mDate;

    // The FragmentManager of the hosting activity calls this method
    // as part of putting the DialogFragment on screen
    @Override
    public Dialog onCreateDialog ( Bundle savedInstanceState ) {
        return new AlertDialog.Builder(getActivity()).setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, null)
                .create();
    }
}
