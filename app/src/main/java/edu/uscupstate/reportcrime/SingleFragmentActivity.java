package edu.uscupstate.reportcrime;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.widget.Toast;

/**
 * Created by Michael on 2/19/2015.
 */
public abstract class SingleFragmentActivity extends FragmentActivity
{
    protected abstract Fragment createFragment();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
        //Create a string to display the curent API Level and display it in a Toast
        String mBuildMessage = "API Level: " + Build.VERSION.SDK;
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
