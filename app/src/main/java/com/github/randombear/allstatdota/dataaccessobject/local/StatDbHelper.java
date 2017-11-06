package com.github.randombear.allstatdota.dataaccessobject.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.github.randombear.allstatdota.dataaccessobject.entities.MatchDetails;
import com.github.randombear.allstatdota.dataaccessobject.entities.PlayerDetails;
import com.github.randombear.allstatdota.dataaccessobject.utility.StatContract.PlayerDetailsEntry;
import com.github.randombear.allstatdota.dataaccessobject.utility.StatContract.MatchDetailsEntry;

import java.util.ArrayList;
import java.util.List;

/**
 * =================================
 * Created by randomBEAR on 07/09/2017.
 * =================================
 */

public class StatDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "dotaStat.db";

    public StatDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    private static final String SQL_CREATE_TABLE_MATCH_DETAILS = "" +
            "CREATE TABLE " + MatchDetailsEntry.TABLE_NAME + " (" +
            MatchDetailsEntry.COLUMN_NAME_MATCH_ID + " INTEGER PRIMARY KEY," +
            MatchDetailsEntry.COLUMN_NAME_MATCH_SEQ_NUM + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_DURATION + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_PRE_GAME_DURATION + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_START_TIME + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_TOWER_STATUS_DIRE + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_TOWER_STATUS_RADIANT + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_BARRACKS_STATUS_RADIANT + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_BARRACKS_STATUS_DIRE + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_CLUSTER + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_FIRST_BLOOD_TIME + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_LOBBY_TYPE + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_HUMAN_PLAYERS + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_LEAGUE_ID + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_GAME_MODE + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_FLAGS + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_RADIANT_SCORE + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_DIRE_SCORE + " INTEGER," +
            MatchDetailsEntry.COLUMN_NAME_RADIANT_WIN + " INTEGER" +
            ");";

    private static final String SQL_CREATE_TABLE_PLAYER_DETAILS = "" +
            "CREATE TABLE " + PlayerDetailsEntry.TABLE_NAME + " (" +
            PlayerDetailsEntry.COLUMN_NAME_ACCOUNT_ID + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_MATCH_ID + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_PLAYER_SLOT + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_HERO_ID + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_ITEM_0 + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_ITEM_1 + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_ITEM_2 + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_ITEM_3 + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_ITEM_4 + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_ITEM_5 + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_BACKPACK_0 + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_BACKPACK_1 + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_BACKPACK_2 + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_KILLS + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_DEATHS + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_ASSISTS + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_LEAVER_STATUS + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_LAST_HITS + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_DENIES + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_GOLD_PER_MIN + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_XP_PER_MIN + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_LEVEL + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_HERO_DAMAGE + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_TOWER_DAMAGE + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_HERO_HEALING + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_GOLD + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_GOLD_SPENT + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_SCALED_HERO_DAMAGE + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_SCALED_TOWER_DAMAGE + " INTEGER," +
            PlayerDetailsEntry.COLUMN_NAME_SCALED_HERO_HEALING + " INTEGER," +
            " PRIMARY KEY (" +
            PlayerDetailsEntry.COLUMN_NAME_ACCOUNT_ID + ", " +
            PlayerDetailsEntry.COLUMN_NAME_HERO_ID + ",  " +
            PlayerDetailsEntry.COLUMN_NAME_MATCH_ID + "), " +
            " FOREIGN KEY (" + PlayerDetailsEntry.COLUMN_NAME_MATCH_ID + ") REFERENCES " +
            MatchDetailsEntry.TABLE_NAME + "(" + MatchDetailsEntry.COLUMN_NAME_MATCH_ID + "));";

    private static final String SQL_DELETE_PLAYER =
            "DROP TABLE IF EXISTS " + PlayerDetailsEntry.TABLE_NAME;

    private static final String SQL_DELETE_MATCH_DETAILS =
            "DROP TABLE IF EXISTS " + MatchDetailsEntry.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MATCH_DETAILS);
        db.execSQL(SQL_CREATE_TABLE_PLAYER_DETAILS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_PLAYER);
        db.execSQL(SQL_DELETE_MATCH_DETAILS);
        onCreate(db);
    }

    /**
     * Put match details and player details in the SQLite db.
     * @param matchesDetails    List of detailed matches
     * @return                  Number of matches inserted
     */
    public int putMatchDetails(List<MatchDetails> matchesDetails) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int insertionCounter = 0;

        for (MatchDetails matchDetails: matchesDetails) {
            values.put(MatchDetailsEntry.COLUMN_NAME_MATCH_ID,matchDetails.getMatchId());
            values.put(MatchDetailsEntry.COLUMN_NAME_DURATION,matchDetails.getDuration());
            values.put(MatchDetailsEntry.COLUMN_NAME_START_TIME,matchDetails.getStartTime());
            values.put(MatchDetailsEntry.COLUMN_NAME_MATCH_SEQ_NUM,matchDetails.getMatchSeqNum());
            values.put(MatchDetailsEntry.COLUMN_NAME_PRE_GAME_DURATION,matchDetails.getPreGameDuration());
            values.put(MatchDetailsEntry.COLUMN_NAME_TOWER_STATUS_DIRE,matchDetails.getTowerStatusDire());
            values.put(MatchDetailsEntry.COLUMN_NAME_TOWER_STATUS_RADIANT,matchDetails.getTowerStatusRadiant());
            values.put(MatchDetailsEntry.COLUMN_NAME_BARRACKS_STATUS_DIRE,matchDetails.getBarracksStatusDire());
            values.put(MatchDetailsEntry.COLUMN_NAME_BARRACKS_STATUS_RADIANT,matchDetails.getBarracksStatusRadiant());
            values.put(MatchDetailsEntry.COLUMN_NAME_CLUSTER,matchDetails.getCluster());
            values.put(MatchDetailsEntry.COLUMN_NAME_FIRST_BLOOD_TIME,matchDetails.getFirstBloodTime());
            values.put(MatchDetailsEntry.COLUMN_NAME_LOBBY_TYPE,matchDetails.getLobbyType());
            values.put(MatchDetailsEntry.COLUMN_NAME_HUMAN_PLAYERS,matchDetails.getHumanPlayers());
            values.put(MatchDetailsEntry.COLUMN_NAME_LEAGUE_ID,matchDetails.getLeagueid());
            values.put(MatchDetailsEntry.COLUMN_NAME_GAME_MODE,matchDetails.getGameMode());
            values.put(MatchDetailsEntry.COLUMN_NAME_FLAGS,matchDetails.getFlags());
            values.put(MatchDetailsEntry.COLUMN_NAME_RADIANT_SCORE,matchDetails.getRadiantScore());
            values.put(MatchDetailsEntry.COLUMN_NAME_DIRE_SCORE,matchDetails.getDireScore());
            values.put(MatchDetailsEntry.COLUMN_NAME_RADIANT_WIN,matchDetails.isRadiantWin() ? 1 : 0);
            db.insert(MatchDetailsEntry.TABLE_NAME,null,values);
            values.clear();
            for (PlayerDetails p : matchDetails.getPlayers()) {
                values.put(PlayerDetailsEntry.COLUMN_NAME_ACCOUNT_ID,p.getAccountId());
                values.put(PlayerDetailsEntry.COLUMN_NAME_MATCH_ID,matchDetails.getMatchId());
                values.put(PlayerDetailsEntry.COLUMN_NAME_PLAYER_SLOT,p.getPlayerSlot());
                values.put(PlayerDetailsEntry.COLUMN_NAME_HERO_ID,p.getHeroId());
                values.put(PlayerDetailsEntry.COLUMN_NAME_ITEM_0,p.getItem0());
                values.put(PlayerDetailsEntry.COLUMN_NAME_ITEM_1,p.getItem1());
                values.put(PlayerDetailsEntry.COLUMN_NAME_ITEM_2,p.getItem2());
                values.put(PlayerDetailsEntry.COLUMN_NAME_ITEM_3,p.getItem3());
                values.put(PlayerDetailsEntry.COLUMN_NAME_ITEM_4,p.getItem4());
                values.put(PlayerDetailsEntry.COLUMN_NAME_ITEM_5,p.getItem5());
                values.put(PlayerDetailsEntry.COLUMN_NAME_BACKPACK_0,p.getBackpack0());
                values.put(PlayerDetailsEntry.COLUMN_NAME_BACKPACK_1,p.getBackpack1());
                values.put(PlayerDetailsEntry.COLUMN_NAME_BACKPACK_2,p.getBackpack2());
                values.put(PlayerDetailsEntry.COLUMN_NAME_KILLS,p.getKills());
                values.put(PlayerDetailsEntry.COLUMN_NAME_DEATHS,p.getDeaths());
                values.put(PlayerDetailsEntry.COLUMN_NAME_ASSISTS,p.getAssists());
                values.put(PlayerDetailsEntry.COLUMN_NAME_LEAVER_STATUS,p.getLeaverStatus());
                values.put(PlayerDetailsEntry.COLUMN_NAME_LAST_HITS,p.getLastHits());
                values.put(PlayerDetailsEntry.COLUMN_NAME_DENIES,p.getDenies());
                values.put(PlayerDetailsEntry.COLUMN_NAME_GOLD_PER_MIN,p.getGoldPerMin());
                values.put(PlayerDetailsEntry.COLUMN_NAME_XP_PER_MIN,p.getXpPerMin());
                values.put(PlayerDetailsEntry.COLUMN_NAME_LEVEL,p.getLevel());
                values.put(PlayerDetailsEntry.COLUMN_NAME_HERO_DAMAGE,p.getHeroDamage());
                values.put(PlayerDetailsEntry.COLUMN_NAME_TOWER_DAMAGE,p.getTowerDamage());
                values.put(PlayerDetailsEntry.COLUMN_NAME_HERO_HEALING,p.getHeroHealing());
                values.put(PlayerDetailsEntry.COLUMN_NAME_GOLD,p.getGold());
                values.put(PlayerDetailsEntry.COLUMN_NAME_GOLD_SPENT,p.getGoldSpent());
                values.put(PlayerDetailsEntry.COLUMN_NAME_SCALED_HERO_DAMAGE,p.getScaledHeroDamage());
                values.put(PlayerDetailsEntry.COLUMN_NAME_SCALED_TOWER_DAMAGE,p.getScaledTowerDamage());
                values.put(PlayerDetailsEntry.COLUMN_NAME_SCALED_HERO_HEALING,p.getScaledHeroHealing());
                db.insert(PlayerDetailsEntry.TABLE_NAME,null,values);
                values.clear();

            }
            insertionCounter++;
        }
        return insertionCounter;

    }

    /**
     * Read matches details and player details from the Database
     * @return          A list containing all the MatchDetails objects present on the db.
     */
    public List<MatchDetails> readMatchDetailsFromDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM match_details, player_details " +
                        "WHERE " +
                        "match_details.match_id = player_details.match_id " +
                        "ORDER BY match_seq_num DESC",
                null);
        ArrayList<MatchDetails> matchesDetails = new ArrayList<>();
        long matchID = 0;
        while(cursor.moveToNext()) {
            long matchIDtemp = cursor.getLong(
                    cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_MATCH_ID));
            if (matchID != matchIDtemp) {
                MatchDetails matchTemp = new MatchDetails();
                matchID = matchIDtemp;
                matchTemp.setMatchId(matchIDtemp);
                matchTemp.setMatchSeqNum(cursor.getLong(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_MATCH_SEQ_NUM)
                ));
                matchTemp.setStartTime(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_START_TIME)
                ));
                matchTemp.setLobbyType(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_LOBBY_TYPE)
                ));
                matchTemp.setDuration(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_DURATION)
                ));
                matchTemp.setPreGameDuration(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_PRE_GAME_DURATION
                )));
                matchTemp.setTowerStatusDire(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_TOWER_STATUS_DIRE
                )));
                matchTemp.setTowerStatusRadiant(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_TOWER_STATUS_RADIANT
                )));
                matchTemp.setBarracksStatusDire(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_BARRACKS_STATUS_DIRE
                )));
                matchTemp.setBarracksStatusRadiant(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_BARRACKS_STATUS_RADIANT
                )));
                matchTemp.setCluster(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_CLUSTER
                )));
                matchTemp.setFirstBloodTime(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_FIRST_BLOOD_TIME
                )));
                matchTemp.setHumanPlayers(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_HUMAN_PLAYERS
                )));
                matchTemp.setLeagueid(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_LEAGUE_ID
                )));
                matchTemp.setGameMode(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_GAME_MODE
                )));
                matchTemp.setFlags(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_FLAGS
                )));
                matchTemp.setRadiantScore(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_RADIANT_SCORE
                )));
                matchTemp.setDireScore(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_DIRE_SCORE
                )));
                matchTemp.setRadiantWin(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchDetailsEntry.COLUMN_NAME_RADIANT_WIN
                )) == 1 );
                matchTemp.setPlayers(new ArrayList<PlayerDetails>());
                matchesDetails.add(matchTemp);
            }
            PlayerDetails playerDetails = new PlayerDetails();
            playerDetails.setAccountId(cursor.getLong(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_ACCOUNT_ID)
            ));
            playerDetails.setAssists(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_ASSISTS)
            ) );
            playerDetails.setBackpack0(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_BACKPACK_0)
            ));
            playerDetails.setBackpack1(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_BACKPACK_1)
            ));
            playerDetails.setBackpack2(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_BACKPACK_2)
            ));
            playerDetails.setDeaths(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_DEATHS)
            ));
            playerDetails.setDenies(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_DENIES)
            ));
            playerDetails.setGold(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_GOLD)
            ));
            playerDetails.setGoldPerMin(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_GOLD_PER_MIN)
            ));
            playerDetails.setGoldSpent(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_GOLD_SPENT)
            ));
            playerDetails.setHeroDamage(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_HERO_DAMAGE)
            ));
            playerDetails.setHeroHealing(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_HERO_HEALING)
            ));
            playerDetails.setHeroId(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_HERO_ID)
            ));
            playerDetails.setItem0(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_ITEM_0)
            ));
            playerDetails.setItem1(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_ITEM_1)
            ));
            playerDetails.setItem2(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_ITEM_2)
            ));
            playerDetails.setItem3(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_ITEM_3)
            ));
            playerDetails.setItem4(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_ITEM_4)
            ));
            playerDetails.setItem5(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_ITEM_5)
            ));
            playerDetails.setPlayerSlot(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_PLAYER_SLOT)
            ));
            playerDetails.setLeaverStatus(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_LEAVER_STATUS)
            ));
            playerDetails.setLevel(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_LEVEL)
            ));
            playerDetails.setScaledHeroDamage(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_SCALED_HERO_DAMAGE)
            ));
            playerDetails.setScaledHeroHealing(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_SCALED_HERO_HEALING)
            ));
            playerDetails.setScaledTowerDamage(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_SCALED_TOWER_DAMAGE)
            ));
            playerDetails.setTowerDamage(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_TOWER_DAMAGE)
            ));
            playerDetails.setXpPerMin(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_XP_PER_MIN)
            ));
            playerDetails.setLastHits(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_LAST_HITS)
            ));
            playerDetails.setKills(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerDetailsEntry.COLUMN_NAME_KILLS)
            ));
            matchesDetails.get(matchesDetails.size()-1).getPlayers().add(playerDetails);
        }

        cursor.close();
        return matchesDetails;
    }
}
