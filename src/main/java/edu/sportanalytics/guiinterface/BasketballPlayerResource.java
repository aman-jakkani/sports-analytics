package edu.sportanalytics.guiinterface;

import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.BasketballController;
import edu.sportanalytics.database.SportsEnum;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

public class BasketballPlayerResource {

    private static final Logger log = Logger.getLogger(BasketballPlayerResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getData(@QueryParam("player") int playerid)
    {
        BasketballController bc = (BasketballController) DBAccess.getInstance().getController(SportsEnum.BASKETBALL);

        JSONObject jo = new JSONObject();

        String returnString = jo.toString();

        log.info("JSON String created: " + returnString);

        return returnString;
    }
}
