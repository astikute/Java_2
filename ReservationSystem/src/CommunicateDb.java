import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

public class CommunicateDb {
	
	public static Time convertIntoTime (String str) {
	    DateFormat dateFormat = new SimpleDateFormat("hh:mm");
	    Date date = null;
		try {
			date = dateFormat.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	    Time time = new Time(date.getTime());
		return time;
	}
	
	public static void displayReservations (Connection conn, DefaultTableModel model) {
		try {
			String queryString = "SELECT employees.id, name, last_name, rent_from, rent_till "
					+ "FROM employees, rent_bike "
					+ "WHERE employees.id=rent_bike.id "
					+ "ORDER BY employees.id";
			PreparedStatement ps = conn.prepareStatement(queryString);
			ResultSet results = ps.executeQuery(queryString);
			while (results.next()) {
				String dbId = results.getString("id");
	    		String dbName = results.getString("name");
	    		String dbLast = results.getString("last_name");
	    		String dbFrom = results.getString("rent_from").substring(0,5);
	    		String dbTill = results.getString("rent_till").substring(0,5);
	    		model.addRow(new Object[] {dbId, dbName, dbLast, dbFrom, dbTill});
			}
			} catch (SQLException ex) {
				System.out.println(ex);
			}
	}
	
	public static boolean validateUser (Connection conn, String input) {
		try {
			String queryString = "SELECT id FROM employees";
			PreparedStatement ps = conn.prepareStatement(queryString);
			ResultSet results = ps.executeQuery(queryString);
			while (results.next()) {
				String dbId = results.getString("id");
				if (dbId.equals(input)) {
					return true;
				}
			} 
			} catch (SQLException ex) {
				System.out.println(ex);
			}
		return false;	
	}
	
	public static boolean checkReservations (Connection conn, String inputTime1, String inputTime2) {
		try {
			String queryString = "SELECT rent_from, rent_till FROM rent_bike";
			PreparedStatement ps = conn.prepareStatement(queryString);
			ResultSet results = ps.executeQuery(queryString);
			Date time1 = convertIntoTime(inputTime1);
			Date time2 = convertIntoTime(inputTime2);

        	while (results.next()) {
        		
        		String dbFrom = results.getString("rent_from");
        		Date db1 = convertIntoTime(dbFrom);
        		String dbTill = results.getString("rent_till");
        		Date db2 = convertIntoTime(dbTill);

        		if ((db1.before(time1) || db1.compareTo(time1) == 0) && db2.after(time1)) {
        			return false;
        		}
        		if (db1.before(time2) && (db2.after(time2) || db2.compareTo(time2) == 0)) {
        			return false;
        		}
        	}
		} catch (SQLException ex) {
			System.out.println(ex);
		}
		return true;
	}
	
	public static void setReservations (Connection conn, String inputTime1, String inputTime2, String input) {
		try {
			String queryString = "INSERT INTO rent_bike (rent_from, rent_till, id) VALUES (?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(queryString);
			ps.setTime(1, convertIntoTime(inputTime1));
			ps.setTime(2, convertIntoTime(inputTime2));
			int parseInput = Integer.parseInt(input);
			ps.setInt(3, parseInput);
			ps.execute();
		} catch (SQLException ex) {
			System.out.println(ex);
		}
	}
}
