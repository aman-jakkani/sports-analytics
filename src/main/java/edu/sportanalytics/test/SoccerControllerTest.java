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
    void getAwayTeamPlayerID() {
        //testing Arsenal vs Manchester City, 2014/2015 season
        List<String> awayplayers = sc.getAwayTeamPlayerID("4329");
        List<String> listOfPlayers = new ArrayList<>();
        listOfPlayers.add("31432");
        listOfPlayers.add("30509");
        listOfPlayers.add("30459");
        Assert.assertEquals(listOfPlayers,awayplayers.subList(0,3));
    }

    @Test
    void getHomeTeamPlayerID() {
        //testing Arsenal vs Manchester City, 2014/2015 season
        List<String> homeplayers = sc.getHomeTeamPlayerID("4329");
        List<String> listOfPlayers = new ArrayList<>();
        listOfPlayers.add("169718");
        listOfPlayers.add("26154");
        listOfPlayers.add("46539");
        Assert.assertEquals(listOfPlayers,homeplayers.subList(0,3));
    }

    @Test
    void getAwayPlayerList() {
        //testing Arsenal vs Manchester City, 2014/2015 season
        List<String> awayplayers = sc.getAwayPlayerList("4329");
        List<String> listOfPlayers = new ArrayList<>();
        listOfPlayers.add("Joe Hart");
        listOfPlayers.add("Pablo Zabaleta");
        listOfPlayers.add("Martin Demichelis");
        Assert.assertEquals(listOfPlayers,awayplayers.subList(0,3));
    }

    @Test
    void getHomePlayerList() {
        List<String> homeplayers = sc.getHomePlayerList("4329");
        List<String> listOfPlayers = new ArrayList<>();
        listOfPlayers.add("Wojciech Szczesny");
        listOfPlayers.add("Mathieu Debuchy");
        listOfPlayers.add("Laurent Koscielny");
        Assert.assertEquals(listOfPlayers,homeplayers.subList(0,3));
    }

}