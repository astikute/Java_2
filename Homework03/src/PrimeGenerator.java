
import java.util.Scanner;

public class PrimeGenerator {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		/* Pirmie 100 pirmskaitli
		 * int [] primeNumbArray = {1, 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41,
		 * 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107, 109, 113, 127,
		 * 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191, 193, 197, 199,
		 * 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271, 277, 281, 283,
		 * 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383,
		 * 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467,
		 * 479, 487, 491, 499, 503, 509, 521, 523, 541};
		 */

		System.out.print("Cik pirmskaitïus ìenerçsim (ievadi no 0 lîdz 100): ");
		int usersChoice = sc.nextInt();
		sc.close();

		int number = 0;
		boolean primeNumber;

		for (int i = 0; i < usersChoice; i++) {
			do {
				primeNumber = true;
				number++;
				for (int j = 2; j < number; j++) {
					if (number % j == 0) {
						primeNumber = false;
						break;
					}
				}
			} while (!primeNumber);
			System.out.print(number + " ");
		}
	}
}
