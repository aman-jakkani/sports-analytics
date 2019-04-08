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

	public abstract CubeRollupData getCube(AggregationEnum agg, AggregationData aggData ,String league);
	public abstract CubeRollupData getRollup(AggregationEnum agg, AggregationData aggData, String league);




}
