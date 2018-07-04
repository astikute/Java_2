package gui;

public class DeleteEvent {

	private String id;
	private String date;
	
	public DeleteEvent(String id, String date) {
		this.id = id;
		this.date = date;
	}
	
	public String getId () {
		return id;
	}
	
	public String getDate () {
		return date;
	}
}
