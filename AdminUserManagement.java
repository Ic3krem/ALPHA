package main;

import java.awt.*;
import java.util.Map;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AdminUserManagement extends JPanel {
    private static final long serialVersionUID = 1L;
    private JTable userTable;
    private DefaultTableModel userTableModel;
    private JTextField searchField;

    public AdminUserManagement() {
        setSize(800, 800);
        setLayout(null);
        setBackground(new Color(0x1D, 0x29, 0x81));

        JLabel title = new JLabel("USER MANAGEMENT");
        title.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(200, 50, 400, 50);
        add(title);

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

        JButton addButton = new JButton("Add User");
        addButton.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
        addButton.setBackground(new Color(0xCCCCCC));
        addButton.setForeground(Color.BLACK);
        addButton.setBounds(600, 140, 120, 30);
        add(addButton);

        JButton deleteButton = new JButton("Delete User");
        deleteButton.setFont(new Font("Arial Unicode MS", Font.BOLD, 14));
        deleteButton.setBackground(new Color(0xCCCCCC));
        deleteButton.setForeground(Color.BLACK);
        deleteButton.setBounds(600, 180, 120, 30);
        add(deleteButton);

        String[] columns = {"User ID", "Full Name", "Age", "Category"};
        userTableModel = new DefaultTableModel(columns, 0);
        userTable = new JTable(userTableModel);
        userTable.setRowHeight(60);
        userTable.getTableHeader().setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
        userTable.getTableHeader().setForeground(new Color(0x1D, 0x29, 0x81));
        userTable.getTableHeader().setBackground(Color.WHITE);
        userTable.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
        userTable.setGridColor(new Color(0xB0, 0xB0, 0xB0));
        userTable.setShowGrid(true);

        JScrollPane scrollPane = new JScrollPane(userTable);
        scrollPane.setBounds(60, 260, 660, 400);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(0x33, 0xB5, 0xFF), 3));
        add(scrollPane);

        refreshTable();

        addButton.addActionListener(e -> {
            AddUserDialog dialog = new AddUserDialog();
            dialog.setLocationRelativeTo(this);
            dialog.setVisible(true);
            if (dialog.isSucceeded()) {
                dialog.getUserRow();
                UserDatabase.register(dialog.getUsername(), dialog.getPassword(), dialog.getFullName(), dialog.getAge(), dialog.getCategory());
                refreshTable();
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = userTable.getSelectedRow();
            if (selectedRow != -1) {
                String fullName = (String) userTableModel.getValueAt(selectedRow, 1);
                String userId = (String) userTableModel.getValueAt(selectedRow, 0);
                String usernameToDelete = null;
                for (Map.Entry<String, User> entry : UserDatabase.getAllUsers().entrySet()) {
                    if (entry.getValue().getUserId().equals(userId)) {
                        usernameToDelete = entry.getKey();
                        break;
                    }
                }
                if (usernameToDelete != null) {
                    int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete user '" + fullName + "'?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        UserDatabase.removeUser(usernameToDelete);
                        refreshTable();
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a user to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
            }
        });

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText().trim().toLowerCase();
            TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(userTableModel);
            userTable.setRowSorter(sorter);
            if (searchText.length() == 0) {
                sorter.setRowFilter(null);
            } else {
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
            }
        });
    }

    private void refreshTable() {
        userTableModel.setRowCount(0);
        for (User user : UserDatabase.getAllUsers().values()) {
            if (!user.getUsername().equals("admin")) {
                userTableModel.addRow(new Object[]{user.getUserId(), user.getFullName(), user.getAge(), user.getCategory()});
            }
        }
    }

    public DefaultTableModel getTableModel() {
        return userTableModel;
    }

    private class AddUserDialog extends JDialog {

		private static final long serialVersionUID = 1L;
		private JTextField fullNameField, usernameField;
        private JPasswordField passwordField, confirmPasswordField;
        private JComboBox<String> categoryBox;
        private JSpinner ageSpinner;
        private boolean succeeded = false;
        private Object[] userRow;

        public AddUserDialog() {
            setTitle("Add User");
            setModal(true);
            setSize(500, 400);
            setLayout(null);
            getContentPane().setBackground(new Color(0x1D, 0x29, 0x81));

            JLabel title = new JLabel("ADD USER");
            title.setFont(new Font("Arial Unicode MS", Font.BOLD, 28));
            title.setForeground(Color.WHITE);
            title.setHorizontalAlignment(SwingConstants.CENTER);
            title.setBounds(0, 20, 500, 40);
            add(title);

            JLabel fullNameLabel = new JLabel("Full Name");
            fullNameLabel.setForeground(Color.WHITE);
            fullNameLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
            fullNameLabel.setBounds(40, 80, 120, 25);
            add(fullNameLabel);
            fullNameField = new JTextField();
            fullNameField.setBounds(40, 110, 180, 28);
            add(fullNameField);

            JLabel categoryLabel = new JLabel("Category");
            categoryLabel.setForeground(Color.WHITE);
            categoryLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
            categoryLabel.setBounds(270, 80, 120, 25);
            add(categoryLabel);
            categoryBox = new JComboBox<>(new String[]{"Student", "Employee", "Alumni", "Outsider"});
            categoryBox.setBounds(270, 110, 180, 28);
            add(categoryBox);

            JLabel usernameLabel = new JLabel("Username");
            usernameLabel.setForeground(Color.WHITE);
            usernameLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
            usernameLabel.setBounds(40, 150, 120, 25);
            add(usernameLabel);
            usernameField = new JTextField();
            usernameField.setBounds(40, 180, 180, 28);
            add(usernameField);

            JLabel ageLabel = new JLabel("Age");
            ageLabel.setForeground(Color.WHITE);
            ageLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
            ageLabel.setBounds(270, 150, 120, 25);
            add(ageLabel);
            ageSpinner = new JSpinner(new SpinnerNumberModel(18, 1, 120, 1));
            ageSpinner.setBounds(270, 180, 60, 28);
            add(ageSpinner);

            JLabel passwordLabel = new JLabel("Password");
            passwordLabel.setForeground(Color.WHITE);
            passwordLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
            passwordLabel.setBounds(40, 220, 120, 25);
            add(passwordLabel);
            passwordField = new JPasswordField();
            passwordField.setBounds(40, 250, 180, 28);
            add(passwordField);

            JLabel confirmPasswordLabel = new JLabel("Confirm Password");
            confirmPasswordLabel.setForeground(Color.WHITE);
            confirmPasswordLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
            confirmPasswordLabel.setBounds(40, 290, 180, 25);
            add(confirmPasswordLabel);
            confirmPasswordField = new JPasswordField();
            confirmPasswordField.setBounds(40, 320, 180, 28);
            add(confirmPasswordField);

            JButton addUserButton = new JButton("ADD USER");
            addUserButton.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
            addUserButton.setBackground(new Color(0x4CAF50));
            addUserButton.setForeground(Color.BLACK);
            addUserButton.setBounds(270, 320, 180, 28);
            add(addUserButton);

            addUserButton.addActionListener(e -> {
                String fullName = fullNameField.getText().trim();
                String username = usernameField.getText().trim();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String category = (String) categoryBox.getSelectedItem();
                int age = (Integer) ageSpinner.getValue();

                if (fullName.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "All fields are required.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(this, "Passwords do not match.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if (UserDatabase.getUser(username) != null) {
                    JOptionPane.showMessageDialog(this, "Username already exists.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                // Generate User ID 
                String userId = String.format("%03d", UserDatabase.getAllUsers().size());
                userRow = new Object[]{userId, fullName, age, category};
                succeeded = true;
                dispose();
            });
        }

        public boolean isSucceeded() { return succeeded; }
        public Object[] getUserRow() { return userRow; }
        public String getUsername() { return usernameField.getText().trim(); }
        public String getPassword() { return new String(passwordField.getPassword()); }
        public String getFullName() { return fullNameField.getText().trim(); }
        public int getAge() { return (Integer) ageSpinner.getValue(); }
        public String getCategory() { return (String) categoryBox.getSelectedItem(); }
    }
} 