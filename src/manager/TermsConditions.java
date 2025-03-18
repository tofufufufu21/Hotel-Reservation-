package manager;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;

public class TermsConditions extends JPanel {

    public TermsConditions() {
        setBackground(new Color(30, 144, 255));

        ImageIcon logoIcon = new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/navbar.png");
        setLayout(null);
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(918, 5, 300, 76);  
        add(logoLabel); 
        
        JLabel lblHeader = new JLabel("TERMS AND CONDITIONS", SwingConstants.LEFT);
        lblHeader.setBackground(new Color(3, 91, 150));
        lblHeader.setBounds(0, 0, 1230, 94);
        lblHeader.setForeground(Color.WHITE);
        lblHeader.setFont(new Font("Segoe UI", Font.BOLD, 40));
        lblHeader.setBorder(new EmptyBorder(20, 0, 20, 0));
        add(lblHeader);

        JPanel termsContentPanel = new JPanel();
        termsContentPanel.setBackground(new Color(255, 255, 255));
        termsContentPanel.setLayout(new BoxLayout(termsContentPanel, BoxLayout.Y_AXIS));
        termsContentPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        createTermsPanel("Reservation", "- Our hotel does not accept any refund requests. Please ensure that you are fully committed to your booking.", termsContentPanel);
        createTermsPanel("Timeliness", "- Guests must adhere to the check-in and check-out times. Late check-ins or check-outs may result in additional charges or may not be permitted.", termsContentPanel);
        createTermsPanel("Missing Items", "- All missing items from hotel rooms will not be tolerated. Guests are responsible for all items in the room during their stay. Any missing items will be charged accordingly.", termsContentPanel);
        createTermsPanel("Damaged Property", "- If any property or item in the hotel is damaged by a guest, the guest will be responsible for covering the cost of repair or replacement.", termsContentPanel);
        createTermsPanel("Complaints and Disputes", "- Any complaints regarding the stay should be made immediately to the hotel management. We will attempt to resolve any issues in a professional manner. Disputes not resolved directly may be subject to local jurisdiction.", termsContentPanel);
        createTermsPanel("General", "- General hotel policies apply. Please follow the hotel rules and respect other guests for a pleasant stay.", termsContentPanel);

        // Scroll Pane for Content
        JScrollPane scrollPane = new JScrollPane(termsContentPanel);
        scrollPane.setBounds(0, 94, 1230, 618);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);
    }

    private void createTermsPanel(String title, String description, JPanel parentPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createEmptyBorder(10, 10, 10, 10),
            BorderFactory.createLineBorder(new Color(0, 120, 215), 1, true)
        ));
        panel.setBackground(Color.WHITE);
        panel.setMaximumSize(new Dimension(1200, 200));

        // Title Label
        JLabel titleLabel = new JLabel(title, SwingConstants.LEFT);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 102, 204));
        titleLabel.setBorder(new EmptyBorder(5, 5, 5, 5));

        // Description Text Area
        JTextArea textArea = new JTextArea(description);
        textArea.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        textArea.setForeground(Color.DARK_GRAY);
        textArea.setWrapStyleWord(true);
        textArea.setLineWrap(true);
        textArea.setEditable(false);
        textArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        textArea.setBackground(new Color(245, 245, 245));

        panel.add(titleLabel, BorderLayout.NORTH);
        panel.add(textArea, BorderLayout.CENTER);

        parentPanel.add(panel);
        parentPanel.add(Box.createVerticalStrut(10)); 
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Terms & Conditions");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1250, 750);
        frame.setContentPane(new TermsConditions());
        frame.setLocationRelativeTo(null); 
        frame.setVisible(true);
    }
}
