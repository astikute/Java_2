
public class Circle extends Shape {

	int radiuss;
	private final double Pi = 3.14;
	
	public Circle () {
		this(0, Unit.milimetri);
	}
	
	public Circle (int a) {
		this(a, Unit.milimetri);
	}
	
	public Circle (int a, Unit units) {
		radiuss = a;
		mervieniba = units;
	}
	
	public double area() {
		return Pi * Math.pow(radiuss, 2);
	}
	
	public double perimeter() {
		return 2 * Pi * radiuss;
	}

	public String toString() {
		return "Circle [râdiuss = " + radiuss + ", mçrvienîba = " + mervieniba + "]";
	}
}
