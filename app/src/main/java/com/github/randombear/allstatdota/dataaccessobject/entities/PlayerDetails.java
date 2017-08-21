package com.github.randombear.allstatdota.dataaccessobject.entities;

/**
 * =================================
 * Created by randomBEAR on 04/08/2017.
 * =================================
 */
import org.json.JSONException;
import org.json.JSONObject;

public class PlayerDetails {

    private long accountId;
    private int playerSlot;
    private int heroId;
    private int item0;
    private int item1;
    private int item2;
    private int item3;
    private int item4;
    private int item5;
    private int backpack0;
    private int backpack1;
    private int backpack2;
    private int kills;
    private int deaths;
    private int assists;
    private int leaverStatus;
    private int lastHits;
    private int denies;
    private int goldPerMin;
    private int xpPerMin;
    private int level;
    private int heroDamage;
    private int towerDamage;
    private int heroHealing;
    private int gold;
    private int goldSpent;
    private int scaledHeroDamage;
    private int scaledTowerDamage;
    private int scaledHeroHealing;
    //private List<AbilityUpgrade> abilityUpgrades = null;

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

    public int getItem0() {
        return item0;
    }

    public void setItem0(int item0) {
        this.item0 = item0;
    }

    public int getItem1() {
        return item1;
    }

    public void setItem1(int item1) {
        this.item1 = item1;
    }

    public int getItem2() {
        return item2;
    }

    public void setItem2(int item2) {
        this.item2 = item2;
    }

    public int getItem3() {
        return item3;
    }

    public void setItem3(int item3) {
        this.item3 = item3;
    }

    public int getItem4() {
        return item4;
    }

    public void setItem4(int item4) {
        this.item4 = item4;
    }

    public int getItem5() {
        return item5;
    }

    public void setItem5(int item5) {
        this.item5 = item5;
    }

    public int getBackpack0() {
        return backpack0;
    }

    public void setBackpack0(int backpack0) {
        this.backpack0 = backpack0;
    }

    public int getBackpack1() {
        return backpack1;
    }

    public void setBackpack1(int backpack1) {
        this.backpack1 = backpack1;
    }

    public int getBackpack2() {
        return backpack2;
    }

    public void setBackpack2(int backpack2) {
        this.backpack2 = backpack2;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getLeaverStatus() {
        return leaverStatus;
    }

    public void setLeaverStatus(int leaverStatus) {
        this.leaverStatus = leaverStatus;
    }

    public int getLastHits() {
        return lastHits;
    }

    public void setLastHits(int lastHits) {
        this.lastHits = lastHits;
    }

    public int getDenies() {
        return denies;
    }

    public void setDenies(int denies) {
        this.denies = denies;
    }

    public int getGoldPerMin() {
        return goldPerMin;
    }

    public void setGoldPerMin(int goldPerMin) {
        this.goldPerMin = goldPerMin;
    }

    public int getXpPerMin() {
        return xpPerMin;
    }

    public void setXpPerMin(int xpPerMin) {
        this.xpPerMin = xpPerMin;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getHeroDamage() {
        return heroDamage;
    }

    public void setHeroDamage(int heroDamage) {
        this.heroDamage = heroDamage;
    }

    public int getTowerDamage() {
        return towerDamage;
    }

    public void setTowerDamage(int towerDamage) {
        this.towerDamage = towerDamage;
    }

    public int getHeroHealing() {
        return heroHealing;
    }

    public void setHeroHealing(int heroHealing) {
        this.heroHealing = heroHealing;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getGoldSpent() {
        return goldSpent;
    }

    public void setGoldSpent(int goldSpent) {
        this.goldSpent = goldSpent;
    }

    public int getScaledHeroDamage() {
        return scaledHeroDamage;
    }

    public void setScaledHeroDamage(int scaledHeroDamage) {
        this.scaledHeroDamage = scaledHeroDamage;
    }

    public int getScaledTowerDamage() {
        return scaledTowerDamage;
    }

    public void setScaledTowerDamage(int scaledTowerDamage) {
        this.scaledTowerDamage = scaledTowerDamage;
    }

    public int getScaledHeroHealing() {
        return scaledHeroHealing;
    }

    public void setScaledHeroHealing(int scaledHeroHealing) {
        this.scaledHeroHealing = scaledHeroHealing;
    }

    @Override
    public String toString() {
        return "PlayerDetails{" +
                "accountId=" + accountId +
                ", playerSlot=" + playerSlot +
                ", heroId=" + heroId +
                ", item0=" + item0 +
                ", item1=" + item1 +
                ", item2=" + item2 +
                ", item3=" + item3 +
                ", item4=" + item4 +
                ", item5=" + item5 +
                ", backpack0=" + backpack0 +
                ", backpack1=" + backpack1 +
                ", backpack2=" + backpack2 +
                ", kills=" + kills +
                ", deaths=" + deaths +
                ", assists=" + assists +
                ", leaverStatus=" + leaverStatus +
                ", lastHits=" + lastHits +
                ", denies=" + denies +
                ", goldPerMin=" + goldPerMin +
                ", xpPerMin=" + xpPerMin +
                ", level=" + level +
                ", heroDamage=" + heroDamage +
                ", towerDamage=" + towerDamage +
                ", heroHealing=" + heroHealing +
                ", gold=" + gold +
                ", goldSpent=" + goldSpent +
                ", scaledHeroDamage=" + scaledHeroDamage +
                ", scaledTowerDamage=" + scaledTowerDamage +
                ", scaledHeroHealing=" + scaledHeroHealing +
                '}';
    }

    public static PlayerDetails createFromJSON(JSONObject jsonObject) {
        PlayerDetails playerDetails = new PlayerDetails();
        try {

            //Issue here: anonymous player ID is 64bit while known player ID is 32bit. Naive solution
            //here was cast every id to a 64 bit ID.
            playerDetails.setAccountId(jsonObject.getLong("account_id"));
            playerDetails.setPlayerSlot(jsonObject.getInt("player_slot"));

            playerDetails.setHeroId( jsonObject.getInt("hero_id"));

            playerDetails.setItem0( jsonObject.getInt("item_0"));
            playerDetails.setItem1( jsonObject.getInt("item_1"));
            playerDetails.setItem2( jsonObject.getInt("item_2"));
            playerDetails.setItem3( jsonObject.getInt("item_3"));
            playerDetails.setItem4( jsonObject.getInt("item_4"));
            playerDetails.setItem5( jsonObject.getInt("item_5"));

            playerDetails.setBackpack0( jsonObject.getInt("backpack_0"));
            playerDetails.setBackpack1( jsonObject.getInt("backpack_1"));
            playerDetails.setBackpack2( jsonObject.getInt("backpack_2"));

            playerDetails.setKills( jsonObject.getInt("kills"));
            playerDetails.setDeaths( jsonObject.getInt("deaths"));
            playerDetails.setAssists( jsonObject.getInt("assists"));
            playerDetails.setLeaverStatus( jsonObject.getInt("leaver_status"));
            playerDetails.setLastHits( jsonObject.getInt("last_hits"));
            playerDetails.setDenies( jsonObject.getInt("denies"));

            playerDetails.setGoldPerMin( jsonObject.getInt("gold_per_min"));
            playerDetails.setXpPerMin( jsonObject.getInt("xp_per_min"));
            playerDetails.setLevel( jsonObject.getInt("level"));
            playerDetails.setHeroDamage( jsonObject.getInt("hero_damage"));
            playerDetails.setTowerDamage( jsonObject.getInt("tower_damage"));
            playerDetails.setHeroHealing( jsonObject.getInt("hero_healing"));
            playerDetails.setGold( jsonObject.getInt("gold"));
            playerDetails.setGoldSpent( jsonObject.getInt("gold_spent"));
            playerDetails.setScaledHeroDamage( jsonObject.getInt("scaled_hero_damage"));
            playerDetails.setScaledTowerDamage( jsonObject.getInt("scaled_tower_damage"));
            playerDetails.setScaledHeroHealing( jsonObject.getInt("scaled_hero_healing"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return playerDetails;
    }

}
