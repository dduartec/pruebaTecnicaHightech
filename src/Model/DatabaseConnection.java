package Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	/*
	 * private static String url = "jdbc:mysql://localhost:3306/prototypeeop";
	 * private static String driverName = "com.mysql.jdbc.Driver"; private static
	 * String username = "root"; private static String password = "triala";
	 */
	private Connection con;

	public void connectDB() throws SQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/users", "root", "1234");
		} catch (ClassNotFoundException ex) {
			// log an exception. for example:
			System.out.println("Driver not found.");
		}
	}

	public void disconnectDB() {
		try {
			if (con != null) {
				con.close();
			}
		} catch (Exception e) {
			System.out.println("disconnect failed");
		}
	}

	public Connection getCon() {
		return con;
	}

	public void setCon(Connection con) {
		this.con = con;
	}

}
