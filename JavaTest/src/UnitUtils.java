
public class UnitUtils {

	private static final int centimetresToMilimitres = 10;
	private static final int centimetresToMetres = 100;
	private static final int milimitresToMetres = 1000;

	private UnitUtils() {

	}

	public static double convertPerimeter(Rectangle rec, Unit units) {

		return funkcionConvertPerimeter(rec.perimeter(), rec.getUnits(), units);
	}
	
	public static double convertArea(Rectangle rec, Unit units) {

		return funkcionConvertArea(rec.area(), rec.getUnits(), units);
	}

	private static double funkcionConvertPerimeter(double d, Unit fromUnit, Unit toUnit) {

		if (fromUnit == Unit.milimetri && toUnit == Unit.centimetri) {
			return d / centimetresToMilimitres;
		} else if (fromUnit == Unit.milimetri && toUnit == Unit.metri) {
			return d / milimitresToMetres;
		} else if (fromUnit == Unit.centimetri && toUnit == Unit.milimetri) {
			return d * centimetresToMilimitres;
		} else if (fromUnit == Unit.centimetri && toUnit == Unit.metri) {
			return d / centimetresToMetres;
		} else if (fromUnit == Unit.metri && toUnit == Unit.centimetri) {
			return d * centimetresToMetres;
		} else if (fromUnit == Unit.metri && toUnit == Unit.milimetri) {
			return d / milimitresToMetres;
		} else
			return d;
	}
	
	private static double funkcionConvertArea(double d, Unit fromUnit, Unit toUnit) {

		if (fromUnit == Unit.milimetri && toUnit == Unit.centimetri) {
			return d / Math.pow(centimetresToMilimitres, 2);
		} else if (fromUnit == Unit.milimetri && toUnit == Unit.metri) {
			return d / Math.pow(milimitresToMetres, 2);
		} else if (fromUnit == Unit.centimetri && toUnit == Unit.milimetri) {
			return d * Math.pow(centimetresToMilimitres, 2);
		} else if (fromUnit == Unit.centimetri && toUnit == Unit.metri) {
			return d / Math.pow(centimetresToMetres, 2);
		} else if (fromUnit == Unit.metri && toUnit == Unit.centimetri) {
			return d * Math.pow(centimetresToMetres, 2);
		} else if (fromUnit == Unit.metri && toUnit == Unit.milimetri) {
			return d / Math.pow(milimitresToMetres, 2);
		} else
			return d;
	}
}
