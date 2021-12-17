package com.example.lab2_appmoviles.utils;

import android.os.Bundle;
import android.preference.PreferenceActivity;

import androidx.appcompat.app.AppCompatActivity;


import androidx.preference.PreferenceFragmentCompat;

import com.example.lab2_appmoviles.R;


public class SettingsActivity extends PreferenceActivity {

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings);
    }

}
