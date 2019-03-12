package edu.sportanalytics.guiinterface;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import edu.sportanalytics.database.CubeRollupData;
import org.json.JSONObject;

import edu.sportanalytics.database.BasketballController;
import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.SportsEnum;

@Path("RollupResource")
//Class to retrieve Rollup Statistics
public class RollupResource {
    private static final Logger log = Logger.getLogger(RollupResource.class.getName());

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getData() {
        JSONObject jo = new JSONObject();
        CubeRollupData data = DBAccess.getInstance().getController(SportsEnum.SOCCER).getRollup();
        jo.put("dim1", data.getDim1());
        jo.put("dim2", data.getDim2());
        jo.put("aggie", data.getAggie());
        String returnString = jo.toString();

        log.info("JSON String created: " + returnString);
        return returnString;
    }

}
