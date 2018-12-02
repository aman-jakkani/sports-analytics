package edu.sportanalytics.database;

import java.util.Date;

public class Basketball_Game {

    private int GID, HomeTID, VisitorTID, result,sequence , Attendance, ScoreVisitor, ScoreHome;
    private String League_name, status, tvbroadcast, timeplayed, date;

    private String host, guest; // gastgeber = host, gast = guest

  

    public int getScoreVisitor() {
		return ScoreVisitor;
	}

	public void setScoreVisitor(int scoreVisitor) {
		ScoreVisitor = scoreVisitor;
	}

	public int getScoreHome() {
		return ScoreHome;
	}

	public void setScoreHome(int scoreHome) {
		ScoreHome = scoreHome;
	}

	public int getGID() {
		return GID;
	}

	public void setGID(int gID) {
		GID = gID;
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


    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getGuest() {
        return guest;
    }

    public String getDate() {
		return date;
	}

	public void setDate(String month, String year) {
		this.date = month + " " + year;
	}

	public void setGuest(String guest) {
        this.guest = guest;
    }

	public int getHomeTID() {
		return HomeTID;
	}

	public void setHomeTID(int homeTID) {
		HomeTID = homeTID;
	}

	public int getVisitorTID() {
		return VisitorTID;
	}

	public void setVisitorTID(int visitorTID) {
		VisitorTID = visitorTID;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getTvbroadcast() {
		return tvbroadcast;
	}

	public void setTvbroadcast(String tvbroadcast) {
		this.tvbroadcast = tvbroadcast;
	}

	public String getTimeplayed() {
		return timeplayed;
	}

	public void setTimeplayed(String timeplayed) {
		this.timeplayed = timeplayed;
	}

	public int getAttendance() {
		return Attendance;
	}

	public void setAttendance(int attendance) {
		Attendance = attendance;
	}

	public String getLeague_name() {
		return League_name;
	}

 
}