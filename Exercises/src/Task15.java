
public class Task15 {

	public static void main(String[] args) {

		//15.uzdevums: atbrîvoties no visiem duplikâtiem masîvâ
		
		String [] arr = {"ABC", "CDE", "FGA", "CDE", "GAE", "ABC", "ABO"};
		
		String [] changedArr = new String [arr.length];
		int a = 0; // Lai izsekotu lîdzi, lîdz kuram ir aizpildîts jaunizveidotais masîvs
	
		for (int i = 0; i < arr.length; i++) { // dotâ masîva elementiem
			boolean repeatCheck = false;
			for (int j = 0; j < a; j++) { // no jauna izveidotâ masîva elementiem
				
				if (arr[i].equals(changedArr[j])) {
					repeatCheck = true;
					break;
				}
			}
			if (!repeatCheck) {
				changedArr [a] = arr [i];
				System.out.print(changedArr[a] + " ");
				a++;
			}

		}
	}

}
