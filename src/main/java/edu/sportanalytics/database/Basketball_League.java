package edu.sportanalytics.database;

public class Basketball_League {
    private String name;
    private int league_id, country_id;

    public Basketball_League(String name, int league_id, int country_id){
        this.name = name;
        this.league_id = league_id;
        this.country_id = country_id;
    }

    public String getName(){
        return name;
    }

    public int getLeague_id(){
        return league_id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setLeague_id(int league_id){
        this.league_id = league_id;
    }

    public int getCountry_id(){
        return country_id;
    }

    public void setCountry_id(int country_id){
        this.country_id = country_id;
    }
}