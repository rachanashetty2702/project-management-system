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

public class Projectresources extends JFrame {

	private JPanel contentPane;
	RSTableMetro project_table = new RSTableMetro();
	JCTextField txt_projectid = new JCTextField();
	JCTextField txt_resourceid = new JCTextField();
	JCTextField txt_projectname = new JCTextField();
	JCTextField txt_software = new JCTextField();
	JCTextField txt_hardware = new JCTextField();

	/**
	 * Launch the application.
	 */
	String p_id,r_id,p_name, software,hardware;
	DefaultTableModel model;
	public Projectresources() {
		initcomponents();
		setprojectresourcestotable();
	}
	
	public void setprojectresourcestotable() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_ms","root","rachana");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from resources");
			
			while(rs.next()) {
				String projectid = rs.getString("P_id");
				String resourceid = rs.getString("R_id");
				String projectname = rs.getString("pname");
				String sw = rs.getString("software");
				String hw = rs.getString("hardware");
				
				Object[] obj = {projectid,resourceid,projectname,sw,hw};
				model = (DefaultTableModel) project_table.getModel();
				model.addRow(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// to add  project details
	public boolean addResources() {
		boolean isAdded = false;
		p_id = txt_projectid.getText();
		r_id = txt_resourceid.getText();
		p_name = txt_projectname.getText();
		software = txt_software.getText();
		hardware = txt_hardware.getText();
		
		try {
			Connection con = DBConnection.getConnection();
			String sql = "insert into project_ms.resources values(?,?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,p_id);
			pst.setString(2, r_id);
			pst.setString(3, p_name);
			pst.setString(4, software);
			pst.setString(5, hardware);
			
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
	public boolean updateresources() {
		boolean isUpdated = false;
		p_id = txt_projectid.getText();
		r_id = txt_resourceid.getText();
		p_name = txt_projectname.getText();
		software = txt_software.getText();
		hardware = txt_hardware.getText();
		
		
		try {
			Connection con = DBConnection.getConnection();
			String sql = "update project_ms.resources set p_id = ?, pname = ?, software = ?, hardware = ? where r_id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,p_id);
			pst.setString(2, p_name);
			pst.setString(3, software);
			pst.setString(4, hardware);
			pst.setString(5, r_id);
			
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
	
	public boolean deleteresources() {
		boolean isDeleted = false;
		r_id = txt_resourceid.getText();
		try {
			Connection con = DBConnection.getConnection();
			String sql = "delete from project_ms.resources where r_id = ? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, r_id);
			
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
					Projectresources frame = new Projectresources();
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
		lblEnterProjectId.setBounds(166, 57, 164, 110);
		panel.add(lblEnterProjectId);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\AddNewBookIcons\\AddNewBookIcons\\icons8_Contact_26px.png"));
		lblNewLabel_2.setBounds(98, 112, 62, 72);
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
		txt_projectid.setBounds(166, 135, 303, 32);
		panel.add(txt_projectid);
		
		//JCTextField txt_projectname = new JCTextField();
		txt_resourceid.setSelectionColor(Color.WHITE);
		txt_resourceid.setSelectedTextColor(Color.WHITE);
		txt_resourceid.setPlaceholder("Enter Resource ID");
		txt_resourceid.setPhColor(new Color(236, 236, 236));
		txt_resourceid.setForeground(Color.WHITE);
		txt_resourceid.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_resourceid.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_resourceid.setBackground(new Color(21, 57, 93));
		txt_resourceid.setBounds(166, 255, 303, 32);
		panel.add(txt_resourceid);
		
		JLabel lblEnterProjectName = new JLabel("Enter Resource ID");
		lblEnterProjectName.setForeground(Color.WHITE);
		lblEnterProjectName.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterProjectName.setBounds(166, 177, 164, 110);
		panel.add(lblEnterProjectName);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\human-resources.png"));
		lblNewLabel_2_1.setBounds(98, 232, 62, 72);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblEnterStartDate = new JLabel("Enter Project Name");
		lblEnterStartDate.setForeground(Color.WHITE);
		lblEnterStartDate.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterStartDate.setBounds(166, 297, 164, 110);
		panel.add(lblEnterStartDate);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\blueprint.png"));
		lblNewLabel_2_1_1.setBounds(98, 352, 62, 72);
		panel.add(lblNewLabel_2_1_1);
		
		//JCTextField txt_sdate = new JCTextField();
		txt_projectname.setSelectionColor(Color.WHITE);
		txt_projectname.setSelectedTextColor(Color.WHITE);
		txt_projectname.setPlaceholder("Enter Project Name");
		txt_projectname.setPhColor(new Color(236, 236, 236));
		txt_projectname.setForeground(Color.WHITE);
		txt_projectname.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_projectname.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_projectname.setBackground(new Color(21, 57, 93));
		txt_projectname.setBounds(166, 375, 303, 32);
		panel.add(txt_projectname);
		
		//JCTextField txt_edate = new JCTextField();
		txt_software.setSelectionColor(Color.WHITE);
		txt_software.setSelectedTextColor(Color.WHITE);
		txt_software.setPlaceholder("Enter Software Details");
		txt_software.setPhColor(new Color(236, 236, 236));
		txt_software.setForeground(Color.WHITE);
		txt_software.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_software.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_software.setBackground(new Color(21, 57, 93));
		txt_software.setBounds(166, 495, 303, 32);
		panel.add(txt_software);
		
		JLabel lblEnterEndDate = new JLabel("Enter Software Details");
		lblEnterEndDate.setForeground(Color.WHITE);
		lblEnterEndDate.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterEndDate.setBounds(166, 417, 195, 110);
		panel.add(lblEnterEndDate);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("");
		lblNewLabel_2_1_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\processor.png"));
		lblNewLabel_2_1_2.setBounds(98, 487, 50, 40);
		panel.add(lblNewLabel_2_1_2);
		
		RSMaterialButtonCircle button_add = new RSMaterialButtonCircle();
		button_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (addResources()==true) {
					JOptionPane.showMessageDialog(button_add, "Resources Added");
					clearTable();
					setprojectresourcestotable();
				}else {
					JOptionPane.showMessageDialog(button_add, "Resource Addition Failed");
				}
			}
		});
		button_add.setText("Add");
		button_add.setFont(new Font("Verdana", Font.PLAIN, 17));
		button_add.setBackground(new Color(85, 150, 206));
		button_add.setBounds(23, 660, 154, 48);
		panel.add(button_add);
		
		RSMaterialButtonCircle button_updated = new RSMaterialButtonCircle();
		button_updated.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (updateresources()==true) {
					JOptionPane.showMessageDialog(button_updated, "Resources Updated");
					clearTable();
					setprojectresourcestotable();
				}else {
					JOptionPane.showMessageDialog(button_updated, "Resource Updation Failed");
				}
			}
		});
		button_updated.setText("Update");
		button_updated.setFont(new Font("Verdana", Font.PLAIN, 17));
		button_updated.setBackground(new Color(85, 150, 206));
		button_updated.setBounds(207, 660, 154, 48);
		panel.add(button_updated);
		
		RSMaterialButtonCircle mtrlbtncrclDelete = new RSMaterialButtonCircle();
		mtrlbtncrclDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (deleteresources()==true) {
					JOptionPane.showMessageDialog(mtrlbtncrclDelete, "Resources Deleted");
					clearTable();
					setprojectresourcestotable();
				}else {
					JOptionPane.showMessageDialog(mtrlbtncrclDelete, "Resource Deletion Failed");
				}
			}
		});
		mtrlbtncrclDelete.setText("Delete");
		mtrlbtncrclDelete.setFont(new Font("Verdana", Font.PLAIN, 17));
		mtrlbtncrclDelete.setBackground(new Color(85, 150, 206));
		mtrlbtncrclDelete.setBounds(396, 660, 154, 48);
		panel.add(mtrlbtncrclDelete);
		
		JLabel lblEnterHardwareDetails = new JLabel("Enter Hardware Details");
		lblEnterHardwareDetails.setForeground(Color.WHITE);
		lblEnterHardwareDetails.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterHardwareDetails.setBounds(166, 514, 215, 110);
		panel.add(lblEnterHardwareDetails);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("");
		lblNewLabel_2_1_2_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\software.png"));
		lblNewLabel_2_1_2_1.setBounds(98, 584, 50, 40);
		panel.add(lblNewLabel_2_1_2_1);
		
		
		txt_hardware.setSelectionColor(Color.WHITE);
		txt_hardware.setSelectedTextColor(Color.WHITE);
		txt_hardware.setPlaceholder("Enter Hardware Details");
		txt_hardware.setPhColor(new Color(236, 236, 236));
		txt_hardware.setForeground(Color.WHITE);
		txt_hardware.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_hardware.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_hardware.setBackground(new Color(21, 57, 93));
		txt_hardware.setBounds(166, 592, 303, 32);
		panel.add(txt_hardware);
		
		
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
				txt_resourceid.setText(model.getValueAt(rowNo, 1).toString());
				txt_projectname.setText(model.getValueAt(rowNo, 2).toString());
				txt_software.setText(model.getValueAt(rowNo, 3).toString());
				txt_hardware.setText(model.getValueAt(rowNo, 4).toString());
				
			}
		});
		scrollPane.setBounds(67, 212, 827, 470);
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
				"Project ID", "Resource ID", "Project Name", "Software", "Hardware"
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
		panel_3.setBounds(222, 0, 468, 102);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(" Project Resources");
		lblNewLabel_1.setBounds(56, 0, 425, 103);
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\teamwork1.png"));
		lblNewLabel_1.setForeground(new Color(85, 150, 206));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		
	}
}
