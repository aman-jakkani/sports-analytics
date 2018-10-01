import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccess {
	private static DBAccess instance;
	private static Connection conn;
	private static ResultSet rs;

	public DBAccess() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@dboracleserv.inform.hs-hannover.de:1521:db01", "user",
					"password");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public ResultSet createQuery(String query) {
		Statement stmt = null;
		try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return rs;

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

}
