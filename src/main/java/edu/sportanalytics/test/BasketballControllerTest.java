package edu.sportanalytics.test;

import edu.sportanalytics.database.*;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.lang.model.type.ArrayType;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BasketballControllerTest {
    private BasketballController bc;
    private List<Basketball_League> leaguesList;
    private List<Basketball_Team> teamList;
    private List<Basketball_Game> gamesList;
    private List<Basketball_Season> seasonList;

    @BeforeEach
    void setUp() {
        this.bc = new BasketballController(DBAccess.getInstance());
    }

    @AfterEach
    void tearDown() {
        // Disconnect from db
    }

    @Test
    void getLeagues() {
        List<String> league = this.bc.getLeagues();
        List<String> toComp = new ArrayList<>();
        toComp.add("NBA");
        Assert.assertEquals(league, toComp);
    }

    @Test
    void getTeams() {
        List<String> teams = bc.getTeams("NBA");
        List<String> listOfTeams = new ArrayList<>();
        listOfTeams.add("Hawks");
        listOfTeams.add("Celtics");
        listOfTeams.add("Cavaliers");
        Assert.assertEquals(teams.subList(0,3), listOfTeams);
    }


    @Test
    void getGame() {
    }

    @Test
    void getHomeAndAwayTeam() {
    }

    @Test
    void homeScoreById() {
    }

    @Test
    void guestScoreById() {
    }

    @Test
    void getAttendanceByGame() {
    }

    @Test
    void getLeaguesList() {
    }

    @Test
    void getTeamList() {
    }

    @Test
    void getHomePlayerList() {
    }

    @Test
    public void testDbConnection()
    {
        //Default Params
        String server = DBAccess.getInstance().getServerURL();
        String port = DBAccess.getInstance().getPort();
        String sid = DBAccess.getInstance().getSid();
        String username = DBAccess.getInstance().getUsername();
        String pw = ""; //Enter db password here before testing but DO NOT push pw to git!!
        char[] pwArr = pw.toCharArray();

        DBAccess.getInstance().setDBParams(server, port, sid, username, pwArr );
    }

    @Test
    void getPlayer() {
    }

    @Test
    void getBBPlayer() {
    }

    @Test
    void getRollupStats() {
    }

    @Test
    void getCubeStats() {
    }

    @Test
    void getHomeTeamPlayerID() {
    }

    @Test
    void getAwayTeamPlayerID() {
    }

    @Test
    void getCube() {
    }

    @Test
    void getRollup() {
    }

    @Test
    void tryClose() {
    }

    @Test
    void setDB() {
    }

    @Test
    void getDb() {
    }

    @Test
    void getLeagues1() {
    }

    @Test
    void getTeams1() {
    }

    @Test
    void getSeason1() {
    }

    @Test
    void getGame1() {
    }

    @Test
    void getHomeAndAwayTeam1() {
    }

    @Test
    void getAwayTeamPlayerID1() {
    }

    @Test
    void getHomeTeamPlayerID1() {
    }

    @Test
    void getAwayPlayerList1() {
    }

    @Test
    void getHomePlayerList1() {
    }

    @Test
    void getPlayer1() {
    }

    @Test
    void getCube1() {
    }

    @Test
    void getRollup1() {
    }
}