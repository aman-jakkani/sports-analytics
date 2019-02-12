package edu.sportanalytics.guiinterface;

import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.BasketballController;
import edu.sportanalytics.database.Player;
import edu.sportanalytics.database.Basketball_Player;
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
    public String getData(@QueryParam("token") int token, @QueryParam("playerID") int playerID)
    {
        //TO-DO
        Token tk = Token.getToken(token);
        log.info("Player info for " + playerID + " requested");
        SportsEnum type = tk.getSports();
        if (type == SportsEnum.UNKNOWN) {
            log.severe("Unknown sports parameter");
            return null;
        }
        else if(type == SportsEnum.SOCCER) {
            log.severe("Wrong sports parameter");
            return null;
        }
        else {
            BasketballController bc = (BasketballController) DBAccess.getInstance().getController(type);
            Basketball_Player player = bc.getBBPlayer(Integer.toString(playerID));
            JSONObject jo = new JSONObject();
            jo.put("points", player.getPoints());
            jo.put("rebounds", player.getTotalRebounds());
            jo.put("assists", player.getAssists());
            jo.put("steals", player.getSteals());
            jo.put("turnovers", player.getTurnovers());
            jo.put("games", player.getGamesPlayed());
            String returnString = jo.toString();

            log.info("JSON String created: " + returnString);

            return returnString;
        }
    }
}
