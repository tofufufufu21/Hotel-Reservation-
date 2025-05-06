package admin;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.JOptionPane;
import main.AuroraCoveLandingPage;
import main.TransacHistory;
import manager.UserLoginUI;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class AdminDashboard extends JFrame {

    private String loggedInUser;

    // Existing constructor
    /**
     * @wbp.parser.constructor
     */
    public AdminDashboard(String userEmail) {
        this.loggedInUser = userEmail;
        initialize();
    }

    // New constructor to handle isSuperAdmin
    public AdminDashboard(String userEmail, boolean isSuperAdmin) {
        this.loggedInUser = userEmail;
        initialize();

        if (isSuperAdmin) {
            System.out.println("Super Admin Access Enabled");
            // Add any specific behavior for super admins
        }
    }
    private void initialize() {
        setTitle("Admin Dashboard");
        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        setBounds(100, 100, 1243, 720);
        setResizable(false);
        JPanel contentPane = new JPanel();
        contentPane.setLayout(null);
        setContentPane(contentPane);
        setLocationRelativeTo(null);

        // Add window listener for exit confirmation
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        AdminDashboard.this,
                        "Are you sure you want to exit?",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    // Optional: Add any cleanup code here
                    System.exit(0); // This will close the entire application
                }
            }
        });

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(3, 91, 150));
        leftPanel.setBounds(0, 74, 1227, 637);
        leftPanel.setLayout(null);
        contentPane.add(leftPanel);

        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/ImageIcon/navbar.png"));
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(0, 0, 300, 76);
        contentPane.add(logoLabel);

        // Logout button
        JButton logoutButton = new JButton("LOG OUT");
        logoutButton.setBackground(new Color(192, 192, 192));
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        logoutButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
        logoutButton.setBounds(34, 179, 180, 41);
        leftPanel.add(logoutButton);

        JPanel leftPanel_1 = new JPanel();
        leftPanel_1.setLayout(null);
        leftPanel_1.setBackground(new Color(255, 255, 255));
        leftPanel_1.setBounds(276, 11, 941, 580);
        leftPanel.add(leftPanel_1);

        JButton btnNewButton = new JButton("");
        btnNewButton.setBackground(new Color(3, 91, 150));
        ImageIcon button1Icon = new ImageIcon(getClass().getResource("/ImageIcon/Booked.png"));
        btnNewButton.setIcon(new ImageIcon(button1Icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        btnNewButton.setBounds(30, 26, 265, 544);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Dispose of the current frame and launch TransacHistory
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btnNewButton);
                currentFrame.dispose();  // Close the current frame
                TransacHistory.main(null);  // Open the TransacHistory frame
            }
        });
        leftPanel_1.add(btnNewButton);

        // Button 2 with image (GuestList redirection)
        JButton btnGuestList = new JButton("");
        btnGuestList.setBackground(new Color(3, 91, 150));

        ImageIcon button2Icon = new ImageIcon(getClass().getResource("/ImageIcon/Guest.png"));
        btnGuestList.setIcon(new ImageIcon(button2Icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        btnGuestList.setBounds(332, 26, 265, 544);
        btnGuestList.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Dispose of the current frame and open GuestList
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btnGuestList);
                currentFrame.dispose();  // Close the current frame
                GuestList.main(null);  // Open the GuestList frame
            }
        });
        leftPanel_1.add(btnGuestList);

        // Button 3 with image (RoomListAdmin redirection)
        JButton btnRoomListAdmin = new JButton("");
        btnRoomListAdmin.setBackground(new Color(3, 91, 150));
        ImageIcon button3Icon = new ImageIcon(getClass().getResource("/ImageIcon/hotel.png"));
        btnRoomListAdmin.setIcon(new ImageIcon(button3Icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        btnRoomListAdmin.setBounds(643, 26, 265, 270);
        btnRoomListAdmin.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Dispose of the current frame and open RoomListAdmin
                JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(btnRoomListAdmin);
                currentFrame.dispose();  // Close the current frame
                RoomListAdmin.main(null);  // Open the RoomListAdmin frame
            }
        });
        leftPanel_1.add(btnRoomListAdmin);

        JButton btnNewButton_1_1_1 = new JButton("");
        btnNewButton_1_1_1.setBackground(new Color(3, 91, 150));
        ImageIcon button4Icon = new ImageIcon(getClass().getResource("/ImageIcon/settings.png"));
        btnNewButton_1_1_1.setIcon(new ImageIcon(button4Icon.getImage().getScaledInstance(50, 50, Image.SCALE_SMOOTH)));
        btnNewButton_1_1_1.setBounds(643, 300, 265, 270);

        btnNewButton_1_1_1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                UserManagementOptions userManagementOptions = new UserManagementOptions(AdminDashboard.this);
                userManagementOptions.setVisible(true);
                dispose();
            }
        });

        leftPanel_1.add(btnNewButton_1_1_1);

        // User label
        JLabel userLabel = new JLabel("Username: " + loggedInUser);
        userLabel.setBounds(10, 61, 245, 90);
        userLabel.setBackground(new Color(192, 192, 192));
        userLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 20));
        userLabel.setForeground(Color.WHITE);
        leftPanel.add(userLabel);
    }

    private void logout() {
        setVisible(false);

        // Show the UserLoginUI window
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                AuroraCoveLandingPage.main(null);
            }
        });
    }

    public static void main(String[] args) {
        AdminDashboard frame = new AdminDashboard("MainAdmin");
        frame.setVisible(true);
    }

    private ImageIcon loadImage(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Error: Couldn't find file: " + path);
            return new ImageIcon();
        }
    }

}
