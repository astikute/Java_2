import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableModel;

public class CommunicateDb {
	
	public static Time convertIntoTime (String str) {
	    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
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
					+ "FROM employees "
					+ "LEFT JOIN rent_bike ON employees.id=rent_bike.id "
					+ "ORDER BY employees.id";
			PreparedStatement ps = conn.prepareStatement(queryString);
			ResultSet results = ps.executeQuery(queryString);
			while (results.next()) {
				String dbId = results.getString("id");
	    		String dbName = results.getString("name");
	    		String dbLast = results.getString("last_name");
	    		String dbFrom = results.getString("rent_from");
	    		String dbTill = results.getString("rent_till");
	    		if (dbFrom != null && dbTill != null) {
	    			dbFrom = dbFrom.substring(0, 5);
	    			dbTill = dbTill.substring(0, 5);
	    		}
	    		model.addRow(new Object[] {dbId, dbName, dbLast, dbFrom, dbTill});
			}
			} catch (SQLException sql) {
				System.out.println(sql);
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
			} catch (SQLException sql) {
				System.out.println(sql);
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
		} catch (SQLException sql) {
			System.out.println(sql);
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
		} catch (SQLIntegrityConstraintViolationException e) {
			MainDisplay.showMsg("Employee has already a reservation!");
		} catch (SQLException sql) {
		}
	}
	
	public static void deleteReservations (Connection conn, int id) {
		try {
			String queryString = "DELETE FROM rent_bike WHERE id = ?";
			PreparedStatement ps = conn.prepareStatement(queryString);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException sql) {
			System.out.println(sql);
		}
	}
}
