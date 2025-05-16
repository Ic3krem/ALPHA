package main;

import java.awt.*;
import javax.swing.*;

public class AdminDashboard extends JPanel {
    private static final long serialVersionUID = 1L;

    public AdminDashboard() {
        setSize(800, 800);
        setLayout(null);
        setBackground(new Color(0x1D, 0x29, 0x81));

        JLabel title = new JLabel("Welcome to ALPHA");
        title.setFont(new Font("Arial Unicode MS", Font.BOLD, 40));
        title.setForeground(Color.WHITE);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setBounds(100, 120, 600, 60);
        add(title);

        JLabel paragraph = new JLabel("<html><div style='text-align: center;'>\"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\"</div></html>");
        paragraph.setFont(new Font("Arial Unicode MS", Font.PLAIN, 18));
        paragraph.setForeground(Color.WHITE);
        paragraph.setHorizontalAlignment(SwingConstants.CENTER);
        paragraph.setBounds(100, 210, 600, 200);
        add(paragraph);
    }
} 