package com.example.pokemap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

public class PokemonActivity extends AppCompatActivity {

    private final static String URL = "https://pokeapi.co/api/v2/pokemon/";
    private final static String POKEMON_LOG = "Pokemon-API";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        Objects.requireNonNull(getSupportActionBar()).hide();

        getPokemon();
    }

    private void getPokemon() {
//        start request queue
        RequestQueue queue = Volley.newRequestQueue(this);

//        Request string response
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(POKEMON_LOG, response.toString());
                        showPokemon(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(POKEMON_LOG, "something went wrong!");
            }
        }
        );

//        Add request to queue
        queue.add(jsonRequest);
    }

    private void showPokemon(JSONObject data) {
        //        get layout
        LinearLayout pokemonLayout = findViewById(R.id.pokemonLayout);

        try {
            JSONArray pokemon = (JSONArray)data.get("results");
            for (int i = 0; i < pokemon.length(); i++) {
                JSONObject p = (JSONObject)pokemon.get(i);

//              Get pokemon attributes
                String name = (String)p.get("name");
                Log.d(POKEMON_LOG, name);

                Button pokemonBtn = new Button(this);
                pokemonBtn.setText(name);
                pokemonBtn.setId(i);

                pokemonLayout.addView(pokemonBtn);

                String url = (String)p.get("url");
                Log.d(POKEMON_LOG, url);
            }
        } catch (JSONException e) {
            Log.e(POKEMON_LOG, "JSON exeption error");
        }
    }

    public void goBack(View view) {
        finish();
    }
}