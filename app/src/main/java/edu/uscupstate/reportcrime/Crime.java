package edu.uscupstate.reportcrime;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Michael on 2/5/2015.
 */
public class Crime
{
    private UUID mId;
    private String mTitle;
    private Date mDate;
    private boolean mSolved;

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
        //Changed return type to string, used SimpleDateFormat because it
        //allows you to format every aspect of the date string easily
        //SimpleDateFormat when .format() is called returns the formatted string
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

}
