package edu.sportanalytics.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBAccess {
	private static DBAccess instance;
	private static Connection conn;
	private static ResultSet rs;

	private DBAccess() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sports-analytics");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<Soccer_League> createQueryLeagues(String query) {
		Statement stmt = null;
		ArrayList<Soccer_League> list = new ArrayList<Soccer_League>() ;
		try {
			stmt = conn.createStatement();
			rs = stmt.executeQuery(query);

			/* Placeholder for rs-processing */
			while (rs.next()) {
				list.add(new Soccer_League(rs.getString(1),rs.getInt(2)));
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}

		}
		return list;

	}

	/* Close DB-Connection and reset classobject instance */
	public void closeConnection() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
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
