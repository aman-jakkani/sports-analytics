package edu.sportanalytics.database;

import java.util.Date;

public class Basketball_Game {

    private int Game_ID, HomeTeam_ID, AwayTeam_ID, result, home_team_score, away_team_score;
    private String League_name;
    private int Season;
    private Date data;
    private String host, guest; // gastgeber = host, gast = guest

    public Basketball_Game(int Game_ID, int HomeTeam_ID, int AwayTeam_ID, int Season){
        this.Game_ID = Game_ID;
        this.HomeTeam_ID = HomeTeam_ID;
        this.AwayTeam_ID = AwayTeam_ID;
        this.Season = Season;
        // TODO: finish constructor - determine what all should be included in formal arguments
    }

    public int getGame_ID() {
        return Game_ID;
    }

    public void setGame_ID(int Game_ID) {
        this.Game_ID = Game_ID;
    }

    public int getHomeTeam_ID() {
        return HomeTeam_ID;
    }

    public void setHomeTeam_ID(int HomeTeam_ID) {
        this.HomeTeam_ID = HomeTeam_ID;
    }

    public int getAwayTeam_ID() {
        return AwayTeam_ID;
    }

    public void setAwayTeam_ID(int AwayTeam_ID) {
        this.AwayTeam_ID = AwayTeam_ID;
    }

    public int getSeason(){
        return Season;
    }

    public void setSeason(int Season) {
        this.Season = Season;
    }

    public String getLeague_Name() {
        return League_name;
    }

    public void setLeague_name(String League_name) {
        this.League_name = League_name;
    }

    // how are we formatting the result of a game? A pair of scores? win/loss?
    public int getResult(){
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    // java can't resolve date to a variable
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest;
    }

    public int getHomeTeamScore() {
        return home_team_score;
    }

    public void setHomeTeamScore(int home_team_score) {
        this.home_team_score = home_team_score;
    }

    public int getAwayTeamScore() {
        return away_team_score;
    }

    public void setAwayTeamScore(int away_team_score) {
        this.away_team_score = away_team_score;
    }
}