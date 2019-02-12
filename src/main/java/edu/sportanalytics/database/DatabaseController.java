package edu.sportanalytics.database;

import java.util.List;

public abstract class DatabaseController {
	private DBAccess db;

	public DatabaseController(DBAccess dba) {

	}

	public void setDB(DBAccess dba) {
		db = dba;
	}

	public DBAccess getDb() {
		return db;
	}

	public abstract List<String> getLeagues();

	public abstract List<String> getTeams(String league);

	public abstract List<String> getSeason(String league, String team);

	public abstract List<String> getGame(String season, String team);

	public abstract List<String> getHomeAndAwayTeam(String matchid);
	
	public abstract List<String> getAwayTeamPlayerID(String matchid);
	
	public abstract List<String> getHomeTeamPlayerID(String matchid);

	public abstract List<String> getAwayPlayerList(String matchid);

	public abstract List<String> getHomePlayerList(String matchid);

	public abstract Player getPlayer(String playerID);

	public List<String> getBallPossession(String matchid) {
		return null;
	}

	public List<String> getFouls(String matchid) {
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



}
