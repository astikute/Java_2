package gui;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.Controller;

public class MainDisplay extends JFrame implements 
ActionListener, ListSelectionListener, ItemListener {
	
	//All components 
	Toolkit toolkit = Toolkit.getDefaultToolkit();
	Dimension dimension = toolkit.getScreenSize();
	JFrame frame = new JFrame ("Electric Bicycle Reservation Plan");
	JPanel tableField = new JPanel ();
	JPanel main = new JPanel ();
	JTable table = new JTable();
	JScrollPane scrollPane = new JScrollPane(table);
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
	
	Controller controller = new Controller();
	
	String[] dates = controller.updateDate();
	JComboBox<String> dateList = new JComboBox<>(dates);
		
	public MainDisplay() throws SQLException {

		
        ChangeDateEvent today = new ChangeDateEvent(dates[0]);
        ChangeDateEvent tomorrow = new ChangeDateEvent(dates[1]);
        controller.createTables(tomorrow);
        controller.createTables(today);
        
        //Creates a table for reservation plan view
        setUpView();
        table.setModel(model);
		table.setRowHeight(table.getRowHeight()+5);
		table.getSelectionModel().addListSelectionListener(this);
		tableField.add(scrollPane);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setPreferredSize(new Dimension (dimension.width - 100, dimension.height - 200));
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		for (int i = 0; i < 6; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
	
		//Creates main function bar
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
			int selectedDate = dateList.getSelectedIndex();
			ReservationEvent event1 = new ReservationEvent(input, timeFrom, timeTill, dates[selectedDate]);
			try {
				controller.makeReservation(event1);
				setUpView();
			} catch (SQLException e1) {}
		}
		
		// Performes "delete reservation" when "Cancel reservation" is clicked
		if (e.getSource() == deleteBtn) {
			int selectedRow = table.getSelectedRow();
			String id = (String) model.getValueAt(selectedRow, 0);
			int selectedDate = dateList.getSelectedIndex();
			DeleteEvent event2 = new DeleteEvent (id, dates[selectedDate]);
			try {
				controller.deleteReservation(event2);
				setUpView();
				deleteBtn.setEnabled(false);
			} catch (SQLException e1) {}
		}
	}
	
	//Updates table when reservation date is changed in ComboBox
	public void itemStateChanged(ItemEvent ie) {
		if (ie.getStateChange() == ItemEvent.SELECTED) {
			int selectedDate = dateList.getSelectedIndex();
			ChangeDateEvent event = new ChangeDateEvent(dates[selectedDate]);
			try {
				controller.changeView(event);
				setUpView();
			} catch (SQLException e) {}	
	     }
    }

	//Enables "Cancel reservation" button when table entry with existing reservation is selected 
	public void valueChanged(ListSelectionEvent arg0) {
		int selectedRow = table.getSelectedRow();
		if (selectedRow > -1) {
			if (model.getValueAt(selectedRow, 4) != null && model.getValueAt(selectedRow, 3) != null) {
				deleteBtn.setEnabled(true); 
			} else deleteBtn.setEnabled(false); 
		} else deleteBtn.setEnabled(false); 
	}
	
	//Refresh table data
	public void setUpView () throws SQLException {
		model.setRowCount(0);
        ArrayList<Object[]> list = controller.getReservations();
        for (Object[] object:list) {
        	model.addRow(object);
        }
	}
}

