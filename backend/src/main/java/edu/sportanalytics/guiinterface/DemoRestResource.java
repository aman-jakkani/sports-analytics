package edu.sportanalytics.guiinterface;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path("DemoRestResource")
public class DemoRestResource
{
    private static final Logger log = Logger.getLogger(DemoRestResource.class.getName());

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getDemoResource()
    {
        log.info("DemoRestResource requested");
        return "Just a demo message!";
    }
}
