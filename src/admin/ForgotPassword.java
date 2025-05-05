package admin; // Or admin, depending on your package structure

import javax.swing.*;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


@SuppressWarnings("serial")
public class ForgotPassword extends JFrame {

    private JPanel emailRolePanel;
    private JTextField emailField;
    private JRadioButton adminRadioButton;
    private JRadioButton employeeRadioButton;
    private ButtonGroup roleGroup;
    private JButton findUserButton;

    private JPanel securityAnswerPanel;
    private JLabel securityQuestionLabel;
    private JTextField securityAnswerField;
    private JButton submitAnswerButton;
    // Changed to store the plain text answer retrieved from DB
    private String storedSecurityAnswerPlain;
    private String userEmail; // To store the email for subsequent steps
    private String userRole; // To store the role for subsequent steps

    private JPanel resetPasswordPanel;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;
    private JButton resetPasswordButton;

    // Reference to the login frame that opened this window
    private JFrame loginFrame;

    // Constructor now accepts the login frame
    public ForgotPassword(JFrame loginFrame) {
        this.loginFrame = loginFrame; // Initialize the loginFrame reference

        setTitle("Forgot Password");
        setSize(400, 300); // Adjust size as needed
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new CardLayout()); // Use CardLayout to switch panels

        // --- Step 1: Email and Role Panel ---
        emailRolePanel = new JPanel();
        emailRolePanel.setLayout(new GridBagLayout());
        emailRolePanel.setBorder(BorderFactory.createTitledBorder("Find Your Account"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        emailRolePanel.add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        emailField = new JTextField(20);
        emailRolePanel.add(emailField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        emailRolePanel.add(new JLabel("Role:"), gbc);

        JPanel rolePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        adminRadioButton = new JRadioButton("Admin");
        employeeRadioButton = new JRadioButton("Employee");
        roleGroup = new ButtonGroup();
        roleGroup.add(adminRadioButton);
        roleGroup.add(employeeRadioButton);
        rolePanel.add(adminRadioButton);
        rolePanel.add(employeeRadioButton);

        gbc.gridx = 1;
        gbc.gridy = 1;
        emailRolePanel.add(rolePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        findUserButton = new JButton("Find User");
        emailRolePanel.add(findUserButton, gbc);

        // --- Step 2: Security Answer Panel ---
        securityAnswerPanel = new JPanel();
        securityAnswerPanel.setLayout(new GridBagLayout());
        securityAnswerPanel.setBorder(BorderFactory.createTitledBorder("Security Question"));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        securityQuestionLabel = new JLabel("Security Question:"); // This label will be updated
        securityAnswerPanel.add(securityQuestionLabel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        securityAnswerPanel.add(new JLabel("Your Answer:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        securityAnswerField = new JTextField(20);
        securityAnswerPanel.add(securityAnswerField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        submitAnswerButton = new JButton("Submit Answer");
        securityAnswerPanel.add(submitAnswerButton, gbc);
        securityAnswerPanel.setVisible(false); // Start hidden

        // --- Step 3: Reset Password Panel ---
        resetPasswordPanel = new JPanel();
        resetPasswordPanel.setLayout(new GridBagLayout());
        resetPasswordPanel.setBorder(BorderFactory.createTitledBorder("Reset Password"));
        gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        resetPasswordPanel.add(new JLabel("New Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        newPasswordField = new JPasswordField(20);
        resetPasswordPanel.add(newPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        resetPasswordPanel.add(new JLabel("Confirm Password:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        confirmPasswordField = new JPasswordField(20);
        resetPasswordPanel.add(confirmPasswordField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        resetPasswordButton = new JButton("Reset Password");
        resetPasswordPanel.add(resetPasswordButton, gbc);
        resetPasswordPanel.setVisible(false); // Start hidden

        // Add panels to the frame with CardLayout names
        getContentPane().add(emailRolePanel, "EmailRole");
        getContentPane().add(securityAnswerPanel, "SecurityAnswer");
        getContentPane().add(resetPasswordPanel, "ResetPassword");

        // --- Action Listeners ---
        findUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFindUser();
            }
        });

        submitAnswerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSubmitAnswer();
            }
        });

        resetPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleResetPassword();
            }
        });
    }

    private void handleFindUser() {
        userEmail = emailField.getText().trim();
        boolean isAdmin = adminRadioButton.isSelected();
        boolean isEmployee = employeeRadioButton.isSelected();

        if (userEmail.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your email.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!isAdmin && !isEmployee) {
            JOptionPane.showMessageDialog(this, "Please select a role (Admin or Employee).", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        userRole = isAdmin ? "admin" : "employee";
        String tableName = isAdmin ? "admins" : "employees";

        // Query database to find user and get security question/answer
        String dbURL = "jdbc:mysql://127.0.0.1:3306/hotel";
        String dbUsername = "root";
        String dbPassword = "11211810jr"; // Consider externalizing credentials

        String sql = "SELECT security_question, security_answer FROM " + tableName + " WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, userEmail);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String question = rs.getString("security_question");
                // Retrieve the plain text answer
                storedSecurityAnswerPlain = rs.getString("security_answer");

                if (question == null || question.trim().isEmpty() || storedSecurityAnswerPlain == null || storedSecurityAnswerPlain.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Security information not set for this account. Please contact an administrator.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                securityQuestionLabel.setText("Security Question: " + question);
                securityAnswerField.setText(""); // Clear previous answer
                // Switch to the security answer panel
                CardLayout cl = (CardLayout)(getContentPane().getLayout());
                cl.show(getContentPane(), "SecurityAnswer");

            } else {
                JOptionPane.showMessageDialog(this, "User with this email and role not found.", "User Not Found", JOptionPane.WARNING_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void handleSubmitAnswer() {
        String providedAnswer = securityAnswerField.getText().trim();

        if (providedAnswer.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter your answer.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // --- Compare provided answer directly with the stored plain text answer ---
        // We trim both to handle potential leading/trailing spaces
        if (providedAnswer.equals(storedSecurityAnswerPlain.trim())) {
            // Answer is correct, switch to reset password panel
            newPasswordField.setText(""); // Clear fields
            confirmPasswordField.setText("");
            CardLayout cl = (CardLayout)(getContentPane().getLayout());
            cl.show(getContentPane(), "ResetPassword");
        } else {
            JOptionPane.showMessageDialog(this, "Incorrect security answer.", "Authentication Failed", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void handleResetPassword() {
        String newPassword = new String(newPasswordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());

        if (newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter and confirm your new password.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "New password and confirm password do not match.", "Input Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Optional: Add password strength validation here

        // Hash the new password using BCrypt (as intended)
        String hashedNewPassword = BCrypt.withDefaults().hashToString(12, newPassword.toCharArray());

        // Update the password in the database
        String tableName = userRole.equals("admin") ? "admins" : "employees";
        String dbURL = "jdbc:mysql://127.0.0.1:3306/hotel";
        String dbUsername = "root";
        String dbPassword = "11211810jr"; // Consider externalizing credentials

        String sql = "UPDATE " + tableName + " SET password = ? WHERE email = ?";

        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword);
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, hashedNewPassword);
            stmt.setString(2, userEmail);

            int rowsUpdated = stmt.executeUpdate();

            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Password reset successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Password reset successful, close this window and show the login frame
                dispose();
                if (loginFrame != null) {
                    loginFrame.setVisible(true);
                }
            } else {
                // This case should ideally not happen if findUser was successful,
                // but it's a safeguard.
                JOptionPane.showMessageDialog(this, "Failed to update password. User not found or database error.", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Database Error: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage:
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                // In a real application, you would pass the actual login frame here
                JFrame dummyLoginFrame = new JFrame("Dummy Login Frame");
                dummyLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dummyLoginFrame.setSize(300, 200);
                dummyLoginFrame.setLocationRelativeTo(null);
                dummyLoginFrame.getContentPane().add(new JLabel("This is the dummy login frame."));
                dummyLoginFrame.setVisible(true); // Show the dummy frame first

                ForgotPassword frame = new ForgotPassword(dummyLoginFrame); // Pass the dummy login frame
                // frame.setVisible(true); // You might not want to show ForgotPassword immediately in main
            }
        });
    }
}
