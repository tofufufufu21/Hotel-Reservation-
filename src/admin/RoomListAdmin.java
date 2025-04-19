package admin;

import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
public class RoomListAdmin {

    private static DefaultTableModel tableModel;

    private ImageIcon loadImage(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Error: Couldn't find file: " + path);
            return new ImageIcon();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(RoomListAdmin::createAndShowGUI);
    }

    /**
     * @wbp.parser.entryPoint
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Room Availability - Admin View");
        frame.setBackground(new Color(255, 255, 255));
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Changed to DO_NOTHING
        frame.setSize(900, 600);
        frame.getContentPane().setLayout(null);

        // Add window listener for exit confirmation
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        frame,
                        "Are you sure you want to exit? Any unsaved changes will be lost.",
                        "Exit Confirmation",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE);

                if (confirm == JOptionPane.YES_OPTION) {
                    frame.dispose();
                    AdminDashboard.main(null); // Return to admin dashboard
                }
            }
        });

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(3, 91, 150));
        headerPanel.setBounds(0, 0, 884, 43);
        headerPanel.setLayout(null);
        JLabel titleLabel = new JLabel("ROOM AVAILABILITY");
        titleLabel.setForeground(new Color(255, 255, 255));
        titleLabel.setBounds(26, 13, 193, 22);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.add(titleLabel);
        frame.getContentPane().add(headerPanel);

        // Table Panel
        tableModel = new DefaultTableModel();
        JTable table = new JTable(tableModel);
        tableModel.addColumn("ID");
        tableModel.addColumn("Room Name");
        tableModel.addColumn("Check-In");
        tableModel.addColumn("Check-Out");
        tableModel.addColumn("Availability");
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 42, 884, 464);
        frame.getContentPane().add(scrollPane);
        
        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBounds(0, 506, 884, 55);
        buttonPanel.setBackground(new Color(3, 91, 150));
        buttonPanel.setLayout(null);

        // Back Button
        JButton backButton = new JButton("Back");
        backButton.setForeground(Color.WHITE);
        backButton.setBackground(new Color(0, 64, 128));
        backButton.setBounds(12, 10, 91, 25);
        backButton.addActionListener(e -> {
            frame.dispose(); // Close the current frame
            AdminDashboard.main(null); // Open AdminDashboard
        });
        buttonPanel.add(backButton);

        // Other Buttons (Add, Edit, Delete)
        JButton addButton = new JButton("Add Room");
        addButton.setBounds(539, 10, 91, 25);
        JButton editButton = new JButton("Edit Room");
        editButton.setBounds(642, 10, 91, 25);
        JButton deleteButton = new JButton("Delete Room");
        deleteButton.setBounds(745, 10, 105, 25);

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        frame.getContentPane().add(buttonPanel);

        // Action Listeners
        loadData();
        addButton.addActionListener(e -> addRoom());
        editButton.addActionListener(e -> editRoom(table.getSelectedRow()));
        deleteButton.addActionListener(e -> deleteRoom(table.getSelectedRow()));

        // Display the frame
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static void loadData() {
        tableModel.setRowCount(0); // Clear the table
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://127.0.0.1:3306/hotel", "root", "11211810jr")) {

            String query = "SELECT r.Id, rt.RoomName, r.CheckIn, r.CheckOut, r.Availability " +
                           "FROM rooms r " +
                           "JOIN room_types rt ON r.RoomTypeId = rt.Id";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[] {
                        rs.getInt("Id"),
                        rs.getString("RoomName"),
                        rs.getDate("CheckIn") != null ? rs.getDate("CheckIn") : "mm/dd/yy",
                        rs.getDate("CheckOut") != null ? rs.getDate("CheckOut") : "mm/dd/yy",
                        rs.getBoolean("Availability") ? "Available" : "Booked"
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error loading data from database.");
        }
    }

    private static void addRoom() {
        JTextField roomNameField = new JTextField();
        JTextField checkInField = new JTextField();
        JTextField checkOutField = new JTextField();
        JTextField availabilityField = new JTextField();

        Object[] message = {
                "Room Name:", roomNameField,
                "Check-In (yyyy-mm-dd):", checkInField,
                "Check-Out (yyyy-mm-dd):", checkOutField,
                "Availability (1 for Available, 0 for Booked):", availabilityField
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Add Room", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/hotel", "root", "11211810jr")) {

                String query = "INSERT INTO rooms (RoomTypeId, CheckIn, CheckOut, Availability) " +
                               "VALUES ((SELECT Id FROM room_types WHERE RoomName = ?), ?, ?, ?)";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setString(1, roomNameField.getText());
                stmt.setString(2, checkInField.getText());
                stmt.setString(3, checkOutField.getText());
                stmt.setInt(4, Integer.parseInt(availabilityField.getText()));

                stmt.executeUpdate();
                loadData(); // Reload table data
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error adding room. Please check your input.");
            }
        }
    }

    private static void editRoom(int rowIndex) {
        if (rowIndex >= 0) {
            int id = (int) tableModel.getValueAt(rowIndex, 0);
            String currentRoomName = (String) tableModel.getValueAt(rowIndex, 1);
            String currentCheckIn = (String) tableModel.getValueAt(rowIndex, 2);
            String currentCheckOut = (String) tableModel.getValueAt(rowIndex, 3);
            String currentAvailability = (String) tableModel.getValueAt(rowIndex, 4);

            JTextField roomNameField = new JTextField(currentRoomName);
            JTextField checkInField = new JTextField(currentCheckIn);
            JTextField checkOutField = new JTextField(currentCheckOut);
            JTextField availabilityField = new JTextField(currentAvailability.equals("Available") ? "1" : "0");

            Object[] message = {
                    "Room Name:", roomNameField,
                    "Check-In (yyyy-mm-dd):", checkInField,
                    "Check-Out (yyyy-mm-dd):", checkOutField,
                    "Availability (1 for Available, 0 for Booked):", availabilityField
            };

            int option = JOptionPane.showConfirmDialog(null, message, "Edit Room", JOptionPane.OK_CANCEL_OPTION);
            if (option == JOptionPane.OK_OPTION) {
                try (Connection conn = DriverManager.getConnection(
                        "jdbc:mysql://127.0.0.1:3306/hotel", "root", "11211810jr")) {

                    String query = "UPDATE rooms SET RoomTypeId = (SELECT Id FROM room_types WHERE RoomName = ?), " +
                                   "CheckIn = ?, CheckOut = ?, Availability = ? WHERE Id = ?";
                    PreparedStatement stmt = conn.prepareStatement(query);
                    stmt.setString(1, roomNameField.getText());
                    stmt.setString(2, checkInField.getText());
                    stmt.setString(3, checkOutField.getText());
                    stmt.setInt(4, Integer.parseInt(availabilityField.getText()));
                    stmt.setInt(5, id);

                    stmt.executeUpdate();
                    loadData(); // Reload table data
                } catch (SQLException e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error updating room. Please check your input.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select a row to edit!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }

    private static void deleteRoom(int rowIndex) {
        if (rowIndex >= 0) {
            int id = (int) tableModel.getValueAt(rowIndex, 0);
            try (Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://127.0.0.1:3306/hotel", "root", "11211810jr")) {

                String query = "DELETE FROM rooms WHERE Id = ?";
                PreparedStatement stmt = conn.prepareStatement(query);
                stmt.setInt(1, id);
                stmt.executeUpdate();
                loadData(); // Reload table data
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error deleting room.");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Select a row to delete!", "Warning", JOptionPane.WARNING_MESSAGE);
        }
    }
}
