package edu.sportanalytics.database;

import java.util.List;

public abstract class DatabaseController {
	private DBAccess db;

	public DatabaseController() {
		db = DBAccess.createConnection();

	}

	public DBAccess getDb() {
		return db;
	}

	public abstract List<String> getLeagues();

	public abstract List<String> getTeams();

	public abstract List<String> getSeason(String team);

	public abstract List<String> getGame(String season, String team);

}
