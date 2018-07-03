import javax.swing.table.DefaultTableModel;

public class test {

	public static void main(String[] args) {
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
            	MainDisplay mainDisplay = new MainDisplay();
            	mainDisplay.setUpView();
            	
            	String[] dates = Operations.updateDate();
            	DefaultTableModel model = mainDisplay.model;
            	CommunicateDb.createTable(dates[1]);
            	CommunicateDb.deleteTable(dates[2]);
            	CommunicateDb.displayReservations(dates[0], model);
 
            }
        });
	}
}

