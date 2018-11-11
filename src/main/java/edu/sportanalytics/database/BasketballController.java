package edu.sportanalytics.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BasketballController extends DatabaseController {

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
			gamesString.add(b.getHost() + " vs " + b.getGuest() + " (" + b.getHomeTeamScore() + " : " + b.getAwayTeamScore() + " )");
		}

		return gamesString;
	}


	// TODO: fill out logic
	private List<Basketball_League> findAllLeagues(){
		List<Basketball_League> tempList = new ArrayList<Basketball_League>();
		return tempList;
	}

	// TODO: fill out logic	
	private List<Basketball_Game> findGames(String team, String season){
		List<Basketball_Game> tempList = new ArrayList<Basketball_Game>();
		return tempList;
	}

	// TODO: fill out logic
	private List<Basketball_Season> findSeason(String league, String team){
		List<Basketball_Season> tempList = new ArrayList<Basketball_Season>();
		return tempList;
	}

	// TODO: fill out logic
	private List<Basketball_Team> findTeams(String league){
		List<Basketball_Team> tempList = new ArrayList<Basketball_Team>();
		return tempList;
	}

	public List<Basketball_League> getLeaguesList() {
		return leaguesList;
	}

	public List<Basketball_Team> getTeamList() {
		return teamList;
	}
}
