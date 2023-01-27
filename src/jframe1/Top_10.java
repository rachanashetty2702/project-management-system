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

public class Top_10 extends JFrame {

	private JPanel contentPane;
	RSTableMetro project_table = new RSTableMetro();

	/**
	 * Launch the application.
	 */
	String p_id,p_name,d_name,total_points;
	DefaultTableModel model;
	public Top_10() {
		initcomponents();
		setprojectdetailstotable();
	}
	
	public void setprojectdetailstotable() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_ms","root","rachana");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("SELECT p.p_id, p.p_name, d.D_name, SUM(e.E_points) as total_points, sp.ph_no\r\n"
					+ "FROM project_ms.project p\r\n"
					+ "JOIN evaluated_by e ON p.p_id = e.Proj_id\r\n"
					+ "JOIN project_ms.controlled_by c ON p.p_id = c.pr_id\r\n"
					+ "JOIN project_ms.department d ON d.D_id = c.Dept_id\r\n"
					+ "JOIN (SELECT project_id, MIN(usn) as usn\r\n"
					+ "FROM project_ms.works_on\r\n"
					+ "GROUP BY project_id) w ON w.project_id = p.p_id\r\n"
					+ "JOIN project_ms.students s ON s.usn = w.usn\r\n"
					+ "JOIN project_ms.student_ph sp ON sp.st_id = s.usn\r\n"
					+ "GROUP BY p.p_id, d.D_name\r\n"
					+ "ORDER BY total_points DESC\r\n"
					+ "LIMIT 10;");
			
			while(rs.next()) {
				String projectid = rs.getString("p_id");
				String projectname = rs.getString("p_name");
				String dname = rs.getString("D_name");
				String tpoints = rs.getString("total_points");
				String phno = rs.getString("ph_no");
				
				
				
				Object[] obj = {projectid,projectname,dname,tpoints,phno};
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
					Top_10 frame = new Top_10();
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

		scrollPane.setBounds(143, 209, 1192, 470);
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
				"Project ID", "Project Name", "Department name", "Total points", "Phone no."
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
		lblX.setBounds(47, 10, 50, 27);
		panel_1_1.add(lblX);
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Arial", Font.BOLD, 30));
		
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(new Color(85, 150, 206));
		panel_3.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(85, 150, 206)));
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(478, 44, 511, 83);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("TOP 10 PROJECTS");
		lblNewLabel_1.setBounds(79, 10, 379, 63);
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\top-10.png"));
		lblNewLabel_1.setForeground(new Color(85, 150, 206));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 10, 154, 47);
		panel_2.add(panel_1);
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Homepage home = new Homepage();
				home.setVisible(true);
				dispose();
			}
		});
		panel_1.setBackground(new Color(85, 150, 206));
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Back");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\AddNewBookIcons\\AddNewBookIcons\\icons8_Rewind_48px.png"));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel.setBounds(30, 10, 114, 27);
		panel_1.add(lblNewLabel);
		
		
	}
}
