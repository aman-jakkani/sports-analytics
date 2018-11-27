package edu.sportanalytics.guiinterface;

import edu.sportanalytics.database.SportsEnum;

import java.util.HashMap;
import java.util.logging.Logger;

public class Token {
	private static final Logger log = Logger.getLogger(Token.class.getName());

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

		if(tokenMap.containsKey(this.counter))
        {
            tokenMap.remove(counter);
        }
		tokenMap.put(this.counter, this);
		log.info("Token with cnt " + counter + " created");
		counter--;
		if(counter == 0)
        {
            counter = MAX_MAPSIZE;
            log.warning("Max tokenmapsize. Deleting older Tokens.");
        }
	}

	@Override
	public int hashCode() {
		return java.util.Objects.hash(type, league, team, season, match);
	}

	private static final int MAX_MAPSIZE = 1000000; //Should be enough for starting
	private static int counter = MAX_MAPSIZE;
	private static HashMap<Integer, Token> tokenMap = new HashMap<Integer, Token>();

	public static Token getToken(int key) {
		return tokenMap.get(key);
	}
	public static int getCurrentCnt()
	{
		return counter;
	}
}
