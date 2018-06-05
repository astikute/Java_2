
public class Rectangle extends Shape {
	//�pa��bas
	int garums;
	int platums;
	
	//Konstruktori
	public Rectangle () { //Noklus�tais
		this(0, Unit.milimetri);
	}
	
	public Rectangle (int a) {
		this(a, a, Unit.milimetri);
	}
	
	public Rectangle (int a, Unit units) {
		this(a, a, units);
	}
	
	public Rectangle (int a, int b) {
		this(a, b, Unit.milimetri);
	}
	
	public Rectangle (int a, int b, Unit units) {
		garums = a;
		platums = b;
		mervieniba = units;
	}

	public String toString() {
		return "Rectangle [garums = " + garums + ", platums = " + platums + ", m�rvien�ba = " + mervieniba + "]";
	}
	
	public boolean isSquare() {
		return (garums == platums);
	}
	
	public double area( ) {
		return garums*platums;
	}
	
	public double perimeter () {
		return (garums + platums)*2;
	}
	
}
