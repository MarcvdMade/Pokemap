package com.example.pokemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.Objects;

public class AppActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app);
        Objects.requireNonNull(getSupportActionBar()).hide();
    }

    public void startPokemon(View view) {
        Intent i = new Intent(this, PokemonActivity.class);
        startActivity(i);
        onPause();
    }
}