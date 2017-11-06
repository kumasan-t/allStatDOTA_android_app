package com.github.randombear.allstatdota.dataaccessobject.utility;

import android.provider.BaseColumns;

/**
 * =================================
 * Created by randomBEAR on 07/09/2017.
 * =================================
 */

public final class StatContract {

    private StatContract() {}

    public static class MatchDetailsEntry implements BaseColumns {
        public static final String TABLE_NAME = "match_details";
        public static final String COLUMN_NAME_DURATION = "duration";
        public static final String COLUMN_NAME_PRE_GAME_DURATION = "pre_game_duration";
        public static final String COLUMN_NAME_START_TIME = "start_time";
        public static final String COLUMN_NAME_MATCH_ID = "match_id";
        public static final String COLUMN_NAME_MATCH_SEQ_NUM = "match_seq_num";
        public static final String COLUMN_NAME_TOWER_STATUS_RADIANT = "tower_status_radiant";
        public static final String COLUMN_NAME_TOWER_STATUS_DIRE = "tower_status_dire";
        public static final String COLUMN_NAME_BARRACKS_STATUS_RADIANT = "barracks_status_radiant";
        public static final String COLUMN_NAME_BARRACKS_STATUS_DIRE = "barracks_status_dire";
        public static final String COLUMN_NAME_CLUSTER = "cluster";
        public static final String COLUMN_NAME_FIRST_BLOOD_TIME = "first_blood_time";
        public static final String COLUMN_NAME_LOBBY_TYPE = "lobby_type";
        public static final String COLUMN_NAME_HUMAN_PLAYERS = "human_players";
        public static final String COLUMN_NAME_LEAGUE_ID = "league_id";
        public static final String COLUMN_NAME_GAME_MODE = "game_mode";
        public static final String COLUMN_NAME_FLAGS = "flags";
        public static final String COLUMN_NAME_RADIANT_SCORE = "radiant_score";
        public static final String COLUMN_NAME_DIRE_SCORE = "dire_score";
        public static final String COLUMN_NAME_RADIANT_WIN = "radiant_win";
    }

    public static class PlayerDetailsEntry implements BaseColumns {
        public static final String TABLE_NAME = "player_details";
        public static final String COLUMN_NAME_ACCOUNT_ID = "account_id";
        public static final String COLUMN_NAME_MATCH_ID = "match_id";
        public static final String COLUMN_NAME_PLAYER_SLOT = "player_slot";
        public static final String COLUMN_NAME_HERO_ID = "hero_id";
        public static final String COLUMN_NAME_ITEM_0 = "item_0";
        public static final String COLUMN_NAME_ITEM_1 = "item_1";
        public static final String COLUMN_NAME_ITEM_2 = "item_2";
        public static final String COLUMN_NAME_ITEM_3 = "item_3";
        public static final String COLUMN_NAME_ITEM_4 = "item_4";
        public static final String COLUMN_NAME_ITEM_5 = "item_5";
        public static final String COLUMN_NAME_BACKPACK_0 = "backpack_0";
        public static final String COLUMN_NAME_BACKPACK_1 = "backpack_1";
        public static final String COLUMN_NAME_BACKPACK_2 = "backpack_2";
        public static final String COLUMN_NAME_KILLS = "kills";
        public static final String COLUMN_NAME_DEATHS = "deaths";
        public static final String COLUMN_NAME_ASSISTS = "assists";
        public static final String COLUMN_NAME_LEAVER_STATUS = "leaver_status";
        public static final String COLUMN_NAME_LAST_HITS = "last_hits";
        public static final String COLUMN_NAME_DENIES = "denies";
        public static final String COLUMN_NAME_GOLD_PER_MIN = "gold_per_min";
        public static final String COLUMN_NAME_XP_PER_MIN = "xp_per_min";
        public static final String COLUMN_NAME_LEVEL = "level";
        public static final String COLUMN_NAME_HERO_DAMAGE = "hero_damage";
        public static final String COLUMN_NAME_TOWER_DAMAGE = "tower_damage";
        public static final String COLUMN_NAME_HERO_HEALING = "hero_healing";
        public static final String COLUMN_NAME_GOLD = "gold";
        public static final String COLUMN_NAME_GOLD_SPENT = "gold_spent";
        public static final String COLUMN_NAME_SCALED_HERO_DAMAGE = "scaled_hero_damage";
        public static final String COLUMN_NAME_SCALED_TOWER_DAMAGE = "scaled_tower_damage";
        public static final String COLUMN_NAME_SCALED_HERO_HEALING = "scaled_hero_healing";
    }
}
