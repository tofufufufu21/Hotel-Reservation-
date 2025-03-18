package admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

@SuppressWarnings("serial")
public class RemoveUser extends JFrame {

    private JTextField emailField;
    private JRadioButton adminRadioButton;
    private JRadioButton employeeRadioButton;
    @SuppressWarnings("unused")
	private UserManagementOptions optionsWindow;

    public RemoveUser(UserManagementOptions optionsWindow) {
        this.optionsWindow = optionsWindow;
        setTitle("Remove User");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        emailLabel.setBounds(66, 97, 80, 25);
        getContentPane().add(emailLabel);

        emailField = new JTextField();
        emailField.setBounds(177, 97, 252, 25);
        getContentPane().add(emailField);

        adminRadioButton = new JRadioButton("Admin");
        adminRadioButton.setBounds(177, 135, 80, 25);
        getContentPane().add(adminRadioButton);

        employeeRadioButton = new JRadioButton("Employee");
        employeeRadioButton.setBounds(329, 135, 100, 25);
        getContentPane().add(employeeRadioButton);

        ButtonGroup roleGroup = new ButtonGroup();
        roleGroup.add(adminRadioButton);
        roleGroup.add(employeeRadioButton);

        JButton confirmButton = new JButton("Remove");
        confirmButton.setBackground(new Color(247, 170, 43));
        confirmButton.setBounds(197, 200, 197, 39);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRemove();
            }
        });
        getContentPane().add(confirmButton);

        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
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

        JLabel lblRemoveUser = new JLabel("REMOVE USER");
        lblRemoveUser.setForeground(new Color(0, 64, 128));
        lblRemoveUser.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 40));
        lblRemoveUser.setBounds(217, 29, 200, 39);
        getContentPane().add(lblRemoveUser);

        JPanel panel = new JPanel();
        panel.setBounds(10, 11, 564, 339);
        getContentPane().add(panel);
    }

    private void handleRemove() {
        String email = emailField.getText();
        boolean isAdmin = adminRadioButton.isSelected();

        if (email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter an email.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String tableName = isAdmin ? "admins" : "employees";
        removeUserFromDatabase(tableName, email);

        emailField.setText("");
        adminRadioButton.setSelected(false);
        employeeRadioButton.setSelected(false);
    }

    public void removeUserFromDatabase(String tableName, String email) {
        String dbURL = "jdbc:mysql://localhost:3306/hotel";
        String dbUsername = "root";
        String dbPassword = "AandromedaNnebula11";

        try (Connection conn = DriverManager.getConnection(dbURL, dbUsername, dbPassword)) {
            String sql = "DELETE FROM " + tableName + " WHERE email = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, email);
                int rowsDeleted = stmt.executeUpdate();
                if (rowsDeleted > 0) {
                    JOptionPane.showMessageDialog(this, "User removed successfully!");
                } else {
                    JOptionPane.showMessageDialog(this, "User not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error removing user: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        AdminDashboard frame = new AdminDashboard("MainAdmin");
        frame.setVisible(true);
    }
}
