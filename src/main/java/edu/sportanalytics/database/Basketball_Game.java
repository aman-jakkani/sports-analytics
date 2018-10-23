package edu.sportanalytics.database;

public class Basketball_Game {

    private int Game_ID;
    private int HomeTeam;
    private int AwayTeam;
    private int Season;

    public Basketball_Game(int Game_ID, int HomeTeam, int AwayTeam, int Season){

        this.Game_ID = Game_ID;
        this.HomeTeam = HomeTeam;
        this.AwayTeam = AwayTeam;
        this.Season = Season;

    }

    public int getMatch_ID() {
        return Game_ID;
    }

    public int getHomeTeam_ID() {
        return HomeTeam;
    }

    public int getAwayTeam_ID() {
        return AwayTeam;
    }

    public int getSeason_ID() {
        return Season;
    }



}