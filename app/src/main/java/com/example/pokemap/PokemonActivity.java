package com.example.pokemap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class PokemonActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private String url = "https://pokeapi.co/api/v2/pokemon?limit=20";
    private String urlNext;
    private String urlPrevious;
    private final static String POKEMON_LOG = "Pokemon-API";

    private ArrayList<String> pokemonList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);
        Objects.requireNonNull(getSupportActionBar()).hide();

        pokemonList = new ArrayList<>();

        ListView pokemonView = findViewById(R.id.pokemonView);

        pokemonView.setOnItemClickListener(this);

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pokemonList);

        pokemonView.setAdapter(adapter);

        getPokemon(url);
    }

    private void getPokemon(String url) {
//        start request queue
        RequestQueue queue = Volley.newRequestQueue(this);

//        clear ArrayList
        pokemonList.removeAll(pokemonList);

//        Request string response
        JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.GET, url, null,
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

        try {
            JSONArray pokemon = (JSONArray)data.get("results");

            String next = data.get("next").toString();
            urlNext = next;
            Log.d(POKEMON_LOG, next);

            String previous = data.get("previous").toString();
            urlPrevious = previous;
            Log.d(POKEMON_LOG, previous);

//            get buttons for pagination
            Button previousBtn = findViewById(R.id.previousBtn);
            Button nextBtn = findViewById(R.id.nextBtn);

            if (previous.equals("null")) {
                previousBtn.setVisibility(View.INVISIBLE);
            } else {
                previousBtn.setVisibility(View.VISIBLE);
            }

            if (next.equals("null")) {
                nextBtn.setVisibility(View.INVISIBLE);
            } else {
                nextBtn.setVisibility(View.VISIBLE);
            }

            for (int i = 0; i < pokemon.length(); i++) {
                JSONObject p = (JSONObject)pokemon.get(i);

//              Get pokemon attributes
                String name = (String)p.get("name");
                Log.d(POKEMON_LOG, name);

                pokemonList.add(name);
                adapter.notifyDataSetChanged();

                String url = (String)p.get("url");
                Log.d(POKEMON_LOG, url);
            }
        } catch (JSONException e) {
            Log.e(POKEMON_LOG, "JSON exception error");
        }

    }

    public void onNext(View view) { getPokemon(urlNext); }

    public void onPrevious(View view) { getPokemon(urlPrevious); }

    public void goBack(View view) { finish(); }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d(POKEMON_LOG, pokemonList.get(position));

        Toast toast = new Toast(this);
        toast.setText("you clicked " + pokemonList.get(position));
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.show();

        // handle map pin point
        Intent i = new Intent(this, MapsActivity.class);
        i.putExtra("POKEMON_NAME", pokemonList.get(position));
        startActivity(i);
    }
}