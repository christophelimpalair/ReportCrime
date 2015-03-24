package edu.uscupstate.reportcrime;


import android.support.v4.app.Fragment;
/**
 * Created by Christophe.
 */

public class CrimeListActivity extends SingleFragmentActivity
{
    @Override
    protected Fragment createFragment()
    {
//        return new CrimeFragment();
        return new CrimeListFragment();
    }

}
