package com.example.pokemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private final static int DISPLAY_COUNT = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

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