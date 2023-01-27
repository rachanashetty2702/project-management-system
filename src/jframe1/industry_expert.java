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

public class industry_expert extends JFrame {

	private JPanel contentPane;
	RSTableMetro expert_table = new RSTableMetro();
	JCTextField txt_eid = new JCTextField();
	JCTextField txt_ename = new JCTextField();
	JCTextField txt_expertise = new JCTextField();
	JCTextField txt_experience = new JCTextField();

	/**
	 * Launch the application.
	 */
	String e_id,i_name,i_expertise,i_experience;
	DefaultTableModel model;
	public industry_expert() {
		initcomponents();
		setprojectresourcestotable();
	}
	
	public void setprojectresourcestotable() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_ms","root","rachana");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from industry_expert");
			
			while(rs.next()) {
				String experttid = rs.getString("I_id");
				String name = rs.getString("I_name");
				String expertise = rs.getString("I_expertise");
				String experience = rs.getString("Experience");
				
				Object[] obj = {experttid,name,expertise,experience};
				model = (DefaultTableModel) expert_table.getModel();
				model.addRow(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// to add  project details
	public boolean addResources() {
		boolean isAdded = false;
		e_id = txt_eid.getText();
		i_name = txt_ename.getText();
		i_expertise = txt_expertise.getText();
		i_experience = txt_experience.getText();
		
		try {
			Connection con = DBConnection.getConnection();
			String sql = "insert into project_ms.industry_expert values(?,?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,e_id);
			pst.setString(2, i_name);
			pst.setString(3, i_expertise);
			pst.setString(4, i_experience);
			
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
		e_id = txt_eid.getText();
		i_name = txt_ename.getText();
		i_expertise = txt_expertise.getText();
		i_experience = txt_experience.getText();
		
		
		try {
			Connection con = DBConnection.getConnection();
			String sql = "update project_ms.industry_expert set I_name = ?, I_expertise = ?, Experience = ? where I_id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,i_name);
			pst.setString(2, i_expertise);
			pst.setString(3, i_experience);
			pst.setString(4, e_id);
			
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
		e_id = txt_eid.getText();
		try {
			Connection con = DBConnection.getConnection();
			String sql = "delete from project_ms.industry_expert where I_id = ? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, e_id);
			
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
		DefaultTableModel model = (DefaultTableModel) expert_table.getModel();
		model.setRowCount(0);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					industry_expert frame = new industry_expert();
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
		
		JLabel lblEnterProjectId = new JLabel("Enter Industry Expert ID");
		lblEnterProjectId.setForeground(Color.WHITE);
		lblEnterProjectId.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterProjectId.setBounds(164, 99, 215, 110);
		panel.add(lblEnterProjectId);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\engineering.png"));
		lblNewLabel_2.setBounds(96, 154, 62, 72);
		panel.add(lblNewLabel_2);
		
		//JCTextField txt_projectid = new JCTextField();
		txt_eid.setSelectionColor(Color.WHITE);
		txt_eid.setSelectedTextColor(Color.WHITE);
		txt_eid.setPlaceholder("Enter E_ID");
		txt_eid.setPhColor(new Color(236, 236, 236));
		txt_eid.setForeground(Color.WHITE);
		txt_eid.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_eid.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_eid.setBackground(new Color(21, 57, 93));
		txt_eid.setBounds(164, 177, 303, 32);
		panel.add(txt_eid);
		
		//JCTextField txt_projectname = new JCTextField();
		txt_ename.setSelectionColor(Color.WHITE);
		txt_ename.setSelectedTextColor(Color.WHITE);
		txt_ename.setPlaceholder("Enter Industry Expert name");
		txt_ename.setPhColor(new Color(236, 236, 236));
		txt_ename.setForeground(Color.WHITE);
		txt_ename.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_ename.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_ename.setBackground(new Color(21, 57, 93));
		txt_ename.setBounds(164, 297, 303, 32);
		panel.add(txt_ename);
		
		JLabel lblEnterProjectName = new JLabel("Enter Industry Expert Name");
		lblEnterProjectName.setForeground(Color.WHITE);
		lblEnterProjectName.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterProjectName.setBounds(164, 219, 247, 110);
		panel.add(lblEnterProjectName);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\id-card.png"));
		lblNewLabel_2_1.setBounds(96, 274, 62, 72);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblEnterStartDate = new JLabel("Enter Expertise");
		lblEnterStartDate.setForeground(Color.WHITE);
		lblEnterStartDate.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterStartDate.setBounds(164, 339, 164, 110);
		panel.add(lblEnterStartDate);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\certificate.png"));
		lblNewLabel_2_1_1.setBounds(96, 394, 62, 72);
		panel.add(lblNewLabel_2_1_1);
		
		//JCTextField txt_sdate = new JCTextField();
		txt_expertise.setSelectionColor(Color.WHITE);
		txt_expertise.setSelectedTextColor(Color.WHITE);
		txt_expertise.setPlaceholder("Enter Expertise");
		txt_expertise.setPhColor(new Color(236, 236, 236));
		txt_expertise.setForeground(Color.WHITE);
		txt_expertise.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_expertise.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_expertise.setBackground(new Color(21, 57, 93));
		txt_expertise.setBounds(164, 417, 303, 32);
		panel.add(txt_expertise);
		
		//JCTextField txt_edate = new JCTextField();
		txt_experience.setSelectionColor(Color.WHITE);
		txt_experience.setSelectedTextColor(Color.WHITE);
		txt_experience.setPlaceholder("Enter Experience Details");
		txt_experience.setPhColor(new Color(236, 236, 236));
		txt_experience.setForeground(Color.WHITE);
		txt_experience.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_experience.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_experience.setBackground(new Color(21, 57, 93));
		txt_experience.setBounds(164, 537, 303, 32);
		panel.add(txt_experience);
		
		JLabel lblEnterEndDate = new JLabel("Enter Experience Details");
		lblEnterEndDate.setForeground(Color.WHITE);
		lblEnterEndDate.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterEndDate.setBounds(164, 459, 259, 110);
		panel.add(lblEnterEndDate);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("");
		lblNewLabel_2_1_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\user.png"));
		lblNewLabel_2_1_2.setBounds(96, 529, 50, 40);
		panel.add(lblNewLabel_2_1_2);
		
		RSMaterialButtonCircle button_add = new RSMaterialButtonCircle();
		button_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (addResources()==true) {
					JOptionPane.showMessageDialog(button_add, "Details Added");
					clearTable();
					setprojectresourcestotable();
				}else {
					JOptionPane.showMessageDialog(button_add, "Details Addition Failed");
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
					JOptionPane.showMessageDialog(button_updated, "Details Updated");
					clearTable();
					setprojectresourcestotable();
				}else {
					JOptionPane.showMessageDialog(button_updated, "Details Updation Failed");
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
					JOptionPane.showMessageDialog(mtrlbtncrclDelete, "Details Deleted");
					clearTable();
					setprojectresourcestotable();
				}else {
					JOptionPane.showMessageDialog(mtrlbtncrclDelete, "Details Deletion Failed");
				}
			}
		});
		mtrlbtncrclDelete.setText("Delete");
		mtrlbtncrclDelete.setFont(new Font("Verdana", Font.PLAIN, 17));
		mtrlbtncrclDelete.setBackground(new Color(85, 150, 206));
		mtrlbtncrclDelete.setBounds(396, 660, 154, 48);
		panel.add(mtrlbtncrclDelete);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(579, 0, 947, 791);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		expert_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNo = expert_table.getSelectedRow();
				TableModel model = expert_table.getModel();
				
				txt_eid.setText(model.getValueAt(rowNo, 0).toString());
				txt_ename.setText(model.getValueAt(rowNo, 1).toString());
				txt_expertise.setText(model.getValueAt(rowNo, 2).toString());
				txt_experience.setText(model.getValueAt(rowNo, 3).toString());
				
			}
		});
		scrollPane.setBounds(67, 212, 827, 470);
		panel_2.add(scrollPane);
		
		//RSTableMetro project_table = new RSTableMetro();
		expert_table.setForeground(new Color(0, 0, 0));
		expert_table.setRowHeight(40);
		expert_table.setColorSelBackgound(new Color(85, 150, 206));
		expert_table.setColorFilasBackgound2(new Color(255, 255, 255));
		expert_table.setFuenteHead(new Font("Verdana", Font.PLAIN, 20));
		expert_table.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		expert_table.setColorBackgoundHead(new Color(21, 57, 93));
		expert_table.setColorFilasForeground2(new Color(21, 57, 93));
		expert_table.setColorFilasForeground1(new Color(21, 57, 93));
		scrollPane.setViewportView(expert_table);
		expert_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"I_ID", "I_Name", "Expertise", "Experience"
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
		panel_3.setBounds(210, 0, 522, 102);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(" Industry Expert Details");
		lblNewLabel_1.setBounds(24, 0, 461, 103);
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\i_expert.png"));
		lblNewLabel_1.setForeground(new Color(85, 150, 206));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		
	}
}
