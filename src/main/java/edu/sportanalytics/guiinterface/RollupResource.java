package edu.sportanalytics.guiinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import edu.sportanalytics.database.*;
import org.json.JSONObject;
import org.omg.CORBA.UNKNOWN;

@Path("RollupResource")
//Class to retrieve Rollup Statistics
public class RollupResource {
    private static final Logger log = Logger.getLogger(RollupResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getData(@QueryParam("aggregation") String aggregation, @QueryParam("aggregationEnum") String aggData, @QueryParam("sports") String sports, @QueryParam("league") String league) {
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
        CubeRollupData data = DBAccess.getInstance().getController(sport).getRollup(agg, aggregationData, league);
        jo.put("dim1", data.getDim1());
        jo.put("dim2", data.getDim2());
        jo.put("aggie", data.getAggie());
        String returnString = jo.toString();

        log.info("JSON String created: " + returnString);
        return returnString;
    }

}
