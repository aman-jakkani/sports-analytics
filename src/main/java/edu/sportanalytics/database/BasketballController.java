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

	public BasketballController(){
		super();
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
			longNameLeagues.add(b.getLong_name());
		}
		System.out.println(leaguesList);

		return longNameLeagues;
	}

	@Override
	public List<String> getSeason(String league, String team) {
		seasonList = findSeason(league, team);
		List<String> seasonNames = new ArrayList<String>();

		for (Basketball_Season s : seasonList){
			seasonNames.add(s.getName());
		}

		return seasonNames;
	}


	@Override
	public List<String> getGame(String season, String team) {
		gamesList = findGames(team, season);
		List<String> gamesString = new ArrayList<String>();

		for (Basketball_Game b : gamesList){
//			gamesString.add(b.getHost() + " vs " + b.getGuest() + " (" + b.getHomeTeamScore() + " : " + b.getAwayTeamScore() + " )");
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
		//yet to come: seq, status , tvbroadcast,timeplayed,attendance
		
		try {
			Statement stmt = DBAccess.getConn().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT GID,SEQUENCE,STATUS,HOMETID,VISTORTID,SEASON,TVBROADCAST,DATEMMDD,DATEYYYY,TIMEPLAYED,ATTENDANCE FROM BASKETBALL.GAME;;");
			
			while(rs.next()) {
				Basketball_Game bg = new Basketball_Game();
				bg.setGID(rs.getInt("GID"));
				bg.setSequence(rs.getInt("SEQUENCE"));
				bg.setStatus(rs.getString("STATUS"));
				bg.setVisitorTID(rs.getInt("VISITORTID"));
				bg.setHomeTID(rs.getInt("HOMETUID"));
				bg.setSeason(rs.getInt("STATUS"));
				bg.setTvbroadcast(rs.getString("TVBROADCAST"));
				bg.setTimeplayed(rs.getString("TIMEPLAYED"));
				bg.setAttendance(rs.getInt("ATTENDANCE"));
				//bg.setDate(rs.getDate("GAME_DATE"));
				
				gList.add(bg);
				
			}
			
			
			
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		
		
		
		return gList;
	}

	// Finds Season from league/team
	private List<Basketball_Season> findSeason(String league, String team){
		List<Basketball_Season> bsList = new ArrayList<Basketball_Season>();

		try {
			Statement stmt = DBAccess.getConn().createStatement();
			ResultSet rs = stmt.executeQuery("");
			
			while(rs.next()) {
				Basketball_Season bs = new Basketball_Season();
				bsList.add(bs);
			}
			
			
		} catch (SQLException e) {
			log.severe(e.getMessage());;
		}
		
		return bsList;
	}

	// Finds Team + info using a league
	private List<Basketball_Team> findTeams(String league){
		List<Basketball_Team> teamList = new ArrayList<Basketball_Team>();
		
		try {
			Statement stmt = DBAccess.getConn().createStatement();
			ResultSet rs = stmt.executeQuery("SELECT NAME , ABBREVIATION, LOCATION , NICKNAME  from BASKETBALL.TEAM ;");
			
			while(rs.next()) {
				Basketball_Team bt = new Basketball_Team();
				bt.setLong_name(rs.getString("NAME"));
				//No ID yet
				bt.setAbrevation(rs.getString("ABBREVIATION"));
				bt.setLocation(rs.getString("LOCATION"));
				bt.setShort_name(rs.getString("NICKNAME"));
				
				teamList.add(bt);
			}
			
		} catch (SQLException e) {
		
			log.severe(e.getMessage());
		}
		
		
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
