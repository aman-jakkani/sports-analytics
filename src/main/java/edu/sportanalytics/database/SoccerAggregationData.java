package edu.sportanalytics.database;


public enum SoccerAggregationData implements AggregationData
{
    GOALS
    {
        @Override
        public String toString()
        {
            return  "LEFT JOIN( " +
                    "   SELECT SOCCER02.MATCHRELDIMMART.TEAM_AWAYTEAM_ID AS ATID, SOCCER02.MATCHRELDIMMART.MATCH_ID AS AMID, SOCCER02.MATCHRELDIMMART.AWAY_TEAM_GOAL AS AWAY " +
                    "   FROM SOCCER02.MATCHRELDIMMART " +
                    ") " +
                    "ON (SOCCER02.MATCH.MATCH_ID=AMID AND SOCCER02.TEAM.TEAM_ID=ATID) " +
                    "LEFT JOIN( " +
                    "   SELECT SOCCER02.MATCHRELDIMMART.TEAM_HOMETEAM_ID AS HTID, SOCCER02.MATCHRELDIMMART.MATCH_ID AS HMID, SOCCER02.MATCHRELDIMMART.HOME_TEAM_GOAL AS HOME " +
                    "   FROM SOCCER02.MATCHRELDIMMART " +
                    ") " +
                    "ON (SOCCER02.MATCH.MATCH_ID=HMID AND SOCCER02.TEAM.TEAM_ID=HTID)";

        }
    },
    RED_CARDS
    {
        @Override
        public String toString()
        {
            return  "LEFT JOIN( " +
                    "   SELECT SOCCER02.MATCHRELDIMMART.TEAM_AWAYTEAM_ID AS ATID, SOCCER02.MATCHRELDIMMART.MATCH_ID AS AMID, SOCCER02.MATCHRELDIMMART.AWAYREDCNT AS AWAY " +
                    "   FROM SOCCER02.MATCHRELDIMMART " +
                    ") " +
                    "ON (SOCCER02.MATCH.MATCH_ID=AMID AND SOCCER02.TEAM.TEAM_ID=ATID) " +
                    "LEFT JOIN( " +
                    "   SELECT SOCCER02.MATCHRELDIMMART.TEAM_HOMETEAM_ID AS HTID, SOCCER02.MATCHRELDIMMART.MATCH_ID AS HMID, SOCCER02.MATCHRELDIMMART.HOMEREDCNT AS HOME " +
                    "   FROM SOCCER02.MATCHRELDIMMART " +
                    ") " +
                    "ON (SOCCER02.MATCH.MATCH_ID=HMID AND SOCCER02.TEAM.TEAM_ID=HTID)";

        }
    },
    YELLOW_CARDS
    {
        @Override
        public String toString()
        {
            return  "LEFT JOIN( " +
                    "   SELECT SOCCER02.MATCHRELDIMMART.TEAM_AWAYTEAM_ID AS ATID, SOCCER02.MATCHRELDIMMART.MATCH_ID AS AMID, SOCCER02.MATCHRELDIMMART.AWAYYELLOWCNT + SOCCER02.MATCHRELDIMMART.AWAYYELLOW2CNT AS AWAY " +
                    "   FROM SOCCER02.MATCHRELDIMMART " +
                    ") " +
                    "ON (SOCCER02.MATCH.MATCH_ID=AMID AND SOCCER02.TEAM.TEAM_ID=ATID) " +
                    "LEFT JOIN( " +
                    "   SELECT SOCCER02.MATCHRELDIMMART.TEAM_HOMETEAM_ID AS HTID, SOCCER02.MATCHRELDIMMART.MATCH_ID AS HMID, SOCCER02.MATCHRELDIMMART.HOMEYELLOWCNT + SOCCER02.MATCHRELDIMMART.HOMEYELLOW2CNT AS HOME " +
                    "   FROM SOCCER02.MATCHRELDIMMART " +
                    ") " +
                    "ON (SOCCER02.MATCH.MATCH_ID=HMID AND SOCCER02.TEAM.TEAM_ID=HTID)";

        }
    },
    FOULS
    {
        @Override
        public String toString()
        {
            return  "LEFT JOIN( " +
                    "   SELECT SOCCER02.MATCHRELDIMMART.TEAM_AWAYTEAM_ID AS ATID, SOCCER02.MATCHRELDIMMART.MATCH_ID AS AMID, SOCCER02.MATCHRELDIMMART.AWAYFOULCNT AS AWAY " +
                    "   FROM SOCCER02.MATCHRELDIMMART " +
                    ") " +
                    "ON (SOCCER02.MATCH.MATCH_ID=AMID AND SOCCER02.TEAM.TEAM_ID=ATID) " +
                    "LEFT JOIN( " +
                    "   SELECT SOCCER02.MATCHRELDIMMART.TEAM_HOMETEAM_ID AS HTID, SOCCER02.MATCHRELDIMMART.MATCH_ID AS HMID, SOCCER02.MATCHRELDIMMART.HOMEFOULCNT AS HOME " +
                    "   FROM SOCCER02.MATCHRELDIMMART " +
                    ") " +
                    "ON (SOCCER02.MATCH.MATCH_ID=HMID AND SOCCER02.TEAM.TEAM_ID=HTID)";

        }
    },
    BALL_POSSESSION
    {
        @Override
        public String toString()
        {
            return  "LEFT JOIN( " +
                    "   SELECT SOCCER02.MATCHRELDIMMART.TEAM_AWAYTEAM_ID AS ATID, SOCCER02.MATCHRELDIMMART.MATCH_ID AS AMID, (SOCCER02.MATCHRELDIMMART.AWAYPOS_FSTHALF + AWAYPOS_SCNDHALF)/2 AS AWAY " +
                    "   FROM SOCCER02.MATCHRELDIMMART " +
                    ") " +
                    "ON (SOCCER02.MATCH.MATCH_ID=AMID AND SOCCER02.TEAM.TEAM_ID=ATID) " +
                    "LEFT JOIN( " +
                    "   SELECT SOCCER02.MATCHRELDIMMART.TEAM_HOMETEAM_ID AS HTID, SOCCER02.MATCHRELDIMMART.MATCH_ID AS HMID, (SOCCER02.MATCHRELDIMMART.HOMEPOS_FSTHALF + HOMEPOS_SCNDHALF)/2 AS HOME " +
                    "   FROM SOCCER02.MATCHRELDIMMART " +
                    ") " +
                    "ON (SOCCER02.MATCH.MATCH_ID=HMID AND SOCCER02.TEAM.TEAM_ID=HTID)";
        }
    },
    CORNERS
    {
        @Override
        public String toString()
        {
            return  "LEFT JOIN( " +
                    "   SELECT SOCCER02.MATCHRELDIMMART.TEAM_AWAYTEAM_ID AS ATID, SOCCER02.MATCHRELDIMMART.MATCH_ID AS AMID, SOCCER02.MATCHRELDIMMART.AWAYCORNERCNT AS AWAY " +
                    "   FROM SOCCER02.MATCHRELDIMMART " +
                    ") " +
                    "ON (SOCCER02.MATCH.MATCH_ID=AMID AND SOCCER02.TEAM.TEAM_ID=ATID) " +
                    "LEFT JOIN( " +
                    "   SELECT SOCCER02.MATCHRELDIMMART.TEAM_HOMETEAM_ID AS HTID, SOCCER02.MATCHRELDIMMART.MATCH_ID AS HMID, SOCCER02.MATCHRELDIMMART.HOMECORNERCNT AS HOME " +
                    "   FROM SOCCER02.MATCHRELDIMMART " +
                    ") " +
                    "ON (SOCCER02.MATCH.MATCH_ID=HMID AND SOCCER02.TEAM.TEAM_ID=HTID)";

        }
    },
    UNKNOWN
}
