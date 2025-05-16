package main;

import java.awt.*;
import javax.swing.*;

public class Home extends JPanel {
 static final long serialVersionUID = 1L;
	private CardLayout contentLayout;
	private JPanel contentPanel;
	
    public Home(CardLayout layout, JPanel panel) {
        setSize(800, 800);
        setLayout(null);
        setBackground(Color.WHITE);

        JLabel titleLabel = new JLabel("Welcome to ALPHA");
        titleLabel.setForeground(new Color(0, 64, 128));
        titleLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 40));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(150, 100, 500, 60);
        add(titleLabel);

        JLabel descLabel = new JLabel(
            "<html><div style='text-align: center;'>"
            + "A smart, user-friendly solution built for our university community.<br><br>"
            + "Designed to assist you with Fitness Logging and Tracking Management.<br>"
            + "We aim to enhance your gym experience and help you stay consistent and accountable.<br><br><br><br>"
            + "With ALPHA, we help you get ready, Go Healthy!"
            + "</div></html>"
        );
        descLabel.setFont(new Font("Arial Unicode MS", Font.PLAIN, 16));
        descLabel.setForeground(Color.DARK_GRAY);
        descLabel.setBounds(102, 171, 600, 256);
        descLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(descLabel);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(Home.class.getResource("/main/Images/Workoutpic.png")));
        lblNewLabel.setBounds(373, 438, 70, 70);
        add(lblNewLabel);
        
        JButton CheckIN = new JButton("Check in");
        CheckIN.setFont(new Font("Arial Unicode MS", Font.BOLD, 17));
        CheckIN.setBackground(new Color(0xF0, 0xE9, 0x00));
        CheckIN.setBounds(310, 600, 179, 48);
        CheckIN.addActionListener(e -> contentLayout.show(contentPanel, "useratt"));
        add(CheckIN);
                   
        
        this.contentLayout = layout;
        this.contentPanel = panel;
        
    }
    
}