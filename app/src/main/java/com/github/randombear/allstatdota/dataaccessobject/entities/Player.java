package com.github.randombear.allstatdota.dataaccessobject.entities;

/**
 * =================================
 * Created by randomBEAR on 18/08/2017.
 * =================================
 */

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONException;
import org.json.JSONObject;

public class Player implements Parcelable {

    private long accountId;
    private int playerSlot;
    private int heroId;
    private PlayerDetails playerDetails;

    public final static Parcelable.Creator<Player> CREATOR = new Creator<Player>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Player createFromParcel(Parcel in) {
            Player instance = new Player();
            instance.accountId = ((long) in.readValue((long.class.getClassLoader())));
            instance.playerSlot = ((int) in.readValue((int.class.getClassLoader())));
            instance.heroId = ((int) in.readValue((int.class.getClassLoader())));
            return instance;
        }

        public Player[] newArray(int size) {
            return (new Player[size]);
        }

    };

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public int getPlayerSlot() {
        return playerSlot;
    }

    public void setPlayerSlot(int playerSlot) {
        this.playerSlot = playerSlot;
    }

    public int getHeroId() {
        return heroId;
    }

    public void setHeroId(int heroId) {
        this.heroId = heroId;
    }

    public PlayerDetails getPlayerDetails() {
        return playerDetails;
    }

    public void setPlayerDetails(PlayerDetails playerDetails) {
        this.playerDetails = playerDetails;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(accountId);
        dest.writeValue(playerSlot);
        dest.writeValue(heroId);
    }

    /**
     * Utility function that returns a Player object when given the corresponding JSON object.
     *
     * @param jsonObject well-formatted jsonObject.
     * @return An instance of the class Player.
     */
    public static Player createFromJSON(JSONObject jsonObject) {
        Player playerFromJSON = new Player();
        try {
            playerFromJSON.setAccountId(jsonObject.getLong("account_id"));
            playerFromJSON.setHeroId(jsonObject.getInt("hero_id"));
            playerFromJSON.setPlayerSlot(jsonObject.getInt("player_slot"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return playerFromJSON;
    }

    @Override
    public String toString() {
        return "Player{" +
                "accountId=" + accountId +
                ", playerSlot=" + playerSlot +
                ", heroId=" + heroId +
                '}';
    }

    public int describeContents() {
        return 0;
    }

}