import java.util.Random;

public class ComputerPlayer implements Player {

	public int[] makeMove() {

		Random rnd = new Random();
		
		int[] movePosition = new int[2];

		do {
		
		movePosition[0] = rnd.nextInt(3);
		movePosition[1] = rnd.nextInt(3);

		} while (!Board.checkMove(movePosition[0], movePosition[1]));
		
		return movePosition;
	}

}
