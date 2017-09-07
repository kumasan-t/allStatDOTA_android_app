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
        public static final String COLUMN_NAME_MATCHES = "matches";
    }
}
