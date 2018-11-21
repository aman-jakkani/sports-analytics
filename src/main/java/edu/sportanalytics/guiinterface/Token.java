package edu.sportanalytics.guiinterface;

import edu.sportanalytics.database.SportsEnum;

import java.util.HashMap;

public class Token {
	private SportsEnum type;
	private String league;
	private String team;
	private String season;
	private String match;

	public SportsEnum getSports() {
		return type;
	}

	public String getLeague() {
		return league;
	}

	public String getTeam() {
		return team;
	}

	public String getSeason() {
		return season;
	}

	public String getMatch() {
		return match;
	}

	public String getMatchID() {
		int j = 0;
		String matchTemp = getMatch();
		for (int i = 0; i < matchTemp.length(); i++) {
			if (matchTemp.charAt(i) == ':') {
				j = i;
			}
		}
		matchTemp = match.substring(j + 1, matchTemp.length());
		return matchTemp;
	}

	public Token(SportsEnum type, String league, String team, String season, String match) {
		this.type = type;
		this.league = league;
		this.team = team;
		this.season = season;
		this.match = match;

		tokenMap.put(this.hashCode(), this);
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(type, league, team, season, match);
	}

	private static HashMap<Integer, Token> tokenMap = new HashMap<Integer, Token>();

	public static Token getToken(int key) {
		return tokenMap.get(key);
	}
}
