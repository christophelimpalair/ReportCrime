package edu.uscupstate.reportcrime;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

/**
 * Created by Michael on 2/10/2015.
 */
public class CrimeFragment extends Fragment
{
    private Crime mCrime;
    private EditText mTitleField;
    private Button mDateButton;
    private CheckBox mSolvedCheckBox;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        //You don't inflate the fragment's view in the onCreate method
        super.onCreate(savedInstanceState);
        mCrime = new Crime();
    }

    //You configure the fragment instance in Fragment.onCreate(),
    //but you create and configure the fragment's view in another
    //fragment lifecycle method called onCreateView.  This method is
    //where you inflate the layout for fragment's view and return the
    //inflated view to the hosting activity.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState)
    {
        //The second parameter is your view's parent, which is usually needed to configure
        //the widget properly.  The third parameter tell the layout inflater whether to add
        //the inflated view to view's parent.  You pass in false because you will add the view
        //in the activity's code.
        View v = inflater.inflate(R.layout.fragment_crime, parent, false);

        //Wire up the EditText to respond to user input
        mTitleField = (EditText) v.findViewById(R.id.crime_title);
        mTitleField.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence c, int start, int count, int after)
            {
                //This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence c, int start, int before, int count)
            {
                mCrime.setTitle(c.toString());
            }

            @Override
            public void afterTextChanged(Editable editable)
            {
                //this one too
            }
        });

        mDateButton = (Button) v.findViewById(R.id.crime_date);
        mDateButton.setText(mCrime.getDate()); //only change here was to remove .toString()
        mDateButton.setEnabled(false);

        mSolvedCheckBox = (CheckBox) v.findViewById(R.id.crime_solved);
        mSolvedCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                //Set the crimes solved property
                mCrime.setSolved(isChecked);
                Log.d("ReportCrime: ", "Test Checked Value: " + isChecked);
            }
        });

        return v;
    }
}
