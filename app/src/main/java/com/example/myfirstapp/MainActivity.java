package com.example.myfirstapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.widget.TextView;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import org.json.*;


import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";
    public static String lat = null;
    public static String lon = null;
    public static String acc = null;
    public String newurl = null;


    private FusedLocationProviderClient fusedLocationClient;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        setContentView(R.layout.activity_main);
        TextView weatherBox;
        weatherBox = (TextView) findViewById(R.id.FML);


    }

    //called when the user taps the send button
    public void sendMessage(View view){
        //do something after the button gets tapped.
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        startActivity(intent);
    }


    public void GetLocation(View view){

        setContentView(R.layout.activity_main);
        final TextView box = findViewById(R.id.FML);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted

        }
        ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            lat = Double.toString(location.getLatitude());
                            lon = Double.toString(location.getLongitude());
                            acc = Float.toString(location.getAccuracy());

                            };

                        }

                });
    }

    public void getWeather(View view){

        setContentView(R.layout.activity_main);
        final TextView textBox = findViewById(R.id.FML);


        //set up the requestqueue
        RequestQueue queue = Volley.newRequestQueue(this);

        String initialurl =("https://api.weather.gov/points/" + lat + "," + lon);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, initialurl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.

                        try {
                            JSONObject obj = new JSONObject(response);


                            newurl = obj.getJSONObject("properties").getString("forecast");
                            textBox.setText(newurl);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                textBox.setText("That didn't work!");
            }
        });


// Add the request to the RequestQueue.
        queue.add(stringRequest);


        }

    }

