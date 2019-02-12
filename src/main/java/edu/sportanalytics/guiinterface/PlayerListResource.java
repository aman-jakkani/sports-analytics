package edu.sportanalytics.guiinterface;

import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.SportsEnum;
import org.json.JSONObject;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.logging.Logger;

@Path("PlayerListResource")
public class PlayerListResource {
    private static final Logger log = Logger.getLogger(PlayerListResource.class.getName());


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getMatchList(
            @QueryParam("token") int token)
    {
        Token tk = Token.getToken(token);
        log.info("Player list for match "+ tk.getMatchID());
        SportsEnum type;
        if(tk.getSports()==SportsEnum.SOCCER)
        {
            type = SportsEnum.SOCCER;
        }
        else if(tk.getSports()==SportsEnum.BASKETBALL)
        {
            type = SportsEnum.BASKETBALL;
        }
        else
        {
            type = SportsEnum.UNKNOWN;
            log.severe("Unknown sports parameter");
        }

        List<String> homePlayers = DBAccess.getInstance().getController(type).getHomePlayerList(tk.getMatchID());
        List<String> guestPlayers = DBAccess.getInstance().getController(type).getAwayPlayerList(tk.getMatchID());
        List<String> homePlayerIds = DBAccess.getInstance().getController(type).getHomeTeamPlayerID(tk.getMatchID());
        List<String> guestPlayerIds = DBAccess.getInstance().getController(type).getAwayTeamPlayerID(tk.getMatchID());
        //Wrap in in JSON String

        JSONObject jo = new JSONObject();
        jo.put("homePlayers", homePlayers);
        jo.put("homePlayersID", homePlayerIds);
        jo.put("guestPlayers", guestPlayers);
        jo.put("guestPlayersID", guestPlayerIds);

        String returnString = jo.toString();

        log.info("JSON String created: " + returnString);

        return returnString;
    }
}
