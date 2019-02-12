package edu.sportanalytics.database;

import java.util.Date;

public abstract class Player {
	private int playerId;
	private String name;
	private Date birthday;
	private int height;
	private int weight;

	public Player(int playerId, String name, Date birthday, int height, int weight) {
		this.playerId = playerId;
		this.name = name;
		this.birthday = birthday;
		this.height = height;
		this.weight = weight;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	

}
