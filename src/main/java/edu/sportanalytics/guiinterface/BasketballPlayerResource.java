package edu.sportanalytics.guiinterface;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

public class BasketballPlayerResource {

    private static final Logger log = Logger.getLogger(BasketballPlayerResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getData(@QueryParam("player") String player)
    {
        //TO-DO
        return null;
    }
}
