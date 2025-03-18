package main;

import javax.swing.*;
import admin.loginUI;
import manager.UserLoginUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;

public class AuroraCoveLandingPage extends JFrame {

    public AuroraCoveLandingPage() {

        setTitle("Aurora Cove Hotel");
        setSize(771, 512);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JLabel logoLabel = new JLabel(new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/navbar.png"), SwingConstants.CENTER);
        logoLabel.setBounds(0, 0, 755, 97);

        getContentPane().setLayout(null);
        getContentPane().add(logoLabel);

        JButton adminButton = new JButton(new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/2.png"));
        adminButton.setBackground(new Color(3, 91, 150));
        adminButton.setBounds(61, 108, 256, 281);
        adminButton.setToolTipText("Admin Login");
        getContentPane().add(adminButton);

        JButton receptionistButton = new JButton(new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/1.png"));
        receptionistButton.setBackground(new Color(3, 91, 150));
        receptionistButton.setBounds(407, 108, 256, 281);
        receptionistButton.setToolTipText("Receptionist Login");
        getContentPane().add(receptionistButton);
        
        JLabel lblNewLabel = new JLabel("ADMIN");
        lblNewLabel.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        lblNewLabel.setBounds(158, 400, 75, 32);
        getContentPane().add(lblNewLabel);
        
        JLabel lblAdmin = new JLabel("RECEPTIONIST");
        lblAdmin.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
        lblAdmin.setBounds(491, 400, 131, 32);
        getContentPane().add(lblAdmin);

        adminButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new loginUI().setVisible(true);
                dispose(); 
            }
        });

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
