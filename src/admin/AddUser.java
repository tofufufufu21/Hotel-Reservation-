package admin;

import javax.swing.*;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

@SuppressWarnings("serial")
public class AddUser extends JFrame {

    private JTextField emailField;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JRadioButton adminRadioButton;
    private JRadioButton employeeRadioButton;
    @SuppressWarnings("unused")
    private UserManagementOptions optionsWindow;

    public AddUser(UserManagementOptions optionsWindow) {
        getContentPane().setBackground(new Color(240, 240, 240));
        this.optionsWindow = optionsWindow;
        setTitle("Add User");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        // Add window listener for exit confirmation
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int response = JOptionPane.showConfirmDialog(
                        AddUser.this,
                        "Are you sure you want to exit? Unsaved changes will be lost.",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (response == JOptionPane.YES_OPTION) {
                    dispose();
                    if (optionsWindow != null) {
                        optionsWindow.setVisible(true);
                    }
                }
            }
        });

        // REST OF YOUR ORIGINAL CODE BELOW (UNCHANGED)
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        emailLabel.setBounds(66, 97, 80, 25);
        getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(177, 97, 252, 25);
        getContentPane().add(emailField);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        usernameLabel.setBounds(66, 135, 80, 25);
        getContentPane().add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(177, 135, 252, 25);
        getContentPane().add(usernameField);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        passwordLabel.setBounds(66, 173, 80, 25);
        getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(177, 173, 252, 25);
        getContentPane().add(passwordField);

        adminRadioButton = new JRadioButton("Admin");
        adminRadioButton.setBounds(177, 211, 80, 25);
        getContentPane().add(adminRadioButton);

        employeeRadioButton = new JRadioButton("Employee");
        employeeRadioButton.setBounds(329, 211, 100, 25);
        getContentPane().add(employeeRadioButton);

        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(adminRadioButton);
        roleGroup.add(employeeRadioButton);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBackground(new Color(247, 170, 43));
        confirmButton.setBounds(197, 262, 197, 39);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleConfirm();
            }
        });
        getContentPane().add(confirmButton);

        JButton backButton = new JButton("Back");
        backButton.setForeground(new Color(255, 255, 255));
        backButton.setBackground(new Color(0, 64, 128));
        backButton.setBounds(12, 309, 91, 39);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                optionsWindow.setVisible(true);
            }
        });
        getContentPane().add(backButton);

        JLabel lblAddUser = new JLabel("ADD USER");
        lblAddUser.setForeground(new Color(0, 64, 128));
        lblAddUser.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 40));
        lblAddUser.setBounds(217, 29, 170, 39);
        getContentPane().add(lblAddUser);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 564, 339);
        getContentPane().add(panel);
    }

    private void handleConfirm() {
        String email = emailField.getText();
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        boolean isAdmin = adminRadioButton.isSelected();

        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        if (isAdmin) {
            addUserToDatabase("admins", username, email, hashedPassword, "admin");
        } else {
            addUserToDatabase("employees", username, email, hashedPassword, "employee");
        }

        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        adminRadioButton.setSelected(false);
        employeeRadioButton.setSelected(false);
    }

    public void addUserToDatabase(String tableName, String username, String email, String password, String role) {
        String dbURL = "jdbc:mysql://127.0.0.1:3306/hotel";
        String dbUsername = "root";
        String dbPassword = "11211810jr";

        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword)) {
            String sql = "INSERT INTO " + tableName + " (username, email, password, role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setString(3, password);
                stmt.setString(4, role);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "User added successfully!");
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding user: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        AdminDashboard dashboard = new AdminDashboard(null);
        UserManagementOptions optionsWindow = new UserManagementOptions(dashboard);
        optionsWindow.setVisible(true);
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