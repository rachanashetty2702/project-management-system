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

public class Manageprojects extends JFrame {

	private JPanel contentPane;
	RSTableMetro project_table = new RSTableMetro();
	JCTextField txt_projectid = new JCTextField();
	JCTextField txt_projectname = new JCTextField();
	JCTextField txt_sdate = new JCTextField();
	JCTextField txt_edate = new JCTextField();

	/**
	 * Launch the application.
	 */
	String p_id,p_name;
	Date start_date,end_date;
	DefaultTableModel model;
	public Manageprojects() {
		initcomponents();
		setprojectdetailstotable();
	}
	
	public void setprojectdetailstotable() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_ms","root","rachana");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from project");
			
			while(rs.next()) {
				String projectid = rs.getString("p_id");
				String projectname = rs.getString("p_name");
				Date startdate = rs.getDate("start_date");
				Date enddate = rs.getDate("end_date");
				
				long l1 = startdate.getTime();
				long l2 = enddate.getTime();
				
				java.sql.Date sstartdate = new java.sql.Date(l1);
				java.sql.Date senddate = new java.sql.Date(l2);
				
				Object[] obj = {projectid,projectname,startdate,enddate};
				model = (DefaultTableModel) project_table.getModel();
				model.addRow(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// to add  project details
	public boolean addProject() {
		boolean isAdded = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
		p_id = txt_projectid.getText();
		p_name = txt_projectname.getText();
		try {
			start_date = dateFormat.parse(txt_sdate.getText());
			end_date = dateFormat.parse(txt_edate.getText());
			} catch (ParseException e) {
			    JOptionPane.showMessageDialog(this, "Invalid date format. Please enter the date in yyyy-MM-dd format.");
			}
		
		long l3 = start_date.getTime();
		long l4 = end_date.getTime();
		
		java.sql.Date dstartdate = new java.sql.Date(l3);
		java.sql.Date denddate = new java.sql.Date(l4);
		
		try {
			Connection con = DBConnection.getConnection();
			String sql = "insert into project_ms.project values(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,p_id);
			pst.setString(2, p_name);
			pst.setDate(3, dstartdate);
			pst.setDate(4, denddate);
			
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
	
	//update
	public boolean updateproject() {
		boolean isUpdated = false;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
		p_id = txt_projectid.getText();
		p_name = txt_projectname.getText();
		try {
			start_date = dateFormat.parse(txt_sdate.getText());
			end_date = dateFormat.parse(txt_edate.getText());
			} catch (ParseException e) {
			    JOptionPane.showMessageDialog(this, "Invalid date format. Please enter the date in yyyy-MM-dd format.");
			}
		
		long l5 = start_date.getTime();
		long l6 = end_date.getTime();
		
		java.sql.Date dstartdate = new java.sql.Date(l5);
		java.sql.Date denddate = new java.sql.Date(l6);
		
		try {
			Connection con = DBConnection.getConnection();
			String sql = "update project_ms.project set p_name = ?, start_date = ?, end_date = ? where p_id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, p_name);
	        pst.setDate(2, dstartdate);
	        pst.setDate(3, denddate);
	        pst.setString(4, p_id);
			
			int rowCount = pst.executeUpdate();
			if (rowCount >0) {
				isUpdated = true;
			}else {
				isUpdated = false;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return isUpdated;
		
	}
	//to delete project
	
	public boolean deleteproject() {
		boolean isDeleted = false;
		p_id = txt_projectid.getText();
		try {
			Connection con = DBConnection.getConnection();
			String sql = "delete from project_ms.project where p_id = ? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, p_id);
			
			int rowCount = pst.executeUpdate();
			if (rowCount >0) {
				isDeleted = true;
			}else {
				isDeleted = false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isDeleted;
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
					Manageprojects frame = new Manageprojects();
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
		panel.setBackground(new Color(21, 57, 93));
		panel.setBounds(0, 0, 580, 801);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Homepage home = new Homepage();
				home.setVisible(true);
				dispose();
			}
		});
		panel_1.setBackground(new Color(85, 150, 206));
		panel_1.setBounds(0, 0, 154, 47);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Back");
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\AddNewBookIcons\\AddNewBookIcons\\icons8_Rewind_48px.png"));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Verdana", Font.PLAIN, 18));
		lblNewLabel.setBounds(30, 10, 114, 27);
		panel_1.add(lblNewLabel);
		
		JLabel lblEnterProjectId = new JLabel("Enter Project ID");
		lblEnterProjectId.setForeground(Color.WHITE);
		lblEnterProjectId.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterProjectId.setBounds(167, 114, 164, 110);
		panel.add(lblEnterProjectId);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\AddNewBookIcons\\AddNewBookIcons\\icons8_Contact_26px.png"));
		lblNewLabel_2.setBounds(99, 169, 62, 72);
		panel.add(lblNewLabel_2);
		
		//JCTextField txt_projectid = new JCTextField();
		txt_projectid.setSelectionColor(Color.WHITE);
		txt_projectid.setSelectedTextColor(Color.WHITE);
		txt_projectid.setPlaceholder("Enter Project ID");
		txt_projectid.setPhColor(new Color(236, 236, 236));
		txt_projectid.setForeground(Color.WHITE);
		txt_projectid.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_projectid.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_projectid.setBackground(new Color(21, 57, 93));
		txt_projectid.setBounds(167, 192, 303, 32);
		panel.add(txt_projectid);
		
		//JCTextField txt_projectname = new JCTextField();
		txt_projectname.setSelectionColor(Color.WHITE);
		txt_projectname.setSelectedTextColor(Color.WHITE);
		txt_projectname.setPlaceholder("Enter Project Name");
		txt_projectname.setPhColor(new Color(236, 236, 236));
		txt_projectname.setForeground(Color.WHITE);
		txt_projectname.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_projectname.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_projectname.setBackground(new Color(21, 57, 93));
		txt_projectname.setBounds(167, 312, 303, 32);
		panel.add(txt_projectname);
		
		JLabel lblEnterProjectName = new JLabel("Enter Project Name");
		lblEnterProjectName.setForeground(Color.WHITE);
		lblEnterProjectName.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterProjectName.setBounds(167, 234, 164, 110);
		panel.add(lblEnterProjectName);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\blueprint.png"));
		lblNewLabel_2_1.setBounds(99, 289, 62, 72);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblEnterStartDate = new JLabel("Enter Start Date");
		lblEnterStartDate.setForeground(Color.WHITE);
		lblEnterStartDate.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterStartDate.setBounds(167, 354, 164, 110);
		panel.add(lblEnterStartDate);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\finish.png"));
		lblNewLabel_2_1_1.setBounds(99, 409, 62, 72);
		panel.add(lblNewLabel_2_1_1);
		
		//JCTextField txt_sdate = new JCTextField();
		txt_sdate.setSelectionColor(Color.WHITE);
		txt_sdate.setSelectedTextColor(Color.WHITE);
		txt_sdate.setPlaceholder("Enter Start Date");
		txt_sdate.setPhColor(new Color(236, 236, 236));
		txt_sdate.setForeground(Color.WHITE);
		txt_sdate.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_sdate.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_sdate.setBackground(new Color(21, 57, 93));
		txt_sdate.setBounds(167, 432, 303, 32);
		panel.add(txt_sdate);
		
		//JCTextField txt_edate = new JCTextField();
		txt_edate.setSelectionColor(Color.WHITE);
		txt_edate.setSelectedTextColor(Color.WHITE);
		txt_edate.setPlaceholder("Enter End Date");
		txt_edate.setPhColor(new Color(236, 236, 236));
		txt_edate.setForeground(Color.WHITE);
		txt_edate.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_edate.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_edate.setBackground(new Color(21, 57, 93));
		txt_edate.setBounds(167, 552, 303, 32);
		panel.add(txt_edate);
		
		JLabel lblEnterEndDate = new JLabel("Enter End Date");
		lblEnterEndDate.setForeground(Color.WHITE);
		lblEnterEndDate.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterEndDate.setBounds(167, 474, 146, 110);
		panel.add(lblEnterEndDate);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("");
		lblNewLabel_2_1_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\finish.png"));
		lblNewLabel_2_1_2.setBounds(99, 544, 50, 40);
		panel.add(lblNewLabel_2_1_2);
		
		RSMaterialButtonCircle button_add = new RSMaterialButtonCircle();
		button_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (addProject()==true) {
					JOptionPane.showMessageDialog(button_add, "Project Added");
					clearTable();
					setprojectdetailstotable();
				}else {
					JOptionPane.showMessageDialog(button_add, "Project Addition Failed");
				}
			}
		});
		button_add.setText("Add");
		button_add.setFont(new Font("Verdana", Font.PLAIN, 17));
		button_add.setBackground(new Color(85, 150, 206));
		button_add.setBounds(23, 630, 154, 48);
		panel.add(button_add);
		
		RSMaterialButtonCircle button_updated = new RSMaterialButtonCircle();
		button_updated.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (updateproject()==true) {
					JOptionPane.showMessageDialog(button_updated, "Project Updated");
					clearTable();
					setprojectdetailstotable();
				}else {
					JOptionPane.showMessageDialog(button_updated, "Project Updation Failed");
				}
			}
		});
		button_updated.setText("Update");
		button_updated.setFont(new Font("Verdana", Font.PLAIN, 17));
		button_updated.setBackground(new Color(85, 150, 206));
		button_updated.setBounds(207, 630, 154, 48);
		panel.add(button_updated);
		
		RSMaterialButtonCircle mtrlbtncrclDelete = new RSMaterialButtonCircle();
		mtrlbtncrclDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (deleteproject()==true) {
					JOptionPane.showMessageDialog(mtrlbtncrclDelete, "Project Deleted");
					clearTable();
					setprojectdetailstotable();
				}else {
					JOptionPane.showMessageDialog(mtrlbtncrclDelete, "Project Deletion Failed");
				}
			}
		});
		mtrlbtncrclDelete.setText("Delete");
		mtrlbtncrclDelete.setFont(new Font("Verdana", Font.PLAIN, 17));
		mtrlbtncrclDelete.setBackground(new Color(85, 150, 206));
		mtrlbtncrclDelete.setBounds(396, 630, 154, 48);
		panel.add(mtrlbtncrclDelete);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(579, 0, 947, 791);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		project_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNo = project_table.getSelectedRow();
				TableModel model = project_table.getModel();
				
				txt_projectid.setText(model.getValueAt(rowNo, 0).toString());
				txt_projectname.setText(model.getValueAt(rowNo, 1).toString());
				txt_sdate.setText(model.getValueAt(rowNo, 2).toString());
				txt_edate.setText(model.getValueAt(rowNo, 3).toString());
				
			}
		});
		scrollPane.setBounds(86, 212, 753, 470);
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
				"Project ID", "Project Name", "Start Date", "End Date"
			}
		));
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(85, 150, 206));
		panel_1_1.setBounds(810, 0, 110, 47);
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
		panel_3.setBounds(222, 10, 468, 83);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(" Manage Projects");
		lblNewLabel_1.setBounds(62, 10, 379, 63);
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\list1.png"));
		lblNewLabel_1.setForeground(new Color(85, 150, 206));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		
	}
}
