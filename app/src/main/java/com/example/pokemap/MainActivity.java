package com.example.pokemap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private final static int DISPLAY_COUNT = 4000;
    private final static String LOADING_LOG = "loading-log";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

//        check the darkMode setting
        SettingsActivity.checkDarkMode(this);

//        log loading
        Log.d(LOADING_LOG, "loading...");

//        go from intro screen to app
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(MainActivity.this, AppActivity.class);
                startActivity(i);
                finish();
            }
        }, DISPLAY_COUNT);
    }

}