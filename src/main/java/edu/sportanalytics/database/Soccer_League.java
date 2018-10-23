package edu.sportanalytics.database;
public class Soccer_League {
	private String NAME;
	private int LEAGUE_ID;

	public Soccer_League(String name, int id) {
		NAME = name;
		LEAGUE_ID = id;

	}

	public String getNAME() {
		return NAME;
	}

	public int getLEAGUE_ID() {
		return LEAGUE_ID;
	}

}
