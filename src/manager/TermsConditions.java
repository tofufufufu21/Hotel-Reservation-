package manager;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TermsConditions extends JPanel {

    // Although loadImage is still here, it's not used for the logo anymore
    private ImageIcon loadImage(String path) {
        java.net.URL imgURL = getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Error: Couldn't find file: " + path);
            return new ImageIcon();
        }
    }

    public TermsConditions() {
        setBackground(new Color(30, 144, 255));
        setLayout(null); // Keep null layout as other components are positioned absolutely

        // Removed the logo loading and adding code:
        // ImageIcon logoIcon = loadImage("/ImageIcon/navbar.png");
        // JLabel logoLabel = new JLabel(logoIcon);
        // logoLabel.setBounds(918, 5, 300, 76);
        // add(logoLabel);

        JLabel lblHeader = new JLabel("TERMS AND CONDITIONS", SwingConstants.LEFT);
        lblHeader.setBackground(new Color(3, 91, 150));
        lblHeader.setBounds(0, 0, 1230, 94); // Adjust width if needed based on header placement
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
        scrollPane.setBounds(0, 94, 1230, 618); // Ensure scroll pane covers the area below the header
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        add(scrollPane);

        // Back Button
        JButton balikButton = new JButton("Back");
        // You might want to adjust the position of the back button if it was placed relative to the logo's position
        balikButton.setBounds(1050, 30, 150, 40);
        balikButton.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        balikButton.setBackground(new Color(255, 255, 255));
        balikButton.setForeground(new Color(30, 144, 255));
        balikButton.setFocusPainted(false);
        balikButton.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2));

        // Action to go back to DashboardUser
        balikButton.addActionListener(e -> {
            // Get the parent frame (the frame containing this panel)
            JFrame currentFrame = (JFrame) SwingUtilities.getWindowAncestor(TermsConditions.this);
            if (currentFrame != null) {
                currentFrame.dispose(); // Close the current frame

                // Open the DashboardUser frame on the Event Dispatch Thread
                SwingUtilities.invokeLater(() -> {
                    JFrame dashboardFrame = new JFrame("Dashboard User");
                    // Set close operation for the new dashboard frame
                    dashboardFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                    // Add window listener for exit confirmation to the dashboard frame
                    dashboardFrame.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosing(WindowEvent e) {
                            int confirm = JOptionPane.showConfirmDialog(
                                    dashboardFrame,
                                    "Are you sure you want to exit? Any unsaved data will be lost.",
                                    "Exit Confirmation",
                                    JOptionPane.YES_NO_OPTION,
                                    JOptionPane.WARNING_MESSAGE);

                            if (confirm == JOptionPane.YES_OPTION) {
                                dashboardFrame.dispose();
                            }
                        }
                    });
                    dashboardFrame.setSize(1240, 866);
                    DashboardUser dashboardPanel = new DashboardUser("user@example.com"); // You might want to pass the actual user email here
                    dashboardFrame.getContentPane().add(dashboardPanel);
                    dashboardFrame.setLocationRelativeTo(null);
                    dashboardFrame.setVisible(true);
                });
            }
        });

        add(balikButton);
    }

    private void createTermsPanel(String title, String description, JPanel parentPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(10, 10, 10, 10),
                BorderFactory.createLineBorder(new Color(0, 120, 215), 1, true)
        ));
        panel.setBackground(Color.WHITE);
        // Use a fixed width but let the height be determined by content
        panel.setMaximumSize(new Dimension(Short.MAX_VALUE, 200));


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
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.setSize(1250, 750);
        frame.setResizable(false);
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
                }
            }
        });
        frame.setContentPane(new TermsConditions());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}