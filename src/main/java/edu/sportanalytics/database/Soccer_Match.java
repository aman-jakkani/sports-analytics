package edu.sportanalytics.database;

import java.util.Date;

public class Soccer_Match {

	private int Match_ID, Team_HomeTeam_ID, Team_AwayTeam_ID, Seasonstage_Seasonstage_ID, Leauge_League_ID, result;
	private Date date;

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

	public int getSeasonstage_Seasonstage_ID() {
		return Seasonstage_Seasonstage_ID;
	}

	public void setSeasonstage_Seasonstage_ID(int seasonstage_Seasonstage_ID) {
		Seasonstage_Seasonstage_ID = seasonstage_Seasonstage_ID;
	}

	public int getLeauge_League_ID() {
		return Leauge_League_ID;
	}

	public void setLeauge_League_ID(int leauge_League_ID) {
		Leauge_League_ID = leauge_League_ID;
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

}
