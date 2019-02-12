package edu.sportanalytics.guiinterface;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.SportsEnum;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

@Path("TeamListResource")
public class TeamListResource
{
    private static final Logger log = Logger.getLogger(TeamListResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getTeamList(@QueryParam("league") String league, @QueryParam("sports") String sports)
    {
        log.info("Team list for league " + league + " requested");
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
        
        List<String> teams = DBAccess.getInstance().getController(type).getTeams(league);
        Collections.sort(teams);
        //Query for getting list of different Teams in given league
        //Wrap in in JSON String

        JSONObject jo = new JSONObject();
        jo.put("teams", teams);

        String returnString = jo.toString();

        log.info("JSON String created: " + returnString);

        return returnString;
    }
}
