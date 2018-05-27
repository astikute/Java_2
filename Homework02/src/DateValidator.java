import java.util.Scanner;

public class DateValidator {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.print("Ievadiet datumu: ");
		int datums = sc.nextInt();
		System.out.print("Ievadiet mçnesi: ");
		int menesis = sc.nextInt();
		System.out.print("Ievadiet gadu: ");
		int gads = sc.nextInt();
		
		String [] menesuNosaukumi = {"janvâris", "februâris", "marts", "aprîlis", "maijs", "jûnijs", "jûlijs", "augusts", "septembris",
				"oktobris", "novembris", "decembris"};
	
		if (datums == 31 && (menesis == 4 || menesis == 6 || menesis == 9 || menesis == 11)) {
			System.out.println("Ðâda datuma konkrçtajâ mçnesî nav!");
		} else if (menesis == 2 && (gads % 4 != 0) && (datums == 29)) {
			System.out.println("Ðis gads ir îsais gads!");
		} else {
			System.out.println("Ievadîtie dati: " + datums + "." + menesuNosaukumi [menesis-1] + " " + gads + ".gads");
		}
		
		sc.close();
	}
	

}
