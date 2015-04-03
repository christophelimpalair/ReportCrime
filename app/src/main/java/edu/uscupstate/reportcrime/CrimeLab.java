package edu.uscupstate.reportcrime;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by Christophe.
 *
 * Model class for creating, storing, and retrieving Crime objects
 */
public class CrimeLab
{
    private ArrayList<Crime> mCrimes;
    private static CrimeLab sCrimeLab; // s to denote static
    private Context mAppContext;

    // Context parameter allows the singleton to start activities, access project resources
    private CrimeLab(Context appContext)
    {
        mAppContext = appContext;
        //populate the array list with 100 boring Crime objects
        mCrimes = new ArrayList<Crime>();
        for(int i=0; i<100; ++i)
        {
            Crime c = new Crime();
            c.setTitle("Crime # " + i);
            c.setSolved(i%2==0);
            mCrimes.add(c);
        }
    }

    public static CrimeLab get(Context c)
    {
        if(sCrimeLab == null)
        {
            sCrimeLab = new CrimeLab(c.getApplicationContext());
        }
        return sCrimeLab;
    }

    public ArrayList<Crime> getCrimes()
    {
        return mCrimes;
    }

    public Crime getCrime(UUID id)
    {
        for(Crime c : mCrimes)
        {
            if(c.getID().equals(id))
            {
                return c;
            }
        }
        return null;
    }

    public void addCrime(Crime c)
    {
        mCrimes.add(c);
    }
}
