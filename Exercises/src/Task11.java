
public class Task11 {

	public static void main(String[] args) {

		//11.uzdevums: j�izvada visus pozit�vo divu ciparu skait�us un to ciparu summu
		
		int theNumb = 10;
		
		for (int i = 1; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				System.out.println(theNumb + ": " + i + " + " + j + " = " +(i+j));
				theNumb++;
			}
		}

	}

}
