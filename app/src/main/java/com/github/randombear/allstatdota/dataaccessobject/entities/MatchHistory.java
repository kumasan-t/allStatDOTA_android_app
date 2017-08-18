package com.github.randombear.allstatdota.dataaccessobject.entities;

/**
 * =================================
 * Created by randomBEAR on 18/08/2017.
 * =================================
 */

import java.util.LinkedList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MatchHistory implements Parcelable {

    private int status;
    private int numResults;
    private int totalResults;
    private int resultsRemaining;
    private List<Match> matches = null;
    public final static Parcelable.Creator<MatchHistory> CREATOR = new Creator<MatchHistory>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MatchHistory createFromParcel(Parcel in) {
            MatchHistory instance = new MatchHistory();
            instance.status = ((int) in.readValue((int.class.getClassLoader())));
            instance.numResults = ((int) in.readValue((int.class.getClassLoader())));
            instance.totalResults = ((int) in.readValue((int.class.getClassLoader())));
            instance.resultsRemaining = ((int) in.readValue((int.class.getClassLoader())));
            in.readList(instance.matches, (com.github.randombear.allstatdota
                    .dataaccessobject.entities
                    .Match.class.getClassLoader()));
            return instance;
        }

        public MatchHistory[] newArray(int size) {
            return (new MatchHistory[size]);
        }

    };

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getNumResults() {
        return numResults;
    }

    public void setNumResults(int numResults) {
        this.numResults = numResults;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public int getResultsRemaining() {
        return resultsRemaining;
    }

    public void setResultsRemaining(int resultsRemaining) {
        this.resultsRemaining = resultsRemaining;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(numResults);
        dest.writeValue(totalResults);
        dest.writeValue(resultsRemaining);
        dest.writeList(matches);
    }

    /**
     * Static utility function that allows a fast creation of a representation object given its
     * corresponding json.
     *
     * @param jsonObject A well-formatted jsonObject.
     * @return A new instance of the class MatchHistory.
     */
    public static MatchHistory createFromJSON(JSONObject jsonObject) {
        MatchHistory matchHistoryFromJSON = new MatchHistory();
        try {
            jsonObject = jsonObject.getJSONObject("result");
            matchHistoryFromJSON.setStatus(jsonObject.getInt("status"));
            matchHistoryFromJSON.setTotalResults(jsonObject.getInt("total_results"));
            matchHistoryFromJSON.setNumResults(jsonObject.getInt("num_results"));
            matchHistoryFromJSON.setResultsRemaining(jsonObject.getInt("results_remaining"));

            JSONArray matchesJSON = jsonObject.getJSONArray("matches");
            matchHistoryFromJSON.setMatches(new LinkedList<Match>());
            for (int i = 0; i < matchesJSON.length(); i++) {
                matchHistoryFromJSON.getMatches().add(Match.createFromJSON(matchesJSON.getJSONObject(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return matchHistoryFromJSON;
    }

    @Override
    public String toString() {
        return "MatchHistory{" +
                "\n status=" + status +
                ",\n numResults=" + numResults +
                ",\n totalResults=" + totalResults +
                ",\n resultsRemaining=" + resultsRemaining +
                ",\n matches=" + matches.toString() +
                '}';
    }

    public int describeContents() {
        return 0;
    }
}
