
public class RectangleTest {

	public static void main(String[] args) {
		
		//Taisnst�ris
		Rectangle newRectangle = new Rectangle (4, 5, Unit.centimetri);
		
		System.out.println("isSquare: " + newRectangle.isSquare());
		System.out.println("area: " + newRectangle.area());
		System.out.println("perimeter: " + newRectangle.perimeter());
		System.out.println(newRectangle);
		
		newRectangle.setColor("Red");
		System.out.println("color: " + newRectangle.getColor());
		
		double convertNewRectangle = UnitUtils.convertPerimeter(newRectangle, Unit.milimetri);
		System.out.println("Perimetrs p�rveidots: " + convertNewRectangle + " (no " + newRectangle.getUnits() + " uz milimetri)");
		
		System.out.println();
		
		//Kvadr�ts
		Square newSquare = new Square (250);

		System.out.println("isSquare: " + newSquare.isSquare());
		System.out.println("area: " + newSquare.area());
		System.out.println("perimeter: " + newSquare.perimeter());
		System.out.println(newSquare);
		
		System.out.println("color: " + newSquare.getColor());
		
		double convertNewSquare = UnitUtils.convertArea(newSquare, Unit.metri);
		System.out.println("Laukums p�rveidots: " + convertNewSquare + " (no " + newSquare.getUnits() + " uz metri)");
		
		System.out.println();
		
		//Aplis
		Circle newCircle = new Circle (9, Unit.centimetri);
		System.out.println("area: " + newCircle.area());
		System.out.println("perimeter: " + newCircle.perimeter());
		System.out.println(newCircle);
		
	}
}
