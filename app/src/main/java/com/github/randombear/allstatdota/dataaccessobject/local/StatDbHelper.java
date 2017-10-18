package com.github.randombear.allstatdota.dataaccessobject.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.github.randombear.allstatdota.dataaccessobject.entities.Match;
import com.github.randombear.allstatdota.dataaccessobject.entities.MatchHistory;
import com.github.randombear.allstatdota.dataaccessobject.entities.Player;
import com.github.randombear.allstatdota.dataaccessobject.utility.StatContract.PlayerDetailsEntry;
import com.github.randombear.allstatdota.dataaccessobject.utility.StatContract.MatchDetailsEntry;
import com.github.randombear.allstatdota.dataaccessobject.utility.StatContract.PlayerEntry;
import com.github.randombear.allstatdota.dataaccessobject.utility.StatContract.MatchEntry;
import com.github.randombear.allstatdota.dataaccessobject.utility.StatContract.MatchHistoryEntry;

import java.util.ArrayList;

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

    private static final String SQL_CREATE_TABLE_MATCH_HISTORY = "" +
            "CREATE TABLE " + MatchHistoryEntry.TABLE_NAME + " (" +
            MatchHistoryEntry._ID + " INTEGER PRIMARY KEY, " +
            MatchHistoryEntry.COLUMN_NAME_STATUS + " INTEGER, " +
            MatchHistoryEntry.COLUMN_NAME_NUM_RESULTS + " INTEGER, " +
            MatchHistoryEntry.COLUMN_NAME_TOTAL_RESULTS + " INTEGER, " +
            MatchHistoryEntry.COLUMN_NAME_RESULTS_REMAINING + " INTEGER);";

    private static final String SQL_CREATE_TABLE_MATCHES = "" +
            "CREATE TABLE " + MatchEntry.TABLE_NAME + " (" +
            MatchEntry.COLUMN_NAME_MATCH_ID+ " INTEGER," +
            MatchEntry.COLUMN_NAME_MATCH_SEQ_NUM + " INTEGER," +
            MatchEntry.COLUMN_NAME_START_TIME + " INTEGER," +
            MatchEntry.COLUMN_NAME_LOBBY_TYPE + " INTEGER," +
            MatchEntry.COLUMN_NAME_RADIANT_TEAM_ID + " INTEGER," +
            MatchEntry.COLUMN_NAME_DIRE_TEAM_ID + " INTEGER," +
            MatchEntry.COLUMN_NAME_MATCH_HISTORY + " INTEGER," +
            " FOREIGN KEY (" + MatchEntry.COLUMN_NAME_MATCH_HISTORY + ") REFERENCES " +
            MatchHistoryEntry.TABLE_NAME + "(" + MatchHistoryEntry._ID + "))";

    private static final String SQL_CREATE_TABLE_PLAYER = "" +
            "CREATE TABLE " + PlayerEntry.TABLE_NAME + " (" +
            PlayerEntry.COLUMN_NAME_ACCOUNT_ID + " INTEGER," +
            PlayerEntry.COLUMN_NAME_PLAYER_SLOT + " INTEGER," +
            PlayerEntry.COLUMN_NAME_HERO_ID + " INTEGER," +
            PlayerEntry.COLUMN_NAME_MATCH + " INTEGER," +
            " PRIMARY KEY (" +
            PlayerEntry.COLUMN_NAME_ACCOUNT_ID + "," +
            PlayerEntry.COLUMN_NAME_MATCH + "), " +
            " FOREIGN KEY (" + PlayerEntry.COLUMN_NAME_MATCH + ") REFERENCES " +
            MatchEntry.TABLE_NAME + "(" + MatchEntry.COLUMN_NAME_MATCH_ID + "));";

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
            " FOREIGN KEY (" + MatchDetailsEntry.COLUMN_NAME_MATCH_ID + ") REFERENCES " +
            MatchEntry.TABLE_NAME + "(" + MatchEntry.COLUMN_NAME_MATCH_ID + "));";

    private static final String SQL_CREATE_TABLE_PLAYER_DETAILS = "" +
            "CREATE TABLE " + PlayerDetailsEntry.TABLE_NAME + " (" +
            PlayerDetailsEntry.COLUMN_NAME_ACCOUNT_ID + " INTEGER PRIMARY KEY," +
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
            " FOREIGN KEY (" + PlayerDetailsEntry.COLUMN_NAME_MATCH_ID + ") REFERENCES " +
            MatchDetailsEntry.TABLE_NAME + "(" + MatchDetailsEntry.COLUMN_NAME_MATCH_ID + "));";

    private static final String SQL_DELETE_MATCH_HISTORY =
            "DROP TABLE IF EXISTS " + MatchHistoryEntry.TABLE_NAME;

    private static final String SQL_DELETE_MATCH =
            "DROP TABLE IF EXISTS " + MatchEntry.TABLE_NAME;

    private static final String SQL_DELETE_PLAYER =
            "DROP TABLE IF EXISTS " + PlayerEntry.TABLE_NAME;

    private static final String SQL_DELETE_MATCH_DETAILS =
            "DROP TABLE IF EXISTS " + MatchDetailsEntry.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MATCH_HISTORY);
        db.execSQL(SQL_CREATE_TABLE_MATCHES);
        db.execSQL(SQL_CREATE_TABLE_PLAYER);
        db.execSQL(SQL_CREATE_TABLE_MATCH_DETAILS);
        db.execSQL(SQL_CREATE_TABLE_PLAYER_DETAILS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_MATCH);
        db.execSQL(SQL_DELETE_MATCH_HISTORY);
        db.execSQL(SQL_DELETE_PLAYER);
        db.execSQL(SQL_DELETE_MATCH_DETAILS);
        onCreate(db);
    }

    /**
     * Create a database from scratch containing all the information stored in a MatchHistory
     * object.
     * @param matchHistory          Object containing Matches and Players information.
     * @return                      Number of insertions.
     */
    public int populateDatabase(MatchHistory matchHistory) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        int insertionCounter = 0;

        values.put(MatchHistoryEntry._ID, matchHistory.hashCode());
        values.put(MatchHistoryEntry.COLUMN_NAME_STATUS, matchHistory.getStatus());
        values.put(MatchHistoryEntry.COLUMN_NAME_NUM_RESULTS, matchHistory.getNumResults());
        values.put(MatchHistoryEntry.COLUMN_NAME_TOTAL_RESULTS, matchHistory.getTotalResults());
        values.put(MatchHistoryEntry.COLUMN_NAME_RESULTS_REMAINING, matchHistory.getResultsRemaining());
        db.insert(MatchHistoryEntry.TABLE_NAME, null, values);
        insertionCounter++;
        values.clear();
        for (Match e : matchHistory.getMatches()) {
            values.put(MatchEntry.COLUMN_NAME_MATCH_ID, e.getMatchId());
            values.put(MatchEntry.COLUMN_NAME_MATCH_SEQ_NUM, e.getMatchSeqNum());
            values.put(MatchEntry.COLUMN_NAME_START_TIME, e.getStartTime());
            values.put(MatchEntry.COLUMN_NAME_LOBBY_TYPE, e.getLobbyType());
            values.put(MatchEntry.COLUMN_NAME_RADIANT_TEAM_ID, e.getRadiantTeamId());
            values.put(MatchEntry.COLUMN_NAME_DIRE_TEAM_ID, e.getDireTeamId());
            values.put(MatchEntry.COLUMN_NAME_MATCH_HISTORY, matchHistory.hashCode());
            db.insert(MatchEntry.TABLE_NAME, null, values);
            insertionCounter++;
            values.clear();
            for (Player p : e.getPlayers()) {
                if (p.getAccountId() == 4294967295l) { continue; }
                values.put(PlayerEntry.COLUMN_NAME_ACCOUNT_ID, p.getAccountId());
                values.put(PlayerEntry.COLUMN_NAME_PLAYER_SLOT, p.getPlayerSlot());
                values.put(PlayerEntry.COLUMN_NAME_HERO_ID, p.getHeroId());
                values.put(PlayerEntry.COLUMN_NAME_MATCH, e.getMatchId());
                db.insert(PlayerEntry.TABLE_NAME, null, values);
                values.clear();
                insertionCounter++;
            }
        }
        return insertionCounter;
    }

    /**
     * Read from DB all the information about the matches stored.
     * @return          Returns a MatchHistory object and all its associated matches and players.
     */
    public MatchHistory readMatchHistoryFromDatabase() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM match, player " +
                        "WHERE " +
                        "match_id = match " +
                        "ORDER BY match_seq_num DESC",
                null);
        ArrayList<Match> matches = new ArrayList<>();
        long matchID = 0;
        while(cursor.moveToNext()) {
            long matchIDtemp = cursor.getLong(
                    cursor.getColumnIndexOrThrow(MatchEntry.COLUMN_NAME_MATCH_ID));
            if (matchID != matchIDtemp) {
                Match matchTemp = new Match();
                matchID = matchIDtemp;
                matchTemp.setMatchId(matchIDtemp);
                matchTemp.setMatchSeqNum(cursor.getLong(
                        cursor.getColumnIndexOrThrow(MatchEntry.COLUMN_NAME_MATCH_SEQ_NUM)
                ));
                matchTemp.setStartTime(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchEntry.COLUMN_NAME_START_TIME)
                ));
                matchTemp.setLobbyType(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchEntry.COLUMN_NAME_LOBBY_TYPE)
                ));
                matchTemp.setRadiantTeamId(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchEntry.COLUMN_NAME_RADIANT_TEAM_ID)
                ));
                matchTemp.setDireTeamId(cursor.getInt(
                        cursor.getColumnIndexOrThrow(MatchEntry.COLUMN_NAME_DIRE_TEAM_ID)
                ));
                matchTemp.setPlayers(new ArrayList<Player>());
                matches.add(matchTemp);
            }
            Player player = new Player();
            player.setAccountId(cursor.getLong(
                    cursor.getColumnIndexOrThrow(PlayerEntry.COLUMN_NAME_ACCOUNT_ID)
            ));
            player.setPlayerSlot(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerEntry.COLUMN_NAME_PLAYER_SLOT)
            ));
            player.setHeroId(cursor.getInt(
                    cursor.getColumnIndexOrThrow(PlayerEntry.COLUMN_NAME_HERO_ID)
            ));
            matches.get(matches.size()-1).getPlayers().add(player);
        }
        cursor.close();

        cursor = db.rawQuery("SELECT * FROM match_history", null);

        MatchHistory matchHistory = new MatchHistory();
        cursor.moveToNext();
        matchHistory.setStatus(cursor.getInt(
                cursor.getColumnIndexOrThrow(MatchHistoryEntry.COLUMN_NAME_STATUS)
        ));
        matchHistory.setNumResults(cursor.getInt(
                cursor.getColumnIndexOrThrow(MatchHistoryEntry.COLUMN_NAME_NUM_RESULTS)
        ));
        matchHistory.setResultsRemaining(cursor.getInt(
                cursor.getColumnIndexOrThrow(MatchHistoryEntry.COLUMN_NAME_RESULTS_REMAINING)
        ));
        matchHistory.setTotalResults(cursor.getInt(
                cursor.getColumnIndexOrThrow(MatchHistoryEntry.COLUMN_NAME_TOTAL_RESULTS)
        ));
        matchHistory.setMatches(matches);
        cursor.close();
        return matchHistory;

    }

    public void deleteDatabase(Context context) {
        context.deleteDatabase("dotaStat.db");
    }
}
