import java.util.Scanner;

public class Syntax {

	public static void main(String[] args) {

		System.out.print("Ievadiet skaitli robe��s starp -10 un 10: ");
		Scanner sc = new Scanner(System.in);

		int someNumber = sc.nextInt();

		if (someNumber > 10) {
			System.out.println("Liel�ks par 10!");
			someNumber = 10;
		} else if (someNumber < -10) {
			System.out.println("Maz�ks par -10");
			someNumber = -10;// ja viena rindi�a, ko izpild�t {} var nerakst�t!
		}

		System.out.println("Beigu v�rt�ba: " + someNumber);
		sc.close();
	}
}
