package model;
import java.sql.Connection;
import java.sql.DriverManager;

public class BuildConnection {

	private static String driver = "com.mysql.cj.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/task_290618";
	private static String username = "root";
	private static String password = "";
	private static Connection conn;

	public static Connection getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, username, password);
			return conn;
		} catch (Exception e) {
			return null;
		}
	}
}
