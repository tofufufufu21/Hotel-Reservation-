package manager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DashboardUser extends JPanel {
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private static final long serialVersionUID = 1L;

    public DashboardUser(String email) {
        setBackground(new Color(255, 255, 255));
        setBounds(100, 100, 1240, 800);
        setLayout(null);
        
        JPanel navPanel = new JPanel();
        navPanel.setBounds(0, 109, 230, 756);
        navPanel.setBackground(new Color(255, 204, 102));
        add(navPanel);
        navPanel.setLayout(null);
        
        // Create the buttons
        JButton btnNewButton = new JButton("CHECK IN");
        btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 19));
        btnNewButton.setBackground(new Color(0, 128, 255));
        btnNewButton.setBounds(12, 49, 186, 50);
        navPanel.add(btnNewButton);
        
        JButton btnNewButton_1 = new JButton("CHECK OUT");
        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 19));
        btnNewButton_1.setBackground(new Color(0, 128, 255));
        btnNewButton_1.setBounds(12, 153, 186, 50);
        navPanel.add(btnNewButton_1);
        
        JButton btnNewButton_1_1 = new JButton("ROOMS");
        btnNewButton_1_1.setFont(new Font("Tahoma", Font.BOLD, 19));
        btnNewButton_1_1.setBackground(new Color(0, 128, 255));
        btnNewButton_1_1.setBounds(12, 258, 186, 50);
        navPanel.add(btnNewButton_1_1);
        
        JButton btnNewButton_1_1_1 = new JButton("TERMS & CONDITIONS");
        btnNewButton_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
        btnNewButton_1_1_1.setBackground(new Color(0, 128, 255));
        btnNewButton_1_1_1.setBounds(12, 349, 186, 50);
        navPanel.add(btnNewButton_1_1_1);
        
        JButton btnNewButton_1_1_1_1 = new JButton("GUESTS");
        btnNewButton_1_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 19));
        btnNewButton_1_1_1_1.setBackground(new Color(0, 128, 255));
        btnNewButton_1_1_1_1.setBounds(12, 439, 186, 50);
        navPanel.add(btnNewButton_1_1_1_1);
        
        JButton btnNewButton_1_1_1_1_1 = new JButton("LOG OUT");
        btnNewButton_1_1_1_1_1.setFont(new Font("Tw Cen MT", Font.BOLD, 18));
        btnNewButton_1_1_1_1_1.setBackground(new Color(192, 192, 192));
        btnNewButton_1_1_1_1_1.setBounds(12, 539, 186, 75);
        navPanel.add(btnNewButton_1_1_1_1_1);
        
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBounds(227, 109, 1016, 756);
        add(cardPanel);

        JPanel defaultPanel = createDefaultPanel();
        cardPanel.add(defaultPanel, "Default");

        // Action listeners for the buttons
        btnNewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAndOpenNewFrame(() -> CheckIn.main(null)); // Wrap CheckIn.main in a Runnable
            }
        });

        btnNewButton_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAndOpenNewFrame(() -> CheckOut.main(null)); // Wrap CheckOut.main in a Runnable
            }
        });

        btnNewButton_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAndOpenNewFrame(() -> RoomListManager.main(null)); // Wrap RoomListManager.main in a Runnable
            }
        });

        btnNewButton_1_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAndOpenNewFrame(() -> TermsConditions.main(null)); // Wrap TermsConditions.main in a Runnable
            }
        });

        btnNewButton_1_1_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAndOpenNewFrame(() -> GuestList.main(null)); // Wrap GuestList.main in a Runnable
            }
        });

        btnNewButton_1_1_1_1_1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeAndOpenNewFrame(() -> UserLoginUI.main(null)); // Wrap UserLoginUI.main in a Runnable
            }
        });
    }

    // Close current frame and open a new frame
    private void closeAndOpenNewFrame(Runnable newFrameRunnable) {
        JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(DashboardUser.this);
        topFrame.dispose();
        SwingUtilities.invokeLater(newFrameRunnable); // Now works with Runnable
    }

    public static void main(String[] args) {
        JFrame dashboardFrame = new JFrame("Dashboard User");
        dashboardFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dashboardFrame.setSize(1240, 866);
        dashboardFrame.setLocationRelativeTo(null);

        DashboardUser dashboardUserPanel = new DashboardUser("Employee");
        dashboardFrame.getContentPane().add(dashboardUserPanel);
        dashboardFrame.setVisible(true);
    }

    private JPanel createDefaultPanel() {
        JPanel defaultPanel = new JPanel();
        defaultPanel.setBackground(new Color(0, 128, 192));
        defaultPanel.setLayout(null);

        JPanel panel_1 = new JPanel();
        panel_1.setBounds(60, 13, 920, 180);
        panel_1.setLayout(null);
        panel_1.setBackground(new Color(255, 222, 173));
        defaultPanel.add(panel_1);

        ImageIcon imageIcon1 = new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/image 14.png");
        JLabel lblNewLabel_1 = new JLabel(imageIcon1);
        lblNewLabel_1.setBounds(0, 0, 382, 180);
        panel_1.add(lblNewLabel_1);

        ImageIcon logoIcon = new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/navbar.png");
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(0, 20, 300, 76);
        add(logoLabel);

        JPanel panel_2 = new JPanel();
        panel_2.setLayout(null);
        panel_2.setBackground(new Color(255, 165, 0));
        panel_2.setBounds(696, 0, 224, 55);
        panel_1.add(panel_2);

        JTextPane txtpnnight = new JTextPane();
        txtpnnight.setText("₱2,499/night");
        txtpnnight.setFont(new Font("Tahoma", Font.BOLD, 24));
        txtpnnight.setBackground(new Color(255, 165, 0));
        txtpnnight.setBounds(23, 10, 174, 35);
        panel_2.add(txtpnnight);

        JTextPane txtpnGrandOceanviewSuite_2 = new JTextPane();
        txtpnGrandOceanviewSuite_2.setText("Seashell Suite");
        txtpnGrandOceanviewSuite_2.setForeground(new Color(0, 0, 139));
        txtpnGrandOceanviewSuite_2.setFont(new Font("Tahoma", Font.BOLD, 22));
        txtpnGrandOceanviewSuite_2.setBackground(new Color(255, 222, 173));
        txtpnGrandOceanviewSuite_2.setBounds(392, 0, 275, 33);
        panel_1.add(txtpnGrandOceanviewSuite_2);

        JTextPane txtpnLoremIpsumDolor_2 = new JTextPane();
        txtpnLoremIpsumDolor_2.setText("Discover comfort and simplicity in the cozy Seashell Suite, designed for travelers seeking a relaxing and budget-friendly getaway. This suite features a calming coastal theme with elegant interiors, soft ambient lighting, and modern furniture that evokes the tranquil beauty of the seaside. Ideal for solo travelers or couples, the Seashell Suite offers a queen-size bed dressed with premium linens, a spacious bathroom equipped with toiletries, and large windows that invite natural light into the space. Amenities include a flat-screen TV, complimentary high-speed Wi-Fi.");
        txtpnLoremIpsumDolor_2.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtpnLoremIpsumDolor_2.setBackground(new Color(255, 222, 173));
        txtpnLoremIpsumDolor_2.setBounds(392, 58, 494, 122);
        panel_1.add(txtpnLoremIpsumDolor_2);
        
        JPanel panel_1_1 = new JPanel();
        panel_1_1.setLayout(null);
        panel_1_1.setBackground(new Color(255, 222, 173));
        panel_1_1.setBounds(60, 237, 920, 180);
        defaultPanel.add(panel_1_1);
        
        JLabel lblNewLabel_1_1 = new JLabel(new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/image 15.png"));
        lblNewLabel_1_1.setBounds(0, 0, 382, 170);
        panel_1_1.add(lblNewLabel_1_1);
        
        JPanel panel_2_1 = new JPanel();
        panel_2_1.setLayout(null);
        panel_2_1.setBackground(new Color(255, 165, 0));
        panel_2_1.setBounds(696, 0, 224, 55);
        panel_1_1.add(panel_2_1);
        
        JTextPane txtpnnight_1 = new JTextPane();
        txtpnnight_1.setText("₱6,499/night");
        txtpnnight_1.setFont(new Font("Tahoma", Font.BOLD, 24));
        txtpnnight_1.setBackground(new Color(255, 165, 0));
        txtpnnight_1.setBounds(23, 10, 174, 35);
        panel_2_1.add(txtpnnight_1);
        
        JTextPane txtpnGrandOceanviewSuite_2_1 = new JTextPane();
        txtpnGrandOceanviewSuite_2_1.setText("FamilyCove Suite");
        txtpnGrandOceanviewSuite_2_1.setForeground(new Color(0, 0, 139));
        txtpnGrandOceanviewSuite_2_1.setFont(new Font("Tahoma", Font.BOLD, 22));
        txtpnGrandOceanviewSuite_2_1.setBackground(new Color(255, 222, 173));
        txtpnGrandOceanviewSuite_2_1.setBounds(392, 10, 275, 33);
        panel_1_1.add(txtpnGrandOceanviewSuite_2_1);
        
        JTextPane txtpnLoremIpsumDolor_2_1 = new JTextPane();
        txtpnLoremIpsumDolor_2_1.setText("Discover comfort and simplicity in the cozy Seashell Suite, designed for travelers seeking a relaxing and budget-friendly getaway. This suite features a calming coastal theme with elegant interiors, soft ambient lighting, and modern furniture that evokes the tranquil beauty of the seaside. Ideal for solo travelers or couples, the Seashell Suite offers a queen-size bed dressed with premium linens, a spacious bathroom equipped with toiletries, and large windows that invite natural light into the space. Amenities include a flat-screen TV, complimentary high-speed Wi-Fi.");
        txtpnLoremIpsumDolor_2_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtpnLoremIpsumDolor_2_1.setBackground(new Color(255, 222, 173));
        txtpnLoremIpsumDolor_2_1.setBounds(392, 58, 494, 122);
        panel_1_1.add(txtpnLoremIpsumDolor_2_1);
        
        JPanel panel_1_1_1 = new JPanel();
        panel_1_1_1.setLayout(null);
        panel_1_1_1.setBackground(new Color(255, 222, 173));
        panel_1_1_1.setBounds(60, 467, 920, 180);
        defaultPanel.add(panel_1_1_1);
        
        JLabel lblNewLabel_1_1_1 = new JLabel(new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/image 16.png"));
        lblNewLabel_1_1_1.setBounds(0, 0, 382, 180);
        panel_1_1_1.add(lblNewLabel_1_1_1);
        
        JPanel panel_2_1_1 = new JPanel();
        panel_2_1_1.setLayout(null);
        panel_2_1_1.setBackground(new Color(255, 165, 0));
        panel_2_1_1.setBounds(696, 0, 224, 53);
        panel_1_1_1.add(panel_2_1_1);
        
        JTextPane txtpnnight_1_1 = new JTextPane();
        txtpnnight_1_1.setText("₱10,499/night");
        txtpnnight_1_1.setFont(new Font("Tahoma", Font.BOLD, 24));
        txtpnnight_1_1.setBackground(new Color(255, 165, 0));
        txtpnnight_1_1.setBounds(28, 10, 196, 35);
        panel_2_1_1.add(txtpnnight_1_1);
        
        JTextPane txtpnGrandOceanviewSuite_2_1_1 = new JTextPane();
        txtpnGrandOceanviewSuite_2_1_1.setText("Grand Oceanview\tSuite");
        txtpnGrandOceanviewSuite_2_1_1.setForeground(new Color(0, 0, 139));
        txtpnGrandOceanviewSuite_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 22));
        txtpnGrandOceanviewSuite_2_1_1.setBackground(new Color(255, 222, 173));
        txtpnGrandOceanviewSuite_2_1_1.setBounds(392, 10, 303, 33);
        panel_1_1_1.add(txtpnGrandOceanviewSuite_2_1_1);
        
        JTextPane txtpnLoremIpsumDolor_2_1_1 = new JTextPane();
        txtpnLoremIpsumDolor_2_1_1.setText("Discover comfort and simplicity in the cozy Seashell Suite, designed for travelers seeking a relaxing and budget-friendly getaway. This suite features a calming coastal theme with elegant interiors, soft ambient lighting, and modern furniture that evokes the tranquil beauty of the seaside. Ideal for solo travelers or couples, the Seashell Suite offers a queen-size bed dressed with premium linens, a spacious bathroom equipped with toiletries, and large windows that invite natural light into the space. Amenities include a flat-screen TV, complimentary high-speed Wi-Fi.");
        txtpnLoremIpsumDolor_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 12));
        txtpnLoremIpsumDolor_2_1_1.setBackground(new Color(255, 222, 173));
        txtpnLoremIpsumDolor_2_1_1.setBounds(392, 58, 494, 122);
        panel_1_1_1.add(txtpnLoremIpsumDolor_2_1_1);

        return defaultPanel;
    }
}
