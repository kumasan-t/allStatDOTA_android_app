package com.github.randombear.allstatdota.dataaccessobject.remote;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.dataaccessobject.dotainterface.DotaInterface;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * =================================
 * Created by randomBEAR on 02/08/2017.
 * =================================
 */

public class DotaDataRequest implements DotaInterface {
    private static String BASE_URL = "http://api.steampowered.com/IDOTA2Match_570/";
    private static String API_VERSION = "v1";

    private static String MATCH_HISTORY_PATH = "GetMatchHistory";
    private static String MATCH_DETAILS_PATH = "GetMatchDetails";

    private String mSteamAPIKey;
    private String mSteamUserID;

    private Context mContext;

    public DotaDataRequest(Context context) {
        this.mContext = context;
        this.mSteamUserID = mContext.getString(R.string.local_steam_user_id);
        this.mSteamAPIKey = mContext.getString(R.string.local_steam_api_key);
    }

    @Override
    public void getMatchHistory() {
        Uri builtURI = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(MATCH_HISTORY_PATH)
                .appendPath(API_VERSION)
                .appendQueryParameter("key", mSteamAPIKey)
                .appendQueryParameter("account_id", mSteamUserID)
                .build();

        forwardRequest(builtURI);
    }

    @Override
    public void getMatchDetails(String matchID) {
        Uri builtURI = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(MATCH_DETAILS_PATH)
                .appendPath(API_VERSION)
                .appendQueryParameter("key", mSteamAPIKey)
                .appendQueryParameter("match_id", matchID)
                .build();

        forwardRequest(builtURI);
    }

    @Override
    public void getMatchHistoryBySequenceNumber() {

    }

    @Override
    public void getLeagueListing() {

    }

    @Override
    public void getLiveLeagueGames() {

    }

    @Override
    public void getTeamInfoByTeamID() {

    }

    @Override
    public void getPlayerSummaries() {

    }

    @Override
    public void getGameItems() {

    }

    @Override
    public void getHeroes() {

    }

    private void forwardRequest(Uri uri) {
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(uri.toString(),null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("RESPONSE", response.toString(3));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        requestQueue.add(jsonObjectRequest);
    }
}
