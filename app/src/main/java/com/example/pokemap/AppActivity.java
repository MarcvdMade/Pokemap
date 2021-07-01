package com.example.pokemap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

public class AppActivity extends AppCompatActivity {

    private final static String LOG_APP = "log-app";

    MediaPlayer pika;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Objects.requireNonNull(getSupportActionBar()).hide();
        SettingsActivity.checkDarkMode(this);

        pika = MediaPlayer.create(this, R.raw.pika);
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

        if (!pika.isPlaying()) {
            if (SettingsActivity.checkSound(this)) {
                Log.d(LOG_APP, "Playing sound");
                pika.start();
            } else {
                Log.d(LOG_APP, "Sound is disabled");
                Toast t = new Toast(this);
                t.setText("Enable sound in settings");
                t.setDuration(Toast.LENGTH_SHORT);
                t.show();
            }
        } else {
            Toast soundToast = Toast.makeText(this, "Sound is already playing", Toast.LENGTH_SHORT);
            soundToast.show();
        }
    }
}