package edu.sportanalytics.guiinterface;

import edu.sportanalytics.database.SoccerController;
import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.SportsEnum;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

public class SoccerPlayerResource {

    private static final Logger log = Logger.getLogger(SoccerPlayerResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getData(@QueryParam("player") String player)
    {
        //TO-DO
        SoccerController sc = (SoccerController) DBAccess.getInstance().getController(SportsEnum.SOCCER);

        JSONObject jo = new JSONObject();

        String returnString = jo.toString();

        log.info("JSON String created: " + returnString);

        return returnString;
    }
}