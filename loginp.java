package main;

import java.awt.*;
import javax.swing.*;

public class loginp extends JFrame {
	private static final long serialVersionUID = 1L;
	private CardLayout cardLayout;
    private JPanel mainPanel;
    

    public loginp() {
        setTitle("Alpha Login");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        getContentPane().add(mainPanel);

        Mainpanel loginPanel = new Mainpanel(this);
        Registration registrationPanel = new Registration(this);

        mainPanel.add(loginPanel, "login");
        mainPanel.add(registrationPanel, "register");

        cardLayout.show(mainPanel, "login");
    }

    public void showRegistration() {
        cardLayout.show(mainPanel, "register");
    }

    public void showLogin() {
        cardLayout.show(mainPanel, "login");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            loginp frame = new loginp();
            frame.setVisible(true);
        });
    }
}
