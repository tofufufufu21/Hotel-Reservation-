package admin;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class UserManagementOptions extends JFrame {

    private AdminDashboard dashboard;

    private ImageIcon loadImage(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Error: Couldn't find file: " + path);
            return new ImageIcon();
        }
    }

    public UserManagementOptions(AdminDashboard dashboard) {
        this.dashboard = dashboard;  
        setTitle("User Management Options");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);
        setResizable(false);
        // Add window listener for exit confirmation
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        UserManagementOptions.this,
                        "Are you sure you want to return to the dashboard?",
                        "Confirm Exit",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    dashboard.setVisible(true);
                }
            }
        });

        // Title Label
        JLabel titleLabel = new JLabel("USER MANAGEMENT");
        titleLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 40));
        titleLabel.setBounds(125, 31, 355, 32);
        getContentPane().add(titleLabel);

        // Add User Button
        JButton addUserButton = new JButton("ADD USER");
        addUserButton.setForeground(new Color(255, 255, 255));
        addUserButton.setBackground(new Color(0, 64, 128));
        addUserButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        addUserButton.setBounds(12, 100, 560, 50);
        addUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddUser addUserWindow = new AddUser(UserManagementOptions.this); 
                addUserWindow.setVisible(true);  
                setVisible(false);  
            }
        });
        getContentPane().add(addUserButton);

        JButton removeUserButton = new JButton("REMOVE USER");
        removeUserButton.setForeground(new Color(255, 255, 255));
        removeUserButton.setBackground(new Color(0, 64, 128));
        removeUserButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        removeUserButton.setBounds(12, 170, 560, 50);
        removeUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveUser removeUserWindow = new RemoveUser(UserManagementOptions.this);  
                removeUserWindow.setVisible(true);  
                setVisible(false);  
            }
        });
        getContentPane().add(removeUserButton);

        // View All Users Button
        JButton viewAllUsersButton = new JButton("VIEW ALL USERS");
        viewAllUsersButton.setForeground(new Color(255, 255, 255));
        viewAllUsersButton.setBackground(new Color(0, 64, 128));
        viewAllUsersButton.setFont(new Font("Tahoma", Font.BOLD, 20));
        viewAllUsersButton.setBounds(12, 240, 560, 50);
        viewAllUsersButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewAllUsers viewAllUsersWindow = new ViewAllUsers(UserManagementOptions.this); 
                viewAllUsersWindow.setVisible(true);  
                setVisible(false);  
            }
        });
        getContentPane().add(viewAllUsersButton);
        
        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(0, 64, 128));
        backButton.setBounds(12, 314, 91, 39);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  
                dashboard.setVisible(true);  
            }
        });
        getContentPane().add(backButton);

        setVisible(true);  
    }
}
