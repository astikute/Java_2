import java.util.Scanner;

public class DateValidator {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		int datums = -1;
		while (datums < 0 || datums > 31) {
			System.out.print("Ievadiet datumu: ");
			datums = sc.nextInt();
		}
		
		int menesis = -1;
		while (menesis < 1 || menesis > 12) {
			System.out.print("Ievadiet mçnesi: ");
			menesis = sc.nextInt();
		}
		
		int gads = -1;
		while (gads < 1) {
			System.out.print("Ievadiet gadu: ");
			gads = sc.nextInt();
		}
		sc.close();
		
		String [] menesuNosaukumi = {"janvâris", "februâris", "marts", "aprîlis", "maijs", "jûnijs", "jûlijs", "augusts", "septembris",
				"oktobris", "novembris", "decembris"};
	
		if (datums == 31 && (menesis == 4 || menesis == 6 || menesis == 9 || menesis == 11)) {
			System.out.println("Ðâda datuma konkrçtajâ mçnesî nav!");
		} else if (menesis == 2 && (gads % 4 != 0) && (datums == 29)) {
			System.out.println("Ðis gads ir îsais gads!");
		} else {
			System.out.println("Ievadîtie dati: " + datums + "." + menesuNosaukumi [menesis-1] + " " + gads + ".gads");
		}
	}
}
