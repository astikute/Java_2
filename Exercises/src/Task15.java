
public class Task15 {

	public static void main(String[] args) {

		//15.uzdevums: atbrivoties no visiem duplikatiem masiva
		
		String [] arr = {"ABC", "CDE", "FGA", "CDE", "GAE", "ABC", "ABO"};
		
		String [] changedArr = new String [arr.length];
		int a = 0; // Lai izsekotu lidzi, lidz kuram ir aizpildits jaunizveidotais masivs
	
		for (int i = 0; i < arr.length; i++) { // dota masiva elementiem
			boolean repeatCheck = false;
			for (int j = 0; j < a; j++) { // no jauna izveidota masiva elementiem
				
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
