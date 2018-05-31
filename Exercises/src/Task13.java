
public class Task13 {

	public static void main(String[] args) {

		//13.uzdevums: kâpinât visus masîva elementus treðajâ pakâpç
		
		int [] ints = {1, 3, 5, 6, 7};
		
		for (int i = 0; i < ints.length; i++) {
			ints [i] = (int) Math.pow(ints[i], 3);
			System.out.print(ints[i] + " ");
		}
	}

}
