import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.table.DefaultTableModel;

public class CommunicateDb {
		
	public static void displayReservations (Connection conn, String date, DefaultTableModel model) {
		try {
			String queryString = "SELECT employees.id, name, last_name, rent_from, rent_till, rent_bike.bike_id "
					+ "FROM employees "
					+ "LEFT JOIN rent_bike ON employees.id=rent_bike.id "
					+ "AND date = ? "
					+ "ORDER BY employees.id";
			PreparedStatement ps = conn.prepareStatement(queryString);
			ps.setString(1, date);
			ResultSet results = ps.executeQuery();
			while (results.next()) {
				String dbId = results.getString("id");
	    		String dbName = results.getString("name");
	    		String dbLast = results.getString("last_name");
	    		String dbFrom = results.getString("rent_from");
	    		String dbTill = results.getString("rent_till");
	    		String dbBike = results.getString("bike_id");
	    		if (dbFrom != null && dbTill != null) {
	    			dbFrom = dbFrom.substring(0, 5);
	    			dbTill = dbTill.substring(0, 5);
	    		}
	    		model.addRow(new Object[] {dbId, dbName, dbLast, dbFrom, dbTill, dbBike});
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
	
	public static String checkReservations (Connection conn, String inputTime1, String inputTime2, String date) {
		String[] arr = new String[100];
		int i = 0;
		Time time1 = convertIntoTime(inputTime1);
		Time time2 = convertIntoTime(inputTime2);
		Boolean rentAvailable;
		
		try { 
			String queryString = "SELECT bike_id FROM electric_bikes";
			PreparedStatement ps = conn.prepareStatement(queryString);
			ResultSet results = ps.executeQuery(queryString);
			
			while (results.next()) {
				arr[i] = results.getString("bike_id");
				i++;
			}
		} catch (SQLException sql) {
			System.out.println(sql);
		}
		
		for (int j = 0; j < arr.length; j++) {
			rentAvailable = true;
			try {
				String queryString = "SELECT rent_from, rent_till "
						+ "FROM rent_bike WHERE bike_id = ? and date = ?";
				PreparedStatement ps = conn.prepareStatement(queryString);
				ps.setString(1, arr[j]);
				ps.setString(2, date);
				ResultSet results = ps.executeQuery();
			
				while (results.next()) {
					String dbFrom = results.getString("rent_from");
					String dbTill = results.getString("rent_till");
					Time db1 = convertIntoTime(dbFrom);
					Time db2 = convertIntoTime(dbTill);
					
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
				System.out.println(sql);
			}
			if (rentAvailable) {
				return arr[j];
			} else continue;
		}
		return null;
	}
	
	public static void setReservations (Connection conn, String inputTime1, String inputTime2, String input, String bikeId, String date) {
		try {
			String queryString = "INSERT INTO rent_bike (rent_from, rent_till, id, bike_id, date) "
					+ "VALUES (?, ?, ?, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(queryString);
			ps.setTime(1, modifyTime(inputTime1));
			ps.setTime(2, modifyTime(inputTime2));
			int parseInput = Integer.parseInt(input);
			ps.setInt(3, parseInput);
			ps.setString(4, bikeId);
			ps.setString(5, date);
			ps.execute();
		} catch (SQLIntegrityConstraintViolationException e) {
			MainDisplay.showMsg("Employee has already a reservation!");
		} catch (SQLException sql) {
		}
	}
	
	public static void deleteReservations (Connection conn, int id, String date) {
		try {
			String queryString = "DELETE FROM rent_bike WHERE id = ? and date = ?";
			PreparedStatement ps = conn.prepareStatement(queryString);
			ps.setInt(1, id);
			ps.setString(2, date);
			ps.execute();
		} catch (SQLException sql) {
			System.out.println(sql);
		}
	}
	
	public static String[] updateDate () {
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = GregorianCalendar.getInstance();
		String today = dateFormat.format(calendar.getTime());
		calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		String tomorrow = dateFormat.format(calendar.getTime());
		calendar = GregorianCalendar.getInstance();
		calendar.add(GregorianCalendar.DAY_OF_MONTH, -1);
		String yesterday = dateFormat.format(calendar.getTime());
		String[] dates = {today, tomorrow, yesterday};
		return dates;
	}
	
	public static void deleteYesterdayReservations (Connection conn, String str) {
		try {
			String queryString = "DELETE FROM rent_bike WHERE date = ?";
			PreparedStatement ps = conn.prepareStatement(queryString);
			ps.setString(1, str);
			ps.execute();
		} catch (SQLException sql) {
			System.out.println(sql);
		}
	}
	
	public static Time modifyTime (String str) {
	    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
		try {
			Date date = dateFormat.parse(str);
		    Calendar calendar = GregorianCalendar.getInstance();
		    calendar.setTime(date);
		    calendar.add(GregorianCalendar.HOUR, -1);
		    date = calendar.getTime();
			Time time = new Time(date.getTime());
			return time;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Time convertIntoTime (String str) {
	    DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	    Date date = null;
		try {
			date = dateFormat.parse(str);
			Time time = new Time(date.getTime());
			return time;
		} catch (ParseException e) {
		}
		return null;
	}
	
	public static boolean isValidInput (String str) {
		try {
			new SimpleDateFormat("HH:mm").parse(str);
			return true;
		} catch (ParseException e) {
			MainDisplay.showMsg("Invalid time!");
			return false;
		}
		
	}
}
