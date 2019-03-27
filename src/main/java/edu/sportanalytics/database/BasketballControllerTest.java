package edu.sportanalytics.database;

import java.util.ArrayList;
import java.util.List;
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
}