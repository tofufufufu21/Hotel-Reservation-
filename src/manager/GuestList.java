package manager;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import admin.AdminDashboard;
import manager.GuestList;

import java.sql.*;

public class GuestList extends JPanel {

    private static final long serialVersionUID = 1L;

    public GuestList() {
        setBackground(new Color(3, 91, 150));
        setBounds(100, 100, 1500, 586);
        setLayout(null);

        JPanel navPanel = new JPanel();
        navPanel.setBounds(10, 109, 220, 429);
        navPanel.setBackground(new Color(255, 204, 102));
        add(navPanel);
        navPanel.setLayout(null);

        JButton btnBack = new JButton("BACK");
        btnBack.setFont(new Font("Tahoma", Font.BOLD, 17));
        btnBack.setBounds(12, 36, 180, 53);  
        navPanel.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(GuestList.this);

                parentFrame.dispose();

                JFrame dashboardFrame = new JFrame("Dashboard User");
                dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                dashboardFrame.setSize(1240, 866);  
                dashboardFrame.setLocationRelativeTo(null);  

                DashboardUser dashboardUserPanel = new DashboardUser("Employee"); 
                dashboardFrame.add(dashboardUserPanel);

                dashboardFrame.setVisible(true);
            }
        });
        btnBack.setBounds(12, 36, 180, 53);
        navPanel.add(btnBack);

        JLabel label = new JLabel("New label");
        label.setBounds(338, 565, 328, -58);
        label.setBackground(new Color(0, 128, 192));
        add(label);

        JPanel panel = new JPanel();
        panel.setBounds(230, 109, 1239, 429);
        panel.setBackground(new Color(255, 255, 255));
        add(panel);
        panel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBackground(Color.WHITE);
        panel_1.setBounds(0, 0, 1239, 429);
        panel.add(panel_1);

        ImageIcon logoIcon = new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/navbar.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(10, 14, 300, 76);
        add(logoLabel); 

        String[] navItems = {"CHECK IN", "CHECK OUT", "ROOMS", "TERMS & CONDITIONS", "GUESTS", "LOG OUT"};
        for (String item : navItems) {
            JButton button = new JButton(item);
            button.setBackground(new Color(0, 51, 102));
            button.setForeground(Color.WHITE);
            button.setFont(new Font("Arial", Font.BOLD, 14));
            navPanel.add(button);
        }
        panel_1.setLayout(null);

        // Add JTable for displaying guest data
        JTable guestTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(guestTable);
        scrollPane.setBounds(0, 0, 1237, 429);
        panel_1.add(scrollPane);

        fetchData(guestTable);
    }

    private void fetchData(JTable table) {
        String url = "jdbc:mysql://localhost:3306/hotel"; 
        String user = "root"; 
        String password = "AandromedaNnebula11"; 
        String query = "SELECT * FROM guests";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 1; i <= columnCount; i++) {
                columnNames[i - 1] = metaData.getColumnName(i);
            }

            DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
            while (resultSet.next()) {
                Object[] row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i - 1] = resultSet.getObject(i);
                }
                tableModel.addRow(row);
            }

            table.setModel(tableModel);

        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error fetching data: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new GuestList());
        frame.setSize(1500, 600);
        frame.setVisible(true);
    }
}
