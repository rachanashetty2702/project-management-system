package jframe1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.border.MatteBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JTextField;
import app.bolivia.swing.JCTextField;
import necesario.RSMaterialButtonCircle;
import javax.swing.JScrollPane;
import rojeru_san.complementos.RSTableMetro;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class works_on extends JFrame {

	private JPanel contentPane;
	private JTextField txt_projectid;
	private JTextField pid;
	private JTextField pname;
	private JTextField txt_projectname;
	private JTextField sdate;
	private JTextField txt_startdate;
	private JTextField edate;
	private JTextField txt_enddate;
	private JTextField txt_did;
	private JTextField txt_dname;
	private JTextField fname;
	private JTextField lname;
	private JTextField txt_hname;
	private JTextField email;
	private JTextField txt_iid;
	private JTextField did;
	JCTextField txt_deptid = new JCTextField();
	RSTableMetro project_table = new RSTableMetro();
	String usn;
	DefaultTableModel model;

	/**
	 * Launch the application.
	 */
	
	
	public works_on() {
		initcomponents();
	}
	
	public void setprojectdetailstotable(){
		usn = txt_deptid.getText();
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_ms","root","rachana");
			String sql = "SELECT project_ms.project.p_id, project_ms.project.p_name FROM project_ms.project INNER JOIN project_ms.works_on ON project_ms.project.p_id = project_ms.works_on.project_id\r\n"
					+ " INNER JOIN project_ms.students ON project_ms.works_on.usn = project_ms.students.usn where project_ms.students.usn = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, usn);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String projectid = rs.getString("p_id");
				String projectname = rs.getString("p_name");
				
				
				Object[] obj = {projectid,projectname};
				model = (DefaultTableModel) project_table.getModel();
				model.addRow(obj);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public void adddetails() {
		usn = txt_deptid.getText();
		int row = project_table.getSelectedRow();
		TableModel model = project_table.getModel();
		String project_id = model.getValueAt(row, 0).toString();
		String p_name = model.getValueAt(row, 1).toString();


		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_ms","root","rachana");
			String sql = "SELECT project_ms.students.first_name, project_ms.students.last_name , project_ms.students.email , project_ms.students.D_id,project_ms.project.p_id, project_ms.project.p_name, project_ms.project.start_date, project_ms.project.end_date\r\n"
					+ "FROM project_ms.project \r\n"
					+ "INNER JOIN project_ms.works_on ON project_ms.project.p_id = project_ms.works_on.project_id\r\n"
					+ "INNER JOIN project_ms.students ON project_ms.students.usn = project_ms.works_on.usn where project_ms.students.usn = ? and project_ms.project.p_id = ? and project_ms.project.p_name = ? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, usn);
			pst.setString(2, project_id);
			pst.setString(3, p_name);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				fname.setText(rs.getString("first_name"));
				lname.setText(rs.getString("last_name"));
				email.setText(rs.getString("email"));
				did.setText(rs.getString("D_id"));
				pid.setText(rs.getString("p_id"));
				pname.setText(rs.getString("p_name"));
				sdate.setText(rs.getString("start_date"));
				edate.setText(rs.getString("end_date"));

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clearTable() {
		DefaultTableModel model = (DefaultTableModel) project_table.getModel();
		model.setRowCount(0);
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					works_on frame = new works_on();
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
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(85, 150, 206));
		panel.setBounds(0, 0, 512, 805);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_3_1_1 = new JPanel();
		panel_3_1_1.setLayout(null);
		panel_3_1_1.setForeground(new Color(21, 57, 93));
		panel_3_1_1.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(255, 255, 255)));
		panel_3_1_1.setBackground(new Color(85, 150, 206));
		panel_3_1_1.setBounds(37, 46, 436, 133);
		panel.add(panel_3_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("   Project Details");
		lblNewLabel_1_1_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\project2.png"));
		lblNewLabel_1_1_1.setForeground(Color.WHITE);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_1_1_1.setBounds(0, 10, 441, 107);
		panel_3_1_1.add(lblNewLabel_1_1_1);
		
		txt_projectid = new JTextField();
		txt_projectid.setBorder(new EmptyBorder(0, 0, 0, 0));
		txt_projectid.setForeground(new Color(255, 255, 255));
		txt_projectid.setBackground(new Color(85, 150, 206));
		txt_projectid.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txt_projectid.setText("Project ID :");
		txt_projectid.setBounds(37, 501, 154, 40);
		panel.add(txt_projectid);
		txt_projectid.setColumns(10);
		
		pid = new JTextField();
		pid.setForeground(Color.WHITE);
		pid.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		pid.setColumns(10);
		pid.setBorder(new EmptyBorder(0, 0, 0, 0));
		pid.setBackground(new Color(85, 150, 206));
		pid.setBounds(214, 501, 259, 40);
		panel.add(pid);
		
		pname = new JTextField();
		pname.setForeground(Color.WHITE);
		pname.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		pname.setColumns(10);
		pname.setBorder(new EmptyBorder(0, 0, 0, 0));
		pname.setBackground(new Color(85, 150, 206));
		pname.setBounds(214, 574, 259, 40);
		panel.add(pname);
		
		txt_projectname = new JTextField();
		txt_projectname.setText("Project Name :");
		txt_projectname.setForeground(Color.WHITE);
		txt_projectname.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txt_projectname.setColumns(10);
		txt_projectname.setBorder(new EmptyBorder(0, 0, 0, 0));
		txt_projectname.setBackground(new Color(85, 150, 206));
		txt_projectname.setBounds(37, 574, 154, 40);
		panel.add(txt_projectname);
		
		sdate = new JTextField();
		sdate.setForeground(Color.WHITE);
		sdate.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		sdate.setColumns(10);
		sdate.setBorder(new EmptyBorder(0, 0, 0, 0));
		sdate.setBackground(new Color(85, 150, 206));
		sdate.setBounds(214, 655, 259, 40);
		panel.add(sdate);
		
		txt_startdate = new JTextField();
		txt_startdate.setText("Start Date :");
		txt_startdate.setForeground(Color.WHITE);
		txt_startdate.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txt_startdate.setColumns(10);
		txt_startdate.setBorder(new EmptyBorder(0, 0, 0, 0));
		txt_startdate.setBackground(new Color(85, 150, 206));
		txt_startdate.setBounds(37, 655, 154, 40);
		panel.add(txt_startdate);
		
		edate = new JTextField();
		edate.setForeground(Color.WHITE);
		edate.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		edate.setColumns(10);
		edate.setBorder(new EmptyBorder(0, 0, 0, 0));
		edate.setBackground(new Color(85, 150, 206));
		edate.setBounds(214, 728, 259, 40);
		panel.add(edate);
		
		txt_enddate = new JTextField();
		txt_enddate.setText("End Date :");
		txt_enddate.setForeground(Color.WHITE);
		txt_enddate.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txt_enddate.setColumns(10);
		txt_enddate.setBorder(new EmptyBorder(0, 0, 0, 0));
		txt_enddate.setBackground(new Color(85, 150, 206));
		txt_enddate.setBounds(37, 728, 154, 40);
		panel.add(txt_enddate);
		
		txt_did = new JTextField();
		txt_did.setBounds(37, 202, 154, 40);
		panel.add(txt_did);
		txt_did.setText("First Name :");
		txt_did.setForeground(Color.WHITE);
		txt_did.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txt_did.setColumns(10);
		txt_did.setBorder(new EmptyBorder(0, 0, 0, 0));
		txt_did.setBackground(new Color(85, 150, 206));
		
		fname = new JTextField();
		fname.setBounds(214, 202, 259, 40);
		panel.add(fname);
		fname.setForeground(Color.WHITE);
		fname.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		fname.setColumns(10);
		fname.setBorder(new EmptyBorder(0, 0, 0, 0));
		fname.setBackground(new Color(85, 150, 206));
		
		txt_dname = new JTextField();
		txt_dname.setBounds(37, 275, 120, 40);
		panel.add(txt_dname);
		txt_dname.setText("Last Name :");
		txt_dname.setForeground(Color.WHITE);
		txt_dname.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txt_dname.setColumns(10);
		txt_dname.setBorder(new EmptyBorder(0, 0, 0, 0));
		txt_dname.setBackground(new Color(85, 150, 206));
		
		lname = new JTextField();
		lname.setBounds(214, 275, 288, 40);
		panel.add(lname);
		lname.setForeground(Color.WHITE);
		lname.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		lname.setColumns(10);
		lname.setBorder(new EmptyBorder(0, 0, 0, 0));
		lname.setBackground(new Color(85, 150, 206));
		
		txt_hname = new JTextField();
		txt_hname.setBounds(37, 356, 154, 40);
		panel.add(txt_hname);
		txt_hname.setText("Email :");
		txt_hname.setForeground(Color.WHITE);
		txt_hname.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txt_hname.setColumns(10);
		txt_hname.setBorder(new EmptyBorder(0, 0, 0, 0));
		txt_hname.setBackground(new Color(85, 150, 206));
		
		email = new JTextField();
		email.setBounds(214, 356, 259, 40);
		panel.add(email);
		email.setForeground(Color.WHITE);
		email.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		email.setColumns(10);
		email.setBorder(new EmptyBorder(0, 0, 0, 0));
		email.setBackground(new Color(85, 150, 206));
		
		txt_iid = new JTextField();
		txt_iid.setBounds(37, 429, 180, 40);
		panel.add(txt_iid);
		txt_iid.setText("Department ID :");
		txt_iid.setForeground(Color.WHITE);
		txt_iid.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txt_iid.setColumns(10);
		txt_iid.setBorder(new EmptyBorder(0, 0, 0, 0));
		txt_iid.setBackground(new Color(85, 150, 206));
		
		did = new JTextField();
		did.setBounds(214, 429, 259, 40);
		panel.add(did);
		did.setForeground(Color.WHITE);
		did.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		did.setColumns(10);
		did.setBorder(new EmptyBorder(0, 0, 0, 0));
		did.setBackground(new Color(85, 150, 206));
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(21, 57, 93));
		panel_4.setBounds(0, 0, 110, 46);
		panel.add(panel_4);
		
		JLabel lblNewLabel = new JLabel("Back");
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Homepage home = new Homepage();
				home.setVisible(true);
				dispose();
			}
		});
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\AddNewBookIcons\\AddNewBookIcons\\icons8_Rewind_48px.png"));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		panel_4.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(21, 57, 93));
		panel_1.setBounds(520, 0, 512, 805);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.setLayout(null);
		panel_3_1.setForeground(new Color(21, 57, 93));
		panel_3_1.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(255, 255, 255)));
		panel_3_1.setBackground(new Color(21, 57, 93));
		panel_3_1.setBounds(37, -12, 436, 191);
		panel_1.add(panel_3_1);
		
		JLabel lblNewLabel_1_1 = new JLabel(" Projects Handled");
		lblNewLabel_1_1.setBounds(0, 53, 441, 128);
		panel_3_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\it-department.png"));
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 222, 464, 403);
		panel_1.add(scrollPane);
		
		
		project_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adddetails();
			}
		});
		project_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Project ID", "Project Name"
			}
		));
		project_table.setRowHeight(40);
		project_table.setFuenteHead(new Font("Verdana", Font.PLAIN, 20));
		project_table.setForeground(Color.BLACK);
		project_table.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		project_table.setColorSelBackgound(new Color(85, 150, 206));
		project_table.setColorFilasForeground2(new Color(21, 57, 93));
		project_table.setColorFilasForeground1(new Color(21, 57, 93));
		project_table.setColorFilasBackgound2(new Color(85, 150, 206));
		project_table.setColorBackgoundHead(new Color(21, 57, 93));
		scrollPane.setViewportView(project_table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(1031, 0, 493, 791);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setLayout(null);
		panel_3.setForeground(new Color(85, 150, 206));
		panel_3.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(85, 150, 206)));
		panel_3.setBackground(Color.WHITE);
		panel_3.setBounds(27, 133, 436, 132);
		panel_2.add(panel_3);
		
		JLabel lblNewLabel_1 = new JLabel("  Student Working On");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\computer-worker-on-frontal-view.png"));
		lblNewLabel_1.setForeground(new Color(85, 150, 206));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_1.setBounds(10, 0, 415, 63);
		panel_3.add(lblNewLabel_1);
		
		
		txt_deptid.setSelectionColor(Color.WHITE);
		txt_deptid.setSelectedTextColor(Color.WHITE);
		txt_deptid.setPlaceholder("Enter USN");
		txt_deptid.setPhColor(new Color(0, 0, 0));
		txt_deptid.setForeground(new Color(0, 0, 0));
		txt_deptid.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_deptid.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(85, 150, 206)));
		txt_deptid.setBackground(new Color(255, 255, 255));
		txt_deptid.setBounds(194, 430, 269, 45);
		panel_2.add(txt_deptid);
		
		JLabel lblDepartmentId = new JLabel("Enter USN :");
		lblDepartmentId.setForeground(new Color(85, 150, 206));
		lblDepartmentId.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		lblDepartmentId.setBounds(27, 430, 168, 55);
		panel_2.add(lblDepartmentId);
		
		RSMaterialButtonCircle button_getdetails = new RSMaterialButtonCircle();
		button_getdetails.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clearTable();
				setprojectdetailstotable();
			}
		});
		button_getdetails.setText("Get Details");
		button_getdetails.setFont(new Font("Verdana", Font.PLAIN, 17));
		button_getdetails.setBackground(new Color(85, 150, 206));
		button_getdetails.setBounds(84, 566, 345, 61);
		panel_2.add(button_getdetails);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(85, 150, 206));
		panel_1_1.setBounds(373, 0, 110, 47);
		panel_2.add(panel_1_1);
		
		JLabel lblX = new JLabel("X");
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Arial", Font.BOLD, 30));
		lblX.setBounds(47, 10, 50, 27);
		panel_1_1.add(lblX);
	}
}
