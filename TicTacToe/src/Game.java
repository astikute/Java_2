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
			return GameStatus.Uzvarçjis_spçlçtâjs_1;
		} else if (Board.checkWinner()) {
			return GameStatus.Uzvarçjis_spçlçtâjs_2;
		} else if (Board.checkTie()) {
			return GameStatus.Neizðíirts;
		} else
			return GameStatus.Spçle_aktîva;
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
					System.out.println("1.spçlçtâjs");
					arrPosition = newGame.player1.makeMove();
					playerSymbol = "X";

				} else {
					System.out.println("2.spçlçtâjs");
					arrPosition = newGame.player2.makeMove();
					playerSymbol = "O";
				}

				Board.setMove(arrPosition[0], arrPosition[1], playerSymbol);
				Board.printBoard();

			} while (gameStatus() == GameStatus.Spçle_aktîva);

			System.out.println(gameStatus());

			System.out.println("Vai turpinât (Ja/Ne)?");
			scan = sc.nextLine();
			continueGame = (scan.equals("Ja")) ? true : false;

		} while (continueGame);
	}
}