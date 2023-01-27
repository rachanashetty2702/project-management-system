package jframe1;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;
import app.bolivia.swing.JCTextField;
import necesario.RSMaterialButtonCircle;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Homepage2 extends JFrame {

	private JPanel contentPane;
	JCTextField txt_usn = new JCTextField();
	JCTextField txt_fname = new JCTextField();
	JCTextField txt_mname = new JCTextField();
	JCTextField txt_lname = new JCTextField();
	JCTextField txt_bdate = new JCTextField();
	JCTextField txt_address = new JCTextField();
	JCTextField txt_gender = new JCTextField();
	JCTextField txt_email = new JCTextField();
	JComboBox comboBox = new JComboBox();
	Color mouseEnterColor= new Color(32,75,113);
	Color mouseExitColor= new Color(21,57,93);
	String usn,first_name,middle_name,last_name,address,gender,email,D_id;
	Date birth_date;
	
	
	public boolean addStudent() {
		boolean isAdded = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        usn = txt_usn.getText();
        first_name = txt_fname.getText();
        middle_name = txt_mname.getText();
        last_name = txt_lname.getText();
        try {
			birth_date = dateFormat.parse(txt_bdate.getText());
			} catch (ParseException e) {
			    JOptionPane.showMessageDialog(this, "Invalid date format. Please enter the date in yyyy-MM-dd format.");
			}
        address = txt_address.getText();
        gender = txt_gender.getText();
        email = txt_email.getText();
        D_id = comboBox.getSelectedItem().toString();
		long l3 = birth_date.getTime();
		
		java.sql.Date dbirthdate = new java.sql.Date(l3);
	
		try {
			Connection con = DBConnection.getConnection();
			String sql = "insert into project_ms.students values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,usn);
			pst.setString(2, first_name);
			pst.setString(3, middle_name);
			pst.setString(4, last_name);
			pst.setDate(5, dbirthdate);
			pst.setString(6, address);
			pst.setString(7, gender);
			pst.setString(8, email);
			pst.setString(9, D_id);
			
			int rowCount = pst.executeUpdate();
			if (rowCount >0) {
				isAdded = true;
			}else {
				isAdded = false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return isAdded;
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homepage2 frame = new Homepage2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Homepage2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1523, 823);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1509, 75);
		panel.setBackground(new Color(85, 150, 206));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\r\n");
		lblNewLabel.setBounds(21, 23, 58, 42);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\adminIcons\\adminIcons\\icons8_menu_48px_1.png"));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(74, 15, 4, 50);
		panel.add(panel_1);
		panel_1.setBackground(new Color(21, 57, 93));
		
		JLabel lblNewLabel_1 = new JLabel("PROJECT MANAGEMENT SYSTEM");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(89, 25, 424, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("WELCOME USER");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\adminIcons\\adminIcons\\male_user_50px.png"));
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(1181, 7, 241, 65);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("X");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_3.setBounds(1467, 12, 42, 48);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 29));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(21, 57, 93));
		panel_2.setBounds(0, 74, 340, 712);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.setBorder(new MatteBorder(0, 10, 0, 0, (Color) new Color(85, 150, 206)));
		panel_3_2.setLayout(null);
		panel_3_2.setBackground(new Color(32, 75, 113));
		panel_3_2.setBounds(0, 158, 340, 78);
		panel_2.add(panel_3_2);
		
		JLabel lblNewLabel_4_2 = new JLabel("   Student Details");
		lblNewLabel_4_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\reading-book.png"));
		lblNewLabel_4_2.setForeground(Color.WHITE);
		lblNewLabel_4_2.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_2.setBackground(SystemColor.menu);
		lblNewLabel_4_2.setBounds(41, 22, 194, 35);
		panel_3_2.add(lblNewLabel_4_2);
		
		JPanel panel_3_3 = new JPanel();
		panel_3_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Projectdetails project = new Projectdetails();
				project.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_3.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_3.setBackground(mouseExitColor);
			}
		});
		panel_3_3.setLayout(null);
		panel_3_3.setBackground(new Color(21, 57, 93));
		panel_3_3.setBounds(0, 235, 340, 82);
		panel_2.add(panel_3_3);
		
		JLabel lblNewLabel_4_3 = new JLabel("   Project Details");
		lblNewLabel_4_3.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\execute.png"));
		lblNewLabel_4_3.setForeground(Color.WHITE);
		lblNewLabel_4_3.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_3.setBackground(SystemColor.menu);
		lblNewLabel_4_3.setBounds(41, 25, 200, 35);
		panel_3_3.add(lblNewLabel_4_3);
		
		JPanel panel_3_4 = new JPanel();
		panel_3_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Project_Resources project = new Project_Resources();
				project.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_4.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_4.setBackground(mouseExitColor);
			}
		});
		panel_3_4.setLayout(null);
		panel_3_4.setBackground(new Color(21, 57, 93));
		panel_3_4.setBounds(0, 394, 340, 78);
		panel_2.add(panel_3_4);
		
		JLabel lblNewLabel_4_4 = new JLabel("   Project Resources");
		lblNewLabel_4_4.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\teamwork.png"));
		lblNewLabel_4_4.setForeground(Color.WHITE);
		lblNewLabel_4_4.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_4.setBackground(SystemColor.menu);
		lblNewLabel_4_4.setBounds(41, 22, 189, 35);
		panel_3_4.add(lblNewLabel_4_4);
		
		JPanel panel_3_9 = new JPanel();
		panel_3_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage project = new LoginPage();
				project.setVisible(true);
				dispose();
			}
		});
		panel_3_9.setLayout(null);
		panel_3_9.setBackground(new Color(85, 150, 206));
		panel_3_9.setBounds(0, 630, 340, 60);
		panel_2.add(panel_3_9);
		
		JLabel lblNewLabel_4_9 = new JLabel("   Logout");
		lblNewLabel_4_9.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\adminIcons\\adminIcons\\icons8_Exit_26px_2.png"));
		lblNewLabel_4_9.setForeground(Color.WHITE);
		lblNewLabel_4_9.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_9.setBackground(SystemColor.menu);
		lblNewLabel_4_9.setBounds(41, 10, 152, 35);
		panel_3_9.add(lblNewLabel_4_9);
		
		JPanel panel_3_4_1 = new JPanel();
		panel_3_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_4_1.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_4_1.setBackground(mouseExitColor);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				List_of_previous_project project = new List_of_previous_project();
				project.setVisible(true);
				dispose();
			}
		});
		panel_3_4_1.setLayout(null);
		panel_3_4_1.setBackground(new Color(21, 57, 93));
		panel_3_4_1.setBounds(0, 553, 340, 78);
		panel_2.add(panel_3_4_1);
		
		JLabel lblNewLabel_4_4_1 = new JLabel(" List of Previous Projects");
		lblNewLabel_4_4_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\execute.png"));
		lblNewLabel_4_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_4_1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_4_1.setBackground(SystemColor.menu);
		lblNewLabel_4_4_1.setBounds(41, 22, 243, 35);
		panel_3_4_1.add(lblNewLabel_4_4_1);
		
		JPanel panel_3_3_1 = new JPanel();
		panel_3_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_3_1.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_3_1.setBackground(mouseExitColor);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Student_project project = new Student_project();
				project.setVisible(true);
				dispose();
				
			}
		});
		panel_3_3_1.setLayout(null);
		panel_3_3_1.setBackground(new Color(21, 57, 93));
		panel_3_3_1.setBounds(0, 473, 340, 82);
		panel_2.add(panel_3_3_1);
		
		JLabel lblNewLabel_4_3_1 = new JLabel("   Student-Project Details");
		lblNewLabel_4_3_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\execute.png"));
		lblNewLabel_4_3_1.setForeground(Color.WHITE);
		lblNewLabel_4_3_1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_3_1.setBackground(SystemColor.menu);
		lblNewLabel_4_3_1.setBounds(41, 25, 242, 35);
		panel_3_3_1.add(lblNewLabel_4_3_1);
		
		JPanel panel_3_4_2 = new JPanel();
		panel_3_4_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Contactdetails project = new Contactdetails();
				project.setVisible(true);
				dispose();
			}
		});
		panel_3_4_2.setLayout(null);
		panel_3_4_2.setBackground(new Color(21, 57, 93));
		panel_3_4_2.setBounds(0, 313, 340, 78);
		panel_2.add(panel_3_4_2);
		
		JLabel lblNewLabel_4_4_2 = new JLabel("   Contact Details");
		lblNewLabel_4_4_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\notebook-of-contacts.png"));
		lblNewLabel_4_4_2.setForeground(Color.WHITE);
		lblNewLabel_4_4_2.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_4_2.setBackground(SystemColor.menu);
		lblNewLabel_4_4_2.setBounds(41, 22, 189, 35);
		panel_3_4_2.add(lblNewLabel_4_4_2);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(21, 57, 93)));
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBounds(338, 74, 1171, 712);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setForeground(new Color(85, 150, 206));
		panel_3.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(85, 150, 206)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(335, 10, 566, 100);
		panel_4.add(panel_3);
		
		JLabel lblNewLabel_1_1 = new JLabel(" Student Details");
		lblNewLabel_1_1.setBounds(102, 0, 400, 93);
		panel_3.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\student1.png"));
		lblNewLabel_1_1.setForeground(new Color(85, 150, 206));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		
		txt_usn.setSelectionColor(Color.WHITE);
		txt_usn.setSelectedTextColor(Color.WHITE);
		txt_usn.setPlaceholder("Enter USN");
		txt_usn.setPhColor(Color.BLACK);
		txt_usn.setForeground(Color.BLACK);
		txt_usn.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txt_usn.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(32, 75, 113)));
		txt_usn.setBackground(Color.WHITE);
		txt_usn.setBounds(281, 167, 312, 49);
		panel_4.add(txt_usn);
		
		JLabel lblEnterUsn = new JLabel("Enter USN :");
		lblEnterUsn.setForeground(new Color(85, 150, 206));
		lblEnterUsn.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEnterUsn.setBounds(46, 157, 579, 90);
		panel_4.add(lblEnterUsn);
		
		JLabel lblEnterUsn_1 = new JLabel("Enter First Name :");
		lblEnterUsn_1.setForeground(new Color(85, 150, 206));
		lblEnterUsn_1.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEnterUsn_1.setBounds(46, 262, 448, 90);
		panel_4.add(lblEnterUsn_1);
		
		
		txt_fname.setSelectionColor(Color.WHITE);
		txt_fname.setSelectedTextColor(Color.WHITE);
		txt_fname.setPlaceholder("Enter First Name");
		txt_fname.setPhColor(Color.BLACK);
		txt_fname.setForeground(Color.BLACK);
		txt_fname.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txt_fname.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(32, 75, 113)));
		txt_fname.setBackground(Color.WHITE);
		txt_fname.setBounds(281, 272, 312, 49);
		panel_4.add(txt_fname);
		
		JLabel lblEnterUsn_2 = new JLabel("Enter Middle Name :");
		lblEnterUsn_2.setForeground(new Color(85, 150, 206));
		lblEnterUsn_2.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEnterUsn_2.setBounds(46, 362, 401, 90);
		panel_4.add(lblEnterUsn_2);
		
		
		txt_mname.setSelectionColor(Color.WHITE);
		txt_mname.setSelectedTextColor(Color.WHITE);
		txt_mname.setPlaceholder("Enter Middle Name ");
		txt_mname.setPhColor(Color.BLACK);
		txt_mname.setForeground(Color.BLACK);
		txt_mname.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txt_mname.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(32, 75, 113)));
		txt_mname.setBackground(Color.WHITE);
		txt_mname.setBounds(281, 372, 312, 49);
		panel_4.add(txt_mname);
		
		JLabel lblEnterUsn_3 = new JLabel("Enter Last Name :");
		lblEnterUsn_3.setForeground(new Color(85, 150, 206));
		lblEnterUsn_3.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEnterUsn_3.setBounds(46, 466, 348, 90);
		panel_4.add(lblEnterUsn_3);
		
		
		txt_lname.setSelectionColor(Color.WHITE);
		txt_lname.setSelectedTextColor(Color.WHITE);
		txt_lname.setPlaceholder("Enter Last Name");
		txt_lname.setPhColor(Color.BLACK);
		txt_lname.setForeground(Color.BLACK);
		txt_lname.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txt_lname.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(32, 75, 113)));
		txt_lname.setBackground(Color.WHITE);
		txt_lname.setBounds(281, 476, 312, 49);
		panel_4.add(txt_lname);
		
		
		txt_bdate.setSelectionColor(Color.WHITE);
		txt_bdate.setSelectedTextColor(Color.WHITE);
		txt_bdate.setPlaceholder("Enter Birth Date");
		txt_bdate.setPhColor(Color.BLACK);
		txt_bdate.setForeground(Color.BLACK);
		txt_bdate.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txt_bdate.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(32, 75, 113)));
		txt_bdate.setBackground(Color.WHITE);
		txt_bdate.setBounds(843, 167, 312, 49);
		panel_4.add(txt_bdate);
		
		JLabel lblEnterUsn_4 = new JLabel("Enter Birth Date :");
		lblEnterUsn_4.setForeground(new Color(85, 150, 206));
		lblEnterUsn_4.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEnterUsn_4.setBounds(635, 167, 579, 90);
		panel_4.add(lblEnterUsn_4);
		
		
		txt_address.setSelectionColor(Color.WHITE);
		txt_address.setSelectedTextColor(Color.WHITE);
		txt_address.setPlaceholder("Enter Address ");
		txt_address.setPhColor(Color.BLACK);
		txt_address.setForeground(Color.BLACK);
		txt_address.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txt_address.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(32, 75, 113)));
		txt_address.setBackground(Color.WHITE);
		txt_address.setBounds(843, 267, 312, 49);
		panel_4.add(txt_address);
		
		JLabel lblEnterUsn_5 = new JLabel("Enter Address :");
		lblEnterUsn_5.setForeground(new Color(85, 150, 206));
		lblEnterUsn_5.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEnterUsn_5.setBounds(635, 267, 579, 90);
		panel_4.add(lblEnterUsn_5);
		
		
		txt_gender.setSelectionColor(Color.WHITE);
		txt_gender.setSelectedTextColor(Color.WHITE);
		txt_gender.setPlaceholder("Enter Gender");
		txt_gender.setPhColor(Color.BLACK);
		txt_gender.setForeground(Color.BLACK);
		txt_gender.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txt_gender.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(32, 75, 113)));
		txt_gender.setBackground(Color.WHITE);
		txt_gender.setBounds(843, 372, 312, 49);
		panel_4.add(txt_gender);
		
		JLabel lblEnterUsn_6 = new JLabel("Enter Gender :");
		lblEnterUsn_6.setForeground(new Color(85, 150, 206));
		lblEnterUsn_6.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEnterUsn_6.setBounds(635, 372, 579, 90);
		panel_4.add(lblEnterUsn_6);
		
		
		txt_email.setSelectionColor(Color.WHITE);
		txt_email.setSelectedTextColor(Color.WHITE);
		txt_email.setPlaceholder("Enter Email");
		txt_email.setPhColor(Color.BLACK);
		txt_email.setForeground(Color.BLACK);
		txt_email.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txt_email.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(32, 75, 113)));
		txt_email.setBackground(Color.WHITE);
		txt_email.setBounds(843, 476, 312, 49);
		panel_4.add(txt_email);
		
		JLabel lblEnterUsn_7 = new JLabel("Enter Email :");
		lblEnterUsn_7.setForeground(new Color(85, 150, 206));
		lblEnterUsn_7.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEnterUsn_7.setBounds(635, 476, 579, 90);
		panel_4.add(lblEnterUsn_7);
		
		JLabel lblEnterUsn_8 = new JLabel("Select Department ID :");
		lblEnterUsn_8.setForeground(new Color(85, 150, 206));
		lblEnterUsn_8.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEnterUsn_8.setBounds(324, 547, 579, 90);
		panel_4.add(lblEnterUsn_8);
		
		RSMaterialButtonCircle button_add = new RSMaterialButtonCircle();
		button_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (addStudent()==true) {
					JOptionPane.showMessageDialog(button_add, "Student Added");
				}else {
					JOptionPane.showMessageDialog(button_add, "Student Addition Failed");
				}
			}
		});
		button_add.setText("Add");
		button_add.setFont(new Font("Verdana", Font.PLAIN, 17));
		button_add.setBackground(new Color(85, 150, 206));
		button_add.setBounds(400, 640, 430, 62);
		panel_4.add(button_add);
		comboBox.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(21, 57, 93)));
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "D1", "D2", "D3", "D4", "D5"}));
		comboBox.setToolTipText("");
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setFont(new Font("Tahoma", Font.PLAIN, 22));
		comboBox.setBounds(615, 565, 321, 49);
		panel_4.add(comboBox);
	}
}
