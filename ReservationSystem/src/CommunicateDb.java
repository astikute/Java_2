import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Time;
import javax.swing.table.DefaultTableModel;

public class CommunicateDb {
	
	private static Connection conn = BuildConnection.getConnection();
	private static String queryString;
	private static PreparedStatement ps;
	private static ResultSet results;
	private static String dbId, dbName, dbLast, dbFrom, dbTill, dbBike, tableName;
	
	//Displays all employees and added reservation if one exists
	public static void displayReservations (String date, DefaultTableModel model) {
		tableName = Operations.modifyString(tableName, date);
		model.setRowCount(1);
		try {
			queryString = "SELECT employees.id, name, last_name,"
					+ " " + tableName + ".rent_from, " + tableName + ".rent_till, " + tableName + ".bike_id "
					+ "FROM employees "
					+ "LEFT JOIN " + tableName + " ON employees.id=" + tableName +".id "
					+ "ORDER BY employees.id";
			ps = conn.prepareStatement(queryString);
			results = ps.executeQuery();
			while (results.next()) {
				dbId = results.getString("id");
	    		dbName = results.getString("name");
	    		dbLast = results.getString("last_name");
	    		dbFrom = results.getString("rent_from");
	    		dbTill = results.getString("rent_till");
	    		dbBike = results.getString("bike_id");
	    		if (dbFrom != null && dbTill != null) {
	    			dbFrom = dbFrom.substring(0, 5);
	    			dbTill = dbTill.substring(0, 5);
	    		}
	    		model.addRow(new Object[] {dbId, dbName, dbLast, dbFrom, dbTill, dbBike});
			}
		} catch (SQLException sql) {
			MainDisplay.showMsg("Can not load information from database!");
		}
	}
	
	//Checks if employees id exists
	public static boolean validateUser (String input) {
		try {
			queryString = "SELECT id FROM employees";
			ps = conn.prepareStatement(queryString);
			results = ps.executeQuery(queryString);
			while (results.next()) {
				String dbId = results.getString("id");
				if (dbId.equals(input)) {
					return true;
				}
			} 
		} catch (SQLException sql) {
			MainDisplay.showMsg("Can not load information from database!");
		}
		return false;	
	}
	
	//Gets list of electric bikes
	//Checks if any of bikes are available at chosen time
	//Assigns available bike automatically
	public static String checkReservations (String inputTime1, String inputTime2, String date) {
		String[] arr = new String[100];
		int i = 0;
		Time time1 = Operations.convertIntoTime(inputTime1);
		Time time2 = Operations.convertIntoTime(inputTime2);
		Boolean rentAvailable;
		
		try { 
			queryString = "SELECT bike_id FROM electric_bikes";
			ps = conn.prepareStatement(queryString);
			results = ps.executeQuery(queryString);
			while (results.next()) {
				arr[i] = results.getString("bike_id");
				i++;
			}
		} catch (SQLException sql) {
			MainDisplay.showMsg("Can not load information from database!");
		}
		
		for (int j = 0; j < arr.length; j++) {
			rentAvailable = true;
			tableName = Operations.modifyString(tableName, date);
			try {
				queryString = "SELECT " + tableName + ".rent_from, " + tableName + ".rent_till "
						+ "FROM " + tableName + " WHERE bike_id = ?";
				ps = conn.prepareStatement(queryString);
				ps.setString(1, arr[j]);
				results = ps.executeQuery();
			
				while (results.next()) {
					dbFrom = results.getString("rent_from");
					dbTill = results.getString("rent_till");
					Time db1 = Operations.convertIntoTime(dbFrom);
					Time db2 = Operations.convertIntoTime(dbTill);
					
					if ((db1.before(time1) || db1.compareTo(time1) == 0) && db2.after(time1)) {
						rentAvailable = false;
						break;
					}
					if (db1.before(time2) && (db2.after(time2) || db2.compareTo(time2) == 0)) {
						rentAvailable = false;
						break;
					}
				}
			} catch (SQLException sql) {
				MainDisplay.showMsg("Can not load information from database!");
			}
			if (rentAvailable) {
				return arr[j];
			} else continue;
		}
		return null;
	}
	
	//Updates database with new reservation
	public static void setReservations (String inputTime1, String inputTime2, String input, String bikeId, String date) {
		tableName = Operations.modifyString(tableName, date);
		try {
			queryString = "INSERT INTO " + tableName + " (rent_from, rent_till, id, bike_id) "
					+ "VALUES (?, ?, ?, ?)";
			ps = conn.prepareStatement(queryString);
			ps.setTime(1, Operations.modifyTime(inputTime1));
			ps.setTime(2, Operations.modifyTime(inputTime2));
			int parseInput = Integer.parseInt(input);
			ps.setInt(3, parseInput);
			ps.setString(4, bikeId);
			ps.execute();
		} catch (SQLIntegrityConstraintViolationException e) {
			MainDisplay.showMsg("Employee has already a reservation!");
		} catch (SQLException sql) {
			MainDisplay.showMsg("Can not upload information to database!");
		}
	}
	
	//Deletes reservation from database
	public static void deleteReservations (int id, String date) {
		tableName = Operations.modifyString(tableName, date);
		try {
			queryString = "DELETE FROM " + tableName + " WHERE id = ?";
			ps = conn.prepareStatement(queryString);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException sql) {
			MainDisplay.showMsg("Can not upload information to database!");
		}
	}
	
	//Deletes table in database with date = yesterday
	public static void deleteTable (String str) {
		tableName = Operations.modifyString(tableName, str);
		try {
			queryString = "DROP TABLE IF EXISTS " + tableName;
			ps = conn.prepareStatement(queryString);
			ps.execute();
		} catch (SQLException sql) {
			MainDisplay.showMsg("Can not update database!");
		}
	}
	
	//Creates table in database for date = tomorrow
	public static void createTable (String str) {
		tableName = Operations.modifyString(tableName, str);
		try {
			queryString = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
					+ "id INT NOT NULL, "
					+ "rent_from TIME NOT NULL, "
					+ "rent_till TIME NOT NULL, "
					+ "bike_id VARCHAR(30) NOT NULL, "
					+ "PRIMARY KEY (id))";
			ps = conn.prepareStatement(queryString);
			ps.execute();
		} catch (SQLException sql) {
			MainDisplay.showMsg("Can not update database!");
		}
	}
}
