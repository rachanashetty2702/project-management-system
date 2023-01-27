package jframe1;

import java.awt.EventQueue;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import app.bolivia.swing.JCTextField;
import javax.swing.border.MatteBorder;
import necesario.RSMaterialButtonCircle;
import rojeru_san.complementos.RSTableMetro;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.Dimension;
import java.awt.Component;
import javax.swing.JScrollPane;
import java.awt.SystemColor;

public class List_of_previous_project extends JFrame {

	private JPanel contentPane;
	RSTableMetro project_table = new RSTableMetro();

	/**
	 * Launch the application.
	 */
	String p_id,p_name,d_name,total_points;
	DefaultTableModel model;
	public List_of_previous_project() {
		initcomponents();
		setprojectdetailstotable();
	}
	
	public void setprojectdetailstotable() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_ms","root","rachana");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT project_ms.project.p_id, project_ms.project.p_name,project_ms.resources.hardware,project_ms.resources.software\r\n"
					+ "FROM project_ms.project\r\n"
					+ "JOIN project_ms.resources ON project_ms.project.p_id= project_ms.resources.P_id;\r\n"
					+ "");
			
			while(rs.next()) {
				String projectid = rs.getString("p_id");
				String projectname = rs.getString("p_name");
				String hardware = rs.getString("hardware");
				String software = rs.getString("software");
				
				
				
				Object[] obj = {projectid,projectname,hardware,software};
				model = (DefaultTableModel) project_table.getModel();
				model.addRow(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	


	//method to clear table
	
	public void clearTable() {
		DefaultTableModel model = (DefaultTableModel) project_table.getModel();
		model.setRowCount(0);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					List_of_previous_project frame = new List_of_previous_project();
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
	public void initcomponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1523, 828);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(0, 0, 1526, 791);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();

		scrollPane.setBounds(469, 209, 940, 470);
		panel_2.add(scrollPane);
		
		//RSTableMetro project_table = new RSTableMetro();
		project_table.setForeground(new Color(0, 0, 0));
		project_table.setRowHeight(40);
		project_table.setColorSelBackgound(new Color(85, 150, 206));
		project_table.setColorFilasBackgound2(new Color(255, 255, 255));
		project_table.setFuenteHead(new Font("Verdana", Font.PLAIN, 20));
		project_table.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		project_table.setColorBackgoundHead(new Color(21, 57, 93));
		project_table.setColorFilasForeground2(new Color(21, 57, 93));
		project_table.setColorFilasForeground1(new Color(21, 57, 93));
		scrollPane.setViewportView(project_table);
		project_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Project ID", "Project Name", "Hardware", "Software"
			}
		));
		project_table.getColumnModel().getColumn(2).setPreferredWidth(120);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(85, 150, 206));
		panel_1_1.setBounds(1406, 0, 110, 47);
		panel_2.add(panel_1_1);
		
		JLabel lblX = new JLabel("X");
		lblX.setBounds(50, 20, 50, 27);
		panel_1_1.add(lblX);
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Arial", Font.BOLD, 30));
		
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(new Color(85, 150, 206));
		panel_3.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(85, 150, 206)));
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(724, 90, 511, 83);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("  Previous Projects");
		lblNewLabel_1.setBounds(79, 10, 379, 63);
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\project-management.png"));
		lblNewLabel_1.setForeground(new Color(85, 150, 206));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(new Color(85, 150, 206));
		panel.setBounds(0, 0, 1509, 75);
		panel_2.add(panel);
		
		JLabel lblNewLabel = new JLabel("\r\n");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\adminIcons\\adminIcons\\icons8_menu_48px_1.png"));
		lblNewLabel.setBounds(21, 23, 58, 42);
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(21, 57, 93));
		panel_1.setBounds(74, 15, 4, 50);
		panel.add(panel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("PROJECT MANAGEMENT SYSTEM");
		lblNewLabel_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1_1.setBounds(89, 25, 424, 27);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2 = new JLabel("WELCOME USER");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\adminIcons\\adminIcons\\male_user_50px.png"));
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(1181, 7, 241, 65);
		panel.add(lblNewLabel_2);
		
		JPanel panel_2_1 = new JPanel();
		panel_2_1.setLayout(null);
		panel_2_1.setBackground(new Color(21, 57, 93));
		panel_2_1.setBounds(0, 69, 340, 712);
		panel_2.add(panel_2_1);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Homepage2 project = new Homepage2();
				project.setVisible(true);
				dispose();
			}
		});
		panel_3_2.setLayout(null);
		panel_3_2.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(85, 150, 206)));
		panel_3_2.setBackground(new Color(21, 57, 93));
		panel_3_2.setBounds(0, 158, 340, 78);
		panel_2_1.add(panel_3_2);
		
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
		panel_3_3.setLayout(null);
		panel_3_3.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(85, 150, 206)));
		panel_3_3.setBackground(new Color(21, 57, 93));
		panel_3_3.setBounds(0, 235, 340, 82);
		panel_2_1.add(panel_3_3);
		
		JLabel lblNewLabel_4_3 = new JLabel("   Project Details");
		lblNewLabel_4_3.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\execute.png"));
		lblNewLabel_4_3.setForeground(Color.WHITE);
		lblNewLabel_4_3.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_3.setBackground(new Color(240, 240, 240));
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
		panel_3_4.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(85, 150, 206)));
		panel_3_4.setBackground(new Color(21, 57, 93));
		panel_3_4.setBounds(0, 393, 340, 78);
		panel_2_1.add(panel_3_4);
		
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
		panel_3_9.setBounds(0, 629, 340, 60);
		panel_2_1.add(panel_3_9);
		
		JLabel lblNewLabel_4_9 = new JLabel("   Logout");
		lblNewLabel_4_9.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\adminIcons\\adminIcons\\icons8_Exit_26px_2.png"));
		lblNewLabel_4_9.setForeground(Color.WHITE);
		lblNewLabel_4_9.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_9.setBackground(SystemColor.menu);
		lblNewLabel_4_9.setBounds(41, 10, 152, 35);
		panel_3_9.add(lblNewLabel_4_9);
		
		JPanel panel_3_4_1 = new JPanel();
		panel_3_4_1.setBorder(new MatteBorder(0, 10, 0, 0, (Color) new Color(85, 150, 206)));
		panel_3_4_1.setLayout(null);
		panel_3_4_1.setBackground(new Color(32, 75, 113));
		panel_3_4_1.setBounds(0, 549, 340, 78);
		panel_2_1.add(panel_3_4_1);
		
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
		panel_3_3_1.setBorder(new MatteBorder(0, 0, 0, 0, (Color) new Color(85, 150, 206)));
		panel_3_3_1.setBackground(new Color(21, 57, 93));
		panel_3_3_1.setBounds(0, 469, 340, 82);
		panel_2_1.add(panel_3_3_1);
		
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
		panel_3_4_2.setBounds(0, 312, 340, 78);
		panel_2_1.add(panel_3_4_2);
		
		JLabel lblNewLabel_4_4_2 = new JLabel("   Contact Details");
		lblNewLabel_4_4_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\notebook-of-contacts.png"));
		lblNewLabel_4_4_2.setForeground(Color.WHITE);
		lblNewLabel_4_4_2.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_4_2.setBackground(SystemColor.menu);
		lblNewLabel_4_4_2.setBounds(41, 22, 189, 35);
		panel_3_4_2.add(lblNewLabel_4_4_2);
		
		
	}
}
