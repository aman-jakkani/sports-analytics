package edu.sportanalytics.guiinterface;

import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.Player;
import edu.sportanalytics.database.SportsEnum;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Path("PlayerResource")
public class PlayerResource
{
    private static final Logger log = Logger.getLogger(PlayerResource.class.getName());


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getPlayer(
            @QueryParam("token") int token,
            @QueryParam("playerID") int playerID) {
        Token tk = Token.getToken(token);
        log.info("Player info for " + playerID + " requested");
        SportsEnum type = tk.getSports();
        if (type == SportsEnum.UNKNOWN) {
            log.severe("Unknown sports parameter");
        }

        Player player = DBAccess.getInstance().getController(type).getPlayer(Integer.toString(playerID));

        //Wrap in in JSON String

        JSONObject jo = new JSONObject();

        jo.put("name", player.getName());
        jo.put("birthday", player.getBirthday());
        jo.put("height", player.getHeight());
        jo.put("weight", player.getWeight());

        String returnString = jo.toString();

        log.info("JSON String created: " + returnString);

        return returnString;
    }
}
