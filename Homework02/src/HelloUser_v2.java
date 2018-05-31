import java.util.Scanner;

public class HelloUser_v2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.println("Ievadiet vârdu un uzvârdu: ");
		String input = sc.nextLine();
		sc.close();
		
		input = input.trim();
		
		String [] arr = input.split(" ");
		
		arr[0] = arr[0].substring(0,1).toUpperCase() + arr[0].substring(1).toLowerCase();
		System.out.println("'" + arr[0] + "'");
		
		for (int i = 1; i < arr.length; i++) {
			if (!arr[i].equals("")) {
				System.out.println("'" + arr[i].toUpperCase() + "'");
			}
		}
		if (!input.contains(" ")) {
			System.out.println("'N/A'");
		}
	}

}
