package gui;


public class ReservationEvent {
	
	private String id;
	private String from;
	private String till;
	private String date;
	
	public ReservationEvent(String id, String from, String till, String date) {
		this.id = id;
		this.from = from;
		this.till = till;
		this.date = date;
	}

	public String getId () {
		return id;
	}
	
	public String getTimeFrom () {
		return from;
	}
	
	public String getTimeTill () {
		return till;
	}
	
	public String getDate () {
		return date;
	}
}
		
		
		