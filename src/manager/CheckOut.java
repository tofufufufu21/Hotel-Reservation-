package manager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.*;

public class CheckOut {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Check Out Guest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(841, 500);
        frame.setVisible(true);  

        JPanel contentPanel = new JPanel();
        contentPanel.setBackground(new Color(3, 91, 150));
        contentPanel.setLayout(null);
        frame.getContentPane().add(contentPanel); 

        JLabel roomIdLabel = new JLabel("GUEST ID:");
        roomIdLabel.setForeground(new Color(255, 255, 255));
        roomIdLabel.setFont(new Font("Tahoma", Font.BOLD, 13));
        roomIdLabel.setBounds(50, 50, 80, 30);
        contentPanel.add(roomIdLabel);

        ImageIcon logoIcon = new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/navbar.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(536, 13, 277, 63);  
        contentPanel.add(logoLabel); 

        JTextField roomIdField = new JTextField();
        roomIdField.setBounds(125, 50, 282, 30);
        contentPanel.add(roomIdField);

        JButton searchButton = new JButton("Search");
        searchButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        searchButton.setBounds(400, 50, 100, 30);
        contentPanel.add(searchButton);

        JTable table = new JTable(new DefaultTableModel(
            new Object[]{"Guest ID", "First Name", "Last Name", "Phone Number", "Email Address", "Hotel Room", "Check-In Date", "Check-Out Date", "Total Amount"}, 0));
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(12, 100, 801, 200);
        contentPanel.add(tableScrollPane);

        JButton removeButton = new JButton("CHECK OUT");
        removeButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        removeButton.setBounds(320, 350, 157, 30);
        contentPanel.add(removeButton);

        JButton btnNewButton = new JButton("BACK");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnNewButton.setBounds(41, 404, 89, 30);
        contentPanel.add(btnNewButton);

        btnNewButton.addActionListener((ActionEvent e) -> {
            frame.setVisible(false);

            JFrame dashboardFrame = new JFrame("Dashboard User");
            dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dashboardFrame.setSize(1240, 866);
            DashboardUser dashboardPanel = new DashboardUser("user@example.com");  
            dashboardFrame.getContentPane().add(dashboardPanel);
            dashboardFrame.setVisible(true);
        });

        searchButton.addActionListener((ActionEvent e) -> {
            String guestIdText = roomIdField.getText();
            if (guestIdText.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Guest ID cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int guestId;
            try {
                guestId = Integer.parseInt(guestIdText);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid numeric Guest ID.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0); // Clear previous results

            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hotel", "root", "AandromedaNnebula11")) {

                // Query to fetch guest details by GuestID
                String query = """
                    SELECT GuestID, FirstName, LastName, PhoneNumber, EmailAddress, HotelRoom, 
                           CheckInDate, CheckOutDate, TotalAmount 
                    FROM guests
                    WHERE GuestID = ?;
                """;
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, guestId); // Set GuestID in the query
                ResultSet rs = stmt.executeQuery();

                boolean guestFound = false;
                while (rs.next()) {
                    guestFound = true;
                    model.addRow(new Object[] {
                        rs.getInt("GuestID"),
                        rs.getString("FirstName"),
                        rs.getString("LastName"),
                        rs.getString("PhoneNumber"),
                        rs.getString("EmailAddress"),
                        rs.getString("HotelRoom"),
                        rs.getDate("CheckInDate"),
                        rs.getDate("CheckOutDate"),
                        rs.getBigDecimal("TotalAmount")
                    });
                }

                if (!guestFound) {
                    JOptionPane.showMessageDialog(null, "No guest found with the given ID.", "Info", JOptionPane.INFORMATION_MESSAGE);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred while searching for the guest.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        removeButton.addActionListener((ActionEvent e) -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Please select a guest to remove.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            int guestId = (int) table.getValueAt(selectedRow, 0); // Assuming GuestID is in the first column

            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/hotel", "root", "AandromedaNnebula11")) {

                conn.setAutoCommit(false); // Start transaction

                // Query the guest details to transfer to transaction_history
                String selectGuestQuery = "SELECT * FROM guests WHERE GuestID = ?";
                PreparedStatement selectStmt = conn.prepareStatement(selectGuestQuery);
                selectStmt.setInt(1, guestId);
                ResultSet rs = selectStmt.executeQuery();

                if (rs.next()) {
                    // Insert data into transaction_history
                    String insertHistoryQuery = """
                        INSERT INTO transactionhistory (
                            GuestID, FirstName, LastName, PhoneNumber, EmailAddress, Address, 
                            City, Nationality, IDNumber, HotelRoom, CheckInDate, CheckOutDate, 
                            PaymentMethod, CreditCardNumber, CVV, TotalAmount, LunchPax, DinnerPax, AdditionalServiceCost
                        ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                    """;
                    PreparedStatement historyStmt = conn.prepareStatement(insertHistoryQuery);
                    historyStmt.setInt(1, rs.getInt("GuestID"));
                    historyStmt.setString(2, rs.getString("FirstName"));
                    historyStmt.setString(3, rs.getString("LastName"));
                    historyStmt.setString(4, rs.getString("PhoneNumber"));
                    historyStmt.setString(5, rs.getString("EmailAddress"));
                    historyStmt.setString(6, rs.getString("Address"));
                    historyStmt.setString(7, rs.getString("City"));
                    historyStmt.setString(8, rs.getString("Nationality"));
                    historyStmt.setString(9, rs.getString("IDNumber"));
                    historyStmt.setString(10, rs.getString("HotelRoom"));
                    historyStmt.setDate(11, rs.getDate("CheckInDate"));
                    historyStmt.setDate(12, rs.getDate("CheckOutDate"));
                    historyStmt.setString(13, rs.getString("PaymentMethod"));
                    historyStmt.setString(14, rs.getString("CreditCardNumber"));
                    historyStmt.setString(15, rs.getString("CVV"));
                    historyStmt.setBigDecimal(16, rs.getBigDecimal("TotalAmount"));
                    historyStmt.setInt(17, rs.getInt("LunchPax"));
                    historyStmt.setInt(18, rs.getInt("DinnerPax"));
                    historyStmt.setBigDecimal(19, rs.getBigDecimal("AdditionalServiceCost"));

                    historyStmt.executeUpdate();

                    // Delete the guest from the guests table
                    String deleteGuestQuery = "DELETE FROM guests WHERE GuestID = ?";
                    PreparedStatement deleteStmt = conn.prepareStatement(deleteGuestQuery);
                    deleteStmt.setInt(1, guestId);
                    deleteStmt.executeUpdate();

                    conn.commit(); 

                    JOptionPane.showMessageDialog(null, "Guest checked out successfully and moved to transaction history!");
                    DefaultTableModel model = (DefaultTableModel) table.getModel();
                    model.removeRow(selectedRow);

                } else {
                    JOptionPane.showMessageDialog(null, "Guest not found.", "Error", JOptionPane.ERROR_MESSAGE);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "An error occurred during the removal process.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}