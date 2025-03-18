package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.Color;
import java.awt.Font;

import admin.AdminDashboard; // Import the AdminDashboard class

public class TransacHistory {

    public static void main(String[] args) {
        // Create the JFrame
        JFrame frame = new JFrame("Transaction History");
        frame.getContentPane().setBackground(new Color(0, 102, 204));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1487, 734);
        frame.getContentPane().setLayout(null);

        // Create a JPanel for the header
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1487, 78);
        panel.setBackground(new Color(255, 255, 255)); // Optional: Set a background color
        panel.setLayout(null); // Use null layout for absolute positioning
        frame.getContentPane().add(panel);

        // Add logo to the panel
        ImageIcon logoIcon = new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/navbar.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(10, 10, 300, 56); // Adjust size and position as needed
        panel.add(logoLabel); // Add logoLabel to the panel

        // Define the table with the appropriate columns
        JTable table = new JTable(new DefaultTableModel(
            new Object[]{"Guest ID", "First Name", "Last Name", "Phone Number", "Email Address", 
                         "Address", "City", "Nationality", "ID Number", "Hotel Room", 
                         "Check-In Date", "Check-Out Date", "Payment Method", "Credit Card Number", 
                         "CVV", "Total Amount", "Lunch Pax", "Dinner Pax", "Additional Service Cost"}, 0));
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 90, 1436, 561);
        frame.getContentPane().add(scrollPane);
        
        JButton btnNewButton = new JButton("BACK");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnNewButton.setBounds(20, 657, 97, 25);
        frame.getContentPane().add(btnNewButton);

        // Add action listener to back button
        btnNewButton.addActionListener(e -> {
            frame.dispose();  // Close the current frame
            AdminDashboard.main(null);  // Open the AdminDashboard screen
        });

        // Load data from the database
        loadData(table);

        frame.setVisible(true);
    }

    private static void loadData(JTable table) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotel", "root", "AandromedaNnebula11")) {

            String query = "SELECT * FROM transactionhistory";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("GuestID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("PhoneNumber"),
                        rs.getString("EmailAddress"),
                        rs.getString("Address"),
                        rs.getString("City"),
                        rs.getString("Nationality"),
                        rs.getString("IDNumber"),
                        rs.getString("HotelRoom"),
                        rs.getDate("CheckInDate"),
                        rs.getDate("CheckOutDate"),
                        rs.getString("PaymentMethod"),
                        rs.getString("CreditCardNumber"),
                        rs.getString("CVV"),
                        rs.getBigDecimal("TotalAmount"),
                        rs.getInt("LunchPax"),
                        rs.getInt("DinnerPax"),
                        rs.getBigDecimal("AdditionalServiceCost")
                });
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}