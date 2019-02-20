package edu.sportanalytics.guiinterface;

public class CubeResource
{
    /*
        Params needed for Cube:
        1. Dimension, 2. Dimension (e.g. Team, Season,...)
        Aggregated Value (e.g. Goals)
        Aggregation Function (Max,Min,Avg,...)
        WHERE condition (optional)

        Example SQL:

        SELECT SOCCER02.SEASONSTAGE.NAME AS SEASON, SOCCER02.TEAM.LONG_NAME AS TEAM, AVG(SOCCER02.MATCH.HOME_TEAM_GOAL) as GOALS
        FROM (SOCCER02.SEASONSTAGE JOIN SOCCER02.MATCH ON SOCCER02.SEASONSTAGE.SEASONSTAGE_ID=SOCCER02.MATCH.SEASONSTAGE_SEASONSTAGE_ID)
        JOIN SOCCER02.TEAM ON SOCCER02.MATCH.HOME_TEAM_API_ID=SOCCER02.TEAM.TEAM_API_ID
        WHERE SOCCER02.MATCH.LEAGUE_LEAGUE_ID=7809
        GROUP BY CUBE(SOCCER02.SEASONSTAGE.NAME, SOCCER02.TEAM.LONG_NAME);
         */

}
