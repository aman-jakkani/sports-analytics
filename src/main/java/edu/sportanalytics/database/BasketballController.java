package edu.sportanalytics.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
			gamesString.add(homeName + " vs " + guestName + " (" + b.getHomeScore() + " : " + b.getGuestScore() + " ) MATCH_ID:" + b.getGID());
		}

		return gamesString;
	}


    @Override
    public List<String> getHomeAndAwayTeam(String matchid) {
        List<String> teamList = new ArrayList<String>();
        ps = null;
        rs = null;
        try {
            ps = DBAccess.getConn().prepareStatement(
                    "SELECT NAME FROM BASKETBALL.GAME JOIN BASKETBALL.TEAM ON GAME.HOMETID = BASKETBALL.TEAM.TEAM_ID WHERE GID = ?");

            ps.setString(1, matchid);
            rs = ps.executeQuery();
            if (rs.next()) {
                teamList.add(rs.getString("NAME"));
            }
            ps.close();
            ps = DBAccess.getConn().prepareStatement(
                    "SELECT NAME FROM BASKETBALL.GAME JOIN BASKETBALL.TEAM ON GAME.VISITORTID = BASKETBALL.TEAM.TEAM_ID WHERE GID = ?");

            ps.setString(1, matchid);
            rs = ps.executeQuery();
            if (rs.next()) {
                teamList.add(rs.getString("NAME"));
            }


        } catch (SQLException e) {
            log.severe(e.getMessage());
        }
        tryClose();
        return teamList;
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
	
	//Retrieves The Score of HomeTeam by MatchID
	public String HomeScoreById(String id) {
		ps = null;
		rs = null;
		
		try {
			ps = DBAccess.getConn().prepareStatement("SELECT POINTS From BASKETBALL.GAME "
					+ "join BASKETBALL.SCORE_GAME on ( HOMETID = Team_id) and (GID = GAME_ID) WHERE GID = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				id = rs.getString("POINTS");
				
			}
		}	catch (SQLException e) {
		
			log.severe(e.getMessage());
		}
		
		tryClose();
		
		return id;
		
	}
	
	//Retrieves The Score of GuestTeam by MatchID
	public String GuestScoreById(String id) {
		ps = null;
		rs = null;

		try {
			ps = DBAccess.getConn().prepareStatement("SELECT POINTS From BASKETBALL.GAME "
					+ "join BASKETBALL.SCORE_GAME on ( VISITORTID = Team_id) and (GID = GAME_ID) WHERE GID = ?");
			ps.setString(1, id);
			rs = ps.executeQuery();

			while(rs.next()) {
				id = rs.getString("POINTS");

			}
		}catch (SQLException e) {

			log.severe(e.getMessage());
		}

		tryClose();
		return id;

	}
	public String GetAttendanceByGame(String gid) {
		ps = null;
		rs = null;

	try {
		ps = DBAccess.getConn().prepareStatement("SELECT ATTENDANCE From BASKETBALL.GAME WHERE GID = ?");
		ps.setString(1, gid);
		rs = ps.executeQuery();

		while(rs.next()) {
			gid = rs.getString("ATTENDANCE");
		}
	}catch (SQLException e) {

		log.severe(e.getMessage());
	}

	tryClose();
		return gid;
	}


	public List<Basketball_League> getLeaguesList() {
		return leaguesList;
	}

	public List<Basketball_Team> getTeamList() {
		return teamList;
	}

	@Override
	public List<String> getHomePlayerList (String matchid) {
		List<String> playerList = new ArrayList<>();

		List<String> idList = getHomeTeamPlayerID(matchid);

		for(String id : idList)
		{
			ps = null;
			rs = null;
			try {
				ps = DBAccess.getConn().prepareStatement("SELECT FIRST_NAME, LAST_NAME FROM BASKETBALL.PLAYER WHERE PERSON_ID=?");
				ps.setString(1, id);
				rs = ps.executeQuery();

				if(rs.next())
				{
					String fullname = rs.getString(1) + " " +rs.getString(2);
					playerList.add(fullname);
				}
				else
				{
					playerList.add("NotFound");
				}
			}catch (SQLException e) {

				log.severe(e.getMessage());
			}

			tryClose();
		}

		return playerList;
	}

	@Override
	public List<String> getAwayPlayerList (String matchid) {
		List<String> playerList = new ArrayList<>();

		List<String> idList = getAwayTeamPlayerID(matchid);

		for(String id : idList)
		{
			ps = null;
			rs = null;
			try {
				ps = DBAccess.getConn().prepareStatement("SELECT FIRST_NAME, LAST_NAME FROM BASKETBALL.PLAYER WHERE PERSON_ID=?");
				ps.setString(1, id);
				rs = ps.executeQuery();

				if(rs.next())
				{
					String fullname = rs.getString(1) + " " +rs.getString(2);
					playerList.add(fullname);
				}
				else
				{
					playerList.add("NotFound");
				}
			}catch (SQLException e) {

				log.severe(e.getMessage());
			}

			tryClose();
		}

		return playerList;
	}

    @Override
    public Player getPlayer(String playerID) {
        Player player = null;

		ps = null;
		rs = null;
		try{
			ps = DBAccess.getConn().prepareStatement("SELECT FIRST_NAME, LAST_NAME, BIRTHDATE, HEIGHT, WEIGHT " +
					"FROM BASKETBALL.PLAYER WHERE PERSON_ID=?");
			ps.setString(1, playerID);
			rs = ps.executeQuery();
			int id = Integer.parseInt(playerID);
			String name = "";
			Date birthday = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			int height = 0;
			int weight = 0;

			if(rs.next()){
				String firstname = rs.getString(1);
				String lastname = rs.getString(2);
				name = firstname + lastname;
				String chopDate = rs.getString(3).substring(0,10);
				birthday = df.parse(chopDate);
				//height = rs.getInt(4); Bad Data
				weight = rs.getInt(5);
			}

			player = new Basketball_Player(id, name, birthday, height, weight);
		}catch(Exception e){
			log.severe(e.getMessage());
		}
		tryClose();
		return player;
    }

	public Basketball_Player getBBPlayer(String playerID) {
		Basketball_Player player = null;

		ps = null;
		rs = null;
		try{
			//need to change query to also get points, rebounds, etc.
			ps = DBAccess.getConn().prepareStatement("SELECT FIRST_NAME, LAST_NAME, BIRTHDATE, HEIGHT, WEIGHT " +
					"FROM BASKETBALL.PLAYER WHERE PERSON_ID=?");
			ps.setString(1, playerID);
			rs = ps.executeQuery();
			int id = Integer.parseInt(playerID);
			String name = "";
			Date birthday = null;
			int height = 0;
			int weight = 0;

			while(rs.next()){
				String firstname = rs.getString(1);
				String lastname = rs.getString(2);
				name = firstname + lastname;
				birthday = rs.getDate(3);
				height = rs.getInt(4);
				weight = rs.getInt(5);
			}

			player = new Basketball_Player(id, name, birthday, height, weight);
			//player.setPoints, player.setRebounds, etc.
		}catch(SQLException e){
			log.severe(e.getMessage());
		}
		tryClose();
		return player;
	}

	//To-Do
	public String getRollupStats(String factatt, String aggregfunc, String dimension){
		ps = null;
		rs = null;
		try {
			//ps = DBAccess.getConn().prepareStatement("SELECT ? From BASKETBALL.PLAYER_STATS WHERE GID = ?");
			ps.setString(1, factatt);
			ps.setString(2, aggregfunc);
			ps.setString(3, dimension);
			rs = ps.executeQuery();

			while(rs.next()) {
				factatt = rs.getString("ATTENDANCE");
			}
		}catch (SQLException e) {

			log.severe(e.getMessage());
		}

		tryClose();
		return factatt;
	}

	//To-Do
	public String getCubeStats(String aggregval, String aggregfunc, String dimension1, String dimension2){
		ps = null;
		rs = null;
		try {
			//ps = DBAccess.getConn().prepareStatement("SELECT ? From BASKETBALL.PLAYER_STATS WHERE GID = ?");
			ps.setString(1, aggregval);
			ps.setString(2, aggregfunc);
			ps.setString(3, dimension1);
			ps.setString(4, dimension2);
			rs = ps.executeQuery();

			while(rs.next()) {
				//aggregval = rs.getString("ATTENDANCE");
			}
		}catch (SQLException e) {

			log.severe(e.getMessage());
		}

		tryClose();
		return aggregval;
	}
	public List<String> getHomeTeamPlayerID(String matchid){
		List<String> home_playerlist = new ArrayList<>();
		ps = null;
		rs = null;

		try {
			ps = DBAccess.getConn().prepareStatement("SELECT PLAYER1_ID,PLAYER2_ID,PLAYER3_ID,PLAYER4_ID,PLAYER5_ID,PLAYER6_ID,PLAYER7_ID,PLAYER8_ID,PLAYER9_ID,PLAYER10_ID,PLAYER11_ID,PLAYER12_ID,PLAYER13_ID " +
					"FROM BASKETBALL.GAMELINEUP " +
					"JOIN BASKETBALL.GAME ON BASKETBALL.GAME.HOMETID = BASKETBALL.GAMELINEUP.TEAM_ID " +
					"WHERE GAME_ID = ?");
			ps.setString(1, matchid);
			rs = ps.executeQuery();

			if(rs.next()) {
				for(int i = 1; i < 14; i++) {
					home_playerlist.add(rs.getString(i));
				}
			}
		}catch (SQLException e) {

			log.severe(e.getMessage());
		}

		tryClose();
		return home_playerlist;
	}

	public List<String> getAwayTeamPlayerID(String matchid){
		List<String> playerlist = new ArrayList<>();
		ps = null;
		rs = null;

		try {
			ps = DBAccess.getConn().prepareStatement("SELECT PLAYER1_ID,PLAYER2_ID,PLAYER3_ID,PLAYER4_ID,PLAYER5_ID,PLAYER6_ID,PLAYER7_ID,PLAYER8_ID,PLAYER9_ID,PLAYER10_ID,PLAYER11_ID,PLAYER12_ID,PLAYER13_ID " +
					"FROM BASKETBALL.GAMELINEUP " +
					"JOIN BASKETBALL.GAME ON BASKETBALL.GAME.VISITORTID = BASKETBALL.GAMELINEUP.TEAM_ID " +
					"WHERE GAME_ID = ?");
			ps.setString(1, matchid);
			rs = ps.executeQuery();

			if(rs.next()) {
				for(int i = 1; i < 14; i++) {
					playerlist.add(rs.getString(i));
				}
			}
		}catch (SQLException e) {

			log.severe(e.getMessage());
		}

		tryClose();
		return playerlist;
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
