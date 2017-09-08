package com.github.randombear.allstatdota.dataaccessobject.remote;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.randombear.allstatdota.R;
import com.github.randombear.allstatdota.conn.RequestQueueSingleton;
import com.github.randombear.allstatdota.dataaccessobject.interfaces.VolleyCallback;

import org.json.JSONObject;

/**
 * =================================
 * Created by randomBEAR on 02/08/2017.
 * =================================
 */

public class DotaDataRequest {
    private static String TAG = "JSON_DATA_RESPONSE";
    private static String BASE_URL = "http://api.steampowered.com/IDOTA2Match_570/";
    private static String API_VERSION = "v1";

    private static String MATCH_HISTORY_PATH = "GetMatchHistory";
    private static String MATCH_DETAILS_PATH = "GetMatchDetails";

    private String mSteamAPIKey;
    private String mSteamUserID;

    private Context mContext;

    /**
     * Constructor of the data retrieving object
     *
     * @param context context used to retrieve the request queue instance and some variable that
     *                cannot be exposed on the internet :)
     */
    public DotaDataRequest(Context context) {
        this.mContext = context;
        this.mSteamUserID = mContext.getString(R.string.local_steam_user_id_32_bit);
        this.mSteamAPIKey = mContext.getString(R.string.local_steam_api_key);
    }

    public void getMatchHistory(final VolleyCallback volleyCallback) {
        Uri builtURI = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(MATCH_HISTORY_PATH)
                .appendPath(API_VERSION)
                .appendQueryParameter("key", mSteamAPIKey)
                .appendQueryParameter("account_id", mSteamUserID)
                .build();

        forwardRequest(builtURI, volleyCallback);
    }

    public void getMatchDetails(String matchID, final VolleyCallback volleyCallback) {
        Uri builtURI = Uri.parse(BASE_URL)
                .buildUpon()
                .appendPath(MATCH_DETAILS_PATH)
                .appendPath(API_VERSION)
                .appendQueryParameter("key", mSteamAPIKey)
                .appendQueryParameter("match_id", matchID)
                .build();

        forwardRequest(builtURI, volleyCallback);
    }

    public void getMatchHistoryBySequenceNumber() {

    }

    public void getLeagueListing() {

    }

    public void getLiveLeagueGames() {

    }

    public void getTeamInfoByTeamID() {

    }

    public void getPlayerSummaries() {

    }

    public void getGameItems() {

    }

    public void getHeroes() {

    }

    private void forwardRequest(Uri uri, final VolleyCallback volleyCallback) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(uri.toString(),null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "JSONObject retrieved with no error");
                        volleyCallback.onSuccessResponse(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

        RequestQueueSingleton.getInstance(mContext.getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }
}
