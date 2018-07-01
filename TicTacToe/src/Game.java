import java.util.Scanner;

public class Game {

	private static Scanner sc = new Scanner(System.in);

	private static Game newGame;
	private static boolean continueGame;

	private static int[] arrPosition; // Masivs, lai saglabatu gajiena koordinatas

	private static String scan; // Nolasa ievadito virkni

	private static int playerOneWins = 0; // Skaita uzvaras un neizskirtus
	private static int playerTwoWins = 0;
	private static int tiesCount = 0;

	private static int moveCount; // Lai mainitu speletajus (nepara skaitlis - Player1; para skaitlis - Player2)
	private static String playersVariation; // Saglaba izveli, kadi bus speletaji
	private static String playerSymbol; // Saglaba vertibu "X" vai "O"

	Player player1, player2;

	// Konstruktors
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

		// Noskaidrojam speletajus
		enterPlayers();

		// Speles sakums
		do {
			System.out.println("---TicTacToe----");
			Board.clearBoard();
			Board.printBoard();

			moveCount = 0;

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

			// Noskaidro statusu, lai sekotu lidzi kopejam rezultatam
			overallStatus();

			// Noskaidrojam, vai velas turpinat
			System.out.println("Ja velies turpinat, saki 'Ja'!");
			sc.nextLine(); // Drosibai, ja sc paliek kaut kas no ieprieksejas ievades
			scan = sc.next();
			continueGame = (scan.equals("Ja")) ? true : false;

		} while (continueGame);
	}

	private static void enterPlayers() {

		System.out.print("Izvçlieties spçlçtâjus (varianti: [1] cilvçks-cilvçks, [2] cilvçks-dators): ");
		playersVariation = sc.next();

		switch (playersVariation) {
		default:
			System.out.println("Tâdu izvçli nedevâm! Spçlçsi cilvçks pret cilvçku!");
		case "1":
			newGame = new Game(new HumanPlayer(), new HumanPlayer());
			break;
		case "2":
			newGame = new Game(new HumanPlayer(), new ComputerPlayer());
			break;
		}
	}

	private static void overallStatus() {
		if (gameStatus() == GameStatus.Uzvarçjis_spçlçtâjs_1) {
			playerOneWins++;
		} else if (gameStatus() == GameStatus.Uzvarçjis_spçlçtâjs_2) {
			playerTwoWins++;
		} else
			tiesCount++;

		System.out.println("Uzvaras: 1.spçlçtâjs - " + playerOneWins + "; 2.spçlçtâjs - " + playerTwoWins
				+ "; neizðíirti - " + tiesCount);
	}
}