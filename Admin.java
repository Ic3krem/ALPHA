package main;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Admin extends JFrame {
	private static final long serialVersionUID = 1L;
	private CardLayout contentLayout;
	private JPanel contentPanel;
	private JPanel adminPanel, menu;
	private JLabel homeIcon;
	private JLabel userManagement, attendanceRecords, bodyStats, workoutLogs, logOut;

	private AdminDashboard dashboard;
	private AdminUserManagement userManagementPanel;
	private AdminAttendance attendancePanel;
	private AdminStats statsPanel;
	private AdminWorkoutLogs workoutLogsPanel;
	
	public Admin() {
		setTitle("Admin Panel");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
		
		adminPanel = new JPanel();
		adminPanel.setBackground(Color.BLACK);
		adminPanel.setLayout(new BorderLayout());
		
		menu = new JPanel();
		menu.setBackground(new Color(0xF0, 0xE9, 0x00));
		menu.setPreferredSize(new Dimension(400, 800));
		menu.setLayout(null);

		// Home icon setup
		homeIcon = new JLabel();
		homeIcon.setBounds(176, 40, 48, 48);
		try {
			BufferedImage originalImage = ImageIO.read(Admin.class.getResource("/main/Images/home.png"));
			Image scaledImage = originalImage.getScaledInstance(48, 48, Image.SCALE_SMOOTH);
			homeIcon.setIcon(new ImageIcon(scaledImage));
		} catch (IOException e) {
			e.printStackTrace();
		}
		menu.add(homeIcon);

		// Centralize menu items
		int menuStartY = 180;
		int menuSpacing = 60;
		userManagement = createMenuLabel("User Account Management", menuStartY);
		attendanceRecords = createMenuLabel("View Attendance Records", menuStartY + menuSpacing);
		bodyStats = createMenuLabel("View Body Stats Records", menuStartY + 2 * menuSpacing);
		workoutLogs = createMenuLabel("View Workout Logs", menuStartY + 3 * menuSpacing);
		logOut = createMenuLabel("Logout", menuStartY + 4 * menuSpacing);

		// Add action listeners
		homeIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		homeIcon.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentLayout.show(contentPanel, "dashboard");
			}
		});
		userManagement.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentLayout.show(contentPanel, "users");
			}
		});
		attendanceRecords.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentLayout.show(contentPanel, "attendance");
			}
		});
		bodyStats.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentLayout.show(contentPanel, "stats"); 
			}
		});
		workoutLogs.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				contentLayout.show(contentPanel, "workoutlogs");
			}
		});
		logOut.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				dispose();
				SwingUtilities.invokeLater(() -> {
					new loginp().setVisible(true);
				});
			}
		});

		menu.add(userManagement);
		menu.add(attendanceRecords);
		menu.add(bodyStats);
		menu.add(workoutLogs);
		menu.add(logOut);

		contentLayout = new CardLayout();
		contentPanel = new JPanel(contentLayout);
		contentPanel.setBackground(new Color(0x1D, 0x29, 0x81));
		contentPanel.setPreferredSize(new Dimension(800, 800));

		dashboard = new AdminDashboard();
		userManagementPanel = new AdminUserManagement();
		attendancePanel = new AdminAttendance();
		statsPanel = new AdminStats();
		workoutLogsPanel = new AdminWorkoutLogs();

		contentPanel.add(dashboard, "dashboard");
		contentPanel.add(userManagementPanel, "users");
		contentPanel.add(attendancePanel, "attendance");
		contentPanel.add(statsPanel, "stats");
		contentPanel.add(workoutLogsPanel, "workoutlogs");

		getContentPane().add(adminPanel, BorderLayout.CENTER);
		adminPanel.add(menu, BorderLayout.WEST);
		adminPanel.add(contentPanel, BorderLayout.EAST);

		// Set default view
		contentLayout.show(contentPanel, "dashboard");
	}

	private JLabel createMenuLabel(String text, int y) {
		JLabel label = new JLabel(text);
		label.setFont(new Font("Arial Unicode MS", Font.BOLD, 18));
		label.setForeground(new Color(0x1D, 0x29, 0x81));
		label.setBounds(50, y, 300, 40);
		label.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				label.setForeground(Color.BLACK);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				label.setForeground(new Color(0x1D, 0x29, 0x81));
			}
		});
		return label;
	}
}
