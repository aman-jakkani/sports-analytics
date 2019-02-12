package edu.sportanalytics.database;

import java.util.Date;

public class Basketball_Player extends Player
{
    private int gamesPlayed;
    private int points;
    private int totalRebounds;
    private int assists;
    private int steals;
    private int turnovers;

    public Basketball_Player(int playerId, String name, Date birthday, int height, int weight) {
        super(playerId, name, birthday, height, weight);
    }

    public int getPoints() {
        return points;
    }


    public void setPoints(int points) {
        this.points = points;
    }

    public int getTotalRebounds() {
        return totalRebounds;
    }

    public void setTotalRebounds(int totalRebounds) {
        this.totalRebounds = totalRebounds;
    }

    public int getAssists() {
        return assists;
    }

    public void setAssists(int assists) {
        this.assists = assists;
    }

    public int getSteals() {
        return steals;
    }

    public void setSteals(int steals) {
        this.steals = steals;
    }

    public int getTurnovers() {
        return turnovers;
    }

    public void setTurnovers(int turnovers) {
        this.turnovers = turnovers;
    }

    public int getGamesPlayed() {
        return gamesPlayed;
    }

    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }
}
