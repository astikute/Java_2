import java.sql.SQLException;
import gui.MainDisplay;

public class test {

	public static void main(String[] args) {
		
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	
            	try {
					new MainDisplay();
				} catch (SQLException e) {
					e.printStackTrace();
				}
            }
        });
	}
}

