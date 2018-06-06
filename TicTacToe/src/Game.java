import java.util.Scanner;

public class Game {

	private static int moveCount; // Lai mainitu speletajus (nepara skaitlis - Player1; para skaitlis - Player2)

	Player player1, player2;

	//Konstruktors
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
		boolean continueGame;
		int[] arrPosition; //Masivs, lai saglabatu gajiena koordinatas
		String scan; //Nolasa ievadito virkni
		String playerSymbol; //Saglaba vertibu "X" vai "O"
		int playerOneWins = 0, playerTwoWins = 0, tiesCount = 0; //Skaita uzvaras un neizskirtus
		String playersVariation; //Saglaba izveli, kadi bus speletaji
		Game newGame;
		
		//Noskaidrojam speletajus
		System.out.print("Izv�lieties sp�l�t�jus (varianti: [1] cilv�ks-cilv�ks, [2] cilv�ks-dators): ");
		playersVariation = sc.next();

		switch (playersVariation) {
			default: 
				System.out.println("T�du izv�li nedev�m! Sp�l�si cilv�ks pret cilv�ku!");
			case "1":
				newGame = new Game(new HumanPlayer(), new HumanPlayer());
				break;
			case "2":
				newGame = new Game(new HumanPlayer(), new ComputerPlayer());
				break;
		}
		//Speles sakums
		do {
			System.out.println("---TicTacToe----");
			Board.clearBoard();
			Board.printBoard();

			moveCount = 0;

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

			// Noskaidro statusu, lai sekotu lidzi kopejam rezultatam
			if (gameStatus() == GameStatus.Uzvar�jis_sp�l�t�js_1) {
				playerOneWins++;
			} else if (gameStatus() == GameStatus.Uzvar�jis_sp�l�t�js_2) {
				playerTwoWins++;
			} else
				tiesCount++;

			System.out.println("Uzvaras: 1.sp�l�t�js - " + playerOneWins + "; 2.sp�l�t�js - " + playerTwoWins
					+ "; neiz��irti - " + tiesCount);

			// Noskaidrojam, vai velas turpinat
			System.out.println("Ja velies turpinat, saki 'Ja'!");
			sc.nextLine(); //Drosibai, ja sc paliek kaut kas no ieprieksejas ievades
			scan = sc.next();
			continueGame = (scan.equals("Ja")) ? true : false;

		} while (continueGame);
	}	
}