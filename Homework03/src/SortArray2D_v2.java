import java.util.Scanner;
import java.util.Random;

public class SortArray2D_v2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Random rnd = new Random();
		
		//Paprasam no lietotaja ievadi un parbaudam
		boolean usersChoiceCheck = false;
		int usersChoice = 0;
		System.out.print("Cik masîvus ìenerçt (ievadi no 10 lîdz 20)? ");
		
		while (!usersChoiceCheck ) {
			usersChoice = sc.nextInt();
			if (usersChoice >= 10 && usersChoice <= 20) {
				usersChoiceCheck = true;
			} else System.out.print("Neizdevâs ievade. Mçìini vçlreiz: "); 
		}
		sc.close();
 
		int randomNumb = rnd.nextInt(41) + 10;

		//Izveidojam 2D masivu
		int[][] arr = new int[usersChoice][randomNumb];
		System.out.println();
		for (int i = 0; i < usersChoice; i++) {
			for (int j = 0; j < randomNumb; j++) {
				arr[i][j] = rnd.nextInt(900) + 100;
			}
		}

		int value, setMax, counter, id = 0;
		int[] arrAvg = new int[usersChoice]; //Masivs, lai saglabatu katra individuala masiva vid vertibu

		//Kartojam masivu dilstosa seciba
		for (int j = 0; j < usersChoice; j++) {
			counter = 0;
			while (counter < randomNumb) {
				setMax = 0;
				for (int i = counter; i < randomNumb; i++) {
					if (setMax < arr[j][i]) {
						setMax = arr[j][i];
						id = i;
					}
					arrAvg[j] = arrAvg[j] + arr[j][i]; //Saglaba summu individualajam masivam
				}
				value = arr[j][counter];
				arr[j][counter] = setMax;
				arr[j][id] = value;
				counter++;
			}
			arrAvg[j] = arrAvg[j] / randomNumb; //Aprekina videjo vertibu
		}

		int setMin;
		counter = 0;
		id = 0;
		//Kartojam arrAvg vertibas augosa seciba
		while (counter < usersChoice) {
			setMin = 10000;
			for (int i = counter; i < usersChoice; i++) {
				if (setMin > arrAvg[i]) {
					setMin = arrAvg[i];
					id = i;
				}
			}
			value = arrAvg[counter];
			arrAvg[counter] = setMin;
			arrAvg[id] = value;

			//Samainam 2D individualos masivus (balstoties uz arrAvg rezultata)
			for (int z = 0; z < randomNumb; z++) {
				value = arr[counter][z];
				arr[counter][z] = setMin;
				arr[id][z] = value;
			}
			counter++;
		}

		System.out.println("Masîva daïa ar lielâko vidçjo vçrtîbu: ");

		for (int i = 0; i < randomNumb; i++) {
			if (i != arr[id].length - 1) {
				System.out.print(arr[id][i] + ", ");
			} else
				System.out.print(arr[id][i]);
		}
	}
}
