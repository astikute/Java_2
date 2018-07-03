import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class MainDisplay extends JFrame implements 
ActionListener, ListSelectionListener, ItemListener {
	
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension dimension = toolkit.getScreenSize();

	JFrame frame = new JFrame ("Electric Bike Reservation Plan");
	JPanel tableField = new JPanel ();
	JPanel main = new JPanel ();
    
	JTable table = new JTable();
	JLabel dateLabel = new JLabel("Select date:");
	JLabel userLabel = new JLabel("Employee Id:");
	JTextField inputUser = new JTextField ();
	JLabel timeFrom = new JLabel("From:");
	JTextField inputFrom = new JTextField ("00:00");
	JLabel timeTill = new JLabel("Till:");
	JTextField inputTill = new JTextField ("00:00");
	JButton enterBtn = new JButton("Add");
	JButton deleteBtn = new JButton("Cancel reservation");
	
	Object [] columnNames = {"Id", "Name", "Last Name", "From", "Till", "Bike Id"};
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);
	
	String[] dates = Operations.updateDate();
	JComboBox<String> dateList = new JComboBox<>(dates);
	
	public void setUpView() {

		//Create a table with list of reservations
		model.addRow(columnNames);
		table.setModel(model);
		table.setRowHeight(table.getRowHeight()+5);
		table.getSelectionModel().addListSelectionListener(this);
		tableField.add(table);
	
		//Create main function bar
		main.add(dateLabel);
		dateList.removeItemAt(2);
		dateList.addItemListener(this);
		main.add(dateList);
		main.add(userLabel);
		main.add(inputUser);
		inputUser.setColumns(3);
		main.add(timeFrom);
		main.add(inputFrom);
		inputFrom.setColumns(3);
		main.add(timeTill);
		main.add(inputTill);
		inputTill.setColumns(3);
		main.add(enterBtn);
		enterBtn.addActionListener(this);
		deleteBtn.setEnabled(false);
		deleteBtn.addActionListener(this);
		main.add(deleteBtn);
		main.setBackground(Color.LIGHT_GRAY);
		main.setPreferredSize(new Dimension (dimension.width, 40));
		
		//Add components to frame
		frame.add(main);
		frame.add(tableField);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new FlowLayout());
		frame.setSize(dimension.width, dimension.height);
	}

	public void actionPerformed(ActionEvent e) {
		
		//Performes "add new reservation" when button "Add" is clicked
		if (e.getSource() == enterBtn) {
			String input = inputUser.getText().trim();
			String timeFrom = inputFrom.getText().trim();
			String timeTill = inputTill.getText().trim();
		
			Boolean isValidInput1  = Operations.isValidInput(timeFrom);
			Boolean isValidInput2  = Operations.isValidInput(timeTill);
			Boolean isValidUser = false;
			String bikeId = "";
		
		if (input.length() != 0) {
			if (isValidInput1 && isValidInput2) { 
				isValidUser = CommunicateDb.validateUser(input);
				if (isValidUser) {
					bikeId = CommunicateDb.checkReservations(timeFrom, timeTill, dates[dateList.getSelectedIndex()]);
					if (bikeId != null) {
						CommunicateDb.setReservations(timeFrom, timeTill, input, bikeId, dates[dateList.getSelectedIndex()]);
						CommunicateDb.displayReservations(dates[dateList.getSelectedIndex()], model);
					} else showMsg ("Reservation is not possible! No available bikes at chosen time.");
				} else showMsg ("Invalid id!");
			} else showMsg("Invalid time!");
		} else JOptionPane.showMessageDialog(tableField, "You forgot employees id", "Message", JOptionPane.QUESTION_MESSAGE);
	}
		
		// Performes "delete reservation" when"Cancel reservation" is clicked
		if (e.getSource() == deleteBtn) {
			int selectedRow = table.getSelectedRow();
			int id = Integer.parseUnsignedInt((String) model.getValueAt(selectedRow, 0));
			CommunicateDb.deleteReservations(id, dates[dateList.getSelectedIndex()]);
			CommunicateDb.displayReservations(dates[dateList.getSelectedIndex()], model);
			deleteBtn.setEnabled(false);
		}
	}
	
	//Updates table when reservation date is changed in ComboBox
	public void itemStateChanged(ItemEvent ie) {
		if (ie.getStateChange() == ItemEvent.SELECTED) {
			int selectedDate = dateList.getSelectedIndex();
			CommunicateDb.displayReservations(dates[selectedDate], model);
		}
    }

	//Enables "Cancel reservation" button when table entry with existing reservation is selected 
	public void valueChanged(ListSelectionEvent arg0) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow > 0) {
			if (model.getValueAt(selectedRow, 4) != null && model.getValueAt(selectedRow, 3) != null) {
				deleteBtn.setEnabled(true); 
			} else deleteBtn.setEnabled(false); 
		} else deleteBtn.setEnabled(false); 
	}
	
	//Creates a error message when data is missing/invalid 
	public static void showMsg (String str) {
		JOptionPane.showMessageDialog(null, str, "Message", JOptionPane.ERROR_MESSAGE);
	}
}

