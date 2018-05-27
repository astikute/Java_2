
public class StringOperations {

	public static void main(String[] args) {
		
		String str = "Java Coding School";
		
		System.out.println(str);
		
		String strConcat = str.concat(" is Cool!"); // orìinâlo nemodificç!
		System.out.println(strConcat);
		
		String strUpper = str.toUpperCase(); // orìinâlo nemodificç!
		System.out.println(strUpper);
		
		System.out.println(str.substring(5)); //Coding School - izgrieþ 0-5 un atgrieþ atlikumu
		System.out.println(str.substring(0,4)); //Java - IZGRIEþ un atgrieþ 0-4
		System.out.println(str.substring(12,str.length())); //School
	
		System.out.println(str.contains("Java")); //true
		System.out.println(str.equals("Java")); //false - ðâdi salîdzina string tipa mainîgo!
		System.out.println(str.equalsIgnoreCase("JAVA CODING SCHOOL")); //true
		
		System.out.println(str.toUpperCase().equals("JAVA CODING SCHOOL")); //true - ðâdi arî var secîbâ!

	}
	
	
}
