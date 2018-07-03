import javax.swing.table.DefaultTableModel;

public class test {

	public static void main(String[] args) {
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
            	MainDisplay mainDisplay = new MainDisplay();
            
            	String[] dates = Operations.updateDate();
            	DefaultTableModel model = mainDisplay.model;
            	
            	//Creates table in database for today and tomorrow reservations
            	for (int i = 0; i < 2; i++) {
            		CommunicateDb.createTable(dates[i]);
            	}
            	//Deletes table from previous day
            	CommunicateDb.deleteTable(dates[2]);
            	//Display reservations of today
            	CommunicateDb.displayReservations(dates[0], model);
            }
        });
	}
}

