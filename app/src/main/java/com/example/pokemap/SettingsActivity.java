package com.example.pokemap;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {
    private final static String LOG_SETTINGS = "settings-log";

    private final static String DARK_MODE_KEY = "dark_mode_preference";
    private final static Boolean DARK_MODE_DEFAULT = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.settings, new SettingsFragment())
                    .commit();
        }
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Settings");
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);
        }
    }

    public static void checkDarkMode(Context context) {
        //        check for darkmode
        boolean currentNightMode = PreferenceManager.getDefaultSharedPreferences(context).getBoolean(DARK_MODE_KEY, DARK_MODE_DEFAULT);
        Log.d(LOG_SETTINGS, String.valueOf(currentNightMode));
        if (currentNightMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    @Override
    public void onContentChanged() {
        super.onContentChanged();
        Log.d(LOG_SETTINGS, "test");
    }
}