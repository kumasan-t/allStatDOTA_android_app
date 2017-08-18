package com.github.randombear.allstatdota.dataaccessobject.entities;

/**
 * =================================
 * Created by randomBEAR on 17/08/2017.
 * =================================
 */

import java.util.LinkedList;
import java.util.List;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Match implements Parcelable {

    private long matchId;
    private long matchSeqNum;
    private int startTime;
    private int lobbyType;
    private int radiantTeamId;
    private int direTeamId;
    private List<Player> players = null;
    public final static Parcelable.Creator<Match> CREATOR = new Creator<Match>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Match createFromParcel(Parcel in) {
            Match instance = new Match();
            instance.matchId = ((long) in.readValue((long.class.getClassLoader())));
            instance.matchSeqNum = ((long) in.readValue((long.class.getClassLoader())));
            instance.startTime = ((int) in.readValue((int.class.getClassLoader())));
            instance.lobbyType = ((int) in.readValue((int.class.getClassLoader())));
            instance.radiantTeamId = ((int) in.readValue((int.class.getClassLoader())));
            instance.direTeamId = ((int) in.readValue((int.class.getClassLoader())));
            in.readList(instance.players, (com.github.randombear.allstatdota.
                    dataaccessobject.entities.Player.class.getClassLoader()));
            return instance;
        }

        public Match[] newArray(int size) {
            return (new Match[size]);
        }

    };

    public long getMatchId() {
        return matchId;
    }

    public void setMatchId(long matchId) {
        this.matchId = matchId;
    }

    public long getMatchSeqNum() {
        return matchSeqNum;
    }

    public void setMatchSeqNum(long matchSeqNum) {
        this.matchSeqNum = matchSeqNum;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getLobbyType() {
        return lobbyType;
    }

    public void setLobbyType(int lobbyType) {
        this.lobbyType = lobbyType;
    }

    public int getRadiantTeamId() {
        return radiantTeamId;
    }

    public void setRadiantTeamId(int radiantTeamId) {
        this.radiantTeamId = radiantTeamId;
    }

    public int getDireTeamId() {
        return direTeamId;
    }

    public void setDireTeamId(int direTeamId) {
        this.direTeamId = direTeamId;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(matchId);
        dest.writeValue(matchSeqNum);
        dest.writeValue(startTime);
        dest.writeValue(lobbyType);
        dest.writeValue(radiantTeamId);
        dest.writeValue(direTeamId);
        dest.writeList(players);
    }

    /**
     * Utility function that returns a Match object when given the corresponding JSON object.
     *
     * @param jsonObject well-formatted jsonObject.
     * @return An instance of the class Match.
     */
    public static Match createFromJSON(JSONObject jsonObject) {
        Match matchFromJSON = new Match();
        try {
            matchFromJSON.setMatchId(jsonObject.getLong("match_id"));
            matchFromJSON.setMatchSeqNum(jsonObject.getLong("match_seq_num"));
            matchFromJSON.setStartTime(jsonObject.getInt("start_time"));
            matchFromJSON.setLobbyType(jsonObject.getInt("lobby_type"));
            matchFromJSON.setRadiantTeamId(jsonObject.getInt("radiant_team_id"));
            matchFromJSON.setDireTeamId(jsonObject.getInt("dire_team_id"));

            matchFromJSON.setPlayers(new LinkedList<Player>());
            JSONArray playersJSON = jsonObject.getJSONArray("players");
            for (int i = 0; i < playersJSON.length(); i++) {
                matchFromJSON.getPlayers().add(Player.createFromJSON(playersJSON.getJSONObject(i)));
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return matchFromJSON;
    }

    @Override
    public String toString() {
        return "Match{" +
                "matchId=" + matchId +
                ", matchSeqNum=" + matchSeqNum +
                ", startTime=" + startTime +
                ", lobbyType=" + lobbyType +
                ", radiantTeamId=" + radiantTeamId +
                ", direTeamId=" + direTeamId +
                ", players=" + players.toString() +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

}
