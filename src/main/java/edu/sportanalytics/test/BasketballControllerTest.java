package edu.sportanalytics.test;
import java.util.ArrayList;
import java.util.List;
import edu.sportanalytics.database.BasketballController;
import edu.sportanalytics.database.Basketball_League;
import edu.sportanalytics.database.DBAccess;
import org.junit.Assert;
import org.junit.Test;

public class BasketballControllerTest {

    @Test
    public void testGetLeagues() {
        BasketballController bc = new BasketballController(DBAccess.getInstance());
        List<Basketball_League> leaguesList = bc.findAllLeagues();
        List<String> leagueNames = new ArrayList<String>();
        for (Basketball_League b : leaguesList){
            leagueNames.add(b.getName());
        }
        Assert.assertEquals(leagueNames, bc.getLeagues());
    }

    @Test
    public void testGetTeams() {
        //To-Do
        BasketballController bc = new BasketballController(DBAccess.getInstance());
        List<Basketball_League> leaguesList = bc.findTeams(league);
        List<String> leagueNames = new ArrayList<String>();
        for (Basketball_League b : teamList){
            leagueNames.add(b.getName());
        }
        Assert.assertEquals(teamNames, bc.getTeams());
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