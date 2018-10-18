package edu.sportanalytics.database;

import java.util.List;

public abstract class DatabaseController
{
    public DatabaseController()
    {
        //Hier muss ein wildes Olli die Datenbankverbindung aufbauen

    }

    public abstract List<String> getLeagues();
    public abstract List<String> getTeams();
    public abstract List<String> getSeason(String team);
    public abstract List<String> getGame(String season, String team);
    
}
