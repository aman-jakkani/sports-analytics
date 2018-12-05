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

@Path("RedCardsStatResource")
public class RedCardsStatResource {

	private static final Logger log = Logger.getLogger(RedCardsStatResource.class.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getData(@QueryParam("token") int token) {
		Token tk = Token.getToken(token);
		if (tk.getSports() == SportsEnum.BASKETBALL) {
			return "null";
		}

		SoccerController sc = (SoccerController) DBAccess.getInstance().getController(SportsEnum.SOCCER);
		List<String> redCardList = new ArrayList<>();
		if (tk.getSeason().equals("null")) {
			redCardList.add(sc.getStatAccumulated(tk.getTeam(), tk.getLeague(), "red"));
		} else if (tk.getMatch().equals("null")) {
			redCardList.add(sc.getStatSeasonAccumulated(tk.getTeam(), tk.getSeason(), "red"));
		} else {
			redCardList = sc.getStatMatch(tk.getMatchID(), "red");
		}

		JSONObject jo = new JSONObject();
		jo.put("redCards", redCardList);

		String returnString = jo.toString();

		log.info("JSON String created: " + returnString);

		return returnString;
	}

}
