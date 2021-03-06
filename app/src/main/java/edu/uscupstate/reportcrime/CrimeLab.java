package edu.uscupstate.reportcrime;

import android.content.Context;
import android.util.Log;

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
    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crimes.json";
    private CriminalIntentJSONSerializer mSerializer;

    // Context parameter allows the singleton to start activities, access project resources
    private CrimeLab(Context appContext)
    {
        mAppContext = appContext;
        //populate the array list with 100 boring Crime objects
        mCrimes = new ArrayList<Crime>();
        mSerializer = new CriminalIntentJSONSerializer(mAppContext, FILENAME);
        try {
            mCrimes = mSerializer.loadCrimes();
        } catch (Exception e) {
            mCrimes = new ArrayList<Crime>();
            Log.e(TAG, "Error loading crimes");
        }
//        for(int i=0; i<100; ++i)
//        {
//            Crime c = new Crime();
//            c.setTitle("Crime # " + i);
//            c.setSolved(i%2==0);
//            mCrimes.add(c);
//        }
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

    public boolean saveCrimes()
    {
        try {
            mSerializer.saveCrimes(mCrimes);
            Log.d(TAG,"crimes saved to file");
            return true;
        } catch (Exception e) {
            Log.e(TAG, "Error saving crimes: " + e);
            return false;
        }
    }
}
