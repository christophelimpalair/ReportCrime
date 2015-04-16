package edu.uscupstate.reportcrime;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONTokener;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    public ArrayList<Crime> loadCrimes() throws IOException, JSONException
    {
        ArrayList<Crime> crimes = new ArrayList<Crime>();
        BufferedReader reader = null;

        try {
            // Open and read the file into a StringBuilder
            InputStream in = mContext.openFileInput(mFileName);
            reader = new BufferedReader(new InputStreamReader(in));
            StringBuilder jsonString = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                jsonString.append(line);
            }

            // parse JSON using JSONTokener
            JSONArray array = (JSONArray) new JSONTokener(jsonString.toString()).nextValue();

            // Build the array of crimes from JSONObject;
            for ( int i = 0; i < array.length(); i++)
            {
                crimes.add(new Crime(array.getJSONObject(i)));
            }
        } catch (FileNotFoundException e) {
            // we will ignore this one since it happens when we start fresh
        } finally {
            if (reader != null)
                reader.close();
        }
        return crimes;
    }
}
