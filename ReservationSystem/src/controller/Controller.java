package controller;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import gui.ChangeDateEvent;
import gui.DeleteEvent;
import gui.ReservationEvent;
import model.CommunicateDb;

public class Controller  {

	CommunicateDb db = new CommunicateDb();
	
	public void makeReservation (ReservationEvent ev) throws SQLException {
		String id = ev.getId();
		String timeFrom = ev.getTimeFrom();
		String timeTill = ev.getTimeTill();
		String date = ev.getDate();
		String[] arr = {id, timeFrom, timeTill, date};
		db.setDate(date);
		db.requestReservation(arr);
		if (db.validateUser()) {
			if (db.checkReservations()) {
				db.setReservations();
			} else {
				db.showMsg("No bicycles are available for reservation!");
			}
		} else db.showMsg("Invalid employees id!");
	}
	
	public  ArrayList<Object[]> getReservations () throws SQLException {
		return db.getDbReservations();
	}
	
	public void createTables (ChangeDateEvent ev) throws SQLException {
		String date = ev.getDate();
		db.setDate(date);
		db.createTable();
	}
	
	public void deleteTable (ChangeDateEvent ev) throws SQLException {
		String date = ev.getDate();
		db.setDate(date);
		db.deleteTable();
	}
	
	public void deleteReservation (DeleteEvent ev) throws SQLException {
		String id = ev.getId();
		String date = ev.getDate();
		db.setDate(date);
		db.requestCancelReservation(id, date);
		db.deleteReservations();
	}
	
	public void changeView (ChangeDateEvent ev) throws SQLException {
		String date = ev.getDate();
		db.setDate(date);
	}
	
	//Finds out which date is today, was yesterday and will be tomorrow
	public String[] updateDate () {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = GregorianCalendar.getInstance();
		String today = df.format(calendar.getTime());
		calendar.add(GregorianCalendar.DAY_OF_MONTH, 1);
		String tomorrow = df.format(calendar.getTime());
		calendar = GregorianCalendar.getInstance();
		calendar.add(GregorianCalendar.DAY_OF_MONTH, -1);
		String yesterday = df.format(calendar.getTime());
		String[] dates = {today, tomorrow, yesterday};
		return dates;
	}
}