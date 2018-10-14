package edu.sportanalytics.guiinterface;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
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

        //Query for getting played seasons of given team
        //Wrap in JSON String

        return "Not implemented";
    }
}
