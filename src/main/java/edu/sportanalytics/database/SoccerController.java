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
		leaguesList = findAllLeagues();
	}

	// returns an ArrayList with Name-Attributes of the Leagues-Objects for now
	@Override
	public List<String> getLeagues() {
		List<String> nameLeagues = new ArrayList<String>();
		for (Soccer_League s : leaguesList) {
			nameLeagues.add(s.getNAME());
		}
		return nameLeagues;
	}

	// return LongNames of the Teams for now
	@Override
	public List<String> getTeams() {
		// TODO
		// teamList = findTeams(league);
		List<String> longNameLeagues = new ArrayList<String>();
		for (Soccer_Team s : teamList) {
			longNameLeagues.add(s.getLong_name());
		}
		return longNameLeagues;
	}

	@Override
	public List<String> getSeason(String team) {
		// TODO
		return null;
	}

	@Override
	public List<String> getGame(String season, String team) {
		// TODO
		// matchesList = findMatches(team, stage);
		return null;
	}

	// returns a List of every Match of a Team in a Season
	public List<Soccer_Match> findMatches(Soccer_Team team, Soccer_Seasonstage stage) {
		List<Soccer_Match> tempList = new ArrayList<Soccer_Match>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DBAccess.getConn().createStatement();
			rs = stmt.executeQuery(
					"SELECT m.team_hometeam_id,m.team_awayteam_id FROM SOCCER02.TEAM t join SOCCER02.MATCH m on(t.TEAM_ID = m.team_awayteam_id OR t.TEAM_ID = m.team_hometeam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.LONG_NAME='"
							+ team.long_name + "' AND s.name='" + stage.getName());
			while (rs.next()) {
				Soccer_Match match = new Soccer_Match();
				match.setLeauge_League_ID(rs.getInt("League_League_ID"));
				match.setMatch_ID(rs.getInt("Match_ID"));
				match.setResult(rs.getInt("Result"));
				match.setSeasonstage_Seasonstage_ID(rs.getInt("Seasonstage_Seasonstage_ID"));
				match.setTeam_AwayTeam_ID(rs.getInt("Team_AwayTeam_ID"));
				match.setTeam_HomeTeam_ID(rs.getInt("Team_HomeTeam_ID"));
				match.setDate(rs.getDate("date"));
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
	public List<Soccer_Seasonstage> findSeasonstages(Soccer_League league, Soccer_Team team) {
		List<Soccer_Seasonstage> tempList = new ArrayList<Soccer_Seasonstage>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DBAccess.getConn().createStatement();
			rs = stmt.executeQuery(
					"SELECT DISTINCT s.name FROM SOCCER02.LEAGUE l join SOCCER02.MATCH m on(l.league_id=m.league_league_id) join SOCCER02.TEAM t on(m.AWAY_TEAM_API_ID=t.TEAM_API_ID)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE l.NAME='"
							+ league.getNAME() + "' AND t.LONG_NAME='" + team.getLong_name() + "'");
			while (rs.next()) {
				Soccer_Seasonstage stage = new Soccer_Seasonstage();
				stage.setName(rs.getString("name"));
				stage.setSeasonstage_id(rs.getInt("seasonstage_id"));
				stage.setStage(rs.getInt("stage"));
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
	public List<Soccer_Team> findTeams(Soccer_League league) {
		List<Soccer_Team> tempList = new ArrayList<Soccer_Team>();
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = DBAccess.getConn().createStatement();
			rs = stmt.executeQuery(
					"SELECT DISTINCT t.LONG_NAME FROM SOCCER02.LEAGUE l join SOCCER02.MATCH m on(l.league_id=m.league_league_id) join SOCCER02.TEAM t on(m.AWAY_TEAM_API_ID=t.TEAM_API_ID) WHERE l.NAME='"
							+ league.getNAME() + "'");
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
			rs = stmt.executeQuery("SELECT * FROM SOCCER02.LEAGUE");

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
