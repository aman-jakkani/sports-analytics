package edu.sportanalytics.guiinterface;

import edu.sportanalytics.database.SportsEnum;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

public class TokenResource
{

    private static final Logger log = Logger.getLogger(TokenResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getToken(
            @QueryParam("match") String match,
            @QueryParam("season") String season,
            @QueryParam("team") String team,
            @QueryParam("league") String league,
            @QueryParam("sports") String sports)
     {
        log.info("Token Requested for Game: ");
        SportsEnum type;
        if (sports.equals("Soccer")) {
            type = SportsEnum.SOCCER;
        } else if (sports.equals("Basketball")) {
            type = SportsEnum.BASKETBALL;
        } else {
            type = SportsEnum.UNKNOWN;
            log.severe("Unknown sports parameter: " + sports);
        }

        int token = new Token(type,league, team, season, match).hashCode();
        // Query for getting list of different Teams in given league
        // Wrap in in JSON String

        JSONObject jo = new JSONObject();
        jo.put("token", token);

        String returnString = jo.toString();

        log.info("JSON String created: " + returnString);

        return returnString;
    }
}
