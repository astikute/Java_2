import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Operations {
	
	private static DateFormat dateFormat = new SimpleDateFormat("HH:mm");
	private static Date date;
	private static Time time;

	//Modify time value before adding a new reservation in database
	public static Time modifyTime (String str) {
		try {
			date = dateFormat.parse(str);
		    Calendar calendar = GregorianCalendar.getInstance();
		    calendar.setTime(date);
		    calendar.add(GregorianCalendar.HOUR, -1);
		    date = calendar.getTime();
			time = new Time(date.getTime());
			return time;
		} catch (ParseException e) {
			return null;
		}
	}
	
	//Converts from String to Time variable
	public static Time convertIntoTime (String str) {
		try {
			date = dateFormat.parse(str);
			time = new Time(date.getTime());
			return time;
		} catch (ParseException e) {
			return null;
		}
	}
	
	//Checks if input time is valid
	public static boolean isValidInput (String str) {
		try {
			new SimpleDateFormat("HH:mm").parse(str);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	
	//Modifies database table name
	public static String modifyString (String name, String date) {
		name = "rent_bike_";
		return name + date.replace("-", "");
	}
	
	//Finds out which date is today, was yesterday and will be tomorrow
	public static String[] updateDate () {
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
