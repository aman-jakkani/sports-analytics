package edu.sportanalytics.database;

public class Soccer_Match {
	
	private int Match_ID;
	private int HomeTeam_ID;
	private int AwayTeam_ID;
	private int Season_ID;
	
	public Soccer_Match(int Match_ID, int HomeTeam_ID,int AwayTeam_ID, 
						int Season_ID){
		
		this.Match_ID = Match_ID;
		this.HomeTeam_ID = HomeTeam_ID;
		this.AwayTeam_ID = AwayTeam_ID;
		this.Season_ID = Season_ID;
		
	}

	public int getMatch_ID() {
		return Match_ID;
	}

	public int getHomeTeam_ID() {
		return HomeTeam_ID;
	}

	public int getAwayTeam_ID() {
		return AwayTeam_ID;
	}

	public int getSeason_ID() {
		return Season_ID;
	}
	
	

}
