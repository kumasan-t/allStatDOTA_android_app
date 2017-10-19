package com.github.randombear.allstatdota.dataaccessobject.interfaces;

import com.android.volley.VolleyError;

import org.json.JSONObject;

/**
 * =================================
 * Created by randomBEAR on 17/08/2017.
 * =================================
 */

public interface VolleyCallback {
    /**
     * Callback method used to perform operation on data from the UI thread.
     * @param result            JSONObject result from previous volley request
     */
    void onSuccessResponse(JSONObject result);


    /**
     * Callback method used to perform operation in case of network failure.
     * @param error             Error describer object
     */
    void onErrorResponse(VolleyError error);
}
