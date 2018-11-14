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

	public List<String> getPointsInSeason(int matchid) {
		// TODO
		return null;
	}

	public List<String> getAvgHeigth(int matchid) {
		// TODO
		return null;
	}

	public List<String> getAvgWeight(int matchid) {
		// TODO
		return null;
	}

	/*
	 * The following is only needed for the SoccerController
	 */
	public List<String> getYellowCards(int matchid) {
		return null;
	}

	public List<String> getRedCards(int matchid) {
		// TODO
		return null;
	}

	public List<String> getCornerCnt(int matchid) {
		return null;
	}

}
