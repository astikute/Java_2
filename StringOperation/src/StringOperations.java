
public class StringOperations {

	public static void main(String[] args) {
		
		String str = "Java Coding School";
		
		System.out.println(str);
		
		String strConcat = str.concat(" is Cool!"); // or�in�lo nemodific�!
		System.out.println(strConcat);
		
		String strUpper = str.toUpperCase(); // or�in�lo nemodific�!
		System.out.println(strUpper);
		
		System.out.println(str.substring(5)); //Coding School - izgrie� 0-5 un atgrie� atlikumu
		System.out.println(str.substring(0,4)); //Java - IZGRIE� un atgrie� 0-4
		System.out.println(str.substring(12,str.length())); //School
	
		System.out.println(str.contains("Java")); //true
		System.out.println(str.equals("Java")); //false - ��di sal�dzina string tipa main�go!
		System.out.println(str.equalsIgnoreCase("JAVA CODING SCHOOL")); //true
		
		System.out.println(str.toUpperCase().equals("JAVA CODING SCHOOL")); //true - ��di ar� var sec�b�!

	}
	
	
}
