package edu.sportanalytics.test;

import edu.sportanalytics.database.DBAccess;
import edu.sportanalytics.database.SoccerController;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SoccerControllerTest {
    private SoccerController sc;

    @BeforeEach
    void setUp() {
        this.sc = new SoccerController(DBAccess.getInstance());
    }

    @AfterEach
    void tearDown() {
        // Disconnect from db?
        // Release resources?
    }

    @Test
    void getLeagues() {


    }

    @Test
    void getTeams() {
    }

    @Test
    void getSeason() {
    }

    @Test
    void getGame() {
    }

    @Test
    void getHomeAndAwayTeam() {
    }

    @Test
    void findMatches() {
    }

    @Test
    void findSeasonstages() {
    }

    @Test
    void findTeams() {
    }

    @Test
    void findAllLeagues() {
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