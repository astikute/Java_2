
public abstract class Shape {
	
	String color = "colorless";
	
	Unit mervieniba;

	public String getColor() {
		return color;
	}
	
	public void setColor(String color) {
		this.color = color;
	}
	
	public Unit getUnits () {
		return mervieniba;
	}
	
	public abstract double area( );
	public abstract double perimeter( );
}
