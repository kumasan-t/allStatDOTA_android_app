package com.github.randombear.allstatdota.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.conn.RequestQueueSingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;

public class LoginActivity extends AppCompatActivity {
    private static String BASE_URL = "http://api.steampowered.com/ISteamUser/ResolveVanityURL/v0001/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText editText = findViewById(R.id.login_edit_text);
        Button button = findViewById(R.id.login_button_view);

        button.setOnClickListener(new View.OnClickListener() {
            // Creates a new listener for the button, which takes the input from the edit text
            // above and builds a new volley request that retrives information about the Steam ID
            // 64 bit.
            @Override
            public void onClick(View view) {
                String id = editText.getText().toString();
                Uri buildURI = Uri.parse(BASE_URL)
                        .buildUpon()
                        .appendQueryParameter("key",getString(R.string.local_steam_api_key))
                        .appendQueryParameter("vanityurl",id)
                        .build();
                JsonObjectRequest request = new JsonObjectRequest(buildURI.toString(), null,
                        new Response.Listener<JSONObject>() {

                            // Checks if the retrieval was a success, otherwise shows a Toast
                            // containing an error message.
                            @SuppressLint("ApplySharedPref")
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject responseJSONObject = response.getJSONObject("response");
                                    if (responseJSONObject.getInt("success") == 1) {

                                        // Tricky part: SteamID64 must be converted to SteamID32.
                                        // BigInteger is needed here due to some subtraction errors
                                        // with longs.
                                        BigInteger steam32ID = new BigInteger(responseJSONObject.getString("steamid"));
                                        BigInteger steam32to64 = new BigInteger("76561197960265728");
                                        BigInteger result = steam32ID.subtract(steam32to64);

                                        SharedPreferences pref = getSharedPreferences
                                                (getString(R.string.shared_pref_key),MODE_PRIVATE);

                                        pref.edit().putString(
                                                getString(R.string.shared_pref_key),
                                                String.valueOf(result)
                                        ).commit();

                                        Intent intent = new Intent(getBaseContext(), MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                        startActivity(intent);
                                    } else {
                                        Toast.makeText(LoginActivity.this,
                                                "No matching account",
                                                Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                RequestQueueSingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
            }
        });
    }

}
