import java.util.Scanner;

public class Syntax {

	public static void main(String[] args) {

		System.out.print("Ievadiet skaitli robeþâs starp -10 un 10: ");
		Scanner sc = new Scanner(System.in);

		int someNumber = sc.nextInt();

		if (someNumber > 10) {
			System.out.println("Lielâks par 10!");
			someNumber = 10;
		} else if (someNumber < -10) {
			System.out.println("Mazâks par -10");
			someNumber = -10;// ja viena rindiòa, ko izpildît {} var nerakstît!
		}

		System.out.println("Beigu vçrtîba: " + someNumber);
		sc.close();
	}
}
