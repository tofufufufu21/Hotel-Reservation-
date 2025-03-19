package main;

import javax.swing.*;
import admin.loginUI;
import manager.UserLoginUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

public class AuroraCoveLandingPage extends JFrame {

    // Method to load images safely
    private ImageIcon loadImage(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Error: Couldn't find file: " + path);
            return new ImageIcon(); // Return an empty icon to prevent null errors
        }
    }

    public AuroraCoveLandingPage() {
        setTitle("Aurora Cove Hotel");
        setSize(771, 512);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set layout
        getContentPane().setLayout(null);

        // Logo Label
        JLabel logoLabel = new JLabel(loadImage("/ImageIcon/navbar.png"), SwingConstants.CENTER);
        logoLabel.setBounds(0, 0, 755, 97);
        getContentPane().add(logoLabel);

        // Admin Button with Image
        JButton adminButton = new JButton(loadImage("/ImageIcon/2.png"));
        adminButton.setBackground(new Color(3, 91, 150));
        adminButton.setBounds(61, 108, 256, 281);
        adminButton.setToolTipText("Admin Login");
        getContentPane().add(adminButton);

        // Receptionist Button with Image
        JButton receptionistButton = new JButton(loadImage("/ImageIcon/2.png"));
        receptionistButton.setBackground(new Color(3, 91, 150));
        receptionistButton.setBounds(407, 108, 256, 281);
        receptionistButton.setToolTipText("Receptionist Login");
        getContentPane().add(receptionistButton);

        // Labels for buttons
        JLabel lblAdmin = new JLabel("ADMIN");
        lblAdmin.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        lblAdmin.setBounds(158, 400, 75, 32);
        getContentPane().add(lblAdmin);

        JLabel lblReceptionist = new JLabel("RECEPTIONIST");
        lblReceptionist.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        lblReceptionist.setBounds(491, 400, 131, 32);
        getContentPane().add(lblReceptionist);

        // Admin button action
        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new loginUI().setVisible(true);
                dispose();
            }
        });

        // Receptionist button action
        receptionistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UserLoginUI().setVisible(true);
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AuroraCoveLandingPage landingPage = new AuroraCoveLandingPage();
            landingPage.setVisible(true);
        });
    }
}
