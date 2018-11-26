package edu.sportanalytics.database;

public class Basketball_Team{
    private int team_id, team_api_id;
    String long_name, short_name, abbreviation, location;

    
    public long getTeam_id(){
        return team_id;
    }

    public void setTeam_id(int team_id){
        this.team_id = team_id;
    }
    
    public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLong_name(){
        return long_name;
    }

    public void setLong_name(String long_name){
        this.long_name = long_name;
    }

    public String getAbrevation() {
		return abbreviation;
	}

	public void setAbrevation(String abbreviation) {
		this.abbreviation = abbreviation;
	}

	public String getShort_name(){
        return short_name;
    }

    public void setShort_name(String short_name){
        this.short_name = short_name;
    }

    public int getTeam_api_id(){
        return team_api_id;
    }

    public void setTeam_api_id(int team_api_id){
        this.team_api_id = team_api_id;
    }
}
