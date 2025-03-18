package manager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JButton;

public class RoomListManager {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Room Availability - Employee View");
        frame.getContentPane().setBackground(new Color(0, 128, 192));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 664);
        DefaultTableModel tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Room Name");
        tableModel.addColumn("Check-In");
        tableModel.addColumn("Check-Out");
        tableModel.addColumn("Availability");
        frame.getContentPane().setLayout(null);
        
        
        JPanel panel = new JPanel();
        panel.setBounds(0, 0, 784, 70);
        panel.setLayout(null);  
        frame.getContentPane().add(panel);
        
        ImageIcon logoIcon = new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/navbar.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(10, 0, 300, 76);
        panel.add(logoLabel);

        
        
        JPanel panel_1 = new JPanel();
        panel_1.setBackground(new Color(0, 64, 128));
        panel_1.setBounds(26, 108, 733, 445);
        frame.getContentPane().add(panel_1);
        panel_1.setLayout(null);
        
        JTable table = new JTable();
        table.setModel(tableModel);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 21, 692, 402);
        panel_1.add(scrollPane);
        
        JButton btnNewButton = new JButton("BACK");
        btnNewButton.setBackground(new Color(0, 64, 128));
        btnNewButton.setBounds(27, 577, 89, 23);
        frame.getContentPane().add(btnNewButton);

        // Action listener to navigate back to the dashboard
        btnNewButton.addActionListener(e -> {
            frame.dispose(); // Close the current frame
            DashboardUser dashboard = new DashboardUser("Employee"); // You can pass any required data here
            JFrame dashboardFrame = new JFrame("Dashboard - Employee View");
            dashboardFrame.setSize(1243, 875);
            dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            dashboardFrame.add(dashboard);
            dashboardFrame.setVisible(true);
        });

        loadData(tableModel);

        frame.setVisible(true);
    }

    private static void loadData(DefaultTableModel tableModel) {
        tableModel.setRowCount(0); 
        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/hotel", "root", "AandromedaNnebula11")) {

            String query = "SELECT r.Id, rt.RoomName, r.CheckIn, r.CheckOut, r.Availability " +
                           "FROM rooms r " +
                           "JOIN room_types rt ON r.RoomTypeId = rt.Id";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                tableModel.addRow(new Object[]{
                        rs.getInt("Id"),                                
                        rs.getString("RoomName"),                     
                        rs.getDate("CheckIn") != null ? rs.getDate("CheckIn") : "mm/dd/yy", 
                        rs.getDate("CheckOut") != null ? rs.getDate("CheckOut") : "mm/dd/yy", 
                        rs.getBoolean("Availability") ? "Available" : "Booked" 
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
