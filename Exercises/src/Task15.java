
public class Task15 {

	public static void main(String[] args) {

		//15.uzdevums: atbr�voties no visiem duplik�tiem mas�v�
		
		String [] arr = {"ABC", "CDE", "FGA", "CDE", "GAE", "ABC", "ABO"};
		
		String [] changedArr = new String [arr.length];
		int a = 0; // Lai izsekotu l�dzi, l�dz kuram ir aizpild�ts jaunizveidotais mas�vs
	
		for (int i = 0; i < arr.length; i++) { // dot� mas�va elementiem
			boolean repeatCheck = false;
			for (int j = 0; j < a; j++) { // no jauna izveidot� mas�va elementiem
				
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
