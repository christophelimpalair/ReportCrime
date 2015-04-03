package edu.uscupstate.reportcrime;

import android.content.Intent;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Christophe.
 */
public class CrimeListFragment extends ListFragment
{
    private static final String TAG = "CrimeListFragment";
    private ArrayList<Crime> mCrimes;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        getActivity().setTitle(R.string.crimes_title);

        // Access crimes
        mCrimes = CrimeLab.get(getActivity()).getCrimes();

//        ArrayAdapter<Crime> adapter = new ArrayAdapter<Crime>(getActivity(), // context object required to use the resource ID (next param)
//                android.R.layout.simple_list_item_1, // resource ID identifies the layout that ArrayAdapter will use to create the view object
//                mCrimes); // dataset

        CrimeAdapter adapter = new CrimeAdapter(mCrimes);

        setListAdapter(adapter);
    }

    @Override
    // inflate an option menu
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater)
    {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.fragment_crime_list, menu);
    }

    // Have the adapter return the Crime for the item that was clicked
    // Log that Crime's title
    @Override
    public void onListItemClick(ListView l, View v, int position, long id)
    {
        Crime c = (Crime)(getListAdapter()).getItem(position);
//        Log.d(TAG, c.getTitle() + " was clicked");
        Intent i = new Intent(getActivity(), CrimePagerActivity.class);
        // send the mCrimeId to intent as an extra
        i.putExtra(CrimeFragment.EXTRA_CRIME_ID, c.getID());
        startActivity(i);
    }

    // configures custom layout adapter for list of crimes
    private class CrimeAdapter extends ArrayAdapter<Crime>
    {
        public CrimeAdapter(ArrayList<Crime> crimes)
        {
            super(getActivity(), 0, crimes);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent)
        {
            // If we weren't given a view, inflate one
            if ( null == convertView)
            {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_crime, null);
            }

            // Configure the view for this Crime
            Crime c = getItem(position);

            TextView titleTextView = (TextView)convertView.findViewById(R.id.crime_list_item_titleTextView);
            titleTextView.setText(c.getTitle());

            TextView dateTextView = (TextView)convertView.findViewById(R.id.crime_list_item_dateTextView);
            dateTextView.setText(c.getDate().toString());

            CheckBox solvedCheckBox = (CheckBox)convertView.findViewById(R.id.crime_list_item_solvedCheckBox);
            solvedCheckBox.setChecked(c.isSolved());

            return convertView;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menu_item_new_crime:
                Crime crime = new Crime();
                CrimeLab.get(getActivity()).addCrime(crime);
                Intent i = new Intent(getActivity(), CrimeActivity.class);
                i.putExtra(CrimeFragment.EXTRA_CRIME_ID, crime.getID());
                startActivityForResult(i, 0);
                // indicate no further processing is necessary
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
