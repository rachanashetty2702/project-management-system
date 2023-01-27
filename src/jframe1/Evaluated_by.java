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

public class Evaluated_by extends JFrame {

	private JPanel contentPane;
	RSTableMetro evaluator_table = new RSTableMetro();
	JCTextField txt_projectid = new JCTextField();
	JCTextField txt_eid = new JCTextField();
	JCTextField text_epoints = new JCTextField();

	/**
	 * Launch the application.
	 */
	String p_id,e_id,e_points;
	DefaultTableModel model;
	public Evaluated_by() {
		initcomponents();
		setprojectdetailstotable();
	}
	
	public void setprojectdetailstotable() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_ms","root","rachana");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from project_ms.evaluated_by");
			
			while(rs.next()) {
				String projectid = rs.getString("Proj_id");
				String eid = rs.getString("Eval_id");
				String epoints = rs.getString("E_points");

				
				Object[] obj = {projectid,eid,epoints};
				model = (DefaultTableModel) evaluator_table.getModel();
				model.addRow(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// to add  project details
	public boolean addProject() {
		boolean isAdded = false;
		p_id = txt_projectid.getText();
		e_id = txt_eid.getText();
		e_points = text_epoints.getText();
		
		try {
			Connection con = DBConnection.getConnection();
			String sql = "insert into project_ms.evaluated_by values(?,?,?)";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1,p_id);
			pst.setString(2, e_id);
			pst.setString(3, e_points);
			
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
		p_id = txt_projectid.getText();
		e_id = txt_eid.getText();
		e_points = text_epoints.getText();
		
		try {
			Connection con = DBConnection.getConnection();
			String sql = "update project_ms.evaluated_by set Eval_id = ?, E_points = ? where Proj_id = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, e_id);
	        pst.setString(2, e_points);
	        pst.setString(3, p_id);
			
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
			String sql = "delete from project_ms.evaluated_by where Proj_id  = ? ";
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
		DefaultTableModel model = (DefaultTableModel) evaluator_table.getModel();
		model.setRowCount(0);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Evaluated_by frame = new Evaluated_by();
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
		lblEnterProjectId.setBounds(165, 179, 164, 110);
		panel.add(lblEnterProjectId);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\AddNewBookIcons\\AddNewBookIcons\\icons8_Contact_26px.png"));
		lblNewLabel_2.setBounds(97, 234, 62, 72);
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
		txt_projectid.setBounds(165, 257, 303, 32);
		panel.add(txt_projectid);
		
		//JCTextField txt_projectname = new JCTextField();
		txt_eid.setSelectionColor(Color.WHITE);
		txt_eid.setSelectedTextColor(Color.WHITE);
		txt_eid.setPlaceholder("Enter Evaluator ID");
		txt_eid.setPhColor(new Color(236, 236, 236));
		txt_eid.setForeground(Color.WHITE);
		txt_eid.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_eid.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_eid.setBackground(new Color(21, 57, 93));
		txt_eid.setBounds(165, 377, 303, 32);
		panel.add(txt_eid);
		
		JLabel lblEnterProjectName = new JLabel("Enter Evaluator ID");
		lblEnterProjectName.setForeground(Color.WHITE);
		lblEnterProjectName.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterProjectName.setBounds(165, 299, 164, 110);
		panel.add(lblEnterProjectName);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\blueprint.png"));
		lblNewLabel_2_1.setBounds(97, 354, 62, 72);
		panel.add(lblNewLabel_2_1);
		
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
		button_add.setBounds(72, 582, 154, 48);
		panel.add(button_add);
		
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
		mtrlbtncrclDelete.setBounds(331, 582, 154, 48);
		panel.add(mtrlbtncrclDelete);
		
		JLabel lblEnterEvaluatorPoints = new JLabel("Enter Evaluator Points");
		lblEnterEvaluatorPoints.setForeground(Color.WHITE);
		lblEnterEvaluatorPoints.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterEvaluatorPoints.setBounds(165, 419, 242, 110);
		panel.add(lblEnterEvaluatorPoints);
		
		
		text_epoints.setSelectionColor(Color.WHITE);
		text_epoints.setSelectedTextColor(Color.WHITE);
		text_epoints.setPlaceholder("Enter Evaluator Points");
		text_epoints.setPhColor(new Color(236, 236, 236));
		text_epoints.setForeground(Color.WHITE);
		text_epoints.setFont(new Font("Verdana", Font.PLAIN, 16));
		text_epoints.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		text_epoints.setBackground(new Color(21, 57, 93));
		text_epoints.setBounds(165, 497, 303, 32);
		panel.add(text_epoints);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\certificate.png"));
		lblNewLabel_2_1_1.setBounds(97, 474, 62, 72);
		panel.add(lblNewLabel_2_1_1);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(579, 0, 947, 791);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		evaluator_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNo = evaluator_table.getSelectedRow();
				TableModel model = evaluator_table.getModel();
				
				txt_projectid.setText(model.getValueAt(rowNo, 0).toString());
				txt_eid.setText(model.getValueAt(rowNo, 1).toString());
				text_epoints.setText(model.getValueAt(rowNo, 2).toString());
			}
		});
		scrollPane.setBounds(86, 212, 753, 470);
		panel_2.add(scrollPane);
		
		//RSTableMetro project_table = new RSTableMetro();
		evaluator_table.setForeground(new Color(0, 0, 0));
		evaluator_table.setRowHeight(40);
		evaluator_table.setColorSelBackgound(new Color(85, 150, 206));
		evaluator_table.setColorFilasBackgound2(new Color(255, 255, 255));
		evaluator_table.setFuenteHead(new Font("Verdana", Font.PLAIN, 20));
		evaluator_table.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		evaluator_table.setColorBackgoundHead(new Color(21, 57, 93));
		evaluator_table.setColorFilasForeground2(new Color(21, 57, 93));
		evaluator_table.setColorFilasForeground1(new Color(21, 57, 93));
		scrollPane.setViewportView(evaluator_table);
		evaluator_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Project ID", "Evaluator ID", "E_points"
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
		panel_3.setBounds(200, 0, 543, 93);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(" Project Evaluated By");
		lblNewLabel_1.setBounds(64, 20, 394, 63);
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\check1.png"));
		lblNewLabel_1.setForeground(new Color(85, 150, 206));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		
	}
}
