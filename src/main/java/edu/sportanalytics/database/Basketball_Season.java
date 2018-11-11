package edu.sportanalytics.database;

public class Basketball_Season {
    private String name;
    private int year, season_id;

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getYear(){
        return year;
    }

    public void setYear(int year){
        this.year = year;
    }

    public int getSeason_id(){
        return season_id;
    }

    public void setSeason_id(int season_id){
        this.season_id = season_id;
    }
}