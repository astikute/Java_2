import java.awt.BorderLayout;
import java.awt.Color;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class MainDisplay extends JFrame implements ActionListener, ListSelectionListener  {
	
	Connection conn;
	
	JPanel tableField = new JPanel ();
	static JPanel main = new JPanel ();
	JTable table = new JTable();
	JButton enterBtn = new JButton("Add");
	JLabel userLabel = new JLabel("Employee Id:");
	JTextField inputUser = new JTextField ();
	JLabel timeFrom = new JLabel("From:");
	JTextField inputFrom = new JTextField ("00:00");
	JLabel timeTill = new JLabel("Till:");
	JTextField inputTill = new JTextField ("00:00");
	JButton deleteBtn = new JButton("Cancel reservation");
	
	Object [] columnNames = {"Id", "Name", "Last Name", "From", "Till"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	
	public MainDisplay(Connection conn) {
		
		this.conn = conn;
		
		model.addRow(columnNames);
		CommunicateDb.displayReservations(conn, model);
		table.setModel(model);
		table.setRowHeight(table.getRowHeight()+5);
		table.getSelectionModel().addListSelectionListener(this);
		tableField.add(table);
		add(tableField, BorderLayout.CENTER);
		
		main.add(userLabel);
		main.add(inputUser);
		inputUser.setColumns(4);
		main.add(timeFrom);
		main.add(inputFrom);
		inputFrom.setColumns(4);
		main.add(timeTill);
		main.add(inputTill);
		inputTill.setColumns(4);
		main.add(enterBtn);
		enterBtn.addActionListener(this);
		deleteBtn.setEnabled(false);
		deleteBtn.addActionListener(this);
		main.add(deleteBtn);
		main.setBackground(Color.LIGHT_GRAY);
		add(main, BorderLayout.BEFORE_FIRST_LINE);
		
		setSize (1000,500);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		
		if (e.getSource() == enterBtn) {
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
					model.setRowCount(1);
					CommunicateDb.displayReservations(conn, model);
				} else JOptionPane.showMessageDialog(main, "Reservation is not possible!", "Message", JOptionPane.ERROR_MESSAGE);
			} else JOptionPane.showMessageDialog(main, "Invalid id!", "Message", JOptionPane.ERROR_MESSAGE);
		} else JOptionPane.showMessageDialog(main, "You forgot employee id.", "Message", JOptionPane.QUESTION_MESSAGE); 
		}
		
		if (e.getSource() == deleteBtn) {
			int selectedRow = table.getSelectedRow();
			int id = Integer.parseUnsignedInt((String) model.getValueAt(selectedRow, 0));
			CommunicateDb.deleteReservations(conn, id);
			model.setRowCount(1);
			CommunicateDb.displayReservations(conn, model);
			deleteBtn.setEnabled(false);
		}
	}

	public void valueChanged(ListSelectionEvent arg0) {
		
		int selectedRow = table.getSelectedRow();
		if (selectedRow > 0) {
			if (model.getValueAt(selectedRow, 4) != null && model.getValueAt(selectedRow, 3) != null) {
				deleteBtn.setEnabled(true); 
			} else deleteBtn.setEnabled(false);
		} 
	}
	
	public static void showMsg (String str) {
		JOptionPane.showMessageDialog(main, str, "Message", JOptionPane.ERROR_MESSAGE);
	}
}

