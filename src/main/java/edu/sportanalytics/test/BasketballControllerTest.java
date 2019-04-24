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

    @BeforeEach
    void setUp() {
        this.bc = new BasketballController(DBAccess.getInstance());
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
        List<String> league = this.bc.getLeagues();
        List<String> toComp = new ArrayList<>();
        toComp.add("NBA");
        Assert.assertEquals(toComp, league);
    }

    @Test
    void getTeams() {
        List<String> teams = bc.getTeams("NBA");
        List<String> listOfTeams = new ArrayList<>();
        listOfTeams.add("Hawks");
        listOfTeams.add("Celtics");
        listOfTeams.add("Cavaliers");
        Assert.assertEquals(listOfTeams,teams.subList(0, 3));
    }

    @Test
    void getSeason() {
        List<String> season = bc.getSeason("NBA", "Celtics");
        List<String> listOfSeasons = new ArrayList<>();
        listOfSeasons.add("2009");
        listOfSeasons.add("2011");
        listOfSeasons.add("2014");
        Assert.assertEquals(listOfSeasons,season.subList(0, 3));
    }

    @Test
    void getGame() {
        //List<String> games = bc.getGame("2018", "Celtics");
        //System.out.println(games);
    }

    @Test
    void getHomeAndAwayTeam() {
        //testing Celtics vs Lakers, 2017 season
        List<String> teams = bc.getHomeAndAwayTeam("21700161");
        List<String> listOfTeams = new ArrayList<>();
        listOfTeams.add("Celtics");
        listOfTeams.add("Lakers");
        Assert.assertEquals(listOfTeams, teams);
    }

    @Test
    void homeScoreById() {
        //testing 76ers vs Knicks, 2017 season
        String score = bc.HomeScoreById("21700842");
        Assert.assertEquals(score, "108");
    }

    @Test
    void guestScoreById() {
        //testing Heat vs Spurs, 2017 season
        String score = bc.GuestScoreById("21700059");
        Assert.assertEquals(score, "117");
    }

    @Test
    void getHomePlayerList() {
        //testing Bulls vs Mavericks, 2018 season
        List<String> homeplayers = bc.getHomePlayerList("21800193");
        List<String> listOfPlayers = new ArrayList<>();
        listOfPlayers.add("Harrison Barnes");
        listOfPlayers.add("Luka Doncic");
        listOfPlayers.add("DeAndre Jordan");
        Assert.assertEquals(listOfPlayers,homeplayers.subList(0,3));
    }

    @Test
    void getAwayPlayerList() {
        //testing Celtics vs Lakers, 2017 season
        List<String> awayplayers = bc.getAwayPlayerList("21700161");
        List<String> listOfPlayers = new ArrayList<>();
        listOfPlayers.add("Jayson Tatum");
        listOfPlayers.add("Marcus Morris");
        listOfPlayers.add("Aron Baynes");
        Assert.assertEquals(listOfPlayers,awayplayers.subList(0,3));
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
}