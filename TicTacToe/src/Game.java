import java.util.Scanner;

public class Game {

	private static int moveCount; //Lai mainitu speletajus (nepara skaitlis - Player1; para skaitlis - Player2)

	Player player1, player2;

	private Game(Player p1, Player p2) {
		player1 = p1;
		player2 = p2;
	}

	private static GameStatus gameStatus() {

		if (Board.checkWinner() && moveCount % 2 != 0) {
			return GameStatus.Uzvar�jis_sp�l�t�js_1;
		} else if (Board.checkWinner()) {
			return GameStatus.Uzvar�jis_sp�l�t�js_2;
		} else if (Board.checkTie()) {
			return GameStatus.Neiz��irts;
		} else
			return GameStatus.Sp�le_akt�va;
	}

	public static void playGame() {
		
		Scanner sc = new Scanner(System.in);
		boolean continueGame; int[] arrPosition; String scan; String playerSymbol;
		
		do {
			System.out.println("---TicTacToe----");
			Board.clearBoard();
			Board.printBoard();
			
			moveCount = 0;

			Game newGame = new Game(new HumanPlayer(), new ComputerPlayer());

			do {

				moveCount++;
				if (moveCount % 2 != 0) {
					System.out.println("1.sp�l�t�js");
					arrPosition = newGame.player1.makeMove();
					playerSymbol = "X";

				} else {
					System.out.println("2.sp�l�t�js");
					arrPosition = newGame.player2.makeMove();
					playerSymbol = "O";
				}

				Board.setMove(arrPosition[0], arrPosition[1], playerSymbol);
				Board.printBoard();

			} while (gameStatus() == GameStatus.Sp�le_akt�va);

			System.out.println(gameStatus());

			System.out.println("Vai turpin�t (Ja/Ne)?");
			scan = sc.nextLine();
			continueGame = (scan.equals("Ja")) ? true : false;

		} while (continueGame);
	}
}