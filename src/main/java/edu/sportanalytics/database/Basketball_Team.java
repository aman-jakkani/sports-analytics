package edu.sportanalytics.database;

import java.util.ArrayList;
import java.util.List;

public class Basketball_Team{

    private int teamID;
    private List<Integer> playerIDs;
    private int wins;
    private int losses;

    public Basketball_Team(int teamID, List<Integer> playerIDs, int wins, int losses){
        this.teamID = teamID;
        this.playerIDs = playerIDs;
        this.wins = wins;
        this.losses = losses;
    }

    public int getWins(){
        return wins;
    }
    public int getLosses(){
        return losses;
    }
    /*public int getWins(){
        return teamID
    }
    public int getWins(){
        return teamID
    }*/

}
