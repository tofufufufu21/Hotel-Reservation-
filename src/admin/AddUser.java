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
    private JComboBox<String> securityQuestionComboBox; // Added
    private JTextField securityAnswerField; // Added
    @SuppressWarnings("unused")
    private UserManagementOptions optionsWindow;


    public AddUser(UserManagementOptions optionsWindow) {
        getContentPane().setBackground(new Color(240, 240, 240));
        this.optionsWindow = optionsWindow;
        setTitle("Add User");
        // Increased height to accommodate new fields
        setSize(600, 500); // Increased height from 400 to 500
        setResizable(false);
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

        // --- Original Components (Bounds adjusted) ---
        JLabel lblAddUser = new JLabel("ADD USER");
        lblAddUser.setForeground(new Color(0, 64, 128));
        lblAddUser.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 40));
        lblAddUser.setBounds(217, 20, 170, 39); // Moved up slightly
        getContentPane().add(lblAddUser);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        emailLabel.setBounds(66, 80, 80, 25); // Adjusted Y
        getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(177, 80, 252, 25); // Adjusted Y
        getContentPane().add(emailField);

        JLabel usernameLabel = new JLabel("Username: ");
        usernameLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        usernameLabel.setBounds(66, 118, 80, 25); // Adjusted Y
        getContentPane().add(usernameLabel);

        usernameField = new JTextField();
        usernameField.setBounds(177, 118, 252, 25); // Adjusted Y
        getContentPane().add(usernameField);

        JLabel passwordLabel = new JLabel("Password: ");
        passwordLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        passwordLabel.setBounds(66, 156, 80, 25); // Adjusted Y
        getContentPane().add(passwordLabel);

        passwordField = new JPasswordField();
        passwordField.setBounds(177, 156, 252, 25); // Adjusted Y
        getContentPane().add(passwordField);

        // --- Added Security Question Components ---
        JLabel securityQuestionLabel = new JLabel("Security Q:");
        securityQuestionLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        securityQuestionLabel.setBounds(66, 194, 80, 25); // Positioned below password
        getContentPane().add(securityQuestionLabel);

        String[] securityQuestions = {
                "-- Select a Question --", // Optional placeholder
                "What is your pet name?",
                "What is your favorite food?",
                "What is your favorite vlogger?"
        };
        securityQuestionComboBox = new JComboBox<>(securityQuestions);
        securityQuestionComboBox.setBounds(177, 194, 252, 25); // Positioned below password
        getContentPane().add(securityQuestionComboBox);

        JLabel securityAnswerLabel = new JLabel("Answer:");
        securityAnswerLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        securityAnswerLabel.setBounds(66, 232, 80, 25); // Positioned below question
        getContentPane().add(securityAnswerLabel);

        securityAnswerField = new JTextField();
        securityAnswerField.setBounds(177, 232, 252, 25); // Positioned below question
        getContentPane().add(securityAnswerField);
        // --- End Added Components ---

        adminRadioButton = new JRadioButton("Admin");
        adminRadioButton.setBounds(177, 270, 80, 25); // Adjusted Y
        getContentPane().add(adminRadioButton);

        employeeRadioButton = new JRadioButton("Employee");
        employeeRadioButton.setBounds(329, 270, 100, 25); // Adjusted Y
        getContentPane().add(employeeRadioButton);

        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(adminRadioButton);
        roleGroup.add(employeeRadioButton);

        JButton confirmButton = new JButton("Confirm");
        confirmButton.setBackground(new Color(247, 170, 43));
        confirmButton.setBounds(197, 315, 197, 39); // Adjusted Y
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
        backButton.setBounds(12, 400, 91, 39); // Adjusted Y for new height
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                optionsWindow.setVisible(true);
            }
        });
        getContentPane().add(backButton);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 564, 440); // Adjusted height
        getContentPane().add(panel);
    }

    private void handleConfirm() {
        String email = emailField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String securityQuestion = (String) securityQuestionComboBox.getSelectedItem();
        String securityAnswer = securityAnswerField.getText().trim(); // Added
        boolean isAdmin = adminRadioButton.isSelected();
        boolean isEmployee = employeeRadioButton.isSelected(); // Check if employee is selected too

        // --- Basic Input Validation ---
        if (email.isEmpty() || username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Email, Username, and Password cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!isAdmin && !isEmployee) {
            JOptionPane.showMessageDialog(this, "Please select a role (Admin or Employee).", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Validate security question selection (ensure a real question is chosen)
        if (securityQuestionComboBox.getSelectedIndex() <= 0) { // Index 0 is placeholder
            JOptionPane.showMessageDialog(this, "Please select a security question.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (securityAnswer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Security Answer cannot be empty.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // --- End Validation ---


        String hashedPassword = BCrypt.withDefaults().hashToString(12, password.toCharArray());

        String role = "";
        String tableName = "";
        if (isAdmin) {
            tableName = "admins";
            role = "admin";
        } else if (isEmployee){ // Use else if to be explicit
            tableName = "employees";
            role = "employee";
        } else {
            // This case should ideally be caught by validation, but good as a fallback.
            System.err.println("Error: No role selected.");
            return;
        }

        // Pass security question and answer to the database method
        addUserToDatabase(tableName, username, email, hashedPassword, role, securityQuestion, securityAnswer);

        // Clear fields after attempting to add
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        securityQuestionComboBox.setSelectedIndex(0); // Reset dropdown
        securityAnswerField.setText(""); // Clear security answer
        // Deselect radio buttons
        adminRadioButton.setSelected(false);
        employeeRadioButton.setSelected(false);
        // Or better, use ButtonGroup's clearSelection method if the group variable is accessible
        // roleGroup.clearSelection(); // Requires roleGroup to be an instance variable
    }

    // Modified method signature and SQL query
    public void addUserToDatabase(String tableName, String username, String email, String password, String role, String securityQuestion, String securityAnswer) {
        String dbURL = "jdbc:mysql://127.0.0.1:3306/hotel";
        String dbUsername = "root";
        String dbPassword = "11211810jr"; // Consider externalizing credentials

        // Added security_question and security_answer columns
        String sql = "INSERT INTO " + tableName + " (username, email, password, role, security_question, security_answer) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword)) {
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, username);
                stmt.setString(2, email);
                stmt.setString(3, password); // Storing hashed password
                stmt.setString(4, role);
                stmt.setString(5, securityQuestion); // Added parameter
                stmt.setString(6, securityAnswer);  // Added parameter (Stored as plain text - Consider hashing for better security)

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    JOptionPane.showMessageDialog(this, "User added successfully!");
                }
            }
        } catch (SQLIntegrityConstraintViolationException e) {
            // Catch specific duplicate entry error
            if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("for key 'username'")) {
                JOptionPane.showMessageDialog(this, "Error: Username '" + username + "' already exists.", "Database Error", JOptionPane.ERROR_MESSAGE);
            } else if (e.getMessage().contains("Duplicate entry") && e.getMessage().contains("for key 'email'")) {
                JOptionPane.showMessageDialog(this, "Error: Email '" + email + "' already exists.", "Database Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error adding user: A database constraint was violated.\n" + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding user: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace(); // Print stack trace for debugging
        }
    }

    // No changes needed in main or loadImage
    public static void main(String[] args) {
        // Assuming AdminDashboard and UserManagementOptions exist and work
        // This main method might be for testing; ensure it fits your application flow
        AdminDashboard dashboard = null; // Or however you initialize it
        UserManagementOptions optionsWindow = new UserManagementOptions(dashboard); // Pass necessary context
        AddUser addUserFrame = new AddUser(optionsWindow); // Pass optionsWindow reference
        addUserFrame.setVisible(true);
        // optionsWindow.setVisible(true); // Decide which window appears first
    }

    private ImageIcon loadImage(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Error: Couldn't find file: " + path);
            return new ImageIcon(); // Return empty icon to avoid null pointer
        }
    }
}