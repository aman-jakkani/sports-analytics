package edu.sportanalytics.database;

import java.util.ArrayList;
import java.util.List;

public class SoccerController extends DatabaseController

{
	private ArrayList<Soccer_League> leaguesList;

	public SoccerController() {
		super();

		leaguesList = super.getDb().createQueryLeagues("SELECT NAME FROM SOCCER02.LEAGUE");

	}

	public List<String> getLeagues() {
		List<String> nameLeagues = null;
		for (Soccer_League s : leaguesList) {
			nameLeagues.add(s.getNAME());
		}
		return nameLeagues;
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
