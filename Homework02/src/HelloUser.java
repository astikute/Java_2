import java.util.Scanner;

public class HelloUser {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		// Ievada datus
		System.out.println("Ievadiet v�rdu un uzv�rdu: ");
		String vardsUzvards = sc.nextLine();

		// Likvid� atstarpes virkne s�kum�, ja t�das ir
		vardsUzvards = vardsUzvards.trim();

		String vards = "", vardsKorekcija;
		
		// Ja ir ievad�ts v�rds + uzv�rds(-i)
		if (vardsUzvards.contains(" ")) {
			vards = vardsUzvards.substring(0, vardsUzvards.indexOf(' ')); // atdala v�rdu no ievad�t�s virknes
			vardsKorekcija = vards.substring(0, 1).toUpperCase() + vards.substring(1).toLowerCase();
			System.out.println("'" + vardsKorekcija + "'");

			String uzvards = vardsUzvards.substring(vardsUzvards.indexOf(' ')).trim().toUpperCase(); // atdala uzv�rda(-u) da�u

			if (uzvards.contains(" ")) { // Ja ir vair�ki uzv�rdi, atdala katru atsevi��i
				String [] arr = uzvards.split(" ");
				for (int i = 0; i < arr.length; i++) {
					if (!arr[i].equals("")) {
						System.out.print("'" + arr[i] + "'");
					}
				/*String uzvards1 = uzvards.substring(0, uzvards.indexOf(' '));
				String uzvards2 = uzvards.substring(uzvards.indexOf(' ')).trim();
				System.out.println("'" + uzvards1 + "-" + uzvards2 + "'");*/
				}
			} else { // Ja ir tikai viens uzv�rds
				System.out.print("'" + uzvards + "'");
			}
			
		} else { // Ja ir ievad�ts tikai v�rds
			vardsKorekcija = vardsUzvards.substring(0, 1).toUpperCase() + vardsUzvards.substring(1).toLowerCase();
			System.out.println("'" + vardsKorekcija + "'");
			System.out.println("'N/A'");
		}
		sc.close();
	}
}
