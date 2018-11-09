package edu.sportanalytics.database;

import java.util.Date;

public class Soccer_Match {

	private int Match_ID, Team_HomeTeam_ID, Team_AwayTeam_ID, result, home_team_goal, away_team_goal;
	private String Seasonstage_name, Leauge_name;
	private Date date;
	private String gastgeber, gast;

	public int getMatch_ID() {
		return Match_ID;
	}

	public void setMatch_ID(int match_ID) {
		Match_ID = match_ID;
	}

	public int getTeam_HomeTeam_ID() {
		return Team_HomeTeam_ID;
	}

	public void setTeam_HomeTeam_ID(int team_HomeTeam_ID) {
		Team_HomeTeam_ID = team_HomeTeam_ID;
	}

	public int getTeam_AwayTeam_ID() {
		return Team_AwayTeam_ID;
	}

	public void setTeam_AwayTeam_ID(int team_AwayTeam_ID) {
		Team_AwayTeam_ID = team_AwayTeam_ID;
	}

	public String getSeasonstage_name() {
		return Seasonstage_name;
	}

	public void setSeasonstage_name(String seasonstage_name) {
		Seasonstage_name = seasonstage_name;
	}

	public String getLeauge_name() {
		return Leauge_name;
	}

	public void setLeauge_name(String leauge_name) {
		Leauge_name = leauge_name;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getGastgeber() {
		return gastgeber;
	}

	public void setGastgeber(String gastgeber) {
		this.gastgeber = gastgeber;
	}

	public String getGast() {
		return gast;
	}

	public void setGast(String gast) {
		this.gast = gast;
	}

	public int getAway_team_goal() {
		return away_team_goal;
	}

	public void setAway_team_goal(int away_team_goal) {
		this.away_team_goal = away_team_goal;
	}

	public int getHome_team_goal() {
		return home_team_goal;
	}

	public void setHome_team_goal(int home_team_goal) {
		this.home_team_goal = home_team_goal;
	}

}
