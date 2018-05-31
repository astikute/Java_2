
public class Task17 {

	public static void main(String[] args) {

		//17.uzdevums: noskaidrot, vai tas ir ma�iskais
		
		int [][] ints = //{{17, 10, 15}, {12, 14, 16}, {13, 18, 11}}; //ir
						//{{5, 4, 1}, {1, 9, 7}, {5, 2, 8}}; // nav
						//{{1, 15, 14, 4}, {10, 11, 8, 5}, {7, 6, 9, 12}, {16, 2, 3, 13}}; // ir!
						{{1, 15, 14, 4}, {11, 10, 8, 5}, {6, 7, 9, 12}, {16, 2, 3, 13}}; // diagon��u summa !=
		
		boolean Check = true; //Lai sekotu l�dzi, vai visi nosac�jumi izpild�s
		
		//P�rbauda, vai dimensijas ir vien�das
		for (int i = 0; i < ints.length; i++) {
				if (ints.length != ints[i].length) {
					Check = false;
				}
		}
		if (Check) {
			int [] sumResultsRow = new int [ints.length]; //Mas�vs rindu summu saglab��anai
			int [] sumResultsCol = new int [ints.length]; //Mas�vs kolonnu summu saglab��anai
			int [] sumResultsD = new int [2]; //Mas�vs abu diagon��u summu saglab��anai
		
			for (int i = 0; i < ints.length; i++) {
				for (int j = 0; j < ints[i].length; j++ ) { 
					sumResultsRow[i] = sumResultsRow[i] + ints[i][j]; //Summ� rind�s eso��s v�rt�bas kop�
					sumResultsCol [j] = sumResultsCol[j] + ints[i][j]; //Summ� kolonn�s eso��s v�rt�bas kop�
				}
				//Summ� pa diagon�li eso��s v�rt�bas
				sumResultsD[0] = sumResultsD[0] + ints[i][i]; 
				sumResultsD[1] = sumResultsD[1] + ints[i][ints.length-1-i];
			}
			// Sal�dzina, vai rindu un kolonnu summas ir vien�das
			for (int i = 1; i < ints.length; i++) {
				if ((sumResultsRow [i-1] != sumResultsRow [i]) || (sumResultsCol [i-1] != sumResultsCol [i])) {
					Check = false;
					break;
				}
			}
			// P�rbauda, vai diagon�les ir vien�das
			if (sumResultsD[0] != sumResultsD[1]) {
				Check = false;
			}
		}
		if (Check) {
			System.out.println("Tas ir ma�iskais kvadr�ts!");
		} else System.out.println("Tas NAV ma�iskais kvadr�ts");
	}
}
