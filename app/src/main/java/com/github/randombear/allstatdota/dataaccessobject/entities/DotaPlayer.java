package com.github.randombear.allstatdota.dataaccessobject.entities;

/**
 * =================================
 * Created by randomBEAR on 04/08/2017.
 * =================================
 */
import org.json.JSONException;
import org.json.JSONObject;

public class DotaPlayer {

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

    public static DotaPlayer createFromJSON(JSONObject jsonObject) {
        DotaPlayer dotaPlayer = new DotaPlayer();
        try {

            //Issue here: anonymous player ID is 64bit while known player ID is 32bit. Naive solution
            //here was cast every id to a 64 bit ID.
            dotaPlayer.setAccountId(Long.valueOf((Integer) jsonObject.get("account_id")));
            dotaPlayer.setPlayerSlot((int) jsonObject.get("player_slot"));

            dotaPlayer.setHeroId((int) jsonObject.get("hero_id"));

            dotaPlayer.setItem0((int) jsonObject.get("item_0"));
            dotaPlayer.setItem1((int) jsonObject.get("item_1"));
            dotaPlayer.setItem2((int) jsonObject.get("item_2"));
            dotaPlayer.setItem3((int) jsonObject.get("item_3"));
            dotaPlayer.setItem4((int) jsonObject.get("item_4"));
            dotaPlayer.setItem5((int) jsonObject.get("item_5"));

            dotaPlayer.setBackpack0((int) jsonObject.get("backpack_0"));
            dotaPlayer.setBackpack1((int) jsonObject.get("backpack_1"));
            dotaPlayer.setBackpack2((int) jsonObject.get("backpack_2"));

            dotaPlayer.setKills((int) jsonObject.get("kills"));
            dotaPlayer.setDeaths((int) jsonObject.get("deaths"));
            dotaPlayer.setAssists((int) jsonObject.get("assists"));
            dotaPlayer.setLeaverStatus((int) jsonObject.get("leaver_status"));
            dotaPlayer.setLastHits((int) jsonObject.get("last_hits"));
            dotaPlayer.setDenies((int) jsonObject.get("denies"));

            dotaPlayer.setGoldPerMin((int) jsonObject.get("gold_per_min"));
            dotaPlayer.setXpPerMin((int) jsonObject.get("xp_per_min"));
            dotaPlayer.setLevel((int) jsonObject.get("level"));
            dotaPlayer.setHeroDamage((int) jsonObject.get("hero_damage"));
            dotaPlayer.setTowerDamage((int) jsonObject.get("tower_damage"));
            dotaPlayer.setHeroHealing((int) jsonObject.get("hero_healing"));
            dotaPlayer.setGold((int) jsonObject.get("gold"));
            dotaPlayer.setGoldSpent((int) jsonObject.get("gold_spent"));
            dotaPlayer.setScaledHeroDamage((int) jsonObject.get("scaled_hero_damage"));
            dotaPlayer.setScaledTowerDamage((int) jsonObject.get("scaled_tower_damage"));
            dotaPlayer.setScaledHeroHealing((int) jsonObject.get("scaled_hero_healing"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return dotaPlayer;
    }

}
