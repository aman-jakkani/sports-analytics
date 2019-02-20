package edu.sportanalytics.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

	public List<Soccer_League> getLeaguesList() {
		return leaguesList;
	}

	public List<Soccer_Team> getTeamList() {
		return teamList;
	}

	// This method returns a piece of data for the home- and away-team for a
	// statistic stat
	public List<String> getStatMatch(String matchid, String stat) {
		List<String> list = new ArrayList<>();
		stmt = null;
		rs = null;
		String query = "";
		switch (stat) {
		case "possession":
			query = "SELECT (homepos_fsthalf+homepos_scndhalf)/2 as HOME, (awaypos_fsthalf+awaypos_scndhalf)/2 as AWAY FROM SOCCER02.MATCHRELDIMMART WHERE match_id="
					+ matchid;
			break;
		case "foul":
			query = "SELECT homefoulcnt as HOME, awayfoulcnt as AWAY FROM SOCCER02.MATCHRELDIMMART WHERE match_id="
					+ matchid;
			break;
		case "goal":
			break;
		case "corner":
			query = "SELECT homecornercnt as HOME, awaycornercnt as AWAY FROM SOCCER02.MATCHRELDIMMART WHERE match_id="
					+ matchid;
			break;
		case "red":
			query = "SELECT homeredcnt as HOME, awayredcnt as AWAY FROM SOCCER02.MATCHRELDIMMART WHERE match_id="
					+ matchid;
			break;
		case "yellow":
			query = "SELECT (homeyellowcnt+homeyellow2cnt) as HOME, (awayyellowcnt+awayyellow2cnt) as AWAY FROM SOCCER02.MATCHRELDIMMART WHERE match_id="
					+ matchid;
			break;
		case "score":
			query = "SELECT HOME_TEAM_GOAL AS HOME, AWAY_TEAM_GOAL AS AWAY FROM SOCCER02.Match WHERE MATCH_ID ="
					+ matchid;
			break;
		}

		try {
			stmt = DBAccess.getConn().createStatement();
			rs = stmt.executeQuery(query);
			while (rs.next()) {
				list.add(Integer.toString(rs.getInt("HOME")));
				list.add(Integer.toString(rs.getInt("AWAY")));
			}

		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();
		return list;
	}

	// This method returns a cummulative value for a statistic stat for a team in a
	// season
	public String getStatSeasonAccumulated(String team, String season, String stat) {
		int statResult = 0;
		stmt = null;
		rs = null;
		String queryHome = "";
		String queryAway = "";
		switch (stat) {
		case "foul":
			queryHome = "SELECT homefoulcnt AS HOME FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_hometeam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "' and s.name='" + season + "'";
			queryAway = "SELECT awayfoulcnt AS AWAY FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_awayteam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "' and s.name='" + season + "'";
			break;
		case "goal":
			break;
		case "corner":
			queryHome = "SELECT homecornercnt AS HOME FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_hometeam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "' and s.name='" + season + "'";
			queryAway = "SELECT awaycornercnt AS AWAY FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_awayteam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "' and s.name='" + season + "'";
			break;
		case "red":
			queryHome = "SELECT homeredcnt AS HOME FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_hometeam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "' and s.name='" + season + "'";
			queryAway = "SELECT awayredcnt AS AWAY FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_awayteam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "' and s.name='" + season + "'";
			break;
		case "yellow":
			queryHome = "SELECT (homeyellowcnt+homeyellow2cnt) AS HOME FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_hometeam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "' and s.name='" + season + "'";
			queryAway = "SELECT (awayyellowcnt+awayyellow2cnt) AS AWAY FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_awayteam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "' and s.name='" + season + "'";
			break;
		case "score":
			queryHome = "SELECT HOME_TEAM_GOAL AS HOME FROM SOCCER02.TEAM t join SOCCER02.MATCH m on(t.TEAM_ID = m.team_hometeam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "' and s.name='" + season + "'";
			queryAway = "SELECT AWAY_TEAM_GOAL AS AWAY FROM SOCCER02.TEAM t join SOCCER02.MATCH m on(t.TEAM_ID = m.team_awayteam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "' and s.name='" + season + "'";
			break;
		}
		try {
			stmt = DBAccess.getConn().createStatement();
			rs = stmt.executeQuery(queryHome);
			while (rs.next()) {
				statResult += rs.getInt("HOME");
			}
			rs = stmt.executeQuery(queryAway);
			while (rs.next()) {
				statResult += rs.getInt("AWAY");
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();
		return Integer.toString(statResult);
	}

	// This method returns a cummulative value for a statistic stat for a team in
	// all seasons
	public String getStatAccumulated(String team, String league, String stat) {
		int statResult = 0;
		stmt = null;
		rs = null;
		String queryHome = "";
		String queryAway = "";
		switch (stat) {
		case "foul":
			queryHome = "SELECT homefoulcnt AS HOME FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_hometeam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "'";
			queryAway = "SELECT awayfoulcnt AS AWAY FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_awayteam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "'";
			break;
		case "goal":
			break;
		case "corner":
			queryHome = "SELECT homecornercnt AS HOME FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_hometeam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "'";
			queryAway = "SELECT awaycornercnt AS AWAY FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_awayteam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "'";
			break;
		case "red":
			queryHome = "SELECT homeredcnt AS HOME FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_hometeam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "'";
			queryAway = "SELECT awayredcnt AS AWAY FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_awayteam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "'";
			break;
		case "yellow":
			queryHome = "SELECT (homeyellowcnt+homeyellow2cnt) AS HOME FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_hometeam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "'";
			queryAway = "SELECT (awayyellowcnt+awayyellow2cnt) AS AWAY FROM SOCCER02.TEAM t join SOCCER02.MATCHRELDIMMART m on(t.TEAM_ID = m.team_awayteam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "'";
			break;
		case "score":
			queryHome = "SELECT HOME_TEAM_GOAL AS HOME FROM SOCCER02.TEAM t join SOCCER02.MATCH m on(t.TEAM_ID = m.team_hometeam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "'";
			queryAway = "SELECT AWAY_TEAM_GOAL AS AWAY FROM SOCCER02.TEAM t join SOCCER02.MATCH m on(t.TEAM_ID = m.team_awayteam_id)join SOCCER02.SEASONSTAGE s on(m.SEASONSTAGE_SEASONSTAGE_ID=s.SEASONSTAGE_ID) WHERE t.long_name='"
					+ team + "'";
			break;
		}
		try {
			stmt = DBAccess.getConn().createStatement();
			rs = stmt.executeQuery(queryHome);
			while (rs.next()) {
				statResult += rs.getInt("HOME");
			}
			rs = stmt.executeQuery(queryAway);
			while (rs.next()) {
				statResult += rs.getInt("AWAY");
			}
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();
		return Integer.toString(statResult);

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


	@Override
	public List<String> getAwayTeamPlayerID(String matchid) {
		ArrayList<String> playerAwayList = new ArrayList<>();
		rs = null;
		ps = null;
		try{
			ps = DBAccess.getConn().prepareStatement("SELECT PLAYER_PLAYER_ID1, PLAYER_PLAYER_ID2, PLAYER_PLAYER_ID3, PLAYER_PLAYER_ID4, PLAYER_PLAYER_ID5, PLAYER_PLAYER_ID6, PLAYER_PLAYER_ID7, PLAYER_PLAYER_ID8, PLAYER_PLAYER_ID9, PLAYER_PLAYER_ID10, PLAYER_PLAYER_ID11 FROM SOCCER02.MATCHLINEUP l JOIN SOCCER02.MATCH m on(l.matchlineup_id = m.matchlineup_awaymatchlineup_id) WHERE m.match_id = ?");
			ps.setString(1, matchid);
			String columnName = "PLAYER_PLAYER_ID";
			rs = ps.executeQuery();
			rs.next();
			for(int i = 1; i<12; i++){
				playerAwayList.add(Integer.toString(rs.getInt(columnName+Integer.toString(i))));
			}
		
		}catch(SQLException e){
			log.severe(e.getMessage());
		}
		tryClose();
		return playerAwayList;
	}


	@Override
	public List<String> getHomeTeamPlayerID(String matchid) {
		ArrayList<String> playerHomeList = new ArrayList<>();
		rs = null;
		ps = null;
		try{
			ps = DBAccess.getConn().prepareStatement("SELECT PLAYER_PLAYER_ID1, PLAYER_PLAYER_ID2, PLAYER_PLAYER_ID3, PLAYER_PLAYER_ID4, PLAYER_PLAYER_ID5, PLAYER_PLAYER_ID6, PLAYER_PLAYER_ID7, PLAYER_PLAYER_ID8, PLAYER_PLAYER_ID9, PLAYER_PLAYER_ID10, PLAYER_PLAYER_ID11 FROM SOCCER02.MATCHLINEUP l JOIN SOCCER02.MATCH m on(l.matchlineup_id = m.matchlineup_homematchlineup_id) WHERE m.match_id = ?");
			ps.setString(1, matchid);
			String columnName = "PLAYER_PLAYER_ID";
			rs = ps.executeQuery();
			rs.next();
			for(int i = 1; i<12; i++){
				playerHomeList.add(Integer.toString(rs.getInt(columnName+Integer.toString(i))));
			}
			
		}catch(SQLException e){
			log.severe(e.getMessage());
		}
		tryClose();
		return playerHomeList;
	}


	@Override
	public List<String> getAwayPlayerList(String matchid) {
		List<String> tempPlayerIDList = getAwayTeamPlayerID(matchid);
		ArrayList<String> awayPlayerList = new ArrayList<>();
		ps = null;
		rs = null;
		try{
			ps = DBAccess.getConn().prepareStatement("SELECT name FROM SOCCER02.PLAYER WHERE player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ?");
			for(int i = 0;i < tempPlayerIDList.size(); i++){
				ps.setString(i+1, tempPlayerIDList.get(i));
			}
			rs = ps.executeQuery();
			while(rs.next()){
				awayPlayerList.add(rs.getString("name"));
			}
		}catch(SQLException e){
			log.severe(e.getMessage());
		}
		tryClose();
		return awayPlayerList;
	}


	@Override
	public List<String> getHomePlayerList(String matchid) {
		List<String> tempPlayerIDList = getHomeTeamPlayerID(matchid);
		ArrayList<String> homePlayerList = new ArrayList<>();
		ps = null;
		rs = null;
		try{
			ps = DBAccess.getConn().prepareStatement("SELECT name FROM SOCCER02.PLAYER WHERE player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ? OR player_api_id = ?");
			for(int i = 0;i < tempPlayerIDList.size(); i++){
				ps.setString(i+1, tempPlayerIDList.get(i));
			}
			rs = ps.executeQuery();
			while(rs.next()){
				homePlayerList.add(rs.getString("name"));
			}
		}catch(SQLException e){
			log.severe(e.getMessage());
		}
		tryClose();
		return homePlayerList;
	}
	
	public List<String> getPlayerStats(String player_api_id){
		return null;
	}


	@Override
	public Player getPlayer(String playerID) {
		Soccer_Player player = null;
		ps = null;
		rs = null;
		try{
			ps = DBAccess.getConn().prepareStatement("SELECT p.player_name, p.birthday, p.height, p.weight, a.preferred_foot, a.overall_rating, a.strength, a.shot_power FROM SOCCER02.PLAYER_2 p JOIN SOCCER02.PLAYER2_ATTRIBUTES a on(p.id = a.id) WHERE  p.id = ?");
			ps.setString(1, playerID);
			rs = ps.executeQuery();
			int id = Integer.parseInt(playerID);
			String name = "";
			Date birthday = null;
			int height = 0;
			int weight = 0;
			
			int overallRating = 0;
			String preferredFoot = "";
			int strength = 0;
			int shotPower = 0;
			
			while(rs.next()){
				name = rs.getString("player_name");
				birthday = rs.getDate("birthday");
				height = rs.getInt("height");
				weight = rs.getInt("weight");
				overallRating = rs.getInt("Overall_Rating");
				preferredFoot = rs.getString("preferred_foot");
				strength = rs.getInt("strength");
				shotPower = rs.getInt("shot_power");
			}
			String tempHeight = Integer.toString(height);
			height = Integer.parseInt(tempHeight.substring(0,3));
		
			player = new Soccer_Player(id, name, birthday, height, weight);
			player.setOverallRating(overallRating);
			player.setPreferredFoot(preferredFoot);
			player.setStrength(strength);
			player.setShotPower(shotPower);
			
		}catch(SQLException e){
			log.severe(e.getMessage());
		}
		tryClose();
		return player;
	}

}
