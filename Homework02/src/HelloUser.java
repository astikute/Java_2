import java.util.Scanner;

public class HelloUser {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Ievada datus
		System.out.println("Ievadiet vârdu un uzvârdu: ");
		String vardsUzvards = sc.nextLine();

		// Likvidç atstarpes virkne sâkumâ, ja tâdas ir
		vardsUzvards = vardsUzvards.trim();

		String vards = "", vardsKorekcija;
		
		// Ja ir ievadîts vârds + uzvârds(-i)
		if (vardsUzvards.contains(" ")) {
			vards = vardsUzvards.substring(0, vardsUzvards.indexOf(' ')); // atdala vârdu no ievadîtâs virknes
			vardsKorekcija = vards.substring(0, 1).toUpperCase() + vards.substring(1).toLowerCase();
			System.out.println("'" + vardsKorekcija + "'");

			String uzvards = vardsUzvards.substring(vardsUzvards.indexOf(' ')).trim().toUpperCase(); // atdala uzvârda(-u) daïu

			if (uzvards.contains(" ")) { // Ja ir vairâki uzvârdi, atdala katru atseviðíi
				String [] arr = uzvards.split(" ");
				for (int i = 0; i < arr.length; i++) {
					if (!arr[i].equals("")) {
						System.out.print("'" + arr[i] + "'");
					}
				/*String uzvards1 = uzvards.substring(0, uzvards.indexOf(' '));
				String uzvards2 = uzvards.substring(uzvards.indexOf(' ')).trim();
				System.out.println("'" + uzvards1 + "-" + uzvards2 + "'");*/
				}
			} else { // Ja ir tikai viens uzvârds
				System.out.print("'" + uzvards + "'");
			}
			
		} else { // Ja ir ievadîts tikai vârds
			vardsKorekcija = vardsUzvards.substring(0, 1).toUpperCase() + vardsUzvards.substring(1).toLowerCase();
			System.out.println("'" + vardsKorekcija + "'");
			System.out.println("'N/A'");
		}
		sc.close();
	}
}
