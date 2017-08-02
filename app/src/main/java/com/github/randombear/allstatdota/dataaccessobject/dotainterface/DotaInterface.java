package com.github.randombear.allstatdota.dataaccessobject.dotainterface;

/**
 * =================================
 * Created by randomBEAR on 31/07/2017.
 * =================================
 */

public interface DotaInterface {

    //Dota 2 Matches

    /**
     * Used to get a list of matches played.
     */
    public void getMatchHistory();

    /**
     * Used to get detailed information about a specified match.
     */
    public void getMatchDetails();

    /**
     * Used to get the matches in the order which they were recorded (i.e. sorted ascending by
     * match_seq_num). This means that the first match on the first page of results returned by
     * the call will be the very first public mm-match recorded in the stats. This API combines
     * GetMatchHistory with GetMatchDetails - i.e. each page returned requires no additional calls
     * for more data.
     */
    public void getMatchHistoryBySequenceNumber();

    /**
     * Used to get a list of the tournament leagues that are available for viewing in the client
     * (i.e. you can buy a ticket to them).
     * Intended for use in conjunction with GetLiveLeagueGames.
     */
    public void getLeagueListing();

    /**
     * Used to get a list of the tournament games that are currently in progress.
     */
    public void getLiveLeagueGames();

    /**
     * Used to get data about teams that have been created in the client.
     * Note that this call by default will return a list of every team (with 100 per page)
     * sorted ascending by team_id
     */
    public void getTeamInfoByTeamID();

    /**
     * Used to get details about a player's Steam account.
     */
    public void getPlayerSummaries();

    /**
     * Used to get an UP-TO-DATE list of in-game items.
     * Note that it is important to include a "language" parameter if you want the human-readable names of items.
     *
     * see: https://wiki.teamfortress.com/wiki/WebAPI/GetGameItems .
     */
    public void getGameItems();

    /**
     * Used to get an UP-TO-DATE list of heroes.
     * Note that it is important to include a "language" parameter if you want the human-readable
     * names of heroes.
     * see: https://wiki.teamfortress.com/wiki/WebAPI/GetHeroes .
     */
    public void getHeroes();
}