import java.util.Random;
import java.util.Scanner;

public class Task10 {

	public static void main(String[] args) {

		//10.uzdevums: jauzmin uzgeneretais skaitlis
		Scanner sc = new Scanner (System.in);
		Random rnd = new Random();
		int randomNumb = rnd.nextInt(10) + 1;
		int userNumb = -1;
		boolean guess = false;
		
		System.out.println("I have chosen a number between 1 and 10, try to guess it!");
		System.out.println("What is your number?");
		
		while (!guess) {
			userNumb = sc.nextInt();
			if (userNumb != randomNumb) {
				System.out.println("No, try again!");
				continue;
			} guess = true;
		}
		sc.close();
		
		System.out.println("Yes, that's it!");
	}

}
