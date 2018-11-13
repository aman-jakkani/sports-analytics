package edu.sportanalytics.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SoccerController extends DatabaseController

{
	private List<Soccer_League> leaguesList;
	private List<Soccer_Team> teamList;
	private List<Soccer_Seasonstage> seasonstageList;
	private List<Soccer_Match> matchesList;
	private static final Logger log = Logger.getLogger(SoccerController.class.getName());

	public SoccerController() {
		super();
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
		System.out.println(leaguesList);
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

	@Override
	public List<String> getGame(String season, String team) {
		matchesList = findMatches(team, season);
		List<String> matchesString = new ArrayList<String>();
		for (Soccer_Match s : matchesList) {
			matchesString.add(s.getGastgeber() + " vs " + s.getGast() + " (" + s.getHome_team_goal() + " : "
					+ s.getAway_team_goal() + ")");
		}

		return matchesString;
	}

	@Override
	public List<String> getYellowCards(int matchid) {
		// TODO
		List<String> tempList = findYellowCards(matchid);
		return tempList;
	}

	public List<String> findYellowCards(int matchid) {
		List<String> tempList = new ArrayList<String>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DBAccess.getConn().createStatement();
			rs = stmt.executeQuery(
					"SELECT HOMEYELLOWCNT,AWAYYELLOWCNT FROM SOCCER02.MATCHRELDIMMART WHERE Match_Id =" + matchid);
			while (rs.next()) {
				tempList.add(Integer.toString(rs.getInt("HOMEYELLOWCNT")));
				tempList.add(Integer.toString(rs.getInt("AWAYYELLOWCNT")));
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		return tempList;

	}

	// returns a List of every Match of a Team in a Season
	public List<Soccer_Match> findMatches(String team, String season) {
		List<Soccer_Match> tempList = new ArrayList<Soccer_Match>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DBAccess.getConn().createStatement();

			rs = stmt.executeQuery(
					"Select t1.long_name AS t1, t2.long_name AS t2, home_team_goal, away_team_goal FROM (SOCCER02.MATCH m join SOCCER02.SEASONSTAGE s on (s.SEASONSTAGE_ID = m.SEASONSTAGE_SEASONSTAGE_ID)),SOCCER02.TEAM t1,SOCCER02.TEAM t2 where (t1.team_id=m.team_hometeam_id and t2.team_id = m.team_awayteam_id)AND s.name='"
							+ season + "' AND (t1.long_name='" + team + "' or t2.long_name='" + team + "')");
			while (rs.next()) {
				Soccer_Match match = new Soccer_Match();
				match.setGastgeber(rs.getString("t1"));
				match.setGast(rs.getString("t2"));
				match.setAway_team_goal(rs.getInt("away_team_goal"));
				match.setHome_team_goal(rs.getInt("home_team_goal"));
				tempList.add(match);
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				log.severe(e.getMessage());
			}

		}

		return tempList;
	}

	// returns a List of every Seasonstage for a team in their league
	public List<Soccer_Seasonstage> findSeasonstages(String league, String team) {
		List<Soccer_Seasonstage> tempList = new ArrayList<Soccer_Seasonstage>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DBAccess.getConn().createStatement();
			rs = stmt.executeQuery(
					"SELECT DISTINCT s.name FROM SOCCER02.LEAGUE l join SOCCER02.MATCH m on(l.league_id=m.league_league_id) join SOCCER02.TEAM t on(m.AWAY_TEAM_API_ID=t.TEAM_API_ID)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE l.NAME='"
							+ league + "' AND t.LONG_NAME='" + team + "'");
			while (rs.next()) {
				Soccer_Seasonstage stage = new Soccer_Seasonstage();
				stage.setName(rs.getString("name"));
				tempList.add(stage);
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				log.severe(e.getMessage());
			}

		}

		return tempList;
	}

	// returns a List of every Team of the specific League
	public List<Soccer_Team> findTeams(String league) {
		List<Soccer_Team> tempList = new ArrayList<Soccer_Team>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DBAccess.getConn().createStatement();
			rs = stmt.executeQuery(
					"SELECT DISTINCT t.LONG_NAME, t.SHort_name, t.team_id, t.TEAM_API_ID FROM SOCCER02.LEAGUE l join SOCCER02.MATCH m on(l.league_id=m.league_league_id) join SOCCER02.TEAM t on(m.AWAY_TEAM_API_ID=t.TEAM_API_ID) WHERE l.NAME='"
							+ league + "'");
			while (rs.next()) {
				Soccer_Team team = new Soccer_Team();
				team.setLong_name(rs.getString("long_name"));
				team.setShort_name(rs.getString("short_name"));
				team.setTeam_id(rs.getInt("team_id"));
				team.setTeam_api_id(rs.getInt("team_api_id"));
				tempList.add(team);
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				log.severe(e.getMessage());
			}

		}

		return tempList;
	}

	// returns a List of every Soccer_League
	public List<Soccer_League> findAllLeagues() {
		List<Soccer_League> tempList = new ArrayList<Soccer_League>();
		Statement stmt = null;
		ResultSet rs = null;
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
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				log.severe(e.getMessage());
			}

		}
		return tempList;

	}

	public List<Soccer_League> getLeaguesList() {
		return leaguesList;
	}

	public List<Soccer_Team> getTeamList() {
		return teamList;
	}

}
