package edu.sportanalytics.database;

import java.util.List;

public class BasketballController extends DatabaseController
{

    public List<String> getLeagues()
    {
        //no leagues in Basketball
        return null;
    }



	@Override
	public List<String> getTeams(String league) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getSeason(String league, String team) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public List<String> getGame(String season, String team) {
		// TODO Auto-generated method stub
		return null;
	}
}
