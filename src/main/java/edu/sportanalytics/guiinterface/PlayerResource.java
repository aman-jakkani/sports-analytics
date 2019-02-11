package edu.sportanalytics.guiinterface;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

public class PlayerResource {

    private static final Logger log = Logger.getLogger(PlayerResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPlayerData(@QueryParam("player") String player)
    {
        //TO-DO
        return null;
    }
}
