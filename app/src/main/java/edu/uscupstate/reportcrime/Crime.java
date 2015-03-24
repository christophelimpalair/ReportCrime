package edu.uscupstate.reportcrime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Christophe.
 *
 * Model Class to control Crime parameters
 */
public class Crime
{
    private UUID mId; // Universally Unique Identifier
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

    // Set ID and Date in Crime constructor
    public Crime()
    {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    public String getTitle()
    {
        return mTitle;
    }

    public void setTitle(String title)
    {
        mTitle = title;
    }

    public UUID getID()
    {
        return mId;
    }

    public String getDate()
    {
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM dd, yyyy");
        return sdf.format(mDate);
    }
    public void setDate(Date date) {
        mDate = date;
    }
    public boolean isSolved() {
        return mSolved;
    }
    public void setSolved(boolean solved) {
        mSolved = solved;
    }

    // Override toString to display title of Crime instead of memory address
    @Override
    public String toString()
    {
        return getTitle();
    }
}


