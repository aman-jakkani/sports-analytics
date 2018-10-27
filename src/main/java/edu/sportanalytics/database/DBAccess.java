package edu.sportanalytics.database;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.logging.Logger;

public class DBAccess {
	private static DBAccess instance;
	private static Connection conn;
	private static final Logger log = Logger.getLogger(DBAccess.class.getName());

	//params
	private String serverURL;
	private String port;
	private String sid;
	private String username;
	private String pw;

	private DBAccess()
	{
		//Default values
		serverURL = "sportanalysisdb.inform.hs-hannover.de";
		port = "1521";
		sid = "/orcl";
		username = "SYSTEM";
		pw = "oracle"; // <----- very very bad.... dont try this at home ;)
	}

	/* Close DB-Connection and reset classobject instance */
	public void resetConnection() {
		closeConnection();
		instance = null;
	}

	private void closeConnection()
	{
		try {
			log.info("Closing DB-Connection");
			if(conn == null || conn.isClosed())
			{
				return;
			}
			conn.close();
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
	}

	synchronized public static DBAccess getInstance() {
		if (instance == null) {
			instance = new DBAccess();
		}
		return instance;
	}

	public void establishConnection()
	{
		try {
			log.info("Creating DB-Connection");
			String connectionString = "jdbc:oracle:thin:@" +
					serverURL +
					":" +
					port +
					""
					+ sid;
			log.info("Connection String: " + connectionString);
			//log.info("username: " + username +" password: " + pw);
			DriverManager.setLoginTimeout(3); //maybe a bit to short
			conn = DriverManager.getConnection(connectionString, username, pw);
			log.info("Connection established");
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
	}

	public static Connection getConn() {
		return conn;
	}

	public void setDBParams(String server, String port, String sid,String username, char[] pw)
	{
		this.serverURL = server;
		this.port = port;
		this.username = username;
		this.pw = String.copyValueOf(pw);

		closeConnection();
		establishConnection();
	}

	public String getServerURL() {
		return serverURL;
	}

	public String getPort() {
		return port;
	}

	public String getSid() {
		return sid;
	}

	public String getUsername() {
		return username;
	}

	public DatabaseController getController(SportsEnum type)
	{
		if(type == SportsEnum.SOCCER)
		{
			return new SoccerController();
		}
		else if(type == SportsEnum.BASKETBALL)
		{
			return new BasketballController();
		}
		else
		{
			log.severe("Unknown sports");
			return null;
		}
	}
}
