package edu.sportanalytics.guiinterface;

import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.SportsEnum;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Path("LeagueListResource")
public class LeagueListResource
{
    private static final Logger log = Logger.getLogger(LeagueListResource.class.getName());


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getData(@QueryParam("sports")String sports)
    {
        log.info("League list for sport " + sports + " requested");

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
        List<String> leagues = DBAccess.getInstance().getController(type).getLeagues();
        //Here call query to receive all leagues for this particular sports
        //Wrap it in JSON String

        return "Not implemented";
    }
}
