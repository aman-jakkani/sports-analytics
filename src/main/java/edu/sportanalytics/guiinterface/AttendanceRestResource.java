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
import edu.sportanalytics.database.SportsEnum;

@Path("AttendanceRestResource")
public class AttendanceRestResource {

	
	
	private static final Logger log = Logger.getLogger(AttendanceRestResource.class.getName());

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public String getData(@QueryParam("token") int token) {
		Token tk = Token.getToken(token);

		if(tk.getSports() == SportsEnum.SOCCER)
		{
			return "null";
		}

		BasketballController bc = (BasketballController)  DBAccess.getInstance().getController(SportsEnum.BASKETBALL);
		
		List<String> attendance = new ArrayList<>();
		
		attendance.add(bc.GetAttendanceByGame(tk.getMatchID()));

		
		JSONObject jo = new JSONObject();
		jo.put("attendance", attendance);
		
		String returnString = jo.toString();

		log.info("JSON String created: " + returnString);

		return returnString;
	}

}
