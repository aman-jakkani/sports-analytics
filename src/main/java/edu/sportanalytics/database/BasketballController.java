package edu.sportanalytics.database;

import java.util.List;

public class BasketballController extends DatabaseController
{

    public List<String> getLeagues()
    {
        //no leagues in Basketball
        return null;
    }

    public List<String> getTeams() {
        return null;
    }

    public List<String> getSeason(String team) {
        return null;
    }

    public List<String> getGame(String season, String team) {
        return null;
    }
}
