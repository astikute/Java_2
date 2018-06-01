import java.util.Scanner;
import java.util.Random;

public class SortArray_v2 {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Random rnd = new Random();

		// Noskaidrojam masiva izmeru
		System.out.println("Ievadiet masîva izmçru (robeþâs no 20 lîdz 40): ");
		int arrSize = 0;
		boolean checkArrSize = false;

		while (!checkArrSize) {
			arrSize = sc.nextInt();
			if (arrSize >= 20 && arrSize <= 40) {
				checkArrSize = true;
			} else
				System.out.println("Neatbilstoða vçrtîba! Ievadiet vçlreiz: ");
		}
		sc.close();

		// Izveidojam masivu, pieskiram vertibas un izvadam
		int[] arr = new int[arrSize];
		for (int i = 0; i < arrSize; i++) {
			arr[i] = rnd.nextInt(90) + 10;
		}
		PrintArray("Masîva elementi: ", arr);

		// Atrodam min vertibu
		int value, setMin, counter = 0, id = 0;
		while (counter < arrSize) {
			setMin = 100;
			for (int i = counter; i < arrSize; i++) {
				if (setMin > arr[i]) {
					setMin = arr[i]; //Saglaba min vertibu
					id = i; //Saglaba min vertibai atbilstoso sunas adresi
				}
			}
			// Samaina masiva elementu vertibas
			value = arr[counter];
			arr[counter] = setMin; 
			arr[id] = value;
			counter++;
		}
		System.out.println();
		PrintArray("Masîva elementi augoðâ secîbâ: ", arr);
	}

	public static void PrintArray(String str, int[] array) {
		System.out.print(str);
		for (int i = 0; i < array.length; i++) {
			if (i != array.length - 1) {
				System.out.print(array[i] + ", ");
			} else
				System.out.print(array[i]);
		}
	}
}
