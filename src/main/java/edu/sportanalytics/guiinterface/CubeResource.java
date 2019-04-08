package edu.sportanalytics.guiinterface;

import edu.sportanalytics.database.*;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Path("CubeResource")
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
    private static final Logger log = Logger.getLogger(edu.sportanalytics.guiinterface.CubeResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getData(@QueryParam("aggregation") String aggregation, @QueryParam("aggregationData") String aggData,@QueryParam("sports") String sports, @QueryParam("league") String league) {

        AggregationEnum agg = AggregationEnum.UNKNOWN;
        for(AggregationEnum i : AggregationEnum.values())
        {
            if(i.toString().equals(aggregation))
            {
                agg=i;
            }
        }
        AggregationData aggregationData = BasketballAggregationData.UNKNOWN;
        SportsEnum sport = SportsEnum.UNKNOWN;
        if(sports.equals("Soccer"))
        {
            aggregationData = SoccerAggregationData.UNKNOWN;
            sport = SportsEnum.SOCCER;
            for(SoccerAggregationData i : SoccerAggregationData.values())
            {
                if(i.name().equals(aggData))
                {
                    aggregationData = i;
                }
            }

        }
        if(sports.equals("Basketball"))
        {
            sport = SportsEnum.BASKETBALL;
            for(BasketballAggregationData i : BasketballAggregationData.values())
            {
                if(i.name().equals(aggData))
                {
                    aggregationData = i;
                }
            }
        }


        JSONObject jo = new JSONObject();
        CubeRollupData data = DBAccess.getInstance().getController(sport).getCube(agg, aggregationData, league);
        jo.put("dim1", data.getDim1());
        jo.put("dim2", data.getDim2());
        jo.put("aggie", data.getAggie());
        String returnString = jo.toString();

            log.info("JSON String created: " + returnString);
            return returnString;

    }
}

