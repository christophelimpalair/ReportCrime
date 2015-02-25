package edu.uscupstate.reportcrime;

import android.support.v4.app.ListFragment;
import android.os.Bundle;

import java.util.ArrayList;

/**
 * Created by Michael on 2/19/2015.
 */
public class CrimeListFragment extends ListFragment
{
    private static final String TAG = "CrimeListFragment";
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getActivity().setTitle(R.string.crimes_title);
        //access crimes
        mCrimes = CrimeLab.get(getActivity()).getCrimes();
    }
}
