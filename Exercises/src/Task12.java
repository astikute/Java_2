
public class Task12 {

	public static void main(String[] args) {

		//12.uzdevums: string masiva aizstaj skaitlus, ja tie dalas ar 3 (ar "fizz"), ar 5 ("buzz"), ar abiem ("fizzBuzz")
		
		String [] arr = {"1", "5", "3", "15", "8", "9", "26"};
		
		int [] ints = new int [arr.length];
		
		for (int i = 0; i < arr.length; i++) {
			
			ints [i] = Integer.parseInt(arr[i]);
			
			if (ints[i] % 5 == 0 && ints[i] % 3 == 0) {
				arr[i] = "fizzBuzz";
			} else if (ints[i] % 5 == 0) {
				arr[i] = "buzz";
			} else if (ints[i] % 3 == 0) {
				arr[i] = "fizz";
			}
			
			System.out.print(arr[i] + " ");
		}
	}

}
