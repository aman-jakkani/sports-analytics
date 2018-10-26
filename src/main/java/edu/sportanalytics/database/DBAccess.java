package edu.sportanalytics.database;

import java.sql.Connection;
import java.sql.DriverManager;

import java.sql.SQLException;
import java.util.logging.Logger;

public class DBAccess {
	private static DBAccess instance;
	private static Connection conn;
	private static final Logger log = Logger.getLogger(DBAccess.class.getName());

	private DBAccess() {
		try {
			log.info("Creating DB-Connection");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sports-analytics");
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
	}

	/* Close DB-Connection and reset classobject instance */
	public void closeConnection() {
		try {
			log.info("Closing DB-Connection");
			conn.close();
		} catch (SQLException e) {
			log.severe(e.getMessage());
		}
		instance = null;
	}

	synchronized public static DBAccess createConnection() {
		if (instance == null) {
			instance = new DBAccess();
		}
		return instance;
	}

	public static Connection getConn() {
		return conn;
	}

}
