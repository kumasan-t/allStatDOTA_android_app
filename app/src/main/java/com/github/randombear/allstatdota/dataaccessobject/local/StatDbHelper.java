package com.github.randombear.allstatdota.dataaccessobject.local;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.github.randombear.allstatdota.dataaccessobject.utility.StatContract.PlayerEntry;
import com.github.randombear.allstatdota.dataaccessobject.utility.StatContract.MatchEntry;
import com.github.randombear.allstatdota.dataaccessobject.utility.StatContract.MatchHistoryEntry;

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

    private static final String SQL_DELETE_MATCH_HISTORY =
            "DROP TABLE IF EXISTS " + MatchHistoryEntry.TABLE_NAME;

    private static final String SQL_DELETE_MATCH =
            "DROP TABLE IF EXISTS " + MatchEntry.TABLE_NAME;

    private static final String SQL_DELETE_PLAYER =
            "DROP TABLE IF EXISTS " + PlayerEntry.TABLE_NAME;

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE_MATCH_HISTORY);
        db.execSQL(SQL_CREATE_TABLE_MATCHES);
        db.execSQL(SQL_CREATE_TABLE_PLAYER);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_MATCH);
        db.execSQL(SQL_DELETE_MATCH_HISTORY);
        db.execSQL(SQL_DELETE_PLAYER);
        onCreate(db);
    }
}
