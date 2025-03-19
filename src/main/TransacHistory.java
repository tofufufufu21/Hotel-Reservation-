package main;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import admin.AdminDashboard;

public class TransacHistory {

    private ImageIcon loadImage(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Error: Couldn't find file: " + path);
            return new ImageIcon(); // Return an empty icon to prevent errors
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Transaction History");
        frame.getContentPane().setBackground(new Color(0, 102, 204));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1487, 734);
        frame.getContentPane().setLayout(null);

        // Header panel
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 1487, 78);
        panel.setBackground(Color.WHITE);
        panel.setLayout(null);
        frame.getContentPane().add(panel);

        // Load logo image correctly
        JLabel logoLabel = new JLabel(new ImageIcon(TransacHistory.class.getResource("/ImageIcon/navbar.png")));
        logoLabel.setBounds(10, 10, 300, 56);
        panel.add(logoLabel);

        JTable table = new JTable(new DefaultTableModel(
                new Object[]{"Guest ID", "First Name", "Last Name", "Phone Number", "Email Address",
                        "Address", "City", "Nationality", "ID Number", "Hotel Room",
                        "Check-In Date", "Check-Out Date", "Payment Method", "Credit Card Number",
                        "CVV", "Total Amount", "Lunch Pax", "Dinner Pax", "Additional Service Cost"}, 0));

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 90, 1336, 561);
        frame.getContentPane().add(scrollPane);

        JButton btnNewButton = new JButton("BACK");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnNewButton.setBounds(20, 657, 97, 25);
        frame.getContentPane().add(btnNewButton);

        btnNewButton.addActionListener(e -> {
            frame.dispose();
            new AdminDashboard("admin@example.com").setVisible(true);
        });

        loadData(table);
        frame.setVisible(true);
    }

    private static void loadData(JTable table) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/hotel", "root", "11211810jr")) {

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
