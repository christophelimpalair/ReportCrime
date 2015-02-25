package edu.uscupstate.reportcrime;


import android.support.v4.app.Fragment;
/**
 * Created by Michael on 2/24/2015.
 */

public class CrimeListActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
        return new CrimeListFragment();
    }

}
