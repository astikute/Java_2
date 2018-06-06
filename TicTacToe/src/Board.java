
public class Board {
	
	private static String[][] arr = { { " ", " ", " " }, { " ", " ", " " }, { " ", " ", " " } };

	public static void printBoard() {
		for (int i = 0; i < arr.length; i++) {
			System.out.print("|");
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + "|");
			}
			System.out.println();
		}
	}

	public static void setMove(int a, int b, String str) {
		arr[a][b] = str;
	}

	public static boolean checkMove(int a, int b) {
		return (arr[a][b].equals(" "));
	}

	public static boolean checkWinner() {
		for (int i = 0; i < arr.length; i++) {
			if (!arr[i][0].equals(" ")) { // Parbauda, vai rinda sakas ar "X" vai "O"
				if (arr[i][0].equals(arr[i][1]) && arr[i][1].equals(arr[i][2])) { // parbauda, vai rindas elementi ir vienadi
					return true;
				}
				if (!arr[0][i].equals(" ")) { // Parbauda, vai kolonna sakas ar "X" vai "O"
					if (arr[0][i].equals(arr[1][i]) && arr[1][i].equals(arr[2][i])) { // parbauda, vai kolonnas elementi ir vienadi
						return true;
					}
				}
			}
		}
		if (!arr[1][1].equals(" ")) { // Parbauda, vai masiva centrs satur "X" vai "O"
			if (arr[0][0].equals(arr[1][1]) && arr[1][1].equals(arr[2][2])) { // parbauda, vai diagonales visi elementi ir vienadi
				return true;
			} else if (arr[2][0].equals(arr[1][1]) && arr[1][1].equals(arr[0][2])) {
				return true;
			}
		}
		return false;
	}

	public static boolean checkTie() {
		int filledPositions = 0;
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				if (arr[i][j].equals("X") || arr[i][j].equals("O")) {
					filledPositions++;
				}
			}
		}
		return filledPositions == 9;
	}

	public static void clearBoard() {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = " ";
			}
		}
	}
}
