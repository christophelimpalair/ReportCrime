package edu.uscupstate.reportcrime;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBarActivity;
import android.widget.Toast;

/**
 * Created by Christophe.
 */
public abstract class SingleFragmentActivity extends ActionBarActivity
{
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        // Current API Level
        String mBuildMessage = "API Level: " + Build.VERSION.SDK;
        // Display it in a Toast
        Toast.makeText(getApplicationContext(), mBuildMessage, Toast.LENGTH_SHORT).show();
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.fragmentContainer);
        if(fragment==null)
        {
            fragment = createFragment();
            manager.beginTransaction()
                    .add(R.id.fragmentContainer, fragment)
                    .commit();
        }
    }

}
