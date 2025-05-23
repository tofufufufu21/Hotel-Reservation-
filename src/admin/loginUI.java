package admin; // Or your appropriate package

import at.favre.lib.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


import admin.ForgotPassword; // Assuming ForgotPassword is in the admin package

@SuppressWarnings("serial")
public class loginUI extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPanel contentPane;

    private JFrame previousFrame; // Reference to the previous frame

    // Constructor now accepts the previous frame
    public loginUI(JFrame previousFrame) {
        this.previousFrame = previousFrame; // Store the reference

        setTitle("Admin Login");
        setSize(900, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Changed to DO_NOTHING
        setLocationRelativeTo(null);

        // Add window listener for exit confirmation
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        loginUI.this,
                        "Are you sure you want to exit the application?",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    System.exit(0);
                }
            }
        });

        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        JPanel loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Ensure the image path is correct relative to your resources
                ImageIcon backgroundImage = new ImageIcon(getClass().getResource("/ImageIcon/bglog.png"));
                if (backgroundImage.getImage() != null) {
                    g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                } else {
                    System.err.println("Background image not found: /ImageIcon/bglog.png");
                    // Optionally, draw a fallback color or message
                }
            }
        };
        loginPanel.setPreferredSize(new Dimension(450, 600));
        loginPanel.setLayout(null); // Using null layout requires manual positioning
        contentPane.add(loginPanel, BorderLayout.WEST);

        JLabel loginLabel = new JLabel("LOG IN");
        loginLabel.setFont(new Font("Sitka Text", Font.BOLD, 30));
        loginLabel.setForeground(Color.WHITE);
        loginLabel.setBounds(149, 100, 115, 40);
        loginPanel.add(loginLabel);

        JLabel emailLabel = new JLabel("Email");
        emailLabel.setFont(new Font("Sitka Text", Font.PLAIN, 22));
        emailLabel.setForeground(Color.WHITE);
        emailLabel.setBounds(100, 180, 100, 30);
        loginPanel.add(emailLabel);

        emailField = new JTextField();
        emailField.setBackground(new Color(221, 221, 221));
        emailField.setBounds(100, 210, 200, 30);
        loginPanel.add(emailField);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Sitka Text", Font.PLAIN, 22));
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setBounds(100, 260, 100, 30);
        loginPanel.add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBackground(new Color(221, 221, 221));
        passwordField.setBounds(100, 290, 200, 30);
        loginPanel.add(passwordField);

        JButton loginButton = new JButton("");
        loginButton.setBounds(154, 370, 95, 30);
        // Ensure the image path is correct relative to your resources
        ImageIcon arrowIcon = new ImageIcon(getClass().getResource("/ImageIcon/buton.png"));
        if (arrowIcon.getImage() != null) {
            loginButton.setIcon(arrowIcon);
            loginButton.setBorderPainted(false); // Make button look like just the icon
            loginButton.setContentAreaFilled(false); // Make button look like just the icon
            loginButton.setFocusPainted(false); // Remove focus border
        } else {
            System.err.println("Login button image not found: /ImageIcon/buton.png");
            loginButton.setText("Login"); // Fallback text if image not found
        }
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleAdminLogin();
            }
        });

        // --- Modified Forgot Password Button for better visibility ---
        JButton forgotPasswordButton = new JButton("Forgot Password?");
        forgotPasswordButton.setFont(new Font("Tahoma", Font.BOLD, 12)); // Make font bold
        forgotPasswordButton.setForeground(Color.BLACK); // Darker text color for contrast
        forgotPasswordButton.setBackground(new Color(240, 240, 240)); // Light gray background
        forgotPasswordButton.setOpaque(true); // Ensure background is painted
        forgotPasswordButton.setBorderPainted(true); // Show border
        forgotPasswordButton.setFocusPainted(false); // Remove focus border
        forgotPasswordButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // Make it look clickable
        forgotPasswordButton.setBounds(100, 333, 150, 25); // Position below password, adjusted size
        loginPanel.add(forgotPasswordButton);

        forgotPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the ForgotPassword frame, passing the current loginUI instance
                ForgotPassword forgotPasswordFrame = new ForgotPassword(loginUI.this); // Pass 'this'
                forgotPasswordFrame.setVisible(true);
                // Optionally, hide the login frame when Forgot Password is shown
                // loginUI.this.setVisible(false);
            }
        });

        // --- Added Back Button ---
        JButton backButton = new JButton("Back");
        backButton.setFont(new Font("Tahoma", Font.BOLD, 12));
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(0, 64, 128)); // Match your previous button style
        backButton.setBounds(20, 500, 80, 30); // Position at bottom left
        loginPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleBack();
            }
        });


        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Ensure the image path is correct relative to your resources
                ImageIcon rightSideImage = new ImageIcon(getClass().getResource("/ImageIcon/hotel6.png"));
                if (rightSideImage.getImage() != null) {
                    g.drawImage(rightSideImage.getImage(), 0, 0, getWidth(), getHeight(), this);
                } else {
                    System.err.println("Right side image not found: /ImageIcon/hotel6.png");
                    // Optionally, draw a fallback color or message
                }
            }
        };
        contentPane.add(imagePanel, BorderLayout.CENTER);

        // The setVisible(true) call should ideally be done after the frame is fully built,
        // often in the method that creates this frame, not in the constructor itself.
        // However, it works here for a simple example.
        // setVisible(true); // Consider moving this call outside the constructor
    }

    private void handleAdminLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both email and password.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (validateAdminLogin(email, password)) {
            boolean isSuperAdmin = checkIfSuperAdmin(email);

            if (isSuperAdmin) {
                // JOptionPane.showMessageDialog(this, "Welcome, Super Admin! Redirecting to Admin Dashboard with full access.");
            } else {
                // JOptionPane.showMessageDialog(this, "Login Successful! Welcome to the Admin Dashboard.");
            }

            openAdminDashboard(email, isSuperAdmin);
            dispose(); // Close login window after successful login
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Email or Password!", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateAdminLogin(String email, String password) {
        // CONSIDER: Externalize database credentials instead of hardcoding
        String dbURL = "jdbc:mysql://127.0.0.1:3306/hotel";
        String dbUsername = "root";
        String dbPassword = "11211810jr";

        String sql = "SELECT password FROM admins WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    // Use BCrypt.verifyer() to safely compare password with the stored hash
                    BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), storedHash);
                    return result.verified; // Access the verified field
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error during login validation: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Print stack trace for debugging
        } catch (Exception e) {
            // Catch any other potential exceptions during BCrypt verification
            JOptionPane.showMessageDialog(this, "An unexpected error occurred during login: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


        return false;
    }

    private boolean checkIfSuperAdmin(String email) {
        // CONSIDER: Externalize database credentials instead of hardcoding
        String dbURL = "jdbc:mysql://127.0.0.1:3306/hotel";
        String dbUsername = "root";
        String dbPassword = "11211810jr";

        String sql = "SELECT super_admin FROM admins WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    // Check if the column exists before trying to get it
                    try {
                        return rs.getBoolean("super_admin");
                    } catch (SQLException columnEx) {
                        // Handle case where 'super_admin' column might be missing (though the schema suggests it exists)
                        System.err.println("Column 'super_admin' not found in admins table.");
                        return false; // Assume not super admin if column is missing
                    }
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error during super admin check: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Print stack trace for debugging
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "An unexpected error occurred during super admin check: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }

        return false;
    }

    private void openAdminDashboard(String email, boolean isSuperAdmin) {
        SwingUtilities.invokeLater(() -> {
            // Pass 'this' (the current loginUI frame) as the previous frame
            // to the AdminDashboard constructor if AdminDashboard should have a back button
            // leading back to the login page. Otherwise, pass null or another appropriate frame.
            AdminDashboard adminDashboard = new AdminDashboard(email, isSuperAdmin /*, this*/); // Modify AdminDashboard constructor if needed
            adminDashboard.setVisible(true);
        });
    }

    // --- Added handleBack method with verification ---
    private void handleBack() {
        int confirm = JOptionPane.showConfirmDialog(
                this,
                "Are you sure you want to go back? Your entered details will be lost.",
                "Confirm Go Back",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            dispose(); // Close the current login frame
            if (previousFrame != null) {
                previousFrame.setVisible(true); // Show the previous frame
            } else {
                // If there is no previous frame, you might want to
                // open a main menu or simply exit the application
                // JOptionPane.showMessageDialog(null, "No previous page to go back to.");
                // System.exit(0); // Or handle appropriately
            }
        }
    }

    // The main method should ideally be in a separate launcher class
    // that decides which UI to show first. For this example,
    // we'll create a simple placeholder previous frame.
    public static void main(String[] args) {
        // Create a dummy previous frame for demonstration
        JFrame dummyPreviousFrame = new JFrame("Previous Page (Placeholder)");
        dummyPreviousFrame.setSize(300, 200);
        dummyPreviousFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dummyPreviousFrame.setLocationRelativeTo(null);
        dummyPreviousFrame.getContentPane().add(new JLabel("This is the previous page."));
        dummyPreviousFrame.setVisible(true); // Show the dummy frame first

        // Launch the login UI, passing the dummy frame as the previous frame
        SwingUtilities.invokeLater(() -> {
            loginUI login = new loginUI(dummyPreviousFrame); // Pass the previous frame
            login.setVisible(true);
            // Hide the dummy frame after loginUI is shown, but don't dispose it yet
            // so it can be shown again when "Back" is clicked.
            dummyPreviousFrame.setVisible(false);
        });
    }
}
