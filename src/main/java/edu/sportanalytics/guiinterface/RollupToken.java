package edu.sportanalytics.guiinterface;

import edu.sportanalytics.database.SportsEnum;

import java.util.HashMap;
import java.util.logging.Logger;

public class RollupToken {
    private static final Logger log = Logger.getLogger(RollupToken.class.getName());

    private SportsEnum type;
    private String league;
    private String team;
    private String season;
    private String factatt;
    private String aggregfunc;
    private String aggregstyle;
    private String dimension;

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

    public String getFactAttribute() {
        return factatt;
    }

    public String getDimension() {
        return dimension;
    }

    public RollupToken(SportsEnum type, String league, String team, String season, String factatt, String aggregfunc, String aggregstyle, String dimension) {
        this.type = type;
        this.league = league;
        this.team = team;
        this.season = season;
        this.aggregfunc = aggregfunc;
        this.aggregstyle = aggregstyle;
        this.factatt = factatt;
        this.dimension = dimension;

        if(rolltokenMap.containsKey(this.counter))
        {
            rolltokenMap.remove(counter);
        }
        rolltokenMap.put(this.counter, this);
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
        return java.util.Objects.hash(type, league, team, season, factatt, aggregfunc, aggregstyle, dimension);
    }

    private static final int MAX_MAPSIZE = 1000000; //Should be enough for starting
    private static int counter = MAX_MAPSIZE;
    private static HashMap<Integer, RollupToken> rolltokenMap = new HashMap<Integer, RollupToken>();

    public static RollupToken getToken(int key) {
        return rolltokenMap.get(key);
    }
    public static int getCurrentCnt()
    {
        return counter;
    }
}
