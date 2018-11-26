package edu.sportanalytics.database;

import java.util.List;

public abstract class DatabaseController {
	private DBAccess db;

	public DatabaseController() {
		db = DBAccess.getInstance();

	}

	public DBAccess getDb() {
		return db;
	}

	public abstract List<String> getLeagues();

	public abstract List<String> getTeams(String league);

	public abstract List<String> getSeason(String league, String team);

	public abstract List<String> getGame(String season, String team);
	
	public List<String> getBallPossession(String matchid){
		return null;
	}
	public List<String> getFouls(String matchid){
		return null;
	}

	public List<String> getPointsInSeason(String matchid) {
		// TODO
		return null;
	}

	public List<String> getAvgHeigth(String matchid) {
		// TODO
		return null;
	}

	public List<String> getAvgWeight(String matchid) {
		// TODO
		return null;
	}
	
	public List<String> getHomeAndAwayTeam(String matchid){
		return null;
	}

	/*
	 * The following is only needed for the SoccerController
	 */
	public List<String> getYellowCards(String matchid) {
		return null;
	}

	public List<String> getRedCards(String matchid) {
		return null;
	}

	public List<String> getCornerCnt(String matchid) {
		return null;
	}
}
