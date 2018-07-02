import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Connection;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class MainDisplay extends JFrame implements ActionListener, ListSelectionListener, ItemListener  {
	
	Connection conn;
	
	JPanel tableField = new JPanel ();
	static JPanel main = new JPanel ();
	JTable table = new JTable();
	JLabel dateLabel = new JLabel("Select date:");
	JButton enterBtn = new JButton("Add");
	JLabel userLabel = new JLabel("Employee Id:");
	JTextField inputUser = new JTextField ();
	JLabel timeFrom = new JLabel("From:");
	JTextField inputFrom = new JTextField ("00:00");
	JLabel timeTill = new JLabel("Till:");
	JTextField inputTill = new JTextField ("00:00");
	JButton deleteBtn = new JButton("Cancel reservation");
	
	Object [] columnNames = {"Id", "Name", "Last Name", "From", "Till", "Bike Id"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	
	String[] dates = CommunicateDb.updateDate();
	JComboBox<String> dateList = new JComboBox<>(dates);
	
	public MainDisplay(Connection conn) {
		
		this.conn = conn;
		
		model.addRow(columnNames);
		CommunicateDb.deleteYesterdayReservations(conn, dates[2]);
		CommunicateDb.displayReservations(conn, dates[0], model);
		table.setModel(model);
		table.setRowHeight(table.getRowHeight()+5);
		table.getSelectionModel().addListSelectionListener(this);
		tableField.add(table);
		add(tableField, BorderLayout.CENTER);
	
		main.add(dateLabel);
		dateList.removeItemAt(2);
		dateList.addItemListener(this);
		main.add(dateList);
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
		
		Boolean isValidInput1  = CommunicateDb.isValidInput(timeFrom);
		Boolean isValidInput2  = CommunicateDb.isValidInput(timeTill);
		Boolean isValidUser = false;
		String bikeId = "";
		
		if (input.length() != 0) {
		if (isValidInput1 && isValidInput2) { 
			isValidUser = CommunicateDb.validateUser(conn, input);
			if (isValidUser) {
				bikeId = CommunicateDb.checkReservations(conn, timeFrom, timeTill, dates[dateList.getSelectedIndex()]);
				if (bikeId != null) {
					CommunicateDb.setReservations(conn, timeFrom, timeTill, input, bikeId, dates[dateList.getSelectedIndex()]);
					model.setRowCount(1);
					CommunicateDb.displayReservations(conn, dates[dateList.getSelectedIndex()], model);
				} else JOptionPane.showMessageDialog(main, "Reservation is not possible! No available bikes at chosen time.", "Message", JOptionPane.ERROR_MESSAGE);
			} else JOptionPane.showMessageDialog(main, "Invalid id!", "Message", JOptionPane.ERROR_MESSAGE);
		}
		} else JOptionPane.showMessageDialog(main, "You forgot employees id", "Message", JOptionPane.QUESTION_MESSAGE);
		}
		
		if (e.getSource() == deleteBtn) {
			int selectedRow = table.getSelectedRow();
			int id = Integer.parseUnsignedInt((String) model.getValueAt(selectedRow, 0));
			CommunicateDb.deleteReservations(conn, id, dates[dateList.getSelectedIndex()]);
			model.setRowCount(1);
			CommunicateDb.displayReservations(conn, dates[dateList.getSelectedIndex()], model);
			deleteBtn.setEnabled(false);
		}
	}
	
	public void itemStateChanged(ItemEvent ie) {
		if (ie.getStateChange() == ItemEvent.SELECTED) {
		int selectedDate = dateList.getSelectedIndex();
		model.setRowCount(1);
		CommunicateDb.displayReservations(conn, dates[selectedDate], model);
		}
    }

	public void valueChanged(ListSelectionEvent arg0) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow > 0) {
			if (model.getValueAt(selectedRow, 4) != null && model.getValueAt(selectedRow, 3) != null) {
				deleteBtn.setEnabled(true); 
			} else deleteBtn.setEnabled(false); 
		} else deleteBtn.setEnabled(false); 
	}
	
	public static void showMsg (String str) {
		JOptionPane.showMessageDialog(main, str, "Message", JOptionPane.ERROR_MESSAGE);
	}
}

