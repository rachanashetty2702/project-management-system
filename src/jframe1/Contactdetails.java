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

public class Contactdetails extends JFrame {

	private JPanel contentPane;
	JCTextField txt_projectid = new JCTextField();
	JCTextField txt_projectname = new JCTextField();

	/**
	 * Launch the application.
	 */
	String usn,phno;
	
	public boolean addProject() {
		boolean isAdded = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
		usn = txt_projectid.getText();
		phno = txt_projectname.getText();

		try {
			Connection con = DBConnection.getConnection();
			String sql = "insert into project_ms.student_ph values(?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,usn);
			pst.setString(2, phno);

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
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Contactdetails frame = new Contactdetails();
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
	public Contactdetails() {
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
		panel_3_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Homepage2 project = new Homepage2();
				project.setVisible(true);
				dispose();
			}
		});
		panel_3_2.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(85, 150, 206)));
		panel_3_2.setLayout(null);
		panel_3_2.setBackground(new Color(21, 57, 93));
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
		});
		panel_3_3.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(85, 150, 206)));
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
		});
		panel_3_4.setLayout(null);
		panel_3_4.setBackground(new Color(21, 57, 93));
		panel_3_4.setBounds(0, 395, 340, 78);
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
		panel_3_9.setBounds(0, 631, 340, 60);
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
			public void mouseClicked(MouseEvent e) {
				List_of_previous_project project = new List_of_previous_project();
				project.setVisible(true);
				dispose();
			}
		});
		panel_3_4_1.setLayout(null);
		panel_3_4_1.setBackground(new Color(21, 57, 93));
		panel_3_4_1.setBounds(0, 551, 340, 78);
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
			public void mouseClicked(MouseEvent e) {
				Student_project project = new Student_project();
				project.setVisible(true);
				dispose();
			}
		});
		panel_3_3_1.setLayout(null);
		panel_3_3_1.setBackground(new Color(21, 57, 93));
		panel_3_3_1.setBounds(0, 471, 340, 82);
		panel_2.add(panel_3_3_1);
		
		JLabel lblNewLabel_4_3_1 = new JLabel("   Student-Project Details");
		lblNewLabel_4_3_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\execute.png"));
		lblNewLabel_4_3_1.setForeground(Color.WHITE);
		lblNewLabel_4_3_1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_3_1.setBackground(SystemColor.menu);
		lblNewLabel_4_3_1.setBounds(41, 25, 242, 35);
		panel_3_3_1.add(lblNewLabel_4_3_1);
		
		JPanel panel_3_4_2 = new JPanel();
		panel_3_4_2.setBorder(new MatteBorder(0, 10, 0, 0, (Color) new Color(85, 150, 206)));
		panel_3_4_2.setLayout(null);
		panel_3_4_2.setBackground(new Color(32, 75, 113));
		panel_3_4_2.setBounds(0, 321, 340, 78);
		panel_2.add(panel_3_4_2);
		
		JLabel lblNewLabel_4_4_2 = new JLabel("   Contact Details");
		lblNewLabel_4_4_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\notebook-of-contacts.png"));
		lblNewLabel_4_4_2.setForeground(Color.WHITE);
		lblNewLabel_4_4_2.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_4_2.setBackground(SystemColor.menu);
		lblNewLabel_4_4_2.setBounds(41, 22, 189, 35);
		panel_3_4_2.add(lblNewLabel_4_4_2);
		
		JPanel panel_4 = new JPanel();
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
		
		JLabel lblNewLabel_1_1 = new JLabel(" Contact Details");
		lblNewLabel_1_1.setBounds(102, 0, 400, 93);
		panel_3.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\student1.png"));
		lblNewLabel_1_1.setForeground(new Color(85, 150, 206));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		//JCTextField txt_projectid = new JCTextField();
		txt_projectid.setSelectionColor(Color.WHITE);
		txt_projectid.setSelectedTextColor(Color.WHITE);
		txt_projectid.setPlaceholder("Enter USN ");
		txt_projectid.setPhColor(new Color(0, 0, 0));
		txt_projectid.setForeground(new Color(0, 0, 0));
		txt_projectid.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txt_projectid.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(32, 75, 113)));
		txt_projectid.setBackground(new Color(255, 255, 255));
		txt_projectid.setBounds(443, 225, 541, 49);
		panel_4.add(txt_projectid);
		
		JLabel lblEnterProjectId = new JLabel("Enter USN :");
		lblEnterProjectId.setForeground(new Color(85, 150, 206));
		lblEnterProjectId.setFont(new Font("Tahoma", Font.BOLD, 22));
		lblEnterProjectId.setBounds(176, 204, 215, 90);
		panel_4.add(lblEnterProjectId);
		
		//JCTextField txt_projectname = new JCTextField();
		txt_projectname.setSelectionColor(Color.WHITE);
		txt_projectname.setSelectedTextColor(Color.WHITE);
		txt_projectname.setPlaceholder("Enter Phone Number ");
		txt_projectname.setPhColor(Color.BLACK);
		txt_projectname.setForeground(new Color(0, 0, 0));
		txt_projectname.setFont(new Font("Tahoma", Font.PLAIN, 22));
		txt_projectname.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(32, 75, 113)));
		txt_projectname.setBackground(Color.WHITE);
		txt_projectname.setBounds(443, 341, 541, 49);
		panel_4.add(txt_projectname);
		
		JLabel pname = new JLabel("Enter Phone Number :");
		pname.setForeground(new Color(85, 150, 206));
		pname.setFont(new Font("Tahoma", Font.BOLD, 22));
		pname.setBounds(176, 317, 257, 90);
		panel_4.add(pname);
		
		RSMaterialButtonCircle button_add = new RSMaterialButtonCircle();
		button_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (addProject()==true) {
					JOptionPane.showMessageDialog(button_add, "Details Added");
				}else {
					JOptionPane.showMessageDialog(button_add, "Details Addition Failed");
				}
			}
		});
		button_add.setText("Add");
		button_add.setFont(new Font("Verdana", Font.PLAIN, 17));
		button_add.setBackground(new Color(85, 150, 206));
		button_add.setBounds(459, 500, 430, 62);
		panel_4.add(button_add);
	}
}
