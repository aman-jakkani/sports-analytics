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

import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.SoccerController;
import edu.sportanalytics.database.SportsEnum;

@Path("YellowCardsStatResource")
public class YellowCardsStatResource {

	private static final Logger log = Logger.getLogger(YellowCardsStatResource.class.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getData(@QueryParam("token") int token) {
		Token tk = Token.getToken(token);

		SoccerController sc = (SoccerController) DBAccess.getInstance().getController(SportsEnum.SOCCER);

		List<String> yellowCards = new ArrayList<>();
		if (tk.getSeason().equals("null")) {
			yellowCards.add(sc.getYellowCardsAccumulatedSeasons(tk.getTeam(), tk.getLeague()));
		} else if (tk.getMatch().equals("null")) {

			yellowCards.add(sc.getYellowCardsAccumulated(tk.getTeam(), tk.getSeason()));
		} else {
			yellowCards = sc.getYellowCards(tk.getMatchID());
		}
		System.out.println(tk.getMatch());
		JSONObject jo = new JSONObject();
		jo.put("yellowCards", yellowCards);

		String returnString = jo.toString();

		log.info("JSON String created: " + returnString);

		return returnString;
	}

}
