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
	private static final Logger log = Logger.getLogger(SoccerController.class.getName());

	public SoccerController() {
		super();

		leaguesList = createQueryLeagues("SELECT NAME, LEAGUE_ID FROM SOCCER02.LEAGUE");

	}
	// returns an ArrayList with Name-Attributes of the Leagues-Objects
	public List<String> getLeagues() {
		List<String> nameLeagues = new ArrayList<String>();
		for (Soccer_League s : leaguesList) {
			nameLeagues.add(s.getNAME());
		}
		return nameLeagues;
	}

	public List<String> getTeams() {
		return null;
	}

	public List<String> getSeason(String team) {
		return null;
	}

	public List<String> getGame(String season, String team) {
		return null;
	}

	public ArrayList<Soccer_League> createQueryLeagues(String query) {
		Statement stmt = null;
		ResultSet rs = null;
		ArrayList<Soccer_League> list = new ArrayList<Soccer_League>();
		try {
			stmt = DBAccess.getConn().createStatement();
			rs = stmt.executeQuery(query);

			/* Placeholder for rs-processing */
			while (rs.next()) {
				list.add(new Soccer_League(rs.getString("NAME"), rs.getInt("LEAGUE_ID")));
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
		return list;

	}
}
