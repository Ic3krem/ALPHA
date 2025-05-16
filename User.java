package main;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.*;

public class User extends JFrame {
	private CardLayout contentLayout;
	private JPanel contentPanel;
    private static final long serialVersionUID = 1L;
    private JPanel userPanel, menu, panel;
    private JLabel status, statusLabel, homeIcon;
    private JButton checkIn, checkOut, bodyStats, attendanceTracker, workoutTracker, logOut;
    
    private UserAttendance useratt;
    private int currentRow = -1;
    private String userId;
    private String username;
    private String password;
    private String fullName;
    private int age;
    private String category;
    
    public User(String userId, String username, String password, String fullName, int age, String category) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
        this.category = category;
        setTitle("User Panel");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        getContentPane().setLayout(new BorderLayout());
        setDoubleBuffered(true);

        userPanel = new JPanel();
        userPanel.setBackground(Color.BLACK);
        userPanel.setLayout(new BorderLayout());

        menu = new JPanel();
        menu.setBackground(new Color(0x1D, 0x29, 0x81));
        menu.setPreferredSize(new Dimension(400, 800));
        menu.setLayout(null);

        panel = new JPanel();
        panel.setBounds(113, 142, 174, 69);
        panel.setLayout(new BorderLayout());
        menu.add(panel);

        JLabel timeLabel = new JLabel();
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        timeLabel.setFont(new Font("Arial Unicode MS", Font.BOLD, 32));
        timeLabel.setForeground(new Color(0x1D, 0x29, 0x81));
        timeLabel.setBackground(new Color(240, 233, 0));
        timeLabel.setOpaque(true);
        panel.add(timeLabel, BorderLayout.CENTER);

        Timer timer = new Timer(1000, e -> {
            String time = new SimpleDateFormat("HH:mm").format(new Date());
            timeLabel.setText(time);
        });
        timer.start();
     

        status = new JLabel("Status");
        status.setHorizontalAlignment(SwingConstants.CENTER);
        status.setForeground(new Color(210, 210, 210));
        status.setFont(new Font("Arial Unicode MS", Font.BOLD, 16));
        status.setBounds(174, 262, 51, 26);
        
        checkIn = new JButton("Check in");
        checkIn.setBackground(new Color(128, 255, 128));
        checkIn.setBounds(83, 378, 107, 47);
        checkIn.addActionListener(e -> {
            if (currentRow == -1) {
                String logId = String.format("%03d", useratt.getTableModel().getRowCount() + 1);
                String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
                String checkInTime = new SimpleDateFormat("HH:mm:ss").format(new Date());

                useratt.getTableModel().addRow(new Object[]{logId, date, checkInTime, ""});
                currentRow = useratt.getTableModel().getRowCount() - 1;
                statusLabel.setText("Checked In");
            } else {
                JOptionPane.showMessageDialog(this, "You already checked in today!", "Notice", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        checkOut = new JButton("Check out");
        checkOut.setBackground(new Color(255, 128, 128));
        checkOut.setBounds(210, 378, 107, 47);
        checkOut.addActionListener(e -> {
            if (currentRow != -1) {
                String checkOutTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
                useratt.getTableModel().setValueAt(checkOutTime, currentRow, 3);
                statusLabel.setText("Checked Out");
                currentRow = -1;
            } else {
                JOptionPane.showMessageDialog(this, "You need to check in first!", "Warning", JOptionPane.WARNING_MESSAGE);
            }
        });

        bodyStats = new JButton("Check Body Stats");
        bodyStats.setBounds(83, 445, 234, 47);
        bodyStats.addActionListener(e -> contentLayout.show(contentPanel, "bodyStats"));

        attendanceTracker = new JButton("Attendance Tracker");
        attendanceTracker.setBounds(83, 510, 234, 47);
        attendanceTracker.addActionListener(e -> contentLayout.show(contentPanel, "useratt"));

        workoutTracker = new JButton("Workout Tracker");
        workoutTracker.setBounds(83, 569, 234, 47);        
        workoutTracker.addActionListener(e -> contentLayout.show(contentPanel, "workout"));

        logOut = new JButton("Log out");
        logOut.setBounds(144, 642, 112, 40);  
        logOut.addActionListener(e -> {
            dispose(); 
            SwingUtilities.invokeLater(() -> {
                new loginp().setVisible(true); 
            });
        });

        statusLabel = new JLabel("Inactive");
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        statusLabel.setForeground(Color.WHITE);
        statusLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
        statusLabel.setBounds(83, 291, 234, 32);

        homeIcon = new JLabel("New label");
        homeIcon.setBounds(185, 98, 30, 30);
        homeIcon.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        homeIcon.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                contentLayout.show(contentPanel, "home");
            }
        });
        

        try {
            BufferedImage originalImage = ImageIO.read(User.class.getResource("/main/Images/home.png"));
            BufferedImage invertedImage = new BufferedImage(
                originalImage.getWidth(),
                originalImage.getHeight(),
                BufferedImage.TYPE_INT_ARGB
            );

            for (int y = 0; y < originalImage.getHeight(); y++) {
                for (int x = 0; x < originalImage.getWidth(); x++) {
                    int rgba = originalImage.getRGB(x, y);
                    Color col = new Color(rgba, true);
                    Color invertedCol = new Color(
                        255 - col.getRed(),
                        255 - col.getGreen(),
                        255 - col.getBlue(),
                        col.getAlpha()
                    );
                    invertedImage.setRGB(x, y, invertedCol.getRGB());
                }
            }

            Image scaledImage = invertedImage.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            homeIcon.setIcon(new ImageIcon(scaledImage));

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        JLabel UMakLogo = new JLabel("");
        ImageIcon originalIcon = new ImageIcon(Mainpanel.class.getResource("/main/Images/UMak Logo Outline - White R.png"));
        Image scaledImage = originalIcon.getImage().getScaledInstance(70, 70, Image.SCALE_SMOOTH);
        UMakLogo.setIcon(new ImageIcon(scaledImage));
        UMakLogo.setBounds(20, 20, 70, 70);
        menu.add(UMakLogo);
        
        menu.add(homeIcon);
        menu.add(statusLabel);
        menu.add(checkIn);
        menu.add(status);
        menu.add(checkOut);
        menu.add(bodyStats);
        menu.add(attendanceTracker);
        menu.add(workoutTracker);
        menu.add(logOut);
        
        getContentPane().add(userPanel, BorderLayout.CENTER);
        userPanel.add(menu, BorderLayout.WEST);
        
        contentLayout = new CardLayout();
        contentPanel = new JPanel(contentLayout);
        contentPanel.setPreferredSize(new Dimension(800, 800));

        
        Home homePanel = new Home(contentLayout, contentPanel);
        UserBodystats bodystats = new UserBodystats(username);
        useratt = new UserAttendance();
        userWorkout workout = new userWorkout();

        contentPanel.add(homePanel, "home");
        contentPanel.add(bodystats, "bodyStats");
        contentPanel.add(useratt, "useratt");
        contentPanel.add(workout, "workout");

        userPanel.add(contentPanel, BorderLayout.EAST);

        // Set default panel
        contentLayout.show(contentPanel, "home");
                  
             
    }

	private void setDoubleBuffered(boolean b) {

		
	}
    
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}