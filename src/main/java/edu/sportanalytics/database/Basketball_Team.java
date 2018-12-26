package edu.sportanalytics.database;

public class Basketball_Team{
    private int wins, loses;
    String name, code, abbreviation, city,pct,team_id;

    
    public String getPct() {
		return pct;
	}

	public void setPct(String pct) {
		this.pct = pct;
	}

	public String getTeam_id(){
        return team_id;
    }

    public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLoses() {
		return loses;
	}

	public void setLoses(int loses) {
		this.loses = loses;
	}

	public void setTeam_id(String team_id){
        this.team_id = team_id;
    }


    public String getAbrevation() {
		return abbreviation;
	}

	public void setAbrevation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}



}
