import java.text.DecimalFormat;

class Task16 {

	public static void main(String[] args) {

		//16.uzdevums: apprekina studenta videjo atzimi un saglaba tas viena masiva
		
		int [][] arrStudent = {	{68, 58, 79},
								{79, 76, 81} };
		
		String [] averageScore = new String [arrStudent.length];
		double sumScore;
		
		DecimalFormat dF = new DecimalFormat ("##.00");
		
		for (int i = 0; i < arrStudent.length; i++) {
			sumScore = 0;
			
			for (int j = 0; j < arrStudent[i].length; j++) {
				sumScore = sumScore + arrStudent[i][j];
			}
			
			averageScore [i] = dF.format((sumScore/arrStudent[i].length));
			System.out.println("Students_" + (i+1) + ": " + averageScore[i]);
		
	}
}}
