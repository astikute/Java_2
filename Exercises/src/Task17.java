
public class Task17 {

	public static void main(String[] args) {

		//17.uzdevums: noskaidrot, vai tas ir maìiskais
		
		int [][] ints = //{{17, 10, 15}, {12, 14, 16}, {13, 18, 11}}; //ir
						//{{5, 4, 1}, {1, 9, 7}, {5, 2, 8}}; // nav
						//{{1, 15, 14, 4}, {10, 11, 8, 5}, {7, 6, 9, 12}, {16, 2, 3, 13}}; // ir!
						{{1, 15, 14, 4}, {11, 10, 8, 5}, {6, 7, 9, 12}, {16, 2, 3, 13}}; // diagonâïu summa !=
		
		boolean Check = true; //Lai sekotu lîdzi, vai visi nosacîjumi izpildâs
		
		//Pârbauda, vai dimensijas ir vienâdas
		for (int i = 0; i < ints.length; i++) {
				if (ints.length != ints[i].length) {
					Check = false;
				}
		}
		if (Check) {
			int [] sumResultsRow = new int [ints.length]; //Masîvs rindu summu saglabâðanai
			int [] sumResultsCol = new int [ints.length]; //Masîvs kolonnu summu saglabâðanai
			int [] sumResultsD = new int [2]; //Masîvs abu diagonâïu summu saglabâðanai
		
			for (int i = 0; i < ints.length; i++) {
				for (int j = 0; j < ints[i].length; j++ ) { 
					sumResultsRow[i] = sumResultsRow[i] + ints[i][j]; //Summç rindâs esoðâs vçrtîbas kopâ
					sumResultsCol [j] = sumResultsCol[j] + ints[i][j]; //Summç kolonnâs esoðâs vçrtîbas kopâ
				}
				//Summç pa diagonâli esoðâs vçrtîbas
				sumResultsD[0] = sumResultsD[0] + ints[i][i]; 
				sumResultsD[1] = sumResultsD[1] + ints[i][ints.length-1-i];
			}
			// Salîdzina, vai rindu un kolonnu summas ir vienâdas
			for (int i = 1; i < ints.length; i++) {
				if ((sumResultsRow [i-1] != sumResultsRow [i]) || (sumResultsCol [i-1] != sumResultsCol [i])) {
					Check = false;
					break;
				}
			}
			// Pârbauda, vai diagonâles ir vienâdas
			if (sumResultsD[0] != sumResultsD[1]) {
				Check = false;
			}
		}
		if (Check) {
			System.out.println("Tas ir maìiskais kvadrâts!");
		} else System.out.println("Tas NAV maìiskais kvadrâts");
	}
}
