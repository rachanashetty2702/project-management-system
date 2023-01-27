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
import java.sql.SQLException;
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

public class Project_evaluators extends JFrame {

	private JPanel contentPane;
	private JTextField txt_projectid;
	private JTextField pname;
	private JTextField sdate;
	private JTextField txt_projectname;
	private JTextField enddate;
	private JTextField txt_startdate;
	private JTextField textField_5;
	private JTextField txt_enddate;
	private JTextField txt_did;
	private JTextField txt_dname;
	private JTextField eid;
	private JTextField ename;
	private JTextField txt_hname;
	private JTextField designation;
	private JTextField pid;
	JCTextField txt_pid = new JCTextField();
	RSTableMetro evaluator_table = new RSTableMetro();
	String p_id;
	DefaultTableModel model;
	private JTextField txtTotalPoints;
	private JTextField text_tpoints;

	/**
	 * Launch the application.
	 */
	
	
	public Project_evaluators() {
		initcomponents();
	}
	
	public void setprojectdetailstotable(){
		p_id = txt_pid.getText();
		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_ms","root","rachana");
			String sql = "SELECT project_ms.evaluated_by.Eval_id , project_ms.evaluated_by.E_points\r\n"
					+ "FROM project_ms.evaluated_by\r\n"
					+ "WHERE project_ms.evaluated_by.Proj_id = ?;";
			String sql1 = "select total_points from works_on where project_id = ? ;";
			PreparedStatement pst = con.prepareStatement(sql);
			PreparedStatement pst1 = con.prepareStatement(sql1);
			pst.setString(1, p_id);
			pst1.setString(1, p_id);
			ResultSet rs = pst.executeQuery();
			ResultSet rs1 = pst1.executeQuery();
			while(rs.next()) {
				String evalid = rs.getString("Eval_id");
				String epoints = rs.getString("E_points");
				
				
				Object[] obj = {evalid,epoints};
				model = (DefaultTableModel) evaluator_table.getModel();
				model.addRow(obj);
			}
			while(rs1.next()) {

				text_tpoints.setText(rs1.getString("total_points"));
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	public void adddetails() {
		p_id = txt_pid.getText();
		int row = evaluator_table.getSelectedRow();
		TableModel model = evaluator_table.getModel();
		String evalid = model.getValueAt(row, 0).toString();
		String epoints = model.getValueAt(row, 1).toString();


		try {
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_ms","root","rachana");
			String sql = "SELECT project_ms.evaluator.E_id, project_ms.evaluator.E_name, project_ms.evaluator.designation , project_ms.project.p_id, project_ms.project.p_name, project_ms.project.start_date, project_ms.project.end_date\r\n"
					+ "FROM project_ms.project \r\n"
					+ "INNER JOIN project_ms.evaluated_by ON project_ms.project.p_id = project_ms.evaluated_by.Proj_id\r\n"
					+ "INNER JOIN project_ms.evaluator ON project_ms.evaluator.E_id = project_ms.evaluated_by.Eval_id where project_ms.evaluated_by.Proj_id = ? and project_ms.evaluated_by.Eval_id = ? and project_ms.evaluated_by.E_points = ? ;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, p_id);
			pst.setString(2, evalid);
			pst.setString(3, epoints);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next()) {
				eid.setText(rs.getString("E_id"));
				ename.setText(rs.getString("E_name"));
				designation.setText(rs.getString("designation"));
				pid.setText(rs.getString("p_id"));
				pname.setText(rs.getString("p_name"));
				sdate.setText(rs.getString("start_date"));
				enddate.setText(rs.getString("end_date"));

			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void clearTable() {
		DefaultTableModel model = (DefaultTableModel) evaluator_table.getModel();
		model.setRowCount(0);
	}

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Project_evaluators frame = new Project_evaluators();
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
		
		JLabel lblNewLabel_1_1_1 = new JLabel("   Evaluator Details");
		lblNewLabel_1_1_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\job-description.png"));
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
		txt_projectid.setBounds(37, 429, 154, 40);
		panel.add(txt_projectid);
		txt_projectid.setColumns(10);
		
		pname = new JTextField();
		pname.setForeground(Color.WHITE);
		pname.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		pname.setColumns(10);
		pname.setBorder(new EmptyBorder(0, 0, 0, 0));
		pname.setBackground(new Color(85, 150, 206));
		pname.setBounds(214, 501, 259, 40);
		panel.add(pname);
		
		sdate = new JTextField();
		sdate.setForeground(Color.WHITE);
		sdate.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		sdate.setColumns(10);
		sdate.setBorder(new EmptyBorder(0, 0, 0, 0));
		sdate.setBackground(new Color(85, 150, 206));
		sdate.setBounds(214, 574, 259, 40);
		panel.add(sdate);
		
		txt_projectname = new JTextField();
		txt_projectname.setText("Project Name :");
		txt_projectname.setForeground(Color.WHITE);
		txt_projectname.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txt_projectname.setColumns(10);
		txt_projectname.setBorder(new EmptyBorder(0, 0, 0, 0));
		txt_projectname.setBackground(new Color(85, 150, 206));
		txt_projectname.setBounds(37, 502, 154, 40);
		panel.add(txt_projectname);
		
		enddate = new JTextField();
		enddate.setForeground(Color.WHITE);
		enddate.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		enddate.setColumns(10);
		enddate.setBorder(new EmptyBorder(0, 0, 0, 0));
		enddate.setBackground(new Color(85, 150, 206));
		enddate.setBounds(214, 655, 259, 40);
		panel.add(enddate);
		
		txt_startdate = new JTextField();
		txt_startdate.setText("Start Date :");
		txt_startdate.setForeground(Color.WHITE);
		txt_startdate.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txt_startdate.setColumns(10);
		txt_startdate.setBorder(new EmptyBorder(0, 0, 0, 0));
		txt_startdate.setBackground(new Color(85, 150, 206));
		txt_startdate.setBounds(37, 583, 154, 40);
		panel.add(txt_startdate);
		
		textField_5 = new JTextField();
		textField_5.setForeground(Color.WHITE);
		textField_5.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		textField_5.setColumns(10);
		textField_5.setBorder(new EmptyBorder(0, 0, 0, 0));
		textField_5.setBackground(new Color(85, 150, 206));
		textField_5.setBounds(214, 728, 259, 40);
		panel.add(textField_5);
		
		txt_enddate = new JTextField();
		txt_enddate.setText("End Date :");
		txt_enddate.setForeground(Color.WHITE);
		txt_enddate.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txt_enddate.setColumns(10);
		txt_enddate.setBorder(new EmptyBorder(0, 0, 0, 0));
		txt_enddate.setBackground(new Color(85, 150, 206));
		txt_enddate.setBounds(37, 656, 154, 40);
		panel.add(txt_enddate);
		
		txt_did = new JTextField();
		txt_did.setBounds(37, 202, 154, 40);
		panel.add(txt_did);
		txt_did.setText("Evaluator ID :");
		txt_did.setForeground(Color.WHITE);
		txt_did.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txt_did.setColumns(10);
		txt_did.setBorder(new EmptyBorder(0, 0, 0, 0));
		txt_did.setBackground(new Color(85, 150, 206));
		
		eid = new JTextField();
		eid.setBounds(214, 202, 259, 40);
		panel.add(eid);
		eid.setForeground(Color.WHITE);
		eid.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		eid.setColumns(10);
		eid.setBorder(new EmptyBorder(0, 0, 0, 0));
		eid.setBackground(new Color(85, 150, 206));
		
		txt_dname = new JTextField();
		txt_dname.setBounds(37, 275, 166, 40);
		panel.add(txt_dname);
		txt_dname.setText("Evaluator Name :");
		txt_dname.setForeground(Color.WHITE);
		txt_dname.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txt_dname.setColumns(10);
		txt_dname.setBorder(new EmptyBorder(0, 0, 0, 0));
		txt_dname.setBackground(new Color(85, 150, 206));
		
		ename = new JTextField();
		ename.setBounds(197, 275, 305, 40);
		panel.add(ename);
		ename.setForeground(Color.WHITE);
		ename.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		ename.setColumns(10);
		ename.setBorder(new EmptyBorder(0, 0, 0, 0));
		ename.setBackground(new Color(85, 150, 206));
		
		txt_hname = new JTextField();
		txt_hname.setBounds(37, 356, 154, 40);
		panel.add(txt_hname);
		txt_hname.setText("Designation :");
		txt_hname.setForeground(Color.WHITE);
		txt_hname.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txt_hname.setColumns(10);
		txt_hname.setBorder(new EmptyBorder(0, 0, 0, 0));
		txt_hname.setBackground(new Color(85, 150, 206));
		
		designation = new JTextField();
		designation.setBounds(195, 356, 288, 40);
		panel.add(designation);
		designation.setForeground(Color.WHITE);
		designation.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		designation.setColumns(10);
		designation.setBorder(new EmptyBorder(0, 0, 0, 0));
		designation.setBackground(new Color(85, 150, 206));
		
		pid = new JTextField();
		pid.setBounds(214, 429, 259, 40);
		panel.add(pid);
		pid.setForeground(Color.WHITE);
		pid.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		pid.setColumns(10);
		pid.setBorder(new EmptyBorder(0, 0, 0, 0));
		pid.setBackground(new Color(85, 150, 206));
		
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
		
		JLabel lblNewLabel_1_1 = new JLabel("  Project Details");
		lblNewLabel_1_1.setBounds(0, 53, 441, 128);
		panel_3_1.add(lblNewLabel_1_1);
		lblNewLabel_1_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\it-department.png"));
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 222, 464, 403);
		panel_1.add(scrollPane);
		
		
		evaluator_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				adddetails();
			}
		});
		evaluator_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Evaluator ID", "E_points"
			}
		));
		evaluator_table.setRowHeight(40);
		evaluator_table.setFuenteHead(new Font("Verdana", Font.PLAIN, 20));
		evaluator_table.setForeground(Color.BLACK);
		evaluator_table.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		evaluator_table.setColorSelBackgound(new Color(85, 150, 206));
		evaluator_table.setColorFilasForeground2(new Color(21, 57, 93));
		evaluator_table.setColorFilasForeground1(new Color(21, 57, 93));
		evaluator_table.setColorFilasBackgound2(new Color(85, 150, 206));
		evaluator_table.setColorBackgoundHead(new Color(21, 57, 93));
		scrollPane.setViewportView(evaluator_table);
		
		txtTotalPoints = new JTextField();
		txtTotalPoints.setText("Total Points :");
		txtTotalPoints.setForeground(Color.WHITE);
		txtTotalPoints.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		txtTotalPoints.setColumns(10);
		txtTotalPoints.setBorder(new EmptyBorder(0, 0, 0, 0));
		txtTotalPoints.setBackground(new Color(21, 57, 93));
		txtTotalPoints.setBounds(37, 687, 137, 55);
		panel_1.add(txtTotalPoints);
		
		text_tpoints = new JTextField();
		text_tpoints.setForeground(Color.WHITE);
		text_tpoints.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 20));
		text_tpoints.setColumns(10);
		text_tpoints.setBorder(new EmptyBorder(0, 0, 0, 0));
		text_tpoints.setBackground(new Color(21, 57, 93));
		text_tpoints.setBounds(214, 687, 259, 55);
		panel_1.add(text_tpoints);
		
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
		
		JLabel lblNewLabel_1 = new JLabel("  Project Evaluators");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\list1.png"));
		lblNewLabel_1.setForeground(new Color(85, 150, 206));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 28));
		lblNewLabel_1.setBounds(39, 0, 415, 63);
		panel_3.add(lblNewLabel_1);
		
		
		txt_pid.setSelectionColor(Color.WHITE);
		txt_pid.setSelectedTextColor(Color.WHITE);
		txt_pid.setPlaceholder("Enter Project ID ");
		txt_pid.setPhColor(new Color(0, 0, 0));
		txt_pid.setForeground(new Color(0, 0, 0));
		txt_pid.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_pid.setBorder(new MatteBorder(0, 0, 5, 0, (Color) new Color(85, 150, 206)));
		txt_pid.setBackground(new Color(255, 255, 255));
		txt_pid.setBounds(194, 430, 269, 45);
		panel_2.add(txt_pid);
		
		JLabel lblDepartmentId = new JLabel("Project ID :");
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
