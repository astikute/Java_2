package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

public class CommunicateDb {
	
	private Connection conn = BuildConnection.getConnection();
	
	private DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	private ArrayList<Object[]> allReservations = new ArrayList<>();
	private String queryString;
	private PreparedStatement ps;
	private ResultSet results;
	private String dbId, dbName, dbLast, dbFrom, dbTill, dbBike, tableName, id, timeFrom, timeTill, date;
	private String[] request;

	public void requestReservation (String[] arr) {
		this.request = arr;
		this.id = request[0];
		this.timeFrom = request[1];
		this.timeTill = request[2];
		this.date = request[3];
	}
	
	public void setDate (String date) {
		this.date = date;
		tableName = "rent_bike_" + this.date.replace("-", "");
	}
	
	public void requestCancelReservation (String id, String date) {
		this.id = id;
		this.date = date;
	}
	
	//Displays all employees and added reservation if one exists
	public ArrayList<Object[]> getDbReservations () throws SQLException {
		allReservations.clear();
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
	    		Object[] reservation = new Object[] {dbId, dbName, dbLast, dbFrom, dbTill, dbBike};
	    		allReservations.add(reservation);
			}
			return allReservations;
	}
	
	//Checks if employees id exists
	public boolean validateUser () throws SQLException {
		queryString = "SELECT id FROM employees";
		ps = conn.prepareStatement(queryString);
		results = ps.executeQuery(queryString);
		while (results.next()) {
			dbId = results.getString("id");
			if (dbId.equals(id)) {
				return true;
			}
		}
		return false;	
	}
	
	//Gets list of electric bikes
	//Checks if any of bikes are available at chosen time
	//Assigns available bike automatically
	public boolean checkReservations () throws SQLException {
		String[] arr = new String[100];
		int i = 0;
		Time time1 = convertIntoTime(timeFrom);
		Time time2 = convertIntoTime(timeTill);
		Boolean rentAvailable;
		
		if (time1 != null && time2 != null) {
		queryString = "SELECT bike_id FROM electric_bikes";
		ps = conn.prepareStatement(queryString);
		results = ps.executeQuery(queryString);
		while (results.next()) {
			arr[i] = results.getString("bike_id");
			i++;
		}
		
			for (int j = 0; j < arr.length; j++) {
				rentAvailable = true;
				queryString = "SELECT " + tableName + ".rent_from, " + tableName + ".rent_till "
						+ "FROM " + tableName + " WHERE bike_id = ?";
				ps = conn.prepareStatement(queryString);
				ps.setString(1, arr[j]);
				results = ps.executeQuery();
				
				while (results.next()) {
					dbFrom = results.getString("rent_from");
					dbTill = results.getString("rent_till");
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
				if (rentAvailable) {
					this.dbBike = arr[j];
					return true;
				} else continue;
			}
		}
		results.close();
		ps.close();
		return false;
	}
	
	//Updates database with new reservation
	public void setReservations () throws SQLException {
		queryString = "INSERT INTO " + tableName + " (rent_from, rent_till, id, bike_id) "
				+ "VALUES (?, ?, ?, ?)";
		ps = conn.prepareStatement(queryString);
		ps.setTime(1, modifyTime(timeFrom));
		ps.setTime(2, modifyTime(timeTill));
		ps.setString(3, id);
		ps.setString(4, dbBike);
		ps.execute();
	}
	
	//Deletes reservation from database
	public void deleteReservations () throws SQLException {
		queryString = "DELETE FROM " + tableName + " WHERE id = ?";
		ps = conn.prepareStatement(queryString);
		ps.setString(1, id);
		ps.execute();
	}
	
	//Deletes table in database with date = yesterday
	public void deleteTable () throws SQLException {
		queryString = "DROP TABLE IF EXISTS " + tableName;
		ps = conn.prepareStatement(queryString);
		ps.execute();
	}
	
	//Creates table in database
	public void createTable () throws SQLException {
		queryString = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
				+ "id VARCHAR(100) NOT NULL, "
				+ "rent_from TIME NOT NULL, "
				+ "rent_till TIME NOT NULL, "
				+ "bike_id VARCHAR(30) NOT NULL, "
				+ "PRIMARY KEY (id))";
		ps = conn.prepareStatement(queryString);
		ps.execute();
	}
	
	//Modifies time value before adding a new reservation in database
	public Time modifyTime (String str) {
		try {
			Date date = dateFormat.parse(str);
		    Calendar calendar = GregorianCalendar.getInstance();
		    calendar.setTime(date);
		    calendar.add(GregorianCalendar.HOUR, -1);
		    date = calendar.getTime();
			Time time = new Time(date.getTime());
			return time;
		} catch (ParseException e) {
			return null;
		}
	}
	
	//Converts from String to Time variable
	public Time convertIntoTime (String str) {
		try {
			Date date = dateFormat.parse(str);
			Time time = new Time(date.getTime());
			return time;
		} catch (ParseException e) {
			showMsg("Invalid time!");
			return null;
		}
	}
	
	public void showMsg (String str) {
		JOptionPane.showMessageDialog(null, str, "Message", JOptionPane.ERROR_MESSAGE);
	}
}

