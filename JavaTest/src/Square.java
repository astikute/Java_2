
public class Square extends Rectangle {
	
	
	public Square() {
		this(0, Unit.milimetri);
	}
	
	public Square (int a) {
		super(a, a, Unit.milimetri);
	}
	
	public Square (int a, Unit units) {
		super(a, a, units);
	}
	
	public String toString() {
		return "Square [malas garums = " + garums + ", mçrvienîba = " + mervieniba + "]";
	}

}
