package manager;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet; // Added for ResultSet
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import com.toedter.calendar.JDateChooser;

public class CheckIn extends JPanel {

	private ImageIcon loadImage(String path) {
		java.net.URL imgURL = getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Error: Couldn't find file: " + path);
			return new ImageIcon(); // Return an empty icon or handle as error
		}
	}

	private static final long serialVersionUID = 1L;
	private JTextField firstNameField;
	private JTextField lastNameField;
	private JTextField phoneNumberField;
	private JTextField emailField;
	private JTextField addressField;
	private JTextField cityField;
	private JTextField nationalityField;
	private JTextField textField_8; // Likely for Credit Card Number
	private JTextField textField_10; // Likely for CVV
	// private JTextField textField; // Unused in provided snippet
	// private JTextField textField_1; // Unused in provided snippet
	// private JButton backButton; // Declared but not initialized in snippet, handled by btnNewButton
	// private JTextField paxCountField = new JTextField(); // Declared but not used in snippet
	// private JLabel totalLabel; // Declared but not used in snippet
	private JTextField totalField;
	private JDateChooser checkInDateChooser;
	private JDateChooser checkOutDateChooser;
	private ButtonGroup selectRoom;
	private JRadioButton cashPay;
	private JRadioButton creditCard;
	private JSpinner lunchField;
	private JSpinner dinnerField;
	@SuppressWarnings("rawtypes")
	private JComboBox idcomboBox;


	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CheckIn() {

		setBackground(new Color(255, 255, 255));
		setBounds(100, 100, 1219, 800);

		ImageIcon logoIcon = loadImage("/ImageIcon/navbar.png"); // Use loadImage method

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

		idcomboBox = new JComboBox();
		idcomboBox.setEditable(true); // Allow text input for ID number
		idcomboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		idcomboBox.setModel(new DefaultComboBoxModel(new String[] {"PASSPORT", "NATIONAL ID", "PAG-IBIG ID", "BARANGAY ID"}));
		idcomboBox.setBounds(23, 615, 171, 36);
		guestDetailsPanel.add(idcomboBox);

		JLabel idNumLabel = new JLabel("ID NO.");
		idNumLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		idNumLabel.setBounds(23, 595, 117, 20); // Adjusted label position for ID Type
		guestDetailsPanel.add(idNumLabel);

		// You might need a separate JTextField for the ID number itself if JComboBox is only for type
		// For this example, we'll assume the ID number is typed into the editable JComboBox.

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
		roomLabel1.setBounds(10, 10, 118, 14); // Adjusted width
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
		roomLabel2.setBounds(10, 11, 128, 14); // Adjusted width
		roomPanel2.add(roomLabel2);

		JLabel roomDesc2 = new JLabel("Good for family");
		roomDesc2.setForeground(Color.BLACK);
		roomDesc2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		roomDesc2.setBounds(10, 24, 118, 14);
		roomPanel2.add(roomDesc2);

		JPanel pricePanel3 = new JPanel();
		pricePanel3.setLayout(null);
		pricePanel3.setBackground(new Color(244, 164, 96));
		pricePanel3.setBounds(169, 211, 147, 48); // x-coordinate seems off, should be similar to others
		hotelRoomsPanel.add(pricePanel3);

		JLabel priceLabel3 = new JLabel("₱10,499/night");
		priceLabel3.setForeground(Color.BLACK);
		priceLabel3.setFont(new Font("Tahoma", Font.BOLD, 12));
		priceLabel3.setBounds(10, 10, 118, 28);
		pricePanel3.add(priceLabel3);

		JPanel roomPanel3 = new JPanel();
		roomPanel3.setLayout(null);
		roomPanel3.setBackground(new Color(255, 235, 205));
		roomPanel3.setBounds(22, 211, 147, 48); // Adjusted width
		hotelRoomsPanel.add(roomPanel3);

		// Corrected label for the room name
		JLabel roomLabel3 = new JLabel("Grand Ocean Suite");
		roomLabel3.setForeground(new Color(65, 105, 225));
		roomLabel3.setFont(new Font("Tahoma", Font.BOLD, 13));
		roomLabel3.setBounds(10, 10, 137, 14); // Adjusted width
		roomPanel3.add(roomLabel3);

		JLabel roomDesc3 = new JLabel("Good for groups");
		roomDesc3.setForeground(Color.BLACK);
		roomDesc3.setFont(new Font("Tahoma", Font.PLAIN, 11));
		roomDesc3.setBounds(10, 24, 118, 14); // Adjusted y
		roomPanel3.add(roomDesc3);


		selectRoom = new ButtonGroup();

		JRadioButton selectRoom1Radio = new JRadioButton("");
		selectRoom1Radio.setActionCommand("Seashell Suite");
		selectRoom1Radio.setBackground(new Color(0, 128, 192)); // Match panel color
		selectRoom1Radio.setBounds(304, 69, 37, 48);
		hotelRoomsPanel.add(selectRoom1Radio);
		selectRoom.add(selectRoom1Radio);

		JRadioButton selectRoom2Radio = new JRadioButton("");
		selectRoom2Radio.setActionCommand("Family Cove Suite");
		selectRoom2Radio.setBackground(new Color(0, 128, 192)); // Match panel color
		selectRoom2Radio.setBounds(304, 139, 37, 48);
		hotelRoomsPanel.add(selectRoom2Radio);
		selectRoom.add(selectRoom2Radio);

		JRadioButton selectRoom3Radio = new JRadioButton("");
		// Corrected action command to match database RoomName
		selectRoom3Radio.setActionCommand("Grand Ocean Suite");
		selectRoom3Radio.setBackground(new Color(0, 128, 192)); // Match panel color
		selectRoom3Radio.setBounds(316, 211, 37, 48); // Original x-coord was a bit different
		hotelRoomsPanel.add(selectRoom3Radio);
		selectRoom.add(selectRoom3Radio);


		JPanel lunchPanel = new JPanel();
		lunchPanel.setLayout(null);
		lunchPanel.setBackground(new Color(255, 235, 205));
		lunchPanel.setBounds(408, 93, 138, 24);
		hotelRoomsPanel.add(lunchPanel);

		JLabel lunchLabel = new JLabel("Lunch Buffet  599/pax");
		lunchLabel.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lunchLabel.setBounds(10, 0, 134, 23);
		lunchPanel.add(lunchLabel);

		lunchField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1)); // Min 0, Max 100, Step 1
		lunchField.setBounds(368, 93, 37, 24);
		hotelRoomsPanel.add(lunchField);

		dinnerField = new JSpinner(new SpinnerNumberModel(0, 0, 100, 1)); // Min 0, Max 100, Step 1
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

		JButton btnNewButton_1 = new JButton("SEARCH"); // This button's action wasn't defined
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO: Implement search functionality if needed
				JOptionPane.showMessageDialog(null, "Search button clicked - functionality to be implemented.");
			}
		});
		btnNewButton_1.setBackground(new Color(255, 255, 0));
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 19));
		btnNewButton_1.setBounds(404, 301, 142, 70);
		hotelRoomsPanel.add(btnNewButton_1);

		JPanel checkInPanel = new JPanel();
		checkInPanel.setLayout(null);
		checkInPanel.setBackground(Color.WHITE);
		checkInPanel.setBounds(22, 301, 283, 36);
		hotelRoomsPanel.add(checkInPanel);

		JLabel checkInLabel = new JLabel("Check In Date:");
		checkInLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		checkInLabel.setBounds(0, 0, 118, 36);
		checkInPanel.add(checkInLabel);

		checkInDateChooser = new JDateChooser();
		checkInDateChooser.setDateFormatString("yyyy-MM-dd"); // Standard format
		checkInDateChooser.setBounds(115, 0, 168, 36);
		checkInPanel.add(checkInDateChooser);

		JPanel checkOutPanel = new JPanel();
		checkOutPanel.setLayout(null);
		checkOutPanel.setBackground(Color.WHITE);
		checkOutPanel.setBounds(22, 348, 283, 36);
		hotelRoomsPanel.add(checkOutPanel);

		JLabel checkOutLabel = new JLabel("Check Out Date:");
		checkOutLabel.setFont(new Font("Tahoma", Font.BOLD, 10));
		checkOutLabel.setBounds(0, 0, 118, 36);
		checkOutPanel.add(checkOutLabel);

		checkOutDateChooser = new JDateChooser();
		checkOutDateChooser.setDateFormatString("yyyy-MM-dd"); // Standard format
		checkOutDateChooser.setBounds(115, 0, 168, 36);
		checkOutPanel.add(checkOutDateChooser);

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

		cashPay = new JRadioButton("CASH");
		cashPay.setFont(new Font("Tahoma", Font.PLAIN, 16));
		cashPay.setBackground(new Color(240, 255, 255));
		cashPay.setBounds(31, 62, 103, 21);
		panel_1_1_1.add(cashPay);

		creditCard = new JRadioButton("CREDIT CARD");
		creditCard.setFont(new Font("Tahoma", Font.PLAIN, 16));
		creditCard.setBackground(new Color(240, 255, 255));
		creditCard.setBounds(31, 92, 147, 21);
		panel_1_1_1.add(creditCard);

		ButtonGroup paymentMethod = new ButtonGroup();
		paymentMethod.add(cashPay);
		paymentMethod.add(creditCard);
		cashPay.setSelected(true); // Default selection

		Panel panel_2 = new Panel();
		panel_2.setBackground(new Color(244, 164, 96));
		panel_2.setBounds(21, 149, 138, 36);
		panel_1_1_1.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("CREDIT CARD NO.");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(10, 0, 128, 36);
		panel_2.add(lblNewLabel_4);

		textField_8 = new JTextField(); // Credit Card Number
		textField_8.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textField_8.setBounds(165, 149, 193, 36);
		panel_1_1_1.add(textField_8);
		textField_8.setColumns(10);

		Panel panel_2_1 = new Panel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(244, 164, 96));
		panel_2_1.setBounds(300, 30, 249, 53);
		panel_1_1_1.add(panel_2_1);

		JLabel lblNewLabel_5 = new JLabel("TOTAL:");
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5.setBounds(10, 10, 91, 33);
		panel_2_1.add(lblNewLabel_5);

		totalField = new JTextField();
		totalField.setEditable(false);
		totalField.setFont(new Font("Tahoma", Font.BOLD, 16));
		totalField.setBounds(82, 10, 157, 33); // Adjusted height
		panel_2_1.add(totalField);
		totalField.setColumns(10);

		textField_10 = new JTextField(); // CVV
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
		calculateBtn.setForeground(new Color(25, 25, 112));
		calculateBtn.setFont(new Font("Tahoma", Font.PLAIN, 25));
		calculateBtn.setBackground(new Color(244, 164, 96));
		calculateBtn.setBounds(310, 92, 218, 36);
		panel_1_1_1.add(calculateBtn);
		calculateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (checkInDateChooser.getDate() == null || checkOutDateChooser.getDate() == null) {
						JOptionPane.showMessageDialog(null, "Please select check-in and check-out dates.", "Input Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					if (selectRoom.getSelection() == null) {
						JOptionPane.showMessageDialog(null, "Please select a room type.", "Input Error", JOptionPane.ERROR_MESSAGE);
						return;
					}


					java.util.Date utilCheckInDate = checkInDateChooser.getDate();
					java.util.Date utilCheckOutDate = checkOutDateChooser.getDate();

					if (utilCheckOutDate.before(utilCheckInDate) || utilCheckOutDate.equals(utilCheckInDate)) {
						JOptionPane.showMessageDialog(null, "Check-out date must be after check-in date.", "Date Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					java.sql.Date sqlCheckInDate = new java.sql.Date(utilCheckInDate.getTime());
					java.sql.Date sqlCheckOutDate = new java.sql.Date(utilCheckOutDate.getTime());

					String hotelRoom = selectRoom.getSelection().getActionCommand();
					int lunchPax = (int) lunchField.getValue();
					int dinnerPax = (int) dinnerField.getValue();

					double roomCharge = computeRoomCharge(hotelRoom, sqlCheckInDate, sqlCheckOutDate);
					double additionalServiceCost = computeAdditionalServices(lunchPax, dinnerPax);
					double totalAmount = roomCharge + additionalServiceCost;

					totalField.setText(String.format("%.2f", totalAmount));

				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error calculating total: " + ex.getMessage(), "Calculation Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

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
				// Assuming DashboardUser takes a role or similar parameter. Adjust if needed.
				DashboardUser dashboardUserPanel = new DashboardUser("Employee");

				parentFrame.getContentPane().removeAll();
				parentFrame.getContentPane().add(dashboardUserPanel);
				parentFrame.revalidate();
				parentFrame.repaint();
			}
		});

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
					// Validate inputs before proceeding
					if (firstNameField.getText().trim().isEmpty() ||
							lastNameField.getText().trim().isEmpty() ||
							phoneNumberField.getText().trim().isEmpty() ||
							emailField.getText().trim().isEmpty() ||
							addressField.getText().trim().isEmpty() ||
							cityField.getText().trim().isEmpty() ||
							nationalityField.getText().trim().isEmpty() ||
							((JTextField) idcomboBox.getEditor().getEditorComponent()).getText().trim().isEmpty() ||
							checkInDateChooser.getDate() == null ||
							checkOutDateChooser.getDate() == null ||
							selectRoom.getSelection() == null ||
							(!cashPay.isSelected() && !creditCard.isSelected()) ||
							(creditCard.isSelected() && (textField_8.getText().trim().isEmpty() || textField_10.getText().trim().isEmpty())) ||
							totalField.getText().trim().isEmpty() ) {
						JOptionPane.showMessageDialog(null, "Please fill in all required fields and calculate total.", "Input Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					java.util.Date utilCheckInDate = checkInDateChooser.getDate();
					java.util.Date utilCheckOutDate = checkOutDateChooser.getDate();

					if (utilCheckOutDate.before(utilCheckInDate) || utilCheckOutDate.equals(utilCheckInDate)) {
						JOptionPane.showMessageDialog(null, "Check-out date must be after check-in date.", "Date Error", JOptionPane.ERROR_MESSAGE);
						return;
					}


					String dbURL = "jdbc:mysql://127.0.0.1:3306/hotel";
					String dbUser = "root";
					String dbPassword = "11211810jr"; // Be cautious with hardcoded passwords

					conn = DriverManager.getConnection(dbURL, dbUser, dbPassword);
					conn.setAutoCommit(false); // Start transaction

					java.sql.Date sqlCheckInDate = new java.sql.Date(utilCheckInDate.getTime());
					java.sql.Date sqlCheckOutDate = new java.sql.Date(utilCheckOutDate.getTime());

					String firstName = firstNameField.getText().trim();
					String lastName = lastNameField.getText().trim();
					String phoneNumber = phoneNumberField.getText().trim();
					String email = emailField.getText().trim();
					String address = addressField.getText().trim();
					String city = cityField.getText().trim();
					String nationality = nationalityField.getText().trim();
					String idNumber = ((JTextField) idcomboBox.getEditor().getEditorComponent()).getText().trim();

					String hotelRoom = selectRoom.getSelection().getActionCommand();
					String paymentType = cashPay.isSelected() ? "Cash" : "Credit Card";
					String creditCardNumber = paymentType.equals("Credit Card") ? textField_8.getText().trim() : null;
					String cvv = paymentType.equals("Credit Card") ? textField_10.getText().trim() : null;

					int lunchPax = (int) lunchField.getValue();
					int dinnerPax = (int) dinnerField.getValue();

					double roomCharge = computeRoomCharge(hotelRoom, sqlCheckInDate, sqlCheckOutDate);
					double additionalServiceCost = computeAdditionalServices(lunchPax, dinnerPax);
					double totalAmount = Double.parseDouble(totalField.getText()); // Use calculated total

					// Insert into Guests table
					String insertGuestQuery = "INSERT INTO Guests (FirstName, LastName, PhoneNumber, EmailAddress, Address, City, Nationality, IDNumber, HotelRoom, CheckInDate, CheckOutDate, PaymentMethod, CreditCardNumber, CVV, TotalAmount, LunchPax, DinnerPax, AdditionalServiceCost) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
					try (PreparedStatement pstmtGuest = conn.prepareStatement(insertGuestQuery)) {
						pstmtGuest.setString(1, firstName);
						pstmtGuest.setString(2, lastName);
						pstmtGuest.setString(3, phoneNumber);
						pstmtGuest.setString(4, email);
						pstmtGuest.setString(5, address);
						pstmtGuest.setString(6, city);
						pstmtGuest.setString(7, nationality);// The line for idType is now gone
						pstmtGuest.setString(8, idNumber);    // Index changes from 9 to 8
						pstmtGuest.setString(9, hotelRoom);   // Index changes from 10 to 9
						pstmtGuest.setDate(10, sqlCheckInDate); // Index changes from 11 to 10
						pstmtGuest.setDate(11, sqlCheckOutDate);// Index changes from 12 to 11
						pstmtGuest.setString(12, paymentType); // Index changes from 13 to 12
						pstmtGuest.setString(13, creditCardNumber); // Index changes from 14 to 13
						pstmtGuest.setString(14, cvv);         // Index changes from 15 to 14
						pstmtGuest.setDouble(15, totalAmount); // Index changes from 16 to 15
						pstmtGuest.setInt(16, lunchPax);      // Index changes from 17 to 16
						pstmtGuest.setInt(17, dinnerPax);     // Index changes from 18 to 17
						pstmtGuest.setDouble(18, additionalServiceCost); // I
						pstmtGuest.executeUpdate();
					}

					// Update rooms table
					int roomTypeId = 0;
					String getRoomTypeIdQuery = "SELECT Id FROM room_types WHERE RoomName = ?";
					try (PreparedStatement pstmtRoomType = conn.prepareStatement(getRoomTypeIdQuery)) {
						pstmtRoomType.setString(1, hotelRoom);
						ResultSet rsRoomType = pstmtRoomType.executeQuery();
						if (rsRoomType.next()) {
							roomTypeId = rsRoomType.getInt("Id");
						} else {
							conn.rollback(); // Rollback if room type not found
							JOptionPane.showMessageDialog(null, "Error: Could not find Room Type ID for " + hotelRoom, "Database Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}

					// IMPORTANT: Logic to select a *specific* room ID to update
					// This current logic updates the first available room of the type.
					// You might need to first query for an available room ID of that type.
					int specificRoomIdToUpdate = -1;
					String findAvailableRoomQuery = "SELECT Id FROM rooms WHERE RoomTypeId = ? AND Availability = ? LIMIT 1";
					try (PreparedStatement pstmtFindRoom = conn.prepareStatement(findAvailableRoomQuery)) {
						pstmtFindRoom.setInt(1, roomTypeId);
						pstmtFindRoom.setBoolean(2, true); // true for available
						ResultSet rsAvailableRoom = pstmtFindRoom.executeQuery();
						if (rsAvailableRoom.next()) {
							specificRoomIdToUpdate = rsAvailableRoom.getInt("Id");
						} else {
							conn.rollback();
							JOptionPane.showMessageDialog(null, "No available room of type '" + hotelRoom + "' found.", "Booking Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}


					String updateRoomQuery = "UPDATE rooms SET CheckIn = ?, CheckOut = ?, Availability = ? WHERE Id = ?";
					try (PreparedStatement pstmtUpdateRoom = conn.prepareStatement(updateRoomQuery)) {
						pstmtUpdateRoom.setDate(1, sqlCheckInDate);
						pstmtUpdateRoom.setDate(2, sqlCheckOutDate);
						pstmtUpdateRoom.setBoolean(3, false); // false means Booked
						pstmtUpdateRoom.setInt(4, specificRoomIdToUpdate); // Update specific room by its ID

						int rowsAffected = pstmtUpdateRoom.executeUpdate();
						if (rowsAffected <= 0) {
							conn.rollback(); // Rollback if room update failed
							JOptionPane.showMessageDialog(null, "Error: Could not update room status. The room might have been booked by someone else.", "Database Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					}

					conn.commit(); // Commit transaction
					JOptionPane.showMessageDialog(null, "Booking successful and room status updated!", "Success", JOptionPane.INFORMATION_MESSAGE);

					// Optionally, clear fields or navigate away
					clearInputFields();


				} catch (SQLException exSQL) {
					if (conn != null) try { conn.rollback(); } catch (SQLException exRoll) { exRoll.printStackTrace(); }
					exSQL.printStackTrace();
					JOptionPane.showMessageDialog(null, "Database Error: " + exSQL.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
				} catch (NumberFormatException exNum) {
					if (conn != null) try { conn.rollback(); } catch (SQLException exRoll) { exRoll.printStackTrace(); }
					exNum.printStackTrace();
					JOptionPane.showMessageDialog(null, "Invalid number in total amount: " + exNum.getMessage(), "Input Error", JOptionPane.ERROR_MESSAGE);
				}
				catch (Exception ex) {
					if (conn != null) try { conn.rollback(); } catch (SQLException exRoll) { exRoll.printStackTrace(); }
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
				} finally {
					if (conn != null) {
						try {
							conn.setAutoCommit(true); // Reset auto-commit
							conn.close();
						} catch (SQLException exClose) {
							exClose.printStackTrace();
						}
					}
				}
			}
		});
	}

	private void clearInputFields() {
		firstNameField.setText("");
		lastNameField.setText("");
		phoneNumberField.setText("");
		emailField.setText("");
		addressField.setText("");
		cityField.setText("");
		nationalityField.setText("");
		((JTextField) idcomboBox.getEditor().getEditorComponent()).setText("");
		idcomboBox.setSelectedIndex(0);

		checkInDateChooser.setDate(null);
		checkOutDateChooser.setDate(null);

		selectRoom.clearSelection(); // Clear room selection

		lunchField.setValue(0);
		dinnerField.setValue(0);

		totalField.setText("");

		cashPay.setSelected(true); // Default payment method
		textField_8.setText(""); // Clear credit card number
		textField_10.setText(""); // Clear CVV
	}


	private static double computeRoomCharge(String roomType, Date checkIn, Date checkOut) {
		if (checkIn == null || checkOut == null || roomType == null) return 0.0;
		if (checkOut.before(checkIn) || checkOut.equals(checkIn)) return 0.0; // No charge if dates are invalid

		long diffInMillies = Math.abs(checkOut.getTime() - checkIn.getTime());
		long days = diffInMillies / (1000 * 60 * 60 * 24);
		if (days == 0) days = 1; // Minimum 1 day charge if check-in and check-out are on same day but different times logically

		double ratePerDay;
		switch (roomType) {
			case "Seashell Suite":
				ratePerDay = 2499.00;
				break;
			case "Family Cove Suite":
				ratePerDay = 6499.00;
				break;
			case "Grand Ocean Suite": // Corrected room name
				ratePerDay = 10499.00;
				break;
			default:
				ratePerDay = 0.00; // Should not happen if room selection is validated
		}
		return days * ratePerDay;
	}

	private static double computeAdditionalServices(int lunchPax, int dinnerPax) {
		double mealRate = 599.00;
		return (lunchPax + dinnerPax) * mealRate;
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Check-In Guest"); // Title of the frame
			frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			frame.setSize(1219, 840); // Match panel size + frame decorations
			frame.setLocationRelativeTo(null); // Center on screen
			frame.setResizable(false);
			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowClosing(WindowEvent e) {
					int confirm = JOptionPane.showConfirmDialog(
							frame,
							"Are you sure you want to exit? Any unsaved data will be lost.",
							"Exit Confirmation",
							JOptionPane.YES_NO_OPTION,
							JOptionPane.WARNING_MESSAGE);

					if (confirm == JOptionPane.YES_OPTION) {
						frame.dispose();
						// System.exit(0); // Or return to a login/main dashboard
					}
				}
			});

			CheckIn checkInPanel = new CheckIn();
			frame.getContentPane().add(checkInPanel);
			frame.setVisible(true);
		});
	}
}