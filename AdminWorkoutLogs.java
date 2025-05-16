package main;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class AdminWorkoutLogs extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable workoutTable;
    private DefaultTableModel workoutTableModel;

    public AdminWorkoutLogs() {
        setSize(800, 800);
        setLayout(null);
        setBackground(new Color(0x1D, 0x29, 0x81));

        JLabel title = new JLabel("WORKOUT LOGS");
        title.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(200, 50, 400, 50);
        add(title);

        String[] columns = {"Track ID", "Username", "Date", "Equipment", "Classification"};
        DefaultTableModel defaultTableModel = new DefaultTableModel(columns, 0) {
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
		workoutTableModel = defaultTableModel;
        workoutTable = new JTable(workoutTableModel);
        workoutTable.setRowHeight(60);
        workoutTable.getTableHeader().setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
        workoutTable.getTableHeader().setForeground(new Color(0x1D, 0x29, 0x81));
        workoutTable.getTableHeader().setBackground(Color.WHITE);
        workoutTable.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
        workoutTable.setGridColor(new Color(0xB0, 0xB0, 0xB0));
        workoutTable.setShowGrid(true);

        JScrollPane scrollPane = new JScrollPane(workoutTable);
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
        workoutTableModel.setRowCount(0);
        for (WorkoutLog log : WorkoutLogDatabase.getAllLogs()) {
            workoutTableModel.addRow(new Object[]{
                log.getTrackId(),
                log.getUsername(),
                log.getDate(),
                log.getEquipment(),
                log.getClassification()
            });
        }
    }

    public DefaultTableModel getTableModel() {
        return workoutTableModel;
    }
} 