package edu.uscupstate.reportcrime;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;

/**
 * Created by christophe on 4/9/15.
 *
 * This class is responsible of taking the existing
 * ArrayList of crimes and writing it as JSON
 */
public class CriminalIntentJSONSerializer {

    private Context mContext;
    private String mFileName;

    public CriminalIntentJSONSerializer(Context c, String f)
    {
        mContext = c;
        mFileName = f;
    }

    // in saveCrimes method, you create a JSONArray object. Then you call
    // n toJSON method on each crime in the list and add the result to the JSONArray
    public void saveCrimes(ArrayList<Crime> crimes) throws JSONException, IOException
    {
        // Build JSON array
        JSONArray array = new JSONArray();

        for ( Crime c: crimes )
        {
            array.put(c.toJSON());
        }

        // write the file to writer
        Writer writer = null;

        try {
            OutputStream out = mContext.openFileOutput(mFileName, Context.MODE_PRIVATE);
            writer = new OutputStreamWriter(out);
            writer.write(array.toString());
        } finally {
            if ( writer != null )
                writer.close();
        }
    }
}
