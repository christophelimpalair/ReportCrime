package edu.uscupstate.reportcrime;

import org.json.JSONException;
import org.json.JSONObject;

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
    private static final String JSON_ID = "id";
    private static final String JSON_TITLE = "title";
    private static final String JSON_DATE = "date";
    private static final String JSON_SOLVED = "solved";

    public Crime()
    {
        mId = UUID.randomUUID();
        mDate = new Date();
    }

    // Set ID and Date in Crime constructor
    public Crime(JSONObject json) throws JSONException
    {
        mId = UUID.fromString(json.getString(JSON_ID));
        mTitle = json.getString(JSON_TITLE);
        mSolved = json.getBoolean(JSON_SOLVED);
        mDate = new Date(json.getLong(JSON_DATE));
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

    public Date getDate()
    {
//        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, MMM dd, yyyy");
//        return sdf.format(mDate);
        return mDate;
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

    public JSONObject toJSON() throws JSONException
    {
        // The code uses methods from the JSONObject class to handle
        // the business of converting data in a Crime into something
        /// that can be written to a file as JSON
        JSONObject json = new JSONObject();
        json.put(JSON_ID, mId.toString());
        json.put(JSON_TITLE, mTitle);
        json.put(JSON_DATE, mDate.getTime());
        json.put(JSON_SOLVED, mSolved);
        return json;
    }
}


