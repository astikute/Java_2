import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class MainDisplay extends JFrame implements ActionListener  {
	
	Connection conn;
	
	JPanel main = new JPanel ();
	JTable table = new JTable();
	JButton enterBtn = new JButton("OK");
	JLabel info = new JLabel ("Rent bike");
	JLabel userLabel = new JLabel("Id:");
	JTextField inputUser = new JTextField ();
	JLabel timeFrom = new JLabel("From:");
	JTextField inputFrom = new JTextField ("00:00");
	JLabel timeTill = new JLabel("Till:");
	JTextField inputTill = new JTextField ("00:00");
	
	Object [] columnNames = {"Id", "Name", "Last Name", "From", "Till"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	
	public MainDisplay(Connection conn) {
		
		this.conn = conn;
		
		model.addRow(columnNames);
		CommunicateDb.displayReservations(conn, model);

		table.setModel(model);
		table.setRowHeight(table.getRowHeight()+5);
		main.add(table);
		table.setEnabled(false);
		main.add(info);
		main.add(userLabel);
		main.add(inputUser);
		inputUser.setColumns(15);
		main.add(timeFrom);
		main.add(inputFrom);
		inputFrom.setColumns(10);
		main.add(timeTill);
		main.add(inputTill);
		inputTill.setColumns(10);
		main.add(enterBtn);
		main.add(enterBtn);
		enterBtn.addActionListener(this);
		add(main);
		setSize (1000,500);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		String input = inputUser.getText().trim();
		String timeFrom = inputFrom.getText().trim();
		String timeTill = inputTill.getText().trim();
		Boolean isValidUser = false;
		Boolean rentAvailable = false;
		
		if (input.length() != 0) { 
			isValidUser = CommunicateDb.validateUser(conn, input);
			if (isValidUser) {
				rentAvailable = CommunicateDb.checkReservations(conn, timeFrom, timeTill);
				if (rentAvailable) {
					CommunicateDb.setReservations(conn, timeFrom, timeTill, input);
					CommunicateDb.displayReservations(conn, model);
				} else JOptionPane.showMessageDialog(main, "Reservation is not possible!", "Message", JOptionPane.ERROR_MESSAGE);
			} else JOptionPane.showMessageDialog(main, "Invalid id!", "Message", JOptionPane.ERROR_MESSAGE);
		} else JOptionPane.showMessageDialog(main, "You forgot employee id.", "Message", JOptionPane.QUESTION_MESSAGE); 
	}
}

