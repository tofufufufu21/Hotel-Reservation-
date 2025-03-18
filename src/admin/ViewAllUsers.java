package admin;

import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ViewAllUsers extends JFrame {
    private JPanel adminsPanel;
    private JPanel employeesPanel;
    private JScrollPane scrollPane;
    private JFrame parentFrame;

    public ViewAllUsers(JFrame parentFrame) {
        this.parentFrame = parentFrame;

        setTitle("All Users");
        setSize(700, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        // Title label
        JLabel titleLabel = new JLabel("ALL USERS", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        getContentPane().add(titleLabel, BorderLayout.NORTH);

        // Panel to hold admin list
        adminsPanel = new JPanel();
        adminsPanel.setLayout(new BoxLayout(adminsPanel, BoxLayout.Y_AXIS));
        adminsPanel.setBackground(Color.WHITE);

        // Panel to hold employee list
        employeesPanel = new JPanel();
        employeesPanel.setLayout(new BoxLayout(employeesPanel, BoxLayout.Y_AXIS));
        employeesPanel.setBackground(Color.WHITE);

        // Scroll pane for admins and employees panels
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Load and display users
        loadAndDisplayUsers();

        // Back button to return to UserManagementOptions
        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(0, 64, 128));
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("Tahoma", Font.BOLD, 16));
        backButton.addActionListener(e -> {
            setVisible(false);           // Hide this window
            parentFrame.setVisible(true); // Show parent frame
        });
        getContentPane().add(backButton, BorderLayout.SOUTH);
    }

    private void loadAndDisplayUsers() {
        // Fetch users from database
        List<User> admins = new ArrayList<>();
        List<User> employees = new ArrayList<>();
        fetchUsersFromDatabase(admins, employees);

        // Display admins
        JPanel adminSection = new JPanel();
        adminSection.setLayout(new BorderLayout());
        adminSection.setBorder(BorderFactory.createTitledBorder("Admins"));
        JScrollPane adminScroll = new JScrollPane(adminsPanel);
        adminSection.add(adminScroll, BorderLayout.CENTER);

        // Display employees
        JPanel employeeSection = new JPanel();
        employeeSection.setLayout(new BorderLayout());
        employeeSection.setBorder(BorderFactory.createTitledBorder("Employees"));
        JScrollPane employeeScroll = new JScrollPane(employeesPanel);
        employeeSection.add(employeeScroll, BorderLayout.CENTER);

        // Combine both admin and employee sections into the scroll pane
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(adminSection);
        mainPanel.add(employeeSection);

        scrollPane.setViewportView(mainPanel);
    }

    private void fetchUsersFromDatabase(List<User> admins, List<User> employees) {
        String url = "jdbc:mysql://localhost:3306/hotel";
        String user = "root";
        String password = "AandromedaNnebula11";

        // Query to fetch from both admins and employees
        String query = "SELECT username, 'admin' AS role FROM admins " +
                       "UNION ALL " +
                       "SELECT username, 'employee' AS role FROM employees";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String username = resultSet.getString("username");
                String role = resultSet.getString("role");

                User userObj = new User(username, role);
                if ("admin".equals(role)) {
                    admins.add(userObj);
                } else {
                    employees.add(userObj);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching users: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }

        // Add users to respective panels
        adminsPanel.removeAll();  // Clear the panel before adding new users
        employeesPanel.removeAll(); // Clear the panel before adding new users

        for (User adminUser : admins) {
            JPanel userPanel = createUserPanel(adminUser);
            adminsPanel.add(userPanel);
        }

        for (User employeeUser : employees) {
            JPanel userPanel = createUserPanel(employeeUser);
            employeesPanel.add(userPanel);
        }

        // Refresh the panels to show new components
        adminsPanel.revalidate();
        adminsPanel.repaint();
        employeesPanel.revalidate();
        employeesPanel.repaint();
    }

    private JPanel createUserPanel(User user) {
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BorderLayout());
        userPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
        userPanel.setPreferredSize(new Dimension(400, 40));

        JLabel usernameLabel = new JLabel(user.getUsername());
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        userPanel.add(usernameLabel, BorderLayout.CENTER);

        return userPanel;
    }

    // Helper class to store user information
    private static class User {
        private String username;
        private String role;

        public User(String username, String role) {
            this.username = username;
            this.role = role;
        }

        public String getUsername() {
            return username;
        }

        public String getRole() {
            return role;
        }
    }
}
