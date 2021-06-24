package com.example.pokemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

public class AppActivity extends AppCompatActivity {

    private final static String LOG_APP = "log-app";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Objects.requireNonNull(getSupportActionBar()).hide();
        SettingsActivity.checkDarkMode(this);
    }

    public void startPokemon(View view) {
        Intent i = new Intent(this, PokemonActivity.class);
        startActivity(i);
        onPause();
    }

    public void settings(View view) {
        Log.d(LOG_APP, "starting settings");
        Intent i = new Intent(this, SettingsActivity.class);
        startActivity(i);
        onPause();
    }

    public void startMap(View view) {
        Log.d(LOG_APP, "starting map");
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        onPause();
    }

    public void playPikaSound(View view) {

        if (SettingsActivity.checkSound(this)) {
            Log.d(LOG_APP, "Playing sound");
            MediaPlayer pika = MediaPlayer.create(this, R.raw.pika);
            pika.start();
        } else {
            Log.d(LOG_APP, "Sound is disabled");
            Toast t = new Toast(this);
            t.setText("Enable sound in settings");
            t.setDuration(Toast.LENGTH_SHORT);
            t.show();
        }
    }
}