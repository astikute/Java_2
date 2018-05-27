import java.util.Scanner;

public class DateValidator {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		
		System.out.print("Ievadiet datumu: ");
		int datums = sc.nextInt();
		System.out.print("Ievadiet m�nesi: ");
		int menesis = sc.nextInt();
		System.out.print("Ievadiet gadu: ");
		int gads = sc.nextInt();
		
		String [] menesuNosaukumi = {"janv�ris", "febru�ris", "marts", "apr�lis", "maijs", "j�nijs", "j�lijs", "augusts", "septembris",
				"oktobris", "novembris", "decembris"};
	
		if (datums == 31 && (menesis == 4 || menesis == 6 || menesis == 9 || menesis == 11)) {
			System.out.println("��da datuma konkr�taj� m�nes� nav!");
		} else if (menesis == 2 && (gads % 4 != 0) && (datums == 29)) {
			System.out.println("�is gads ir �sais gads!");
		} else {
			System.out.println("Ievad�tie dati: " + datums + "." + menesuNosaukumi [menesis-1] + " " + gads + ".gads");
		}
		
		sc.close();
	}
	

}
