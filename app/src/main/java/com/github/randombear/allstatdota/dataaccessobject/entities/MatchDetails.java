package com.github.randombear.allstatdota.dataaccessobject.entities;

/**
 * =================================
 * Created by randomBEAR on 21/08/2017.
 * =================================
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MatchDetails implements Parcelable {

    private List<PlayerDetails> players = null;
    private boolean radiantWin;
    private int duration;
    private int preGameDuration;
    private int startTime;
    private long matchId;
    private long matchSeqNum;
    private int towerStatusRadiant;
    private int towerStatusDire;
    private int barracksStatusRadiant;
    private int barracksStatusDire;
    private int cluster;
    private int firstBloodTime;
    private int lobbyType;
    private int humanPlayers;
    private int leagueid;
    private int positiveVotes;
    private int negativeVotes;
    private int gameMode;
    private int flags;
    private int engine;
    private int radiantScore;
    private int direScore;
    public final static Parcelable.Creator<MatchDetails> CREATOR = new Creator<MatchDetails>() {


        @SuppressWarnings({
                "unchecked"
        })
        public MatchDetails createFromParcel(Parcel in) {
            MatchDetails instance = new MatchDetails();
            instance.setPlayers(new ArrayList<PlayerDetails>());
            in.readList(instance.players, (com.github.randombear.allstatdota
                    .dataaccessobject
                    .entities.PlayerDetails.class.getClassLoader()));
            instance.radiantWin = ((boolean) in.readValue((boolean.class.getClassLoader())));
            instance.duration = ((int) in.readValue((int.class.getClassLoader())));
            instance.preGameDuration = ((int) in.readValue((int.class.getClassLoader())));
            instance.startTime = ((int) in.readValue((int.class.getClassLoader())));
            instance.matchId = ((long) in.readValue((long.class.getClassLoader())));
            instance.matchSeqNum = ((long) in.readValue((long.class.getClassLoader())));
            instance.towerStatusRadiant = ((int) in.readValue((int.class.getClassLoader())));
            instance.towerStatusDire = ((int) in.readValue((int.class.getClassLoader())));
            instance.barracksStatusRadiant = ((int) in.readValue((int.class.getClassLoader())));
            instance.barracksStatusDire = ((int) in.readValue((int.class.getClassLoader())));
            instance.cluster = ((int) in.readValue((int.class.getClassLoader())));
            instance.firstBloodTime = ((int) in.readValue((int.class.getClassLoader())));
            instance.lobbyType = ((int) in.readValue((int.class.getClassLoader())));
            instance.humanPlayers = ((int) in.readValue((int.class.getClassLoader())));
            instance.leagueid = ((int) in.readValue((int.class.getClassLoader())));
            instance.positiveVotes = ((int) in.readValue((int.class.getClassLoader())));
            instance.negativeVotes = ((int) in.readValue((int.class.getClassLoader())));
            instance.gameMode = ((int) in.readValue((int.class.getClassLoader())));
            instance.flags = ((int) in.readValue((int.class.getClassLoader())));
            instance.engine = ((int) in.readValue((int.class.getClassLoader())));
            instance.radiantScore = ((int) in.readValue((int.class.getClassLoader())));
            instance.direScore = ((int) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public MatchDetails[] newArray(int size) {
            return (new MatchDetails[size]);
        }

    };

    public List<PlayerDetails> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDetails> players) {
        this.players = players;
    }

    public boolean isRadiantWin() {
        return radiantWin;
    }

    public void setRadiantWin(boolean radiantWin) {
        this.radiantWin = radiantWin;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPreGameDuration() {
        return preGameDuration;
    }

    public void setPreGameDuration(int preGameDuration) {
        this.preGameDuration = preGameDuration;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

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

    public int getTowerStatusRadiant() {
        return towerStatusRadiant;
    }

    public void setTowerStatusRadiant(int towerStatusRadiant) {
        this.towerStatusRadiant = towerStatusRadiant;
    }

    public int getTowerStatusDire() {
        return towerStatusDire;
    }

    public void setTowerStatusDire(int towerStatusDire) {
        this.towerStatusDire = towerStatusDire;
    }

    public int getBarracksStatusRadiant() {
        return barracksStatusRadiant;
    }

    public void setBarracksStatusRadiant(int barracksStatusRadiant) {
        this.barracksStatusRadiant = barracksStatusRadiant;
    }

    public int getBarracksStatusDire() {
        return barracksStatusDire;
    }

    public void setBarracksStatusDire(int barracksStatusDire) {
        this.barracksStatusDire = barracksStatusDire;
    }

    public int getCluster() {
        return cluster;
    }

    public void setCluster(int cluster) {
        this.cluster = cluster;
    }

    public int getFirstBloodTime() {
        return firstBloodTime;
    }

    public void setFirstBloodTime(int firstBloodTime) {
        this.firstBloodTime = firstBloodTime;
    }

    public int getLobbyType() {
        return lobbyType;
    }

    public void setLobbyType(int lobbyType) {
        this.lobbyType = lobbyType;
    }

    public int getHumanPlayers() {
        return humanPlayers;
    }

    public void setHumanPlayers(int humanPlayers) {
        this.humanPlayers = humanPlayers;
    }

    public int getLeagueid() {
        return leagueid;
    }

    public void setLeagueid(int leagueid) {
        this.leagueid = leagueid;
    }

    public int getPositiveVotes() {
        return positiveVotes;
    }

    public void setPositiveVotes(int positiveVotes) {
        this.positiveVotes = positiveVotes;
    }

    public int getNegativeVotes() {
        return negativeVotes;
    }

    public void setNegativeVotes(int negativeVotes) {
        this.negativeVotes = negativeVotes;
    }

    public int getGameMode() {
        return gameMode;
    }

    public void setGameMode(int gameMode) {
        this.gameMode = gameMode;
    }

    public int getFlags() {
        return flags;
    }

    public void setFlags(int flags) {
        this.flags = flags;
    }

    public int getEngine() {
        return engine;
    }

    public void setEngine(int engine) {
        this.engine = engine;
    }

    public int getRadiantScore() {
        return radiantScore;
    }

    public void setRadiantScore(int radiantScore) {
        this.radiantScore = radiantScore;
    }

    public int getDireScore() {
        return direScore;
    }

    public void setDireScore(int direScore) {
        this.direScore = direScore;
    }

    @Override
    public String toString() {
        return "MatchDetails{" +
                "players=" + players.toString() +
                ", radiantWin=" + radiantWin +
                ", duration=" + duration +
                ", preGameDuration=" + preGameDuration +
                ", startTime=" + startTime +
                ", matchId=" + matchId +
                ", matchSeqNum=" + matchSeqNum +
                ", towerStatusRadiant=" + towerStatusRadiant +
                ", towerStatusDire=" + towerStatusDire +
                ", barracksStatusRadiant=" + barracksStatusRadiant +
                ", barracksStatusDire=" + barracksStatusDire +
                ", cluster=" + cluster +
                ", firstBloodTime=" + firstBloodTime +
                ", lobbyType=" + lobbyType +
                ", humanPlayers=" + humanPlayers +
                ", leagueid=" + leagueid +
                ", positiveVotes=" + positiveVotes +
                ", negativeVotes=" + negativeVotes +
                ", gameMode=" + gameMode +
                ", flags=" + flags +
                ", engine=" + engine +
                ", radiantScore=" + radiantScore +
                ", direScore=" + direScore +
                '}';
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(players);
        dest.writeValue(radiantWin);
        dest.writeValue(duration);
        dest.writeValue(preGameDuration);
        dest.writeValue(startTime);
        dest.writeValue(matchId);
        dest.writeValue(matchSeqNum);
        dest.writeValue(towerStatusRadiant);
        dest.writeValue(towerStatusDire);
        dest.writeValue(barracksStatusRadiant);
        dest.writeValue(barracksStatusDire);
        dest.writeValue(cluster);
        dest.writeValue(firstBloodTime);
        dest.writeValue(lobbyType);
        dest.writeValue(humanPlayers);
        dest.writeValue(leagueid);
        dest.writeValue(positiveVotes);
        dest.writeValue(negativeVotes);
        dest.writeValue(gameMode);
        dest.writeValue(flags);
        dest.writeValue(engine);
        dest.writeValue(radiantScore);
        dest.writeValue(direScore);
    }

    public static MatchDetails createFromJSON(JSONObject jsonObject) {
        MatchDetails matchDetails = new MatchDetails();
        try {
            jsonObject = jsonObject.getJSONObject("result");
            matchDetails.setRadiantWin(jsonObject.getBoolean("radiant_win"));
            matchDetails.setDuration(jsonObject.getInt("duration"));
            matchDetails.setPreGameDuration(jsonObject.getInt("pre_game_duration"));
            matchDetails.setStartTime(jsonObject.getInt("start_time"));
            matchDetails.setMatchId(jsonObject.getLong("match_id"));
            matchDetails.setMatchSeqNum(jsonObject.getLong("match_seq_num"));
            matchDetails.setFirstBloodTime(jsonObject.getInt("first_blood_time"));
            matchDetails.setLobbyType(jsonObject.getInt("lobby_type"));
            matchDetails.setHumanPlayers(jsonObject.getInt("human_players"));
            matchDetails.setLeagueid(jsonObject.getInt("leagueid"));
            matchDetails.setPositiveVotes(jsonObject.getInt("positive_votes"));
            matchDetails.setNegativeVotes(jsonObject.getInt("negative_votes"));
            matchDetails.setGameMode(jsonObject.getInt("game_mode"));
            matchDetails.setFlags(jsonObject.getInt("flags"));
            matchDetails.setEngine(jsonObject.getInt("engine"));
            matchDetails.setRadiantScore(jsonObject.getInt("radiant_score"));
            matchDetails.setDireScore(jsonObject.getInt("dire_score"));

            JSONArray playersDetails = jsonObject.getJSONArray("players");
            matchDetails.setPlayers(new LinkedList<PlayerDetails>());
            for (int i = 0; i < playersDetails.length(); i++) {
                matchDetails.getPlayers().add(PlayerDetails
                        .createFromJSON(playersDetails.getJSONObject(i)));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return matchDetails;
    }

    public int describeContents() {
        return 0;
    }
}