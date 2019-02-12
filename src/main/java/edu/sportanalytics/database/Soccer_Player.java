package edu.sportanalytics.database;

import java.util.Date;

public class Soccer_Player extends Player {
	private int overallRating;
	private int potential;
	private String preferredFoot;
	private String attackingWorkRate;
	private String defensiveWorkRate;
	private int crossing;
	private int finishing;
	private int headingAccuracy;
	private int shortPassing;
	private int volleys;
	private int dribbling;
	private int curve;
	private int freeKickAccuracy;
	private int longPassing;
	private int ballControl;
	private int acceleration;
	private int sprintSpeed;
	private int agility;
	private int reactions;
	private int balance;
	private int shotPower;
	private int jumping;
	private int stamina;
	private int strength;
	private int longShots;
	private int aggression;
	private int interceptions;
	private int positioning;
	private int vision;
	private int penalties;
	private int marking;
	private int standingTackle;
	private int slidingTackle;
	private int gkDiving;
	private int gkHandling;
	private int gkKicking;
	private int gkPositioning;
	private int gkReflexes;

	public Soccer_Player(int playerId, String name, Date birthday, int height, int weight) {
		super(playerId, name, birthday, height, weight);
	}

	public int getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(int overallRating) {
		this.overallRating = overallRating;
	}

	public int getPotential() {
		return potential;
	}

	public void setPotential(int potential) {
		this.potential = potential;
	}

	public String getPreferredFoot() {
		return preferredFoot;
	}

	public void setPreferredFoot(String preferredFoot) {
		this.preferredFoot = preferredFoot;
	}

	public String getAttackingWorkRate() {
		return attackingWorkRate;
	}

	public void setAttackingWorkRate(String attackingWorkRate) {
		this.attackingWorkRate = attackingWorkRate;
	}

	public String getDefensiveWorkRate() {
		return defensiveWorkRate;
	}

	public void setDefensiveWorkRate(String defensiveWorkRate) {
		this.defensiveWorkRate = defensiveWorkRate;
	}

	public int getCrossing() {
		return crossing;
	}

	public void setCrossing(int crossing) {
		this.crossing = crossing;
	}

	public int getFinishing() {
		return finishing;
	}

	public void setFinishing(int finishing) {
		this.finishing = finishing;
	}

	public int getHeadingAccuracy() {
		return headingAccuracy;
	}

	public void setHeadingAccuracy(int headingAccuracy) {
		this.headingAccuracy = headingAccuracy;
	}

	public int getShortPassing() {
		return shortPassing;
	}

	public void setShortPassing(int shortPassing) {
		this.shortPassing = shortPassing;
	}

	public int getVolleys() {
		return volleys;
	}

	public void setVolleys(int volleys) {
		this.volleys = volleys;
	}

	public int getDribbling() {
		return dribbling;
	}

	public void setDribbling(int dribbling) {
		this.dribbling = dribbling;
	}

	public int getCurve() {
		return curve;
	}

	public void setCurve(int curve) {
		this.curve = curve;
	}

	public int getFreeKickAccuracy() {
		return freeKickAccuracy;
	}

	public void setFreeKickAccuracy(int freeKickAccuracy) {
		this.freeKickAccuracy = freeKickAccuracy;
	}

	public int getLongPassing() {
		return longPassing;
	}

	public void setLongPassing(int longPassing) {
		this.longPassing = longPassing;
	}

	public int getBallControl() {
		return ballControl;
	}

	public void setBallControl(int ballControl) {
		this.ballControl = ballControl;
	}

	public int getAcceleration() {
		return acceleration;
	}

	public void setAcceleration(int acceleration) {
		this.acceleration = acceleration;
	}

	public int getSprintSpeed() {
		return sprintSpeed;
	}

	public void setSprintSpeed(int sprintSpeed) {
		this.sprintSpeed = sprintSpeed;
	}

	public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getReactions() {
		return reactions;
	}

	public void setReactions(int reactions) {
		this.reactions = reactions;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public int getShotPower() {
		return shotPower;
	}

	public void setShotPower(int shotPower) {
		this.shotPower = shotPower;
	}

	public int getJumping() {
		return jumping;
	}

	public void setJumping(int jumping) {
		this.jumping = jumping;
	}

	public int getStamina() {
		return stamina;
	}

	public void setStamina(int stamina) {
		this.stamina = stamina;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getLongShots() {
		return longShots;
	}

	public void setLongShots(int longShots) {
		this.longShots = longShots;
	}

	public int getAggression() {
		return aggression;
	}

	public void setAggression(int aggression) {
		this.aggression = aggression;
	}

	public int getInterceptions() {
		return interceptions;
	}

	public void setInterceptions(int interceptions) {
		this.interceptions = interceptions;
	}

	public int getPositioning() {
		return positioning;
	}

	public void setPositioning(int positioning) {
		this.positioning = positioning;
	}

	public int getVision() {
		return vision;
	}

	public void setVision(int vision) {
		this.vision = vision;
	}

	public int getPenalties() {
		return penalties;
	}

	public void setPenalties(int penalties) {
		this.penalties = penalties;
	}

	public int getMarking() {
		return marking;
	}

	public void setMarking(int marking) {
		this.marking = marking;
	}

	public int getStandingTackle() {
		return standingTackle;
	}

	public void setStandingTackle(int standingTackle) {
		this.standingTackle = standingTackle;
	}

	public int getSlidingTackle() {
		return slidingTackle;
	}

	public void setSlidingTackle(int slidingTackle) {
		this.slidingTackle = slidingTackle;
	}

	public int getGkDiving() {
		return gkDiving;
	}

	public void setGkDiving(int gkDiving) {
		this.gkDiving = gkDiving;
	}

	public int getGkHandling() {
		return gkHandling;
	}

	public void setGkHandling(int gkHandling) {
		this.gkHandling = gkHandling;
	}

	public int getGkKicking() {
		return gkKicking;
	}

	public void setGkKicking(int gkKicking) {
		this.gkKicking = gkKicking;
	}

	public int getGkPositioning() {
		return gkPositioning;
	}

	public void setGkPositioning(int gkPositioning) {
		this.gkPositioning = gkPositioning;
	}

	public int getGkReflexes() {
		return gkReflexes;
	}

	public void setGkReflexes(int gkReflexes) {
		this.gkReflexes = gkReflexes;
	}
	
	

}
