package com.programmersgateway.sm1999.covid19sahayak;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefs {
    SharedPreferences mySharedPref ;
    public SharedPrefs(Context context) {
        mySharedPref = context.getSharedPreferences("filename",Context.MODE_PRIVATE);
    }
    // save the night mode state true or false
    public void setNightModeState(Boolean state)
    {
        SharedPreferences.Editor editor = mySharedPref.edit();
        editor.putBoolean("NightMode",state);
        editor.apply();
    }
    // load the night mode state
    public Boolean loadNightModeState()
    {
        Boolean state = mySharedPref.getBoolean("NightMode",false);
        return state;
    }
}