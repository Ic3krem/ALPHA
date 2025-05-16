package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AdminStats extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable statsTable;
    private DefaultTableModel statsTableModel;
    private JTextField searchField;
    private TableRowSorter<DefaultTableModel> sorter;

    public AdminStats() {
        setSize(800, 800);
        setLayout(null);
        setBackground(new Color(0x1D, 0x29, 0x81));

        // Title
        JLabel title = new JLabel("BODY STATS RECORD");
        title.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(200, 50, 400, 50);
        add(title);

        // Search section
        JLabel searchLabel = new JLabel("Search User");
        searchLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
        searchLabel.setForeground(Color.WHITE);
        searchLabel.setBounds(60, 120, 200, 30);
        add(searchLabel);

        searchField = new JTextField();
        searchField.setBounds(60, 150, 220, 30);
        add(searchField);

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
        searchButton.setBackground(new Color(0x4CAF50));
        searchButton.setForeground(Color.BLACK);
        searchButton.setBounds(60, 190, 100, 30);
        add(searchButton);

        // Table columns
        String[] columns = {"Username", "Record ID", "Date", "BMI", "Classification"};
        statsTableModel = new DefaultTableModel(columns, 0) {
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        statsTable = new JTable(statsTableModel);
        statsTable.setRowHeight(60);
        statsTable.getTableHeader().setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
        statsTable.getTableHeader().setForeground(new Color(0x1D, 0x29, 0x81));
        statsTable.getTableHeader().setBackground(Color.WHITE);
        statsTable.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
        statsTable.setGridColor(new Color(0xB0, 0xB0, 0xB0));
        statsTable.setShowGrid(true);

        JScrollPane scrollPane = new JScrollPane(statsTable);
        scrollPane.setBounds(40, 260, 720, 320);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0x33, 0xB5, 0xFF), 3));
        add(scrollPane);

        // Populate table from BodyStatsDatabase
        refreshTable();
        sorter = new TableRowSorter<>(statsTableModel);
        statsTable.setRowSorter(sorter);

        // Search functionality
        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().trim().toLowerCase();
            if (searchText.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText, 0));
            }
        });

        // Date at the bottom center
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
        statsTableModel.setRowCount(0);
        for (BodyStatsRecord record : BodyStatsDatabase.getAllRecords()) {
            statsTableModel.addRow(new Object[]{
                record.getUsername(),
                record.getRecordId(),
                record.getDate(),
                record.getBmi(),
                record.getClassification()
            });
        }
    }

    public DefaultTableModel getTableModel() {
        return statsTableModel;
    }
} 