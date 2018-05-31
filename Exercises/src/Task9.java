import java.util.Scanner;

public class Task9 {

	public static void main(String[] args) {

		//9.uzdevums: jaizvada ievadita skaitla reizrekina tabula
		Scanner sc = new Scanner(System.in);
		
		System.out.print("Ievadiet veselu skaitli: ");
		
		int numb = sc.nextInt();
		sc.close();
		
		for (int i = 0; i < 6; i++) {
			System.out.println(numb + "*" + i + " = " + numb*i);
		}
	}

}
