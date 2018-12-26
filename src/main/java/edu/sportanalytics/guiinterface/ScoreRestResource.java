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

@Path("ScoreRestResource")
public class ScoreRestResource {

	private static final Logger log = Logger.getLogger(ScoreRestResource.class.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getData(@QueryParam("token") int token) {
		Token tk = Token.getToken(token);

		if (tk.getSports() == SportsEnum.BASKETBALL) {
			BasketballController bc = (BasketballController) DBAccess.getInstance()
					.getController(SportsEnum.BASKETBALL);

			List<String> score = new ArrayList<>();

			score.add(bc.HomeScoreById(tk.getMatchID()));
			score.add(bc.GuestScoreById(tk.getMatchID()));

			JSONObject jo = new JSONObject();
			jo.put("score", score);
			String returnString = jo.toString();

			log.info("JSON String created: " + returnString);

			return returnString;

		} else if (tk.getSports() == SportsEnum.SOCCER) {

			SoccerController sc = (SoccerController) DBAccess.getInstance().getController(SportsEnum.SOCCER);

			List<String> score = new ArrayList<>();
			if (tk.getSeason().equals("null")) {
				score.add(sc.getStatAccumulated(tk.getTeam(), tk.getLeague(), "score"));
			} else if (tk.getMatch().equals("null")) {

				score.add(sc.getStatSeasonAccumulated(tk.getTeam(), tk.getSeason(), "score"));
			} else {
				score = sc.getStatMatch(tk.getMatchID(), "score");
			}

			if (score == null) {
				return "null";
			}

			JSONObject jo = new JSONObject();
			jo.put("score", score);
			String returnString = jo.toString();

			log.info("JSON String created: " + returnString);

			return returnString;

		}

		else {

			return null;
		}

	}

}
