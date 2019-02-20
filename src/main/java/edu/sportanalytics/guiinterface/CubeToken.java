package edu.sportanalytics.guiinterface;

import edu.sportanalytics.database.SportsEnum;

import java.util.HashMap;
import java.util.logging.Logger;

public class CubeToken {
    private static final Logger log = Logger.getLogger(RollupToken.class.getName());

    private SportsEnum type;
    private String league;
    private String team;
    private String season;
    private String aggregval;
    private String aggregfunc;
    private String aggregstyle;
    private String dimension1;
    private String dimension2;

    public SportsEnum getSports() {
        return type;
    }

    public String getLeague() {
        return league;
    }

    public String getTeam() {
        return team;
    }

    public String getSeason() {
        return season;
    }

    public String getAggregationFunction() {
        return aggregfunc;
    }

    public String getAggregationStyle() {
        return aggregstyle;
    }

    public String getAggregationValue() {
        return aggregval;
    }

    public String getDimension1() { return dimension1; }

    public String getDimension2() { return dimension2; }

    public CubeToken(SportsEnum type, String league, String team, String season, String aggregval, String aggregfunc, String aggregstyle, String dimension1, String dimension2) {
        this.type = type;
        this.league = league;
        this.team = team;
        this.season = season;
        this.aggregfunc = aggregfunc;
        this.aggregstyle = aggregstyle;
        this.aggregval = aggregval;
        this.dimension1 = dimension1;
        this.dimension2 = dimension2;

        if(cubetokenMap.containsKey(this.counter))
        {
            cubetokenMap.remove(counter);
        }
        cubetokenMap.put(this.counter, this);
        log.info("Token with cnt " + counter + " created");
        counter--;
        if(counter == 0)
        {
            counter = MAX_MAPSIZE;
            log.warning("Max tokenmapsize. Deleting older Tokens.");
        }
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(type, league, team, season, aggregval, aggregfunc, aggregstyle, dimension1, dimension2);
    }

    private static final int MAX_MAPSIZE = 1000000; //Should be enough for starting
    private static int counter = MAX_MAPSIZE;
    private static HashMap<Integer, CubeToken> cubetokenMap = new HashMap<Integer, CubeToken>();

    public static CubeToken getToken(int key) {
        return cubetokenMap.get(key);
    }
    public static int getCurrentCnt()
    {
        return counter;
    }
}

