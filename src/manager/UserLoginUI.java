package manager;

import at.favre.lib.crypto.bcrypt.BCrypt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

@SuppressWarnings("serial")
public class UserLoginUI extends JFrame {
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPanel contentPane;

    public UserLoginUI() {
        setTitle("Employee Login");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        contentPane = new JPanel(new BorderLayout());
        setContentPane(contentPane);

        JPanel loginPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon backgroundImage = new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/bglog.png");
                g.drawImage(backgroundImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        loginPanel.setPreferredSize(new Dimension(450, 600));
        loginPanel.setLayout(null);
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
        loginButton.setBounds(154, 355, 95, 30);
        ImageIcon arrowIcon = new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/buton.png");
        loginButton.setIcon(arrowIcon);
        loginPanel.add(loginButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleEmployeeLogin();
            }
        });

        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon rightSideImage = new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/hotel6.png");
                g.drawImage(rightSideImage.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        contentPane.add(imagePanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private void handleEmployeeLogin() {
        String email = emailField.getText();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email and Password cannot be empty.", "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (validateEmployeeLogin(email, password)) {
            JOptionPane.showMessageDialog(this, "Login Successful! Welcome to the Employee Dashboard.");
            openEmployeeDashboard(email);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Email or Password!", "Login Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean validateEmployeeLogin(String email, String password) {
        String dbURL = "jdbc:mysql://localhost:3306/hotel";
        String dbUsername = "root";
        String dbPassword = "AandromedaNnebula11";

        String sql = "SELECT password FROM employees WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedHash = rs.getString("password");
                    BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), storedHash);
                    return result.verified;
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }

    private void openEmployeeDashboard(String email) {
        DashboardUser dashboard = new DashboardUser(email);
        JFrame dashboardFrame = new JFrame("Employee Dashboard");
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardFrame.setContentPane(dashboard);
        dashboardFrame.setSize(1243, 875);
        dashboardFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new UserLoginUI();
    }
}
