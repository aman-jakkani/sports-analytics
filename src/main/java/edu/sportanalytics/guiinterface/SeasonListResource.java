package edu.sportanalytics.guiinterface;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.SportsEnum;

import java.util.List;
import java.util.logging.Logger;

@Path("SeasonListResource")
public class SeasonListResource
{
    private static final Logger log = Logger.getLogger(SeasonListResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getSeasonList(
            @QueryParam("team") String team,
            @QueryParam("league") String league,
            @QueryParam("sports") String sports)
    {
        log.info("Season list for team " + team + " requested");
        SportsEnum type;
        if(sports.equals("Soccer"))
        {
            type = SportsEnum.SOCCER;
        }
        else if(sports.equals("Basketball"))
        {
            type = SportsEnum.BASKETBALL;
        }
        else
        {
            type = SportsEnum.UNKNOWN;
            log.severe("Unknown sports parameter: " + sports);
        }
        
        List<String> season = DBAccess.getInstance().getController(type).getSeason(league, team);
        //Query for getting list of different Teams in given league
        //Wrap in in JSON String

        JSONObject jo = new JSONObject();
        jo.put("seasons", season);

        String returnString = jo.toString();

        log.info("JSON String created: " + returnString);

        return returnString;
    }
}
