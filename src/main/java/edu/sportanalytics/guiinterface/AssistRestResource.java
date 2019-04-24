package edu.sportanalytics.guiinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import edu.sportanalytics.database.BasketballController;
import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.SoccerController;
import edu.sportanalytics.database.SportsEnum;

@Path("AssistRestResource")
public class AssistRestResource {

    private static final Logger log = Logger.getLogger(ScoreRestResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getData(@QueryParam("token") int token) {
        Token tk = Token.getToken(token);
        List<String> assists = new ArrayList<>();

        if (tk.getSports() == SportsEnum.BASKETBALL) {
            BasketballController bc = (BasketballController) DBAccess.getInstance()
                    .getController(SportsEnum.BASKETBALL);

            // assists.add(bc.HomeScoreById(tk.getMatchID()));
            // assists.add(bc.GuestScoreById(tk.getMatchID()));

            JSONObject jo = new JSONObject();
            jo.put("assists", assists);
            String returnString = jo.toString();

            log.info("JSON String created: " + returnString);

            return returnString;

        } else if (tk.getSports() == SportsEnum.SOCCER) {
            // Fill this in later if there is an assists attribute for soccer
            return null;

        } else {
            return null;
        }
    }
}

