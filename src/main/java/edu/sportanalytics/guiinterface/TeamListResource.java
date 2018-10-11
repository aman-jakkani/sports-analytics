package edu.sportanalytics.guiinterface;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
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

        //Query for getting list of different Teams in given league
        //Wrap in in JSON String

        return "Not implemented";
    }
}
