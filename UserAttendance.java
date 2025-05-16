package main;

import java.awt.Color;
import java.awt.Font;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class UserAttendance extends JPanel {

	 private static final long serialVersionUID = 1L;
	    private JLabel Title, date;
	    public JTable attendance;
	    private DefaultTableModel tableModel;
	
	
	public UserAttendance() {
		setSize(800, 800);
        setLayout(null);
        setBackground(Color.WHITE);
        
        Title = new JLabel("Attendance Tracker");
        Title.setForeground(new Color(0, 64, 128));
        Title.setFont(new Font("Arial Unicode MS", Font.BOLD, 40));
        Title.setHorizontalAlignment(SwingConstants.CENTER);
        Title.setBounds(200, 50, 392, 64);

        
        
        attendance = new JTable();
        String[] columnNames = {"Log ID", "Date", "Check-in Time", "Check-out Time"};
        tableModel = new DefaultTableModel(columnNames, 0);

   
        tableModel.addRow(new Object[]{"001", "2025-05-10", "08:30 AM", "05:15 PM"});
        tableModel.addRow(new Object[]{"002", "2025-05-11", "08:35 AM", "05:20 PM"});
        tableModel.addRow(new Object[]{"003", "2025-05-12", "08:40 AM", "05:10 PM"});
        tableModel.addRow(new Object[]{"004", "2025-05-13", "08:25 AM", "05:05 PM"});
        tableModel.addRow(new Object[]{"005", "2025-05-14", "08:45 AM", "05:30 PM"});

        attendance = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(attendance);
        scrollPane.setBounds(66, 204, 660, 461);
       ;
        
        date = new JLabel("New label");
        date.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
        date.setHorizontalAlignment(SwingConstants.CENTER);
        date.setBounds(255, 698, 281, 36);
        

        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        date.setText("Today's Date: " + currentDate.format(formatter));;
        
        add(date);
        add(scrollPane);
        add(Title);
        
	}
	public DefaultTableModel getTableModel() {
    	return tableModel;
    }

}
