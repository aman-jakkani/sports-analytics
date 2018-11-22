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

	@Override
	public List<String> getBallPossession(String matchid) {
		List<String> possessionList = new ArrayList<String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBAccess.getConn().prepareStatement(
					"SELECT ((HOMEPOS_FSTHALF + HOMEPOS_SCNDHALF)/2) AS HOMEPOSS, ((AWAYPOS_FSTHALF + AWAYPOS_SCNDHALF)/2) AS AWAYPOSS FROM SOCCER02.MATCHRELDIMMART WHERE MATCH_ID =?");

			ps.setString(1, matchid);
			rs = ps.executeQuery();
			while (rs.next()) {
				possessionList.add(Integer.toString(rs.getInt("HOMEPOSS")));
				possessionList.add(Integer.toString(rs.getInt("AWAYPOSS")));
			}

		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();
		return possessionList;
	}

	// returns a List containing the sum of yellow Cards for both halfs for the
	// home- and awayteam.
	@Override
	public List<String> getYellowCards(String matchid) {
		List<String> yellowList = new ArrayList<String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBAccess.getConn().prepareStatement(
					"select ((HOMEYELLOWCNT)+(HOMEYELLOW2CNT)) AS SUM_YELLOW_HOME, ((AWAYYELLOWCNT)+(AWAYYELLOW2CNT))SUM_YELLOW_AWAY FROM SOCCER02.MATCHRELDIMMART WHERE Match_ID=?");
			ps.setString(1, matchid);
			rs = ps.executeQuery();
			while (rs.next()) {
				yellowList.add(Integer.toString(rs.getInt("SUM_YELLOW_HOME")));
				yellowList.add(Integer.toString(rs.getInt("SUM_YELLOW_AWAY")));
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();
		return yellowList;
	}

	// returns a List containing the sum of red Cards for both halfs for the
	// home- and awayteam.
	@Override
	public List<String> getRedCards(String matchid) {
		List<String> redList = new ArrayList<String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBAccess.getConn().prepareStatement(
					"select ((HOMEREDCNT)+(HOMERED2CNT)) AS SUM_RED_HOME, ((AWAYREDCNT)+(AWAYRED2CNT))SUM_RED_AWAY FROM SOCCER02.MATCHRELDIMMART WHERE Match_ID=?");
			ps.setString(1, matchid);
			rs = ps.executeQuery();
			while (rs.next()) {
				redList.add(Integer.toString(rs.getInt("SUM_RED_HOME")));
				redList.add(Integer.toString(rs.getInt("SUM_YELLOW_AWAY")));
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();
		return redList;
	}

	@Override
	public List<String> getFouls(String matchid) {
		List<String> foulsList = new ArrayList<String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBAccess.getConn()
					.prepareStatement("SELECT HOMEFOULCNT, AWAYFOULCNT FROM SOCCER02.MATCHRELDIMMART WHERE Match_ID=?");
			ps.setString(1, matchid);
			rs = ps.executeQuery();
			while (rs.next()) {
				foulsList.add(Integer.toString(rs.getInt("HOMEFOULCNT")));
				foulsList.add(Integer.toString(rs.getInt("AWAYFOULCNT")));
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();
		return foulsList;
	}

	// returns a List containing number of corners for the
	// home- and awayteam.
	@Override
	public List<String> getCornerCnt(String matchid) {
		List<String> cornersCntList = new ArrayList<String>();
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			ps = DBAccess.getConn().prepareStatement(
					"SELECT HOMECORNERCNT,AWAYCORNERCNT FROM SOCCER02.MATCHRELDIMMART WHERE Match_ID=?");
			ps.setString(1, matchid);
			rs = ps.executeQuery();
			while (rs.next()) {

				cornersCntList.add(String.valueOf(rs.getInt("HOMECORNERCNT")));
				cornersCntList.add(Integer.toString(rs.getInt("AWAYCORNERCNT")));
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();
		return cornersCntList;
	}

	// returns a List of every Match of a Team in a Season
	public List<Soccer_Match> findMatches(String team, String season) {
		List<Soccer_Match> tempList = new ArrayList<Soccer_Match>();
		PreparedStatement ps = null;
		ResultSet rs = null;
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
		PreparedStatement ps = null;
		ResultSet rs = null;
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
		PreparedStatement ps = null;
		ResultSet rs = null;
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
		}
		tryClose();

		return tempList;

	}

	public List<Soccer_League> getLeaguesList() {
		return leaguesList;
	}

	public List<Soccer_Team> getTeamList() {
		return teamList;
	}

	public void tryClose() {
		try {
			if (stmt != null) {
				stmt.close();
			} else if (ps != null) {
				ps.close();
			} else if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			log.severe("tryClose: " + e.getMessage());
		}
	}

}
