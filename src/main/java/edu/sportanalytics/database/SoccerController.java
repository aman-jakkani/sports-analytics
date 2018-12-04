package edu.sportanalytics.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SoccerController extends DatabaseController

{
	private Statement stmt;
	private PreparedStatement ps;
	private ResultSet rs;
	private List<Soccer_League> leaguesList;
	private List<Soccer_Team> teamList;
	private List<Soccer_Seasonstage> seasonstageList;
	private List<Soccer_Match> matchesList;
	private static final Logger log = Logger.getLogger(SoccerController.class.getName());

	public SoccerController(DBAccess dba) {
		super(dba);
	}

	// returns an ArrayList with Name-Attributes of the Leagues-Objects
	@Override
	public List<String> getLeagues() {
		leaguesList = findAllLeagues();
		List<String> nameLeagues = new ArrayList<String>();
		for (Soccer_League s : leaguesList) {
			nameLeagues.add(s.getNAME());
		}
		return nameLeagues;
	}

	// returns LongNames of the Teams in a specific league
	@Override
	public List<String> getTeams(String league) {
		teamList = findTeams(league);
		List<String> longNameLeagues = new ArrayList<String>();
		for (Soccer_Team s : teamList) {
			longNameLeagues.add(s.getLong_name());
		}
		return longNameLeagues;
	}

	// returns Names of the Seasons where a team participated
	@Override
	public List<String> getSeason(String league, String team) {
		seasonstageList = findSeasonstages(league, team);
		List<String> nameSeasons = new ArrayList<String>();
		for (Soccer_Seasonstage s : seasonstageList) {
			nameSeasons.add(s.getName());
		}
		return nameSeasons;
	}

	// returns formatted Strings to display soccer matches with Teams, Scores
	// and Team_IDs
	@Override
	public List<String> getGame(String season, String team) {
		matchesList = findMatches(team, season);
		List<String> matchesString = new ArrayList<String>();
		for (Soccer_Match s : matchesList) {
			matchesString.add(s.getGastgeber() + " vs " + s.getGast() + " (" + s.getHome_team_goal() + " : "
					+ s.getAway_team_goal() + ") MATCH_ID:" + s.getMatch_ID());
		}
		return matchesString;
	}

	// returns the home- and awayteam for a game
	@Override
	public List<String> getHomeAndAwayTeam(String matchid) {
		List<String> teamList = new ArrayList<String>();
		ps = null;
		rs = null;
		try {
			ps = DBAccess.getConn().prepareStatement(
					"SELECT t1.LONG_NAME AS t1, t2.LONG_NAME AS t2 FROM SOCCER02.MATCH m, SOCCER02.TEAM t1,SOCCER02.TEAM t2 where (t1.team_id=m.team_hometeam_id and t2.team_id = m.team_awayteam_id)AND m.Match_Id =?");

			ps.setString(1, matchid);
			rs = ps.executeQuery();
			if (rs.next()) {
				teamList.add(rs.getString("t1"));
				teamList.add(rs.getString("t2"));
			}

		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();
		return teamList;
	}

	// returns a List containing the sum of red Cards for both half-times for
	// the
	// home- and away-team at a game
	public List<String> getRedCards(String matchid) {
		List<String> redList = new ArrayList<String>();
		ps = null;
		rs = null;
		try {
			ps = DBAccess.getConn().prepareStatement(
					"select ((HOMEREDCNT)+(HOMERED2CNT)) AS SUM_RED_HOME, ((AWAYREDCNT)+(AWAYRED2CNT))SUM_RED_AWAY FROM SOCCER02.MATCHRELDIMMART WHERE Match_ID=?");
			ps.setString(1, matchid);
			rs = ps.executeQuery();
			if (rs.next()) {
				redList.add(Integer.toString(rs.getInt("SUM_RED_HOME")));
				redList.add(Integer.toString(rs.getInt("SUM_YELLOW_AWAY")));
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();
		return redList;
	}

	// returns a List of every Match of a Team in a Season
	public List<Soccer_Match> findMatches(String team, String season) {
		List<Soccer_Match> tempList = new ArrayList<Soccer_Match>();
		ps = null;
		rs = null;
		try {
			ps = DBAccess.getConn().prepareStatement(
					"Select t1.long_name AS t1, t2.long_name AS t2, home_team_goal, away_team_goal, Match_id FROM (SOCCER02.MATCH m join SOCCER02.SEASONSTAGE s on (s.SEASONSTAGE_ID = m.SEASONSTAGE_SEASONSTAGE_ID)),SOCCER02.TEAM t1,SOCCER02.TEAM t2 where (t1.team_id=m.team_hometeam_id and t2.team_id = m.team_awayteam_id)AND s.name=? AND (t1.long_name=? or t2.long_name=?)");
			ps.setString(1, season);
			ps.setString(2, team);
			ps.setString(3, team);
			rs = ps.executeQuery();
			while (rs.next()) {
				Soccer_Match match = new Soccer_Match();
				match.setGastgeber(rs.getString("t1"));
				match.setGast(rs.getString("t2"));
				match.setAway_team_goal(rs.getInt("away_team_goal"));
				match.setHome_team_goal(rs.getInt("home_team_goal"));
				match.setMatch_ID(rs.getInt("Match_ID"));
				tempList.add(match);
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();

		return tempList;
	}

	// returns a List of every Seasonstage for a team in their league
	public List<Soccer_Seasonstage> findSeasonstages(String league, String team) {
		List<Soccer_Seasonstage> tempList = new ArrayList<Soccer_Seasonstage>();
		ps = null;
		rs = null;
		try {
			ps = DBAccess.getConn().prepareStatement(
					"SELECT DISTINCT s.name FROM SOCCER02.LEAGUE l join SOCCER02.MATCH m on(l.league_id=m.league_league_id) join SOCCER02.TEAM t on(m.AWAY_TEAM_API_ID=t.TEAM_API_ID)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE l.NAME=? AND t.LONG_NAME=?");
			ps.setString(1, league);
			ps.setString(2, team);
			rs = ps.executeQuery();
			while (rs.next()) {
				Soccer_Seasonstage stage = new Soccer_Seasonstage();
				stage.setName(rs.getString("name"));
				tempList.add(stage);
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();

		return tempList;
	}

	// returns a List of every Team of the specific League
	public List<Soccer_Team> findTeams(String league) {
		List<Soccer_Team> tempList = new ArrayList<Soccer_Team>();
		ps = null;
		rs = null;
		try {
			ps = DBAccess.getConn().prepareStatement(
					"SELECT DISTINCT t.LONG_NAME, t.SHort_name, t.team_id FROM SOCCER02.LEAGUE l join SOCCER02.MATCH m on(l.league_id=m.league_league_id) join SOCCER02.TEAM t on (t.TEAM_API_ID = m.home_team_api_id)WHERE l.NAME=?");
			ps.setString(1, league);
			rs = ps.executeQuery();
			while (rs.next()) {
				Soccer_Team team = new Soccer_Team();
				team.setLong_name(rs.getString("LONG_NAME"));
				team.setShort_name(rs.getString("SHORT_NAME"));
				team.setTeam_id(rs.getInt("TEAM_ID"));

				tempList.add(team);
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();
		return tempList;
	}

	// returns a List of every Soccer_League
	public List<Soccer_League> findAllLeagues() {
		List<Soccer_League> tempList = new ArrayList<Soccer_League>();
		stmt = null;
		rs = null;
		try {
			stmt = DBAccess.getConn().createStatement();
			rs = stmt.executeQuery("SELECT League_id,name,Country_Country_ID FROM SOCCER02.LEAGUE");

			while (rs.next()) {
				Soccer_League lg = new Soccer_League();
				lg.setLEAGUE_ID(rs.getInt("League_ID"));
				lg.setNAME(rs.getString("Name"));
				lg.setCountry_Country_ID(rs.getInt("Country_Country_ID"));
				tempList.add(lg);
			}

		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();

		return tempList;

	}

	// returns a List containing the sum of yellow Cards for both half-times for
	// the
	// home- and away-team at a game
	public List<String> getYellowCardsMatch(String matchid) {
		List<String> yellowList = new ArrayList<>();
		yellowList.add(Integer.toString(getCardHome(matchid, "yellow")));
		yellowList.add(Integer.toString(getCardAway(matchid, "yellow")));
		return yellowList;
	}

	public List<String> getRedCardsMatch(String matchid) {
		List<String> redList = new ArrayList<>();
		redList.add(Integer.toString(getCardHome(matchid, "red")));
		redList.add(Integer.toString(getCardAway(matchid, "red")));
		return redList;
	}

	public String getYellowCardsAccumulated(String team, String league) {
		return getCardsAccumulated(team, league, "yellow");
	}

	public String getRedCardsAccumulated(String team, String league) {
		return getCardsAccumulated(team, league, "red");
	}

	// returns the yellow or red cards for all Seasons of a Team
	public String getCardsAccumulated(String team, String league, String cardType) {
		int CardsInASeason = 0;
		stmt = null;
		rs = null;
		String query;
		try {
			if (cardType.equals("yellow")) {
				query = "SELECT (m.away" + cardType + "CNT" + "+m.away" + cardType + "2CNT) "
						+ "AS AWAY FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_awayteam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
						+ team + "'";
			} else {
				query = "SELECT m.away" + cardType
						+ "CNT AS AWAY FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_awayteam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
						+ team + "'";
			}
			stmt = DBAccess.getConn().createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				CardsInASeason += rs.getInt("AWAY");
			}
			stmt = DBAccess.getConn().createStatement();
			if (cardType.equals("yellow")) {
				query = "SELECT (m.home" + cardType + "CNT" + "+m.home" + cardType + "2CNT) "
						+ "AS HOME FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_hometeam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
						+ team + "'";
			} else {
				query = "SELECT m.home" + cardType
						+ "CNT AS HOME FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_hometeam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
						+ team + "'";
			}
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				CardsInASeason += rs.getInt("HOME");
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();

		return Integer.toString(CardsInASeason);

	}

	// returns the yellow cards in a season of a team
	public String getYellowCardsSeasonAccumulated(String team, String season) {
		int yellowInASeason = 0;
		List<Soccer_Match> matches = findMatches(team, season);
		for (Soccer_Match m : matches) {
			if (m.getGast().equals(team)) {
				yellowInASeason += getCardAway(Integer.toString(m.getMatch_ID()), "yellow");
			} else if (m.getGastgeber().equals(team)) {
				yellowInASeason += getCardHome(Integer.toString(m.getMatch_ID()), "yellow");
			}
		}
		return Integer.toString(yellowInASeason);
	}

	public String getRedCardsSeasonAccumulated(String team, String season) {
		int redInASeason = 0;
		List<Soccer_Match> matches = findMatches(team, season);
		for (Soccer_Match m : matches) {
			if (m.getGast().equals(team)) {
				redInASeason += getCardAway(Integer.toString(m.getMatch_ID()), "red");
			} else if (m.getGastgeber().equals(team)) {
				redInASeason += getCardHome(Integer.toString(m.getMatch_ID()), "red");
			}
		}
		return Integer.toString(redInASeason);
	}

	public int getCardHome(String matchid, String cardType) {
		int homeCards = 0;
		ps = null;
		rs = null;
		try {
			if (cardType.equals("yellow")) {
				ps = DBAccess.getConn().prepareStatement(
						"select ((HOMEYELLOWCNT)+(HOMEYELLOW2CNT)) AS SUM_HOME FROM SOCCER02.MATCHRELDIMMART WHERE Match_ID=?");
			} else if (cardType.equals("red")) {
				ps = DBAccess.getConn().prepareStatement(
						"select HOMEREDCNT AS SUM_HOME FROM SOCCER02.MATCHRELDIMMART WHERE Match_ID=?");
			}
			ps.setString(1, matchid);
			rs = ps.executeQuery();
			if (rs.next()) {
				homeCards = rs.getInt("SUM_HOME");
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();
		return homeCards;
	}

	public int getCardAway(String matchid, String cardType) {
		int awayCards = 0;
		ps = null;
		rs = null;
		try {
			if (cardType.equals("yellow")) {
				ps = DBAccess.getConn().prepareStatement(
						"select ((AWAYYELLOWCNT)+(AWAYYELLOW2CNT)) AS SUM_AWAY FROM SOCCER02.MATCHRELDIMMART WHERE Match_ID=?");
			} else if (cardType.equals("red")) {
				ps = DBAccess.getConn().prepareStatement(
						"select AWAYREDCNT AS SUM_AWAY FROM SOCCER02.MATCHRELDIMMART WHERE Match_ID=?");
			}
			ps.setString(1, matchid);
			rs = ps.executeQuery();
			if (rs.next()) {
				awayCards = rs.getInt("SUM_AWAY");
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();
		return awayCards;
	}

	public String getStatAccumulated(String team, String league, String stat) {
		int statInASeason = 0;
		stmt = null;
		rs = null;
		try {

			String query = "SELECT away" + stat
					+ "CNT AS AWAY FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_awayteam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "'";

			stmt = DBAccess.getConn().createStatement();
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				statInASeason += rs.getInt("AWAY");
			}
			stmt = DBAccess.getConn().createStatement();
			query = "SELECT home" + stat
					+ "CNT AS HOME FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_hometeam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "'";
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				statInASeason += rs.getInt("HOME");
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();

		return Integer.toString(statInASeason);

	}

	public String getFoulsSeasonAccumulated(String team, String season) {
		int foulsInASeason = 0;
		List<Soccer_Match> matches = findMatches(team, season);
		for (Soccer_Match m : matches) {
			if (m.getGast().equals(team)) {
				foulsInASeason += (getStatTeam("foul", "away", Integer.toString(m.getMatch_ID())));
			} else if (m.getGastgeber().equals(team)) {
				foulsInASeason += (getStatTeam("foul", "home", Integer.toString(m.getMatch_ID())));
			}
		}
		return Integer.toString(foulsInASeason);
	}

	public String getCornersSeasonAccumulated(String team, String season) {
		int cornersInASeason = 0;
		List<Soccer_Match> matches = findMatches(team, season);
		for (Soccer_Match m : matches) {
			if (m.getGast().equals(team)) {
				cornersInASeason += (getStatTeam("corner", "away", Integer.toString(m.getMatch_ID())));
			} else if (m.getGastgeber().equals(team)) {
				cornersInASeason += (getStatTeam("corner", "home", Integer.toString(m.getMatch_ID())));
			}
		}
		return Integer.toString(cornersInASeason);
	}

	public List<String> getFoulsMatch(String matchid) {
		List<String> foulsList = new ArrayList<>();
		foulsList.add(Integer.toString(getStatTeam("foul", "home", matchid)));
		foulsList.add(Integer.toString(getStatTeam("foul", "away", matchid)));
		return foulsList;
	}

	public List<String> getCornersMatch(String matchid) {
		List<String> cornersList = new ArrayList<>();
		cornersList.add(Integer.toString(getStatTeam("corner", "home", matchid)));
		cornersList.add(Integer.toString(getStatTeam("corner", "away", matchid)));
		return cornersList;
	}

	public List<String> getBallPossessionMatch(String matchid) {
		List<String> ballPoessessionList = new ArrayList<>();
		ballPoessessionList.add(Integer.toString(getStatTeam("possession", "home", matchid)));
		ballPoessessionList.add(Integer.toString(getStatTeam("possession", "away", matchid)));
		return ballPoessessionList;
	}

	// returns a stat of the home or away team of a match (currently Fouls and
	// Corners)
	public int getStatTeam(String stat, String homeoraway, String matchid) {
		int statResult = 0;
		stmt = null;
		rs = null;
		try {
			stmt = DBAccess.getConn().createStatement();
			if (stat.equals("foul") || stat.equals("corner")) {
				rs = stmt.executeQuery("SELECT " + homeoraway + stat
						+ "CNT AS STATS FROM SOCCER02.MATCHRELDIMMART WHERE Match_ID='" + matchid + "'");
			} else if (stat.equals("possession")) {
				if (homeoraway.equals("home")) {
					rs = stmt.executeQuery(
							"SELECT ((HOMEPOS_FSTHALF + HOMEPOS_SCNDHALF)/2) AS STATS FROM SOCCER02.MATCHRELDIMMART WHERE MATCH_ID ="
									+ matchid);
				} else {
					rs = stmt.executeQuery(
							"SELECT ((AWAYPOS_FSTHALF + AWAYPOS_SCNDHALF)/2) AS STATS FROM SOCCER02.MATCHRELDIMMART WHERE MATCH_ID ="
									+ matchid);
				}
			}
			while (rs.next()) {
				statResult = rs.getInt("STATS");
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();
		return statResult;
	}

	public List<Soccer_League> getLeaguesList() {
		return leaguesList;
	}

	public List<Soccer_Team> getTeamList() {
		return teamList;
	}

	public List<String> getScore(String id) {
		List<String> s = new ArrayList<>();
		ps = null;
		rs = null;

		try {
			ps = DBAccess.getConn()
					.prepareStatement("SELECT HOME_TEAM_GOAL, AWAY_TEAM_GOAL FROM Match WHERE MATCH_ID = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();

			while (rs.next()) {
				s.add(rs.getString("HOME_TEAM_GOAL"));
				s.add(rs.getString("AWAY_TEAM_GOAL"));

			}
		} catch (SQLException e) {

			log.severe(e.getMessage());
		}

		tryClose();

		return s;
	}

	public void tryClose() {
		try {
			if (stmt != null) {
				stmt.close();
			}
			if (ps != null) {
				ps.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			log.severe("tryClose: " + e.getMessage());
		}
	}

}
