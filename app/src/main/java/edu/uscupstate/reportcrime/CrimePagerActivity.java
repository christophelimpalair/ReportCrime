package edu.uscupstate.reportcrime;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by christophe on 3/19/15.
 */
public class CrimePagerActivity extends FragmentActivity {
    private ViewPager mViewPager;

    @Override
    public void onCreate( Bundle savedInstanceState )
    {
        super.onCreate(savedInstanceState);

        // Layout views in code
        // Instead of using an XML file, let's define our hierarchy in code
        // ViewPager is fragment container, so you need to assign an ID (standalone ID resources)
        mViewPager = new ViewPager(this);
        mViewPager.setId(R.id.viewPager);
        setContentView(mViewPager);

        final ArrayList<Crime> crimes = CrimeLab.get(this).getCrimes();

        FragmentManager fm = getSupportFragmentManager();
        mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {
            // When you call this method for a position in your array of crimes
            // You will return a CrimeFragment configured to display the crime at that position
            @Override
            public Fragment getItem(int position) {
                UUID crimeId = crimes.get(position).getID();
                return CrimeFragment.newInstance(crimeId);
            }

            @Override
            public int getCount() {
                return crimes.size();
            }


        });

        UUID crimeId = (UUID)getIntent().getSerializableExtra(CrimeFragment.EXTRA_CRIME_ID);

        for (int i = 0; i < crimes.size(); i++)
        {
            if (crimes.get(i).getID().equals(crimeId))
            {
                mViewPager.setCurrentItem(i);
                break;
            }
        }
    }
}
