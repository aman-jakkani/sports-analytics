package edu.sportanalytics.guiinterface;

import edu.sportanalytics.database.SportsEnum;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;
@Path("RollupTokenResource")
public class RollupTokenResource
{

    private static final Logger log = Logger.getLogger(edu.sportanalytics.guiinterface.RollupTokenResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getToken(
        @QueryParam("aggregfunc") String aggregfunc,
        @QueryParam("aggregstyle") String aggregstyle,
        @QueryParam("factatt") String factatt,
        @QueryParam("dimension") String dimension,
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

        int token = RollupToken.getCurrentCnt();
        new RollupToken(type,league, team, season, factatt, aggregfunc, aggregstyle, dimension);
        // Query for getting list of different Teams in given league
        // Wrap in in JSON String

        JSONObject jo = new JSONObject();
        jo.put("token", token);

        String returnString = jo.toString();

        log.info("JSON String created: " + returnString);

        return returnString;
    }
}

