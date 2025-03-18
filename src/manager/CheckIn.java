	package manager;
	
	
	import javax.swing.*;
	
	
	import java.awt.*;
	import javax.swing.border.LineBorder;
	import java.awt.event.ActionListener;
	import java.sql.Connection;
	import java.sql.Date;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.text.SimpleDateFormat;
	import java.awt.event.ActionEvent;
	
	public class CheckIn extends JPanel {
	
	    private static final long serialVersionUID = 1L;
	    private JTextField firstNameField;
	    private JTextField lastNameField;
	    private JTextField phoneNumberField;
	    private JTextField emailField;
	    private JTextField addressField;
	    private JTextField cityField;
	    private JTextField nationalityField;
	    private JTextField textField_8;
	    private JTextField textField_10;
	    private JTextField textField;
	    private JTextField textField_1;
	    private JButton backButton;
	    private JTextField paxCountField = new JTextField();
	    private JLabel totalLabel; 
	    private JTextField totalField;
	
	    @SuppressWarnings("unchecked")
		public CheckIn() {
	    	setBackground(new Color(255, 255, 255));
	        setBounds(100, 100, 1219, 800);
	        
	        ImageIcon logoIcon = new ImageIcon("C:/Users/JC Mendez/Downloads/AuroraCoveHotel/auroraCoveHotel/ImageIcon/navbar.png");
	        setLayout(null);
	        JLabel logoLabel = new JLabel(logoIcon);
	        logoLabel.setBounds(0, 0, 300, 68);
	        add(logoLabel);
	        
	        JPanel reservationMainPanel = new JPanel();
	        reservationMainPanel.setBounds(0, 70, 1243, 739);
	        reservationMainPanel.setBackground(new Color(0, 128, 192));
	        add(reservationMainPanel);
	        reservationMainPanel.setLayout(null);
	        
	        JPanel guestDetailsPanel = new JPanel();
	        guestDetailsPanel.setBorder(new LineBorder(new Color(25, 25, 112), 2));
	        guestDetailsPanel.setBackground(new Color(240, 255, 255));
	        guestDetailsPanel.setBounds(22, 48, 550, 678);
	        reservationMainPanel.add(guestDetailsPanel);
	        guestDetailsPanel.setLayout(null);
	        
	        JLabel titleLabel = new JLabel("GUEST DETAILS");
	        titleLabel.setForeground(new Color(25, 25, 112));
	        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 20));
	        titleLabel.setBounds(23, 22, 227, 36);
	        guestDetailsPanel.add(titleLabel);
	        
	        firstNameField = new JTextField();
	        firstNameField.setFont(new Font("Tahoma", Font.PLAIN, 21));
	        firstNameField.setBounds(23, 102, 231, 36);
	        guestDetailsPanel.add(firstNameField);
	        firstNameField.setColumns(10);
	        
	        JLabel firstNameLabel = new JLabel("FIRST NAME:");
	        firstNameLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        firstNameLabel.setBounds(23, 79, 105, 20);
	        guestDetailsPanel.add(firstNameLabel);
	        
	        lastNameField = new JTextField();
	        lastNameField.setFont(new Font("Tahoma", Font.PLAIN, 21));
	        lastNameField.setColumns(10);
	        lastNameField.setBounds(294, 102, 231, 36);
	        guestDetailsPanel.add(lastNameField);
	        
	        JLabel lblNewLabel_2_1 = new JLabel("LAST NAME:");
	        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblNewLabel_2_1.setBounds(294, 79, 105, 20);
	        guestDetailsPanel.add(lblNewLabel_2_1);
	        
	        phoneNumberField = new JTextField();
	        phoneNumberField.setFont(new Font("Tahoma", Font.PLAIN, 21));
	        phoneNumberField.setColumns(10);
	        phoneNumberField.setBounds(23, 185, 231, 36);
	        guestDetailsPanel.add(phoneNumberField);
	        
	        JLabel lblNewLabel_2_2 = new JLabel("PHONE NO:");
	        lblNewLabel_2_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        lblNewLabel_2_2.setBounds(23, 165, 105, 20);
	        guestDetailsPanel.add(lblNewLabel_2_2);
	        
	        emailField = new JTextField();
	        emailField.setFont(new Font("Tahoma", Font.PLAIN, 21));
	        emailField.setColumns(10);
	        emailField.setBounds(23, 269, 502, 36);
	        guestDetailsPanel.add(emailField);
	        
	        addressField = new JTextField();
	        addressField.setFont(new Font("Tahoma", Font.PLAIN, 21));
	        addressField.setColumns(10);
	        addressField.setBounds(23, 355, 502, 36);
	        guestDetailsPanel.add(addressField);
	        
	        cityField = new JTextField();
	        cityField.setFont(new Font("Tahoma", Font.PLAIN, 21));
	        cityField.setColumns(10);
	        cityField.setBounds(23, 452, 502, 36);
	        guestDetailsPanel.add(cityField);
	        
	        JLabel addressLabel = new JLabel("ADDRESS:");
	        addressLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        addressLabel.setBounds(23, 337, 105, 20);
	        guestDetailsPanel.add(addressLabel);
	        
	        JLabel cityLabel = new JLabel("CITY:");
	        cityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        cityLabel.setBounds(23, 432, 105, 20);
	        guestDetailsPanel.add(cityLabel);
	        
	        JLabel emailLabel = new JLabel("EMAIL ADDRESS:");
	        emailLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        emailLabel.setBounds(23, 251, 171, 20);
	        guestDetailsPanel.add(emailLabel);
	        
	        nationalityField = new JTextField();
	        nationalityField.setFont(new Font("Tahoma", Font.PLAIN, 21));
	        nationalityField.setColumns(10);
	        nationalityField.setBounds(23, 537, 502, 36);
	        guestDetailsPanel.add(nationalityField);
	        
	        JLabel nationalityLabel = new JLabel("NATIONALITY:");
	        nationalityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        nationalityLabel.setBounds(23, 517, 117, 20);
	        guestDetailsPanel.add(nationalityLabel);
	        
	        JComboBox idcomboBox = new JComboBox();
	        idcomboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        idcomboBox.setModel(new DefaultComboBoxModel(new String[] {"PASSPORT", "NATIONAL ID", "PAG-IBIG ID", "BARANGAY ID"}));
	        idcomboBox.setBounds(23, 615, 171, 36);
	        guestDetailsPanel.add(idcomboBox);
	        
	        JLabel idNumLabel = new JLabel("ID NO.");
	        idNumLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        idNumLabel.setBounds(23, 595, 117, 20);
	        guestDetailsPanel.add(idNumLabel);
	        
	        JLabel lblNewLabel = new JLabel("CHECK IN GUEST");
	        lblNewLabel.setForeground(new Color(255, 255, 255));
	        lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
	        lblNewLabel.setBounds(12, 0, 238, 38);
	        reservationMainPanel.add(lblNewLabel);
	        
	        JPanel hotelRoomsPanel = new JPanel();
	        hotelRoomsPanel.setLayout(null);
	        hotelRoomsPanel.setBorder(new LineBorder(new Color(25, 25, 112), 2));
	        hotelRoomsPanel.setBackground(new Color(240, 255, 255));
	        hotelRoomsPanel.setBounds(616, 13, 572, 402);
	        reservationMainPanel.add(hotelRoomsPanel);
	        
	        JLabel hotelRoomsLabel = new JLabel("HOTEL ROOMS");
	        hotelRoomsLabel.setForeground(new Color(25, 25, 112));
	        hotelRoomsLabel.setFont(new Font("Verdana", Font.BOLD, 20));
	        hotelRoomsLabel.setBounds(22, 23, 227, 36);
	        hotelRoomsPanel.add(hotelRoomsLabel);
	        
	        JPanel pricePanel1 = new JPanel();
	        pricePanel1.setBackground(new Color(244, 164, 96));
	        pricePanel1.setBounds(158, 69, 147, 48);
	        hotelRoomsPanel.add(pricePanel1);
	        pricePanel1.setLayout(null);
	        
	        JLabel priceLabel1 = new JLabel("₱2,499/night");
	        priceLabel1.setForeground(Color.BLACK);
	        priceLabel1.setFont(new Font("Tahoma", Font.BOLD, 12));
	        priceLabel1.setBounds(10, 10, 118, 28);
	        pricePanel1.add(priceLabel1);
	        
	        JPanel roomPanel1 = new JPanel();
	        roomPanel1.setBackground(new Color(255, 235, 205));
	        roomPanel1.setBounds(22, 69, 138, 48);
	        hotelRoomsPanel.add(roomPanel1);
	        roomPanel1.setLayout(null);
	        
	        JLabel roomLabel1 = new JLabel("Seashell Suite");
	        roomLabel1.setForeground(new Color(65, 105, 225));
	        roomLabel1.setBounds(10, 10, 93, 14);
	        roomLabel1.setFont(new Font("Tahoma", Font.BOLD, 13));
	        roomPanel1.add(roomLabel1);
	        
	        JLabel roomDesc1 = new JLabel("Good for 1-2 adults");
	        roomDesc1.setForeground(new Color(0, 0, 0));
	        roomDesc1.setFont(new Font("Tahoma", Font.PLAIN, 11));
	        roomDesc1.setBounds(10, 24, 118, 14);
	        roomPanel1.add(roomDesc1);
	        
	        JSeparator separator = new JSeparator();
	        separator.setForeground(new Color(25, 25, 112));
	        separator.setBounds(0, 288, 572, 2);
	        hotelRoomsPanel.add(separator);
	        
	        JPanel pricePanel2 = new JPanel();
	        pricePanel2.setLayout(null);
	        pricePanel2.setBackground(new Color(244, 164, 96));
	        pricePanel2.setBounds(158, 139, 147, 48);
	        hotelRoomsPanel.add(pricePanel2);
	        
	        JLabel priceLabel2 = new JLabel("₱6,499/night");
	        priceLabel2.setForeground(Color.BLACK);
	        priceLabel2.setFont(new Font("Tahoma", Font.BOLD, 12));
	        priceLabel2.setBounds(10, 10, 118, 28);
	        pricePanel2.add(priceLabel2);
	        
	        JPanel roomPanel2 = new JPanel();
	        roomPanel2.setLayout(null);
	        roomPanel2.setBackground(new Color(255, 235, 205));
	        roomPanel2.setBounds(22, 139, 138, 48);
	        hotelRoomsPanel.add(roomPanel2);
	        
	        JLabel roomLabel2 = new JLabel("Family Cove Suite");
	        roomLabel2.setForeground(new Color(65, 105, 225));
	        roomLabel2.setFont(new Font("Tahoma", Font.BOLD, 13));
	        roomLabel2.setBounds(10, 11, 118, 14);
	        roomPanel2.add(roomLabel2);
	        
	        JLabel roomDesc2 = new JLabel("Good for family");
	        roomDesc2.setForeground(Color.BLACK);
	        roomDesc2.setFont(new Font("Tahoma", Font.PLAIN, 11));
	        roomDesc2.setBounds(10, 24, 118, 14);
	        roomPanel2.add(roomDesc2);
	        
	        JPanel pricePanel3 = new JPanel();
	        pricePanel3.setLayout(null);
	        pricePanel3.setBackground(new Color(244, 164, 96));
	        pricePanel3.setBounds(169, 211, 147, 48);
	        hotelRoomsPanel.add(pricePanel3);
	        
	        JLabel priceLabel3 = new JLabel("₱10,499/night");
	        priceLabel3.setForeground(Color.BLACK);
	        priceLabel3.setFont(new Font("Tahoma", Font.BOLD, 12));
	        priceLabel3.setBounds(10, 10, 118, 28);
	        pricePanel3.add(priceLabel3);
	        
	        JPanel roomPanel3 = new JPanel();
	        roomPanel3.setLayout(null);
	        roomPanel3.setBackground(new Color(255, 235, 205));
	        roomPanel3.setBounds(22, 211, 147, 48);
	        hotelRoomsPanel.add(roomPanel3);
	        
	        JLabel roomLabel3 = new JLabel("Grand Oceanview Suite");
	        roomLabel3.setForeground(new Color(65, 105, 225));
	        roomLabel3.setFont(new Font("Tahoma", Font.BOLD, 13));
	        roomLabel3.setBounds(10, 10, 118, 14);
	        roomPanel3.add(roomLabel3);
	        
	        JLabel roomDesc3 = new JLabel("Good for groups");
	        roomDesc3.setForeground(Color.BLACK);
	        roomDesc3.setFont(new Font("Tahoma", Font.PLAIN, 11));
	        roomDesc3.setBounds(10, 24, 118, 14);
	        roomPanel3.add(roomDesc3);
	        
	        JPanel selectroomPanel1 = new JPanel();
	        selectroomPanel1.setLayout(null);
	        selectroomPanel1.setBackground(new Color(0, 128, 192));
	        selectroomPanel1.setBounds(304, 69, 37, 48);
	        hotelRoomsPanel.add(selectroomPanel1);
	        
	        JRadioButton selectRoom1 = new JRadioButton("SeashellSuite");
	        selectRoom1.setActionCommand("Seashell Suite");
	        selectRoom1.setForeground(new Color(0, 128, 192));
	        selectRoom1.setBackground(new Color(0, 128, 192));
	        selectRoom1.setBounds(6, 15, 109, 23);
	        selectroomPanel1.add(selectRoom1);
	        
	        JPanel selectroomPanel2 = new JPanel();
	        selectroomPanel2.setLayout(null);
	        selectroomPanel2.setBackground(new Color(0, 128, 192));
	        selectroomPanel2.setBounds(304, 139, 37, 48);
	        hotelRoomsPanel.add(selectroomPanel2);
	        
	        JRadioButton selectRoom2 = new JRadioButton("FamilyCove");
	        selectRoom2.setActionCommand("FamilyCove");
	        selectRoom2.setForeground(new Color(0, 128, 192));
	        selectRoom2.setBackground(new Color(0, 128, 192));
	        selectRoom2.setBounds(10, 15, 109, 23);
	        selectroomPanel2.add(selectRoom2);
	        
	        JPanel selectroomPanel3 = new JPanel();
	        selectroomPanel3.setLayout(null);
	        selectroomPanel3.setBackground(new Color(0, 128, 192));
	        selectroomPanel3.setBounds(316, 211, 37, 48);
	        hotelRoomsPanel.add(selectroomPanel3);
	        
	        JRadioButton selectRoom3 = new JRadioButton("GrandOceanview");
	        selectRoom3.setActionCommand("GrandOceanview Suite");
	        selectRoom3.setForeground(new Color(0, 128, 192));
	        selectRoom3.setBackground(new Color(0, 128, 192));
	        selectRoom3.setBounds(10, 10, 109, 23);
	        selectroomPanel3.add(selectRoom3);
	        
	        ButtonGroup selectRoom = new ButtonGroup(); 
	        selectRoom.add(selectRoom1);
	        selectRoom.add(selectRoom2);
	        selectRoom.add(selectRoom3);
	        
	        JPanel lunchPanel = new JPanel();
	        lunchPanel.setLayout(null);
	        lunchPanel.setBackground(new Color(255, 235, 205));
	        lunchPanel.setBounds(408, 93, 138, 24);
	        hotelRoomsPanel.add(lunchPanel);
	        
	        JLabel lunchLabel = new JLabel("Lunch Buffet  599/pax");
	        lunchLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        lunchLabel.setBounds(10, 0, 134, 23);
	        lunchPanel.add(lunchLabel);
	        
	        JSpinner lunchField = new JSpinner();
	        lunchField.setBounds(368, 93, 37, 24);
	        hotelRoomsPanel.add(lunchField);
	        
	        
	        JSpinner dinnerField = new JSpinner();
	        dinnerField.setBounds(368, 128, 37, 24);
	        hotelRoomsPanel.add(dinnerField);
	        
	        JPanel dinnerPanel = new JPanel();
	        dinnerPanel.setLayout(null);
	        dinnerPanel.setBackground(new Color(255, 235, 205));
	        dinnerPanel.setBounds(408, 127, 139, 24);
	        hotelRoomsPanel.add(dinnerPanel);
	        
	        JLabel dinnerLabel = new JLabel("Dinner Buffet 599/pax");
	        dinnerLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
	        dinnerLabel.setBounds(10, 0, 134, 23);
	        dinnerPanel.add(dinnerLabel);
	        
	        JLabel otherServicesLabel = new JLabel("OTHER SERVICES");
	        otherServicesLabel.setForeground(new Color(25, 25, 112));
	        otherServicesLabel.setFont(new Font("Verdana", Font.BOLD, 14));
	        otherServicesLabel.setBounds(372, 70, 177, 24);
	        hotelRoomsPanel.add(otherServicesLabel);
	        
	        JButton btnNewButton_1 = new JButton("SEARCH");
	        btnNewButton_1.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	}
	        });
	        btnNewButton_1.setBackground(new Color(255, 255, 0));
	        btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 19));
	        btnNewButton_1.setBounds(404, 301, 142, 70);
	        hotelRoomsPanel.add(btnNewButton_1);
	        
	        JPanel panel = new JPanel();
	        panel.setBackground(new Color(255, 255, 255));
	        panel.setBounds(22, 301, 283, 36);
	        hotelRoomsPanel.add(panel);
	        panel.setLayout(null);
	        
	        textField = new JTextField();
	        textField.setBounds(115, 0, 168, 36);
	        panel.add(textField);
	        textField.setColumns(10);
	        
	        JLabel lblNewLabel_1 = new JLabel("Check In Date:");
	        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 10));
	        lblNewLabel_1.setBounds(0, 0, 118, 36);
	        panel.add(lblNewLabel_1);
	        
	        JPanel panel_1 = new JPanel();
	        panel_1.setLayout(null);
	        panel_1.setBackground(Color.WHITE);
	        panel_1.setBounds(22, 348, 283, 36);
	        hotelRoomsPanel.add(panel_1);
	        
	        textField_1 = new JTextField();
	        textField_1.setColumns(10);
	        textField_1.setBounds(115, 0, 168, 36);
	        panel_1.add(textField_1);
	        
	        JLabel lblNewLabel_1_1 = new JLabel("Check Out Date:");
	        lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 10));
	        lblNewLabel_1_1.setBounds(0, 0, 118, 36);
	        panel_1.add(lblNewLabel_1_1);
	        
	        JPanel panel_1_1_1 = new JPanel();
	        panel_1_1_1.setLayout(null);
	        panel_1_1_1.setBorder(new LineBorder(new Color(25, 25, 112), 2));
	        panel_1_1_1.setBackground(new Color(240, 255, 255));
	        panel_1_1_1.setBounds(616, 428, 572, 206);
	        reservationMainPanel.add(panel_1_1_1);
	        
	        JLabel lblNewLabel_1_1_1 = new JLabel("PAYMENT");
	        lblNewLabel_1_1_1.setForeground(new Color(25, 25, 112));
	        lblNewLabel_1_1_1.setFont(new Font("Verdana", Font.BOLD, 20));
	        lblNewLabel_1_1_1.setBounds(21, 20, 227, 36);
	        panel_1_1_1.add(lblNewLabel_1_1_1);
	        
	        JRadioButton cashPay = new JRadioButton("CASH");
	        cashPay.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        cashPay.setBackground(new Color(240, 255, 255));
	        cashPay.setBounds(31, 62, 103, 21);
	        panel_1_1_1.add(cashPay);
	        
	        JRadioButton creditCard = new JRadioButton("CREDIT CARD");
	        creditCard.setFont(new Font("Tahoma", Font.PLAIN, 16));
	        creditCard.setBackground(new Color(240, 255, 255));
	        creditCard.setBounds(31, 92, 147, 21);
	        panel_1_1_1.add(creditCard);
	        
	        ButtonGroup paymentMethod = new ButtonGroup();
	        paymentMethod.add(cashPay);
	        paymentMethod.add(creditCard);
	        
	        Panel panel_2 = new Panel();
	        panel_2.setBackground(new Color(244, 164, 96));
	        panel_2.setBounds(21, 149, 138, 36);
	        panel_1_1_1.add(panel_2);
	        panel_2.setLayout(null);
	        
	        JLabel lblNewLabel_4 = new JLabel("CREDIT CARD NO.");
	        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        lblNewLabel_4.setBounds(10, 0, 128, 36);
	        panel_2.add(lblNewLabel_4);
	        
	        textField_8 = new JTextField();
	        textField_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        textField_8.setBounds(165, 149, 193, 36);
	        panel_1_1_1.add(textField_8);
	        textField_8.setColumns(10);
	        
	        Panel panel_2_1 = new Panel();
	        panel_2_1.setLayout(null);
	        panel_2_1.setBackground(new Color(244, 164, 96));
	        panel_2_1.setBounds(300, 30, 249, 53);
	        panel_1_1_1.add(panel_2_1);
	
	        // Label for TOTAL
	        JLabel lblNewLabel_5 = new JLabel("TOTAL:");
	        lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
	        lblNewLabel_5.setBounds(10, 10, 91, 33);
	        panel_2_1.add(lblNewLabel_5);
	        
	        totalField = new JTextField();
	        totalField.setEditable(false);
	        totalField.setBounds(82, 10, 157, 29);
	        panel_2_1.add(totalField);
	        totalField.setColumns(10);
	
	        
	        textField_10 = new JTextField();
	        textField_10.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        textField_10.setColumns(10);
	        textField_10.setBounds(432, 149, 117, 36);
	        panel_1_1_1.add(textField_10);
	        
	        Panel panel_2_2 = new Panel();
	        panel_2_2.setLayout(null);
	        panel_2_2.setBackground(new Color(244, 164, 96)); 	
	        panel_2_2.setBounds(364, 149, 74, 36);
	        panel_1_1_1.add(panel_2_2);
	        
	        JLabel lblNewLabel_4_1 = new JLabel("CVC");
	        lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
	        lblNewLabel_4_1.setBounds(20, 0, 33, 36);
	        panel_2_2.add(lblNewLabel_4_1);
	        
	        JButton calculateBtn = new JButton("Calculate");
	        calculateBtn.addActionListener(new ActionListener() {
	        	public void actionPerformed(ActionEvent e) {
	        	}
	        });
	        calculateBtn.setForeground(new Color(25, 25, 112));
	        calculateBtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
	        calculateBtn.setBackground(new Color(244, 164, 96));
	        calculateBtn.setBounds(310, 92, 218, 36);
	        panel_1_1_1.add(calculateBtn);
	        calculateBtn.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	 Connection conn = null;
	                 try {
	                     // Database connection details
	                     String dbURL = "jdbc:mysql://localhost:3306/hotel";
	                     String dbUser = "root";
	                     String dbPassword = "AandromedaNnebula11";

	                     conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);

	                     // Parse dates from input
	                     SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yy");
	                     java.util.Date utilCheckInDate = inputFormat.parse(textField.getText().trim());
	                     java.util.Date utilCheckOutDate = inputFormat.parse(textField_1.getText().trim());
	                     java.sql.Date sqlCheckInDate = new java.sql.Date(utilCheckInDate.getTime());
	                     java.sql.Date sqlCheckOutDate = new java.sql.Date(utilCheckOutDate.getTime());

	                     // Retrieve and trim other input values
	                     String firstName = firstNameField.getText().trim();
	                     String lastName = lastNameField.getText().trim();
	                     String phoneNumber = phoneNumberField.getText().trim();
	                     String email = emailField.getText().trim();
	                     String address = addressField.getText().trim();
	                     String city = cityField.getText().trim();
	                     String nationality = nationalityField.getText().trim();
	                     String idNumber = ((JTextField) idcomboBox.getEditor().getEditorComponent()).getText().trim();
	                     String hotelRoom = selectRoom.getSelection().getActionCommand();
	                     String paymentMethod = cashPay.isSelected() ? "Cash" : "Credit Card";
	                     String creditCardNumber = textField_8.getText().trim();
	                     String cvv = textField_10.getText().trim();

	                     int lunchPax = (int) lunchField.getValue();
	                     int dinnerPax = (int) dinnerField.getValue();

	                     // Calculate room charges
	                     double roomCharge = computeRoomCharge(hotelRoom, sqlCheckInDate, sqlCheckOutDate);

	                     // Calculate additional services
	                     double additionalServiceCost = computeAdditionalServices(lunchPax, dinnerPax);

	                     // Calculate total cost
	                     double totalAmount = roomCharge + additionalServiceCost;
	                     String input = new StringBuilder().append(totalAmount).toString();
	 	                 totalField.setText(input);
	                    

	                    
	                 } catch (Exception ex) {
	                     ex.printStackTrace();
	                     JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                 } finally {
	                     // Close the connection
	                     if (conn != null) {
	                         try {
	                             conn.close();
	                         } catch (Exception ex) {
	                             ex.printStackTrace();
	                         }
	                     }
	                 }
	                 
	                
	            }
	        });
	        
	        
	     // Create the BACK button
	        JButton btnNewButton = new JButton("BACK");
	        btnNewButton.setBackground(new Color(25, 25, 112));
	        btnNewButton.setForeground(new Color(255, 255, 255));
	        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
	        btnNewButton.setBounds(616, 647, 286, 66);
	        reservationMainPanel.add(btnNewButton);
	
	        btnNewButton.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                JFrame parentFrame = (JFrame) SwingUtilities.getWindowAncestor(btnNewButton);
	                
	                DashboardUser dashboardUserPanel = new DashboardUser("Employee"); 
	                
	                parentFrame.getContentPane().removeAll(); 
	                parentFrame.getContentPane().add(dashboardUserPanel);
	                
	                parentFrame.revalidate();
	                parentFrame.repaint();
	            }
	        });
	
	        btnNewButton.setBackground(new Color(25, 25, 112));
	        btnNewButton.setForeground(new Color(255, 255, 255));
	        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 25));
	        btnNewButton.setBounds(616, 647, 286, 66);
	        reservationMainPanel.add(btnNewButton);
	        
	        JButton btnDone = new JButton("DONE");
	        btnDone.setForeground(new Color(25, 25, 112));
	        btnDone.setFont(new Font("Tahoma", Font.PLAIN, 25));
	        btnDone.setBackground(new Color(244, 164, 96));
	        btnDone.setBounds(914, 647, 273, 66);
	        reservationMainPanel.add(btnDone);
	        btnDone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Connection conn = null;
                try {
                    // Database connection details
                    String dbURL = "jdbc:mysql://localhost:3306/hotel";
                    String dbUser = "root";
                    String dbPassword = "AandromedaNnebula11";

                    conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);

                    // Parse dates from input
                    SimpleDateFormat inputFormat = new SimpleDateFormat("MM/dd/yy");
                    java.util.Date utilCheckInDate = inputFormat.parse(textField.getText().trim());
                    java.util.Date utilCheckOutDate = inputFormat.parse(textField_1.getText().trim());
                    java.sql.Date sqlCheckInDate = new java.sql.Date(utilCheckInDate.getTime());
                    java.sql.Date sqlCheckOutDate = new java.sql.Date(utilCheckOutDate.getTime());

                    // Retrieve and trim other input values
                    String firstName = firstNameField.getText().trim();
                    String lastName = lastNameField.getText().trim();
                    String phoneNumber = phoneNumberField.getText().trim();
                    String email = emailField.getText().trim();
                    String address = addressField.getText().trim();
                    String city = cityField.getText().trim();
                    String nationality = nationalityField.getText().trim();
                    String idNumber = ((JTextField) idcomboBox.getEditor().getEditorComponent()).getText().trim();
                    String hotelRoom = selectRoom.getSelection().getActionCommand();
                    String paymentMethod = cashPay.isSelected() ? "Cash" : "Credit Card";
                    String creditCardNumber = textField_8.getText().trim();
                    String cvv = textField_10.getText().trim();

                    int lunchPax = (int) lunchField.getValue();
                    int dinnerPax = (int) dinnerField.getValue();

                    // Calculate room charges
                    double roomCharge = computeRoomCharge(hotelRoom, sqlCheckInDate, sqlCheckOutDate);

                    // Calculate additional services
                    double additionalServiceCost = computeAdditionalServices(lunchPax, dinnerPax);

                    // Calculate total cost
                    double totalAmount = roomCharge + additionalServiceCost;

                    // Database insertion query
                    
                    String insertGuestQuery = "INSERT INTO Guests (FirstName, LastName, PhoneNumber, EmailAddress, Address, City, Nationality, IDNumber, HotelRoom, CheckInDate, CheckOutDate, PaymentMethod, CreditCardNumber, CVV, TotalAmount, LunchPax, DinnerPax, AdditionalServiceCost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                    try (PreparedStatement pstmt = conn.prepareStatement(insertGuestQuery)) {

                        // Set parameters for the query
                        pstmt.setString(1, firstName);
                        pstmt.setString(2, lastName);
                        pstmt.setString(3, phoneNumber);
                        pstmt.setString(4, email);
                        pstmt.setString(5, address);
                        pstmt.setString(6, city);
                        pstmt.setString(7, nationality);
                        pstmt.setString(8, idNumber);
                        pstmt.setString(9, hotelRoom);
                        pstmt.setDate(10, sqlCheckInDate);
                        pstmt.setDate(11, sqlCheckOutDate);
                        pstmt.setString(12, paymentMethod);
                        pstmt.setString(13, paymentMethod.equals("Credit Card") ? creditCardNumber : null);
                        pstmt.setString(14, paymentMethod.equals("Credit Card") ? cvv : null);
                        pstmt.setDouble(15, totalAmount);
                        pstmt.setInt(16, lunchPax);
                        pstmt.setInt(17, dinnerPax);
                        pstmt.setDouble(18, additionalServiceCost);

                        // Execute update
                        pstmt.executeUpdate();
                    }

                    // Success message
                    JOptionPane.showMessageDialog(null, "Booking and additional services saved successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                } finally {
                    // Close the connection
                    if (conn != null) {
                        try {
                            conn.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
    }
        private static double computeRoomCharge(String roomType, Date checkIn, Date checkOut) {
            long diffInMillies = Math.abs(checkOut.getTime() - checkIn.getTime());
            long days = diffInMillies / (1000 * 60 * 60 * 24);

            double ratePerDay;
            switch (roomType) {
                case "Seashell Suite":
                    ratePerDay = 2499.00;
                    break;
                case "Family Cove Suite":
                    ratePerDay = 6499.00;
                    break;
                case "Grand Oceanview Suite":
                    ratePerDay = 10499.00;
                    break;
                default:
                    ratePerDay = 0.00;
            }

            return days * ratePerDay;
        }

        private static double computeAdditionalServices(int lunchPax, int dinnerPax) {
            double mealRate = 599.00;
            return (lunchPax + dinnerPax) * mealRate;
        }



    public static void main(String[] args) {
        JFrame frame = new JFrame("DasboardUser");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1219, 840);

        CheckIn dashboardPanel = new CheckIn();
        frame.getContentPane().add(dashboardPanel);

        frame.setVisible(true);
    }
        }
