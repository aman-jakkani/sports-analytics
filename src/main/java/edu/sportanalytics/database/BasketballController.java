package edu.sportanalytics.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BasketballController extends DatabaseController {

	private Statement stmt;
	private PreparedStatement ps;
	private ResultSet rs;
	private List<Basketball_League> leaguesList;
	private List<Basketball_Team> teamList;
	private List<Basketball_Game> gamesList;
	private List<Basketball_Season> seasonList;
	private static final Logger log = Logger.getLogger(BasketballController.class.getName());

	public BasketballController(DBAccess dba){
		super(dba);
	}

	@Override
    public List<String> getLeagues() {
		leaguesList = findAllLeagues();
		List<String> leagueNames = new ArrayList<String>();
		for (Basketball_League b : leaguesList){
			leagueNames.add(b.getName());
		}
        return leagueNames;
    }


	@Override
	public List<String> getTeams(String league) {
		teamList = findTeams(league);
		List<String> longNameLeagues = new ArrayList<String>();

		for (Basketball_Team b : teamList){
			longNameLeagues.add(b.getName());
		}
		System.out.println(leaguesList);

		return longNameLeagues;
	}

	@Override
	public List<String> getSeason(String league, String team) {
		seasonList = findSeason(league, team);
		List<String> seasonNames = new ArrayList<String>();

		for (Basketball_Season s : seasonList){
			seasonNames.add(s.getSeasonYear());
		}

		return seasonNames;
	}


	@Override
	public List<String> getGame(String season, String team) {
		gamesList = findGames(team, season);
		List<String> gamesString = new ArrayList<String>();

		for (Basketball_Game b : gamesList){
			String homeName = "UNKNOWN";
			String guestName = "UNKNOWN";
			for(Basketball_Team t : teamList)
			{
				if(t.getTeam_id().equals(Integer.toString(b.getHomeTID())))
				{
					homeName = t.getName();
				}
				if(t.getTeam_id().equals(Integer.toString(b.getVisitorTID())))
				{
					guestName = t.getName();
				}
			}
			gamesString.add(homeName + " vs " + guestName + " (" + b.getHomeScore() + " : " + b.getGuestScore() + " )");
		}

		return gamesString;
	}


	// Returns Name and ID of all Basketball.Leagues
	private List<Basketball_League> findAllLeagues(){
		List<Basketball_League> blList = new ArrayList<Basketball_League>();
		Statement stmt= null;
		ResultSet rs = null;
		try {
			stmt = DBAccess.getConn().createStatement();
			rs = stmt.executeQuery("SELECT NAME ,LEAGUE_ID  from BASKETBALL.LEAGUE ");
	
			while(rs.next()) {
				Basketball_League bl = new Basketball_League();
				bl.setName(rs.getString("NAME"));
				bl.setLeague_id(rs.getInt("League_ID"));
				blList.add(bl);
				
			}
			
			
		}catch (SQLException e) {
			log.severe(e.getMessage());
		}
		tryClose();

		return blList;
	}

	// finds games + info from team/season
	private List<Basketball_Game> findGames(String team, String season){
		List<Basketball_Game> gList = new ArrayList<Basketball_Game>();
		ps = null;
		rs = null;
		
		try {
			ps = DBAccess.getConn().prepareStatement("SELECT GID,SEQUENCE,STATUS,HOMETID,VISITORTID,TVBROADCAST,"
					+ "DATEMMDD,DATEYYYY,TIMEPLAYED,ATTENDANCE FROM BASKETBALL.GAME join BASKETBALL.TEAM "
					+ "on  (VISITORTID = team_id ) OR (HOMETID = team_id) WHERE NAME LIKE ? and SEASON = ? ");
			ps.setString(1, team);
			ps.setString(2, season);
			rs = ps.executeQuery();
			while(rs.next()) {
				Basketball_Game bg = new Basketball_Game();
				bg.setGID(rs.getInt("GID"));
				bg.setSequence(rs.getInt("SEQUENCE"));
				bg.setStatus(rs.getString("STATUS"));
				bg.setHomeTID(rs.getInt("HOMETID"));
				bg.setVisitorTID(rs.getInt("VISITORTID"));
				bg.setTvbroadcast(rs.getString("TVBROADCAST"));
				bg.setTimeplayed(rs.getString("TIMEPLAYED"));
				bg.setAttendance(rs.getInt("ATTENDANCE"));
				bg.setDate(rs.getString("DATEMMDD"), rs.getString("DATEYYYY"));

				PreparedStatement aps = DBAccess.getConn().prepareStatement("SELECT BASKETBALL.SCORE_GAME.POINTS FROM BASKETBALL.SCORE_GAME JOIN BASKETBALL.GAME ON BASKETBALL.GAME.GID = BASKETBALL.SCORE_GAME.GAME_ID WHERE BASKETBALL.SCORE_GAME.GAME_ID =? AND BASKETBALL.SCORE_GAME.TEAM_ID =?");
				aps.setInt(1, bg.getGID());
				aps.setInt(2,bg.getHomeTID());

				ResultSet ars = aps.executeQuery();
				if(ars.next())
				{
					bg.setHomeScore(ars.getInt("POINTS"));
				}
				ars.close();

				aps.setInt(1, bg.getGID());
				aps.setInt(2, bg.getVisitorTID());
				ars = aps.executeQuery();
				if(ars.next())
				{
					bg.setGuestScore(ars.getInt("POINTS"));
				}
				ars.close();
				aps.close();

				gList.add(bg);
				
			}
			
			
			
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		
		
		tryClose();
		return gList;
	}

	// Finds Season from league/team
	private List<Basketball_Season> findSeason(String league, String team){
		List<Basketball_Season> bsList = new ArrayList<Basketball_Season>();
		ps = null;
		rs = null;
		
		try {
			ps = DBAccess.getConn().prepareStatement("SELECT distinct SEASON from BASKETBALL.GAME  join BASKETBALL.TEAM "
					+ "on  (VISITORTID = team_id ) OR (HOMETID = team_id) WHERE NAME LIKE ? ");
			ps.setString(1, team);
			rs = ps.executeQuery();
			while(rs.next()) {
				Basketball_Season bs = new Basketball_Season();
				bs.setSeasonYear(rs.getString("SEASON"));
				bsList.add(bs);
			}
			
			
		} catch (SQLException e) {
			log.severe(e.getMessage());;
		}
		
		tryClose();
		return bsList;
	}

	// Finds Team + info using a league
	private List<Basketball_Team> findTeams(String league){
		List<Basketball_Team> teamList = new ArrayList<Basketball_Team>();
		
		ps = null;
		rs= null;
		
		try {
			ps = DBAccess.getConn().prepareStatement("SELECT TEAM_ID, NAME , ABBREVIATION, CITY , Code, W, L, PCT  from BASKETBALL.TEAM");
			rs = ps.executeQuery();
			while(rs.next()) {
				Basketball_Team bt = new Basketball_Team();
				bt.setTeam_id(rs.getString("TEAM_ID"));
				bt.setName(rs.getString("NAME"));
				bt.setAbrevation(rs.getString("ABBREVIATION"));
				bt.setCity(rs.getString("City"));
				bt.setCode(rs.getString("CODE"));
				bt.setWins(rs.getInt("W"));
				bt.setLoses(rs.getInt("L"));
				bt.setPct(Float.toString(rs.getFloat("PCT")));
				
				teamList.add(bt);
			}
			
		} catch (SQLException e) {
		
			log.severe(e.getMessage());
		}
		
		tryClose();
		
		return teamList;
	}

	public List<Basketball_League> getLeaguesList() {
		return leaguesList;
	}

	public List<Basketball_Team> getTeamList() {
		return teamList;
	}
	
	
	public void tryClose(){
		try {
			if(stmt !=null){
				stmt.close();
			}else if(ps != null){
				ps.close();
			}else if(rs !=null){
				rs.close();
			}
		} catch (SQLException e) {
			log.severe("tryClose: "+e.getMessage());
		}
	}
}
