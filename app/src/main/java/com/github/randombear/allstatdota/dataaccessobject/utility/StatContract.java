package com.github.randombear.allstatdota.dataaccessobject.utility;

import android.provider.BaseColumns;

/**
 * =================================
 * Created by randomBEAR on 07/09/2017.
 * =================================
 */

public final class StatContract {

    private StatContract() {}

    public static class MatchHistoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "match_history";
        public static final String COLUMN_NAME_STATUS = "status";
        public static final String COLUMN_NAME_NUM_RESULTS = "num_results";
        public static final String COLUMN_NAME_TOTAL_RESULTS = "total_results";
        public static final String COLUMN_NAME_RESULTS_REMAINING = "results_remaining";

    }

    public static class MatchEntry  implements BaseColumns {
        public static final String TABLE_NAME = "match";
        public static final String COLUMN_NAME_MATCH_ID = "match_id";
        public static final String COLUMN_NAME_MATCH_SEQ_NUM = "match_se_num";
        public static final String COLUMN_NAME_START_TIME = "start_time";
        public static final String COLUMN_NAME_LOBBY_TYPE = "lobby_type";
        public static final String COLUMN_NAME_RADIANT_TEAM_ID = "radiant_team_id";
        public static final String COLUMN_NAME_DIRE_TEAM_ID = "dire_team_id";
        public static final String COLUMN_NAME_MATCH_HISTORY = "match_history_id";
    }

    public static class PlayerEntry implements BaseColumns {
        public static final String TABLE_NAME = "player";
        public static final String COLUMN_NAME_ACCOUNT_ID = "account_id";
        public static final String COLUMN_NAME_PLAYER_SLOT = "player_slot";
        public static final String COLUMN_NAME_HERO_ID = "hero_id";
        public static final String COLUMN_NAME_MATCH = "match";
    }
}
