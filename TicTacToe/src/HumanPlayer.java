import java.util.Scanner;

public class HumanPlayer implements Player {

	public int[] makeMove() {

		Scanner sc = new Scanner(System.in);

		int[] movePosition = new int[2];

		do {
			movePosition[0] = askPlayerMove(sc, "Ievadi rindu (0-2): ");
			movePosition[1] = askPlayerMove(sc, "Ievadi kolonnu (0-2): ");

		} while (!Board.checkMove(movePosition[0], movePosition[1]));

		return movePosition;
	}

	private boolean checkPosition(int a) {
		return (a >= 0 && a <= 2);
	}

	private int askPlayerMove(Scanner sc, String str) {
		int a; boolean isInt;
		do {
			do {
				System.out.print(str);
				isInt = sc.hasNextInt(); //Parbauda, vai ir ievadits skaitlis
				if (!isInt) {
					sc.next();
				}
			} while (!isInt);
			a = sc.nextInt();
		} while (!checkPosition(a)); //Parbauda, vai var novietot taja pozicija
		return a;
	}
}
