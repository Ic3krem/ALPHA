package main;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminAttendance extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable attendanceTable;
    private DefaultTableModel attendanceTableModel;

    public AdminAttendance() {
        setSize(800, 800);
        setLayout(null);
        setBackground(new Color(0x1D, 0x29, 0x81));

        // Title
        JLabel title = new JLabel("ATTENDANCE RECORD");
        title.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(200, 50, 400, 50);
        add(title);

        // Table columns
        String[] columns = {"User ID", "LOG ID", "Date", "Check IN", "Check OUT"};
        attendanceTableModel = new DefaultTableModel(columns, 0) {
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false; // read-only
            }
        };
        attendanceTable = new JTable(attendanceTableModel);
        attendanceTable.setRowHeight(60);
        attendanceTable.getTableHeader().setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
        attendanceTable.getTableHeader().setForeground(new Color(0x1D, 0x29, 0x81));
        attendanceTable.getTableHeader().setBackground(Color.WHITE);
        attendanceTable.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
        attendanceTable.setGridColor(new Color(0xB0, 0xB0, 0xB0));
        attendanceTable.setShowGrid(true);

        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setBounds(40, 120, 720, 400);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0x33, 0xB5, 0xFF), 3));
        add(scrollPane);
        refreshTable();

        JLabel dateLabel = new JLabel();
        dateLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
        dateLabel.setForeground(Color.WHITE);
        dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
        dateLabel.setBounds(0, 650, 800, 30);
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd - MM - yyyy");
        dateLabel.setText(today.format(formatter));
        add(dateLabel);
    }

    private void refreshTable() {
        attendanceTableModel.setRowCount(0);
        for (AttendanceRecord record : AttendanceDatabase.getAllRecords()) {
            attendanceTableModel.addRow(new Object[]{
                record.getUserId(),
                record.getLogId(),
                record.getDate(),
                record.getCheckIn(),
                record.getCheckOut()
            });
        }
    }

    public DefaultTableModel getTableModel() {
        return attendanceTableModel;
    }
} 