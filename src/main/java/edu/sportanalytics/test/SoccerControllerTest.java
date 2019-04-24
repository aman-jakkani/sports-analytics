package edu.sportanalytics.test;

import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.SoccerController;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

class SoccerControllerTest {
    private SoccerController sc;

    @BeforeEach
    void setUp() {
        this.sc = new SoccerController(DBAccess.getInstance());
    }

    @AfterEach
    void tearDown() {
        // Disconnect from db
    }

    @Test
    public void testDbConnection() {
        //Default Params
        String server = DBAccess.getInstance().getServerURL();
        String port = DBAccess.getInstance().getPort();
        String sid = DBAccess.getInstance().getSid();
        String username = DBAccess.getInstance().getUsername();
        String pw = ""; //Enter db password here before testing but DO NOT push pw to git!!
        char[] pwArr = pw.toCharArray();

        DBAccess.getInstance().setDBParams(server, port, sid, username, pwArr);
    }

    @Test
    void getLeagues() {
        List<String> league = sc.getLeagues();
        List<String> toComp = new ArrayList<>();
        toComp.add("Belgium Jupiler League");
        toComp.add("England Premier League");
        toComp.add("France Ligue 1");
        Assert.assertEquals(toComp, league.subList(0,3));
    }

    @Test
    void getTeams() {
        List<String> teams = sc.getTeams("England Premier League");
        List<String> listOfTeams = new ArrayList<>();
        listOfTeams.add("Aston Villa");
        listOfTeams.add("Bournemouth");
        listOfTeams.add("Tottenham Hotspur");
        Assert.assertEquals(listOfTeams,teams.subList(0, 3));
    }

    @Test
    void getSeason() {
        List<String> season = sc.getSeason("Scotland Premier League", "Celtic");
        List<String> listOfSeasons = new ArrayList<>();
        listOfSeasons.add("2012/2013");
        listOfSeasons.add("2015/2016");
        listOfSeasons.add("2008/2009");
        Assert.assertEquals(listOfSeasons,season.subList(0, 3));
    }

    @Test
    void getGame() {
    }

    @Test
    void getHomeAndAwayTeam() {
        //testing Arsenal vs Manchester City, 2014/2015 season
        List<String> teams = sc.getHomeAndAwayTeam("4329");
        List<String> listOfTeams = new ArrayList<>();
        listOfTeams.add("Arsenal");
        listOfTeams.add("Manchester City");
        Assert.assertEquals(listOfTeams, teams);
    }


    @Test
    void getLeaguesList() {
    }

    @Test
    void getTeamList() {
    }

    @Test
    void getStatMatch() {
    }

    @Test
    void getStatSeasonAccumulated() {
    }

    @Test
    void getStatAccumulated() {
    }

    @Test
    void tryClose() {
    }

    @Test
    void getAwayTeamPlayerID() {
    }

    @Test
    void getHomeTeamPlayerID() {
    }

    @Test
    void getAwayPlayerList() {
    }

    @Test
    void getHomePlayerList() {
    }

    @Test
    void getPlayerStats() {
    }

    @Test
    void getPlayer() {
    }

    @Test
    void getCube() {
    }

    @Test
    void getRollup() {
    }

    @Test
    void setDB() {
    }

    @Test
    void getDb() {
    }
}