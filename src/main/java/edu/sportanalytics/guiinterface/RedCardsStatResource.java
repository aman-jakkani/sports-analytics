package edu.sportanalytics.guiinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.SoccerController;
import edu.sportanalytics.database.SportsEnum;

public class RedCardsStatResource {

	private static final Logger log = Logger.getLogger(RedCardsStatResource.class.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getData(@QueryParam("token") int token) {
		Token tk = Token.getToken(token);

		SoccerController sc = (SoccerController) DBAccess.getInstance().getController(SportsEnum.SOCCER);
		List<String> redCardList = sc.getRedCards(tk.getMatchID());
		if (tk.getSeason().equals("null")) {
			redCardList.add(sc.getRedCardsAccumulated(tk.getTeam(), tk.getLeague()));
		} else if (tk.getMatch().equals("null")) {

			redCardList.add(sc.getRedCardsSeasonAccumulated(tk.getTeam(), tk.getSeason()));
		} else {
			redCardList = sc.getRedCardsMatch(tk.getMatchID());
		}

		JSONObject jo = new JSONObject();
		jo.put("redCards", redCardList);

		String returnString = jo.toString();

		log.info("JSON String created: " + returnString);

		return returnString;
	}

}
