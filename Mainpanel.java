package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;

public class Mainpanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private JPanel loginPanel, imagePanel;
    private JLabel loginLabel, usernameLabel, passwordLabel, passwordForgot, signupText, signupLink, imageAlpha;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;

    public Mainpanel(loginp parentFrame) {
        setBackground(Color.BLACK);
        setPreferredSize(new Dimension(1200, 800));
        setLayout(new BorderLayout());

        loginPanel = new JPanel();
        loginPanel.setBackground(new Color(0x1D, 0x29, 0x81));
        loginPanel.setPreferredSize(new Dimension(500, 800));
        loginPanel.setLayout(null);

        imagePanel = new JPanel();
        imagePanel.setBackground(Color.WHITE);
        imagePanel.setPreferredSize(new Dimension(700, 800));
        imagePanel.setLayout(null);

        loginLabel = new JLabel("LOGIN");
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 40));
        loginLabel.setBounds(182, 113, 134, 39);

	        usernameLabel = new JLabel("Username");
	        usernameLabel.setForeground(Color.WHITE);
	        usernameLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
	        usernameLabel.setBounds(92, 205, 325, 61);
	
	        usernameField = new JTextField();
	        usernameField.setBounds(87, 249, 325, 40);
	
	        passwordLabel = new JLabel("Password");
	        passwordLabel.setForeground(Color.WHITE);
	        passwordLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
	        passwordLabel.setBounds(92, 300, 325, 61);
	
	        passwordField = new JPasswordField();
	        passwordField.setBounds(87, 344, 325, 40);
	
	        passwordForgot = new JLabel("Forgot password?");
	        passwordForgot.setForeground(new Color(0xEA, 0xE8, 0xE8));
	        passwordForgot.setFont(new Font("Arial Unicode MS", Font.BOLD, 12));
	        passwordForgot.setBounds(300, 378, 325, 61);
	        passwordForgot.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	      
	        passwordForgot.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                JOptionPane.showMessageDialog(
	                    null,
	                    "Please contact the administrator for password assistance.",
	                    "Forgot Password",
	                    JOptionPane.INFORMATION_MESSAGE
	                );
	            }
	        });
	
	        loginButton = new JButton("→") {
	            /**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				private boolean isHovered = false;
	            private Color gradientStart = new Color(0xF0, 0xE9, 0x00);
	            private Color gradientEnd = new Color(0xFF, 0xD7, 0x00);
	            private Color hoverStart = new Color(0xFF, 0xF0, 0x00);
	            private Color hoverEnd = new Color(0xFF, 0xE0, 0x00);

	            {
	                addMouseListener(new MouseAdapter() {
	                    @Override
	                    public void mouseEntered(MouseEvent e) {
	                        isHovered = true;
	                        repaint();
	                    }

	                    @Override
	                    public void mouseExited(MouseEvent e) {
	                        isHovered = false;
	                        repaint();
	                    }
	                });
	            }

	            @Override
	            protected void paintComponent(Graphics g) {
	                Graphics2D g2 = (Graphics2D) g.create();
	                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
	                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

	                // Create gradient
	                GradientPaint gradient;
	                if (isHovered) {
	                    gradient = new GradientPaint(0, 0, hoverStart, getWidth(), getHeight(), hoverEnd);
	                } else {
	                    gradient = new GradientPaint(0, 0, gradientStart, getWidth(), getHeight(), gradientEnd);
	                }
	                g2.setPaint(gradient);

	                // Draw rounded rectangle with shadow
	                int arc = 30;
	                g2.setColor(new Color(0, 0, 0, 50));
	                g2.fillRoundRect(2, 2, getWidth() - 4, getHeight() - 4, arc, arc);
	                g2.setPaint(gradient);
	                g2.fillRoundRect(0, 0, getWidth() - 2, getHeight() - 2, arc, arc);

	                // Draw border
	                g2.setColor(new Color(0, 0, 0, 30));
	                g2.setStroke(new BasicStroke(1.5f));
	                g2.drawRoundRect(0, 0, getWidth() - 2, getHeight() - 2, arc, arc);

	                // Draw text
	                g2.setColor(Color.BLACK);
	                FontMetrics fm = g2.getFontMetrics();
	                int textX = (getWidth() - fm.stringWidth(getText())) / 2;
	                int textY = ((getHeight() - fm.getHeight()) / 2) + fm.getAscent();
	                g2.drawString(getText(), textX, textY);

	                g2.dispose();
	            }

	            @Override
	            protected void paintBorder(Graphics g) {}
	        };
	        loginButton.setBounds(221, 425, 60, 60);
	        loginButton.setContentAreaFilled(false);
	        loginButton.setBorderPainted(false);
	        loginButton.setFocusPainted(false);
	        loginButton.setFont(new Font("Arial Unicode MS", Font.BOLD, 24));
	        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        
	        loginButton.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                String username = usernameField.getText().trim();
	                String password = new String(passwordField.getPassword()).trim();
	                if (UserDatabase.login(username, password)) {
	                    if (UserDatabase.isAdmin(username)) {
	                        Admin adminFrame = new Admin();
	                        adminFrame.setVisible(true);
	                        parentFrame.setVisible(false);
	                    } else {
	                        User userObj = UserDatabase.getUser(username);
	                        User userFrame = new User(userObj.getUserId(), userObj.getUsername(), userObj.getPassword(), userObj.getFullName(), userObj.getAge(), userObj.getCategory());
	                        userFrame.setVisible(true);
	                        parentFrame.setVisible(false);
	                    }
	                } else {
	                    JOptionPane.showMessageDialog(null,
	                        "Invalid username or password.",
	                        "Login Failed",
	                        JOptionPane.ERROR_MESSAGE);
	                }
	            }
	        });
	
	        signupText = new JLabel("Don't have an account?");
	        signupText.setForeground(Color.WHITE);
	        signupText.setFont(new Font("Arial Unicode MS", Font.BOLD, 13));
	        signupText.setBounds(165, 525, 200, 30);
	
	        signupLink = new JLabel("Click here to Sign-up");
	        signupLink.setForeground(new Color(0xEA, 0xE8, 0xE8));
	        signupLink.setFont(new Font("Arial Unicode MS", Font.PLAIN, 12));
	        signupLink.setBounds(183, 550, 200, 30);
	        signupLink.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
	        signupLink.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                parentFrame.showRegistration();
	            }
	        });
	        

	        imageAlpha = new JLabel();
	        imageAlpha.setBounds(0,0, 700, 800);
	        URL imageUrl = getClass().getResource("/main/Images/ALPHApic.png");
	        if (imageUrl != null) {
	            ImageIcon originalIcon = new ImageIcon(imageUrl);
	            Image scaledImage = originalIcon.getImage().getScaledInstance(
	                imageAlpha.getWidth(), imageAlpha.getHeight(), Image.SCALE_SMOOTH);
	            imageAlpha.setIcon(new ImageIcon(scaledImage));
	        } else {
	            System.err.println("Image not found: /main/Images/ALPHApic.png");
	        }

	       imagePanel.add(imageAlpha);
	        
        loginPanel.add(loginLabel);
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(passwordForgot);
        loginPanel.add(loginButton);
        loginPanel.add(signupText);
        loginPanel.add(signupLink);

        add(loginPanel, BorderLayout.WEST);
        
        JLabel UMakLogo = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(Mainpanel.class.getResource("/main/Images/UMak Logo Outline - White R.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        UMakLogo.setIcon(new ImageIcon(scaledImage));
        UMakLogo.setBounds(20, 20, 70, 70);
        loginPanel.add(UMakLogo);
        add(imagePanel, BorderLayout.CENTER);
    }
}