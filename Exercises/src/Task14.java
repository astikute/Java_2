
public class Task14 {

	public static void main(String[] args) {

		//14.uzdevums: visus mas�va elementus, kas ir 0, p�rvietot uz mas�va beig�m
		
		int [] ints = {0, 4, 0, 7, 8, 9, 0, 1};
		
		int [] changedInts = new int [ints.length]; // s�kum� visi elementi mas�v� ir 0
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
