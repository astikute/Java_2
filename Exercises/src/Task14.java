
public class Task14 {

	public static void main(String[] args) {

		//14.uzdevums: visus masîva elementus, kas ir 0, pârvietot uz masîva beigâm
		
		int [] ints = {0, 4, 0, 7, 8, 9, 0, 1};
		
		int [] changedInts = new int [ints.length]; // sâkumâ visi elementi masîvâ ir 0
		int j = 0;
		
		for (int i = 0; i < ints.length; i++) {
			if (ints[i] != 0) {
				changedInts [j] = ints[i];
				j++;
			}
		}
		for (int i = 0; i < ints.length; i++) {
			System.out.print(changedInts[i] + " ");
		}
	}

}
