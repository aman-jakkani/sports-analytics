package edu.sportanalytics.test;
import java.util.ArrayList;
import java.util.List;
import edu.sportanalytics.database.BasketballController;
import edu.sportanalytics.database.Basketball_League;
import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.SportsEnum;
import org.junit.Assert;
import org.junit.Test;

public class BasketballControllerTest {

    //DBAccess.establishConnection();

    @Test
    public void testGetLeagues() {
        BasketballController bc = (BasketballController) DBAccess.getInstance().getController(SportsEnum.BASKETBALL);
        Assert.assertEquals("NBA", bc.getLeagues());
    }

    @Test
    public void testGetTeams() {
        BasketballController bc = (BasketballController) DBAccess.getInstance().getController(SportsEnum.BASKETBALL);
        Assert.assertEquals(30, bc.getTeams("NBA").size());
    }

    @Test
    public void testGetSeason() {

    }

    @Test
    public void testGetGame() {
    }

    @Test
    public void testGetHomeAndAwayTeam() {
    }

    @Test
    public void testFindAllLeagues() {
    }

    @Test
    public void homeScoreById() {
    }

    @Test
    public void guestScoreById() {
    }

    @Test
    public void getAttendanceByGame() {
    }

    @Test
    public void getLeaguesList() {
    }

    @Test
    public void getTeamList() {
    }

    @Test
    public void getHomePlayerList() {
    }

    @Test
    public void getAwayPlayerList() {
    }

    @Test
    public void getPlayer() {
    }

    @Test
    public void getBBPlayer() {
    }

    @Test
    public void getRollupStats() {
    }

    @Test
    public void getCubeStats() {
    }

    @Test
    public void getHomeTeamPlayerID() {
    }

    @Test
    public void getAwayTeamPlayerID() {
    }

    @Test
    public void getCube() {
    }

    @Test
    public void getRollup() {
    }

    @Test
    public void tryClose() {
    }
}