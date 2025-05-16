package main;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class Registration extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private JTextField nameField, usernameField;
	private JPasswordField passwordField, confirmPasswordField;
	private JPanel regisForm;
	private JLabel fullNameLabel, ageLabel, categoryLabel;
	private JButton signUpButton;
	private JSpinner ageSpinner;
	private JComboBox<String> categoryBox;
	private loginp parentFrame;

	public Registration(loginp parent) {
		this.parentFrame = parent;
		setBackground(new Color(0x1D, 0x29, 0x81));
		setPreferredSize(new Dimension(1200, 800));
		setLayout(null);
		
		JLabel titleLabel = new JLabel("Create Your Account");
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("Tahoma", Font.BOLD, 36));
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBounds(387, 90, 414, 64);
		add(titleLabel);
		
		regisForm = new JPanel();
		regisForm.setBounds(143, 178, 900, 453);
		regisForm.setBackground(Color.WHITE);
		regisForm.setLayout(null);
		add(regisForm);
		
		// Full Name
		fullNameLabel = new JLabel("Full Name");
		fullNameLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
		fullNameLabel.setBounds(45, 43, 98, 24);
		regisForm.add(fullNameLabel);
		nameField = new JTextField();
		nameField.setFont(new Font("Arial Unicode MS", Font.PLAIN, 11));
		nameField.setBounds(45, 67, 293, 38);
		nameField.setColumns(10);
		((AbstractDocument) nameField.getDocument()).setDocumentFilter(new DocumentFilter() {
			public void insertString(FilterBypass fb, int offset, String string, javax.swing.text.AttributeSet attr) throws BadLocationException {
				if (string.matches("[a-zA-Z ]+")) {
					super.insertString(fb, offset, string, attr);
				}
			}
			public void replace(FilterBypass fb, int offset, int length, String text, javax.swing.text.AttributeSet attrs) throws BadLocationException {
				if (text.matches("[a-zA-Z ]+")) {
					super.replace(fb, offset, length, text, attrs);
				}
			}
		});
		regisForm.add(nameField);
		
		// Username
		JLabel usernameLabel = new JLabel("Username");
		usernameLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
		usernameLabel.setBounds(451, 43, 98, 24);
		regisForm.add(usernameLabel);
		usernameField = new JTextField();
		usernameField.setFont(new Font("Arial Unicode MS", Font.PLAIN, 11));
		usernameField.setBounds(451, 67, 293, 38);
		usernameField.setColumns(10);
		regisForm.add(usernameField);
		
		// Age
		ageLabel = new JLabel("Age");
		ageLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
		ageLabel.setBounds(45, 126, 98, 24);
		regisForm.add(ageLabel);
		ageSpinner = new JSpinner();
		ageSpinner.setFont(new Font("Arial Unicode MS", Font.PLAIN, 17));
		ageSpinner.setBounds(45, 151, 66, 38);		
		regisForm.add(ageSpinner);
		
		// Category
		categoryLabel = new JLabel("Category");
		categoryLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
		categoryLabel.setBounds(146, 126, 98, 24);
		regisForm.add(categoryLabel);
		categoryBox = new JComboBox<>(new String[] {"Student", "Employee", "Alumni", "Outsider"});
		categoryBox.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
		categoryBox.setBounds(146, 151, 192, 38);		
		regisForm.add(categoryBox);
		
		// Password
		JLabel passwordLabel = new JLabel("Password");
		passwordLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
		passwordLabel.setBounds(451, 126, 98, 24);
		regisForm.add(passwordLabel);
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Arial Unicode MS", Font.PLAIN, 11));
		passwordField.setBounds(451, 151, 293, 38);
		regisForm.add(passwordField);
		
		// Confirm Password
		JLabel confirmPasswordLabel = new JLabel("Confirm Password");
		confirmPasswordLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
		confirmPasswordLabel.setBounds(451, 209, 154, 24);
		regisForm.add(confirmPasswordLabel);
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setFont(new Font("Arial Unicode MS", Font.PLAIN, 11));
		confirmPasswordField.setBounds(451, 234, 293, 38);
		regisForm.add(confirmPasswordField);
		
		// Sign Up Button
		signUpButton = new JButton("Sign up");
		signUpButton.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
		signUpButton.setBounds(45, 234, 199, 38);
		regisForm.add(signUpButton);
		
		// Sign Up Action
		signUpButton.addActionListener((ActionEvent e) -> {
			String name = nameField.getText().trim();
			String username = usernameField.getText().trim();
			String password = new String(passwordField.getPassword()).trim();
			String confirm = new String(confirmPasswordField.getPassword()).trim();
			int age = (Integer) ageSpinner.getValue();
			String category = (String) categoryBox.getSelectedItem();

			// Validation
			if (name.isEmpty() || username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
				JOptionPane.showMessageDialog(this, "Please fill in all the fields.", "Missing Information", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (age <= 0) {
				JOptionPane.showMessageDialog(this, "Please enter a valid age.", "Invalid Age", JOptionPane.WARNING_MESSAGE);
				return;
			}
			if (!password.equals(confirm)) {
				JOptionPane.showMessageDialog(this, "Passwords do not match.", "Password Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!UserDatabase.register(username, password, name, age, category)) {
				JOptionPane.showMessageDialog(this, "Username already exists.", "Registration Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			JOptionPane.showMessageDialog(this, "Account created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
			parentFrame.showLogin();
		});
	}
}
