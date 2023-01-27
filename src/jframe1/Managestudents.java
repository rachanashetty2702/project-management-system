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
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class Managestudents extends JFrame {

	private JPanel contentPane;
	RSTableMetro student_table = new RSTableMetro();
	JCTextField txt_usn = new JCTextField();
	JCTextField txt_fname = new JCTextField();
	JCTextField txt_mname = new JCTextField();
	JCTextField txt_lname = new JCTextField();
	JCTextField txt_bdate = new JCTextField();
	JCTextField txt_address = new JCTextField();
	JCTextField txt_gender = new JCTextField();
	JCTextField txt_email = new JCTextField();
	JComboBox comboBox = new JComboBox();

	/**
	 * Launch the application.
	 */
	String usn,first_name,middle_name,last_name,address,gender,email,D_id;
	Date birth_date;
	DefaultTableModel model;
	public Managestudents() {
		initcomponents();
		setstudentdetailstotable();
	}
	
	public void setstudentdetailstotable() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_ms","root","rachana");
			Statement st = con.createStatement();
			ResultSet rs = st.executeQuery("select * from students");
			
			while(rs.next()) {
				String usn = rs.getString("usn");
				String firstname = rs.getString("first_name");
				String middlename = rs.getString("middle_name");
				String lastname = rs.getString("last_name");
				Date birthdate = rs.getDate("birth_date");
				String address = rs.getString("address");
				String gender = rs.getString("gender");
				String email = rs.getString("email");
				String Departmentid = rs.getString("D_id");
				
				long l1 = birthdate.getTime();
				//long l2 = enddate.getTime();
				
				java.sql.Date bbirthdate = new java.sql.Date(l1);
				//java.sql.Date senddate = new java.sql.Date(l2);
				
				Object[] obj = {usn, firstname, middlename, lastname, birthdate, address, gender, email, Departmentid};
				model = (DefaultTableModel) student_table.getModel();
				model.addRow(obj);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// to add  project details
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
	
	//update
	public boolean updatestudent() {
		boolean isUpdated = false;
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
			String sql = "update project_ms.students set first_name = ?, middle_name = ?, last_name = ?,birth_date = ?,address = ?, gender = ? , email=?, D_id = ? where usn = ?";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, first_name);
			pst.setString(2, middle_name);
			pst.setString(3, last_name);
			pst.setDate(4, dbirthdate);
			pst.setString(5, address);
			pst.setString(6, gender);
			pst.setString(7, email);
			pst.setString(8, D_id);
			pst.setString(9,usn);
			
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
	
	public boolean deletestudent() {
		boolean isDeleted = false;
		usn = txt_usn.getText();
		try {
			Connection con = DBConnection.getConnection();
			String sql = "delete from project_ms.students where usn = ? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, usn);
			
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
		DefaultTableModel model = (DefaultTableModel) student_table.getModel();
		model.setRowCount(0);
	}
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Managestudents frame = new Managestudents();
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
		panel.setBounds(0, 0, 540, 801);
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
		
		JLabel lblEnterProjectId = new JLabel("Enter USN");
		lblEnterProjectId.setForeground(Color.WHITE);
		lblEnterProjectId.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterProjectId.setBounds(167, 10, 164, 110);
		panel.add(lblEnterProjectId);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\id-card.png"));
		lblNewLabel_2.setBounds(99, 65, 62, 72);
		panel.add(lblNewLabel_2);
		
		//JCTextField txt_projectid = new JCTextField();
		txt_usn.setSelectionColor(Color.WHITE);
		txt_usn.setSelectedTextColor(Color.WHITE);
		txt_usn.setPlaceholder("Enter USN");
		txt_usn.setPhColor(new Color(236, 236, 236));
		txt_usn.setForeground(Color.WHITE);
		txt_usn.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_usn.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_usn.setBackground(new Color(21, 57, 93));
		txt_usn.setBounds(167, 88, 303, 32);
		panel.add(txt_usn);
		
		//JCTextField txt_projectname = new JCTextField();
		txt_fname.setSelectionColor(Color.WHITE);
		txt_fname.setSelectedTextColor(Color.WHITE);
		txt_fname.setPlaceholder("Enter First Name");
		txt_fname.setPhColor(new Color(236, 236, 236));
		txt_fname.setForeground(Color.WHITE);
		txt_fname.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_fname.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_fname.setBackground(new Color(21, 57, 93));
		txt_fname.setBounds(167, 166, 303, 32);
		panel.add(txt_fname);
		
		JLabel lblEnterProjectName = new JLabel("Enter First Name");
		lblEnterProjectName.setForeground(Color.WHITE);
		lblEnterProjectName.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterProjectName.setBounds(167, 88, 164, 110);
		panel.add(lblEnterProjectName);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\user.png"));
		lblNewLabel_2_1.setBounds(99, 143, 62, 72);
		panel.add(lblNewLabel_2_1);
		
		JLabel lblEnterStartDate = new JLabel("Enter Middle Name");
		lblEnterStartDate.setForeground(Color.WHITE);
		lblEnterStartDate.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterStartDate.setBounds(167, 159, 164, 110);
		panel.add(lblEnterStartDate);
		
		JLabel lblNewLabel_2_1_1 = new JLabel("");
		lblNewLabel_2_1_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\user.png"));
		lblNewLabel_2_1_1.setBounds(99, 214, 62, 72);
		panel.add(lblNewLabel_2_1_1);
		
		//JCTextField txt_sdate = new JCTextField();
		txt_mname.setSelectionColor(Color.WHITE);
		txt_mname.setSelectedTextColor(Color.WHITE);
		txt_mname.setPlaceholder("Enter Middle Name");
		txt_mname.setPhColor(new Color(236, 236, 236));
		txt_mname.setForeground(Color.WHITE);
		txt_mname.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_mname.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_mname.setBackground(new Color(21, 57, 93));
		txt_mname.setBounds(167, 237, 303, 32);
		panel.add(txt_mname);
		
		//JCTextField txt_edate = new JCTextField();
		txt_lname.setSelectionColor(Color.WHITE);
		txt_lname.setSelectedTextColor(Color.WHITE);
		txt_lname.setPlaceholder("Enter Last Name");
		txt_lname.setPhColor(new Color(236, 236, 236));
		txt_lname.setForeground(Color.WHITE);
		txt_lname.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_lname.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_lname.setBackground(new Color(21, 57, 93));
		txt_lname.setBounds(167, 310, 303, 32);
		panel.add(txt_lname);
		
		JLabel lblEnterEndDate = new JLabel("Enter Last Name");
		lblEnterEndDate.setForeground(Color.WHITE);
		lblEnterEndDate.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterEndDate.setBounds(167, 232, 146, 110);
		panel.add(lblEnterEndDate);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("");
		lblNewLabel_2_1_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\user.png"));
		lblNewLabel_2_1_2.setBounds(99, 302, 50, 40);
		panel.add(lblNewLabel_2_1_2);
		
		RSMaterialButtonCircle button_add = new RSMaterialButtonCircle();
		button_add.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (addStudent()==true) {
					JOptionPane.showMessageDialog(button_add, "Student Added");
					clearTable();
					setstudentdetailstotable();
				}else {
					JOptionPane.showMessageDialog(button_add, "Student Addition Failed");
				}
			}
		});
		button_add.setText("Add");
		button_add.setFont(new Font("Verdana", Font.PLAIN, 17));
		button_add.setBackground(new Color(85, 150, 206));
		button_add.setBounds(41, 725, 134, 48);
		panel.add(button_add);
		
		RSMaterialButtonCircle button_updated = new RSMaterialButtonCircle();
		button_updated.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (updatestudent()==true) {
					JOptionPane.showMessageDialog(button_updated, "Student Updated");
					clearTable();
					setstudentdetailstotable();
				}else {
					JOptionPane.showMessageDialog(button_updated, "tudent Updation Failed");
				}
			}
		});
		button_updated.setText("Update");
		button_updated.setFont(new Font("Verdana", Font.PLAIN, 17));
		button_updated.setBackground(new Color(85, 150, 206));
		button_updated.setBounds(185, 725, 134, 48);
		panel.add(button_updated);
		
		RSMaterialButtonCircle button_delete = new RSMaterialButtonCircle();
		button_delete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (deletestudent()==true) {
					JOptionPane.showMessageDialog(button_delete, "Student Deleted");
					clearTable();
					setstudentdetailstotable();
				}else {
					JOptionPane.showMessageDialog(button_delete, "Student Deletion Failed");
				}
			}
		});
		button_delete.setText("Delete");
		button_delete.setFont(new Font("Verdana", Font.PLAIN, 17));
		button_delete.setBackground(new Color(85, 150, 206));
		button_delete.setBounds(336, 725, 134, 48);
		panel.add(button_delete);
		
		JLabel lblEnterBirthDate = new JLabel("Enter Birth Date");
		lblEnterBirthDate.setForeground(Color.WHITE);
		lblEnterBirthDate.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterBirthDate.setBounds(167, 306, 146, 110);
		panel.add(lblEnterBirthDate);
		
		JLabel lblNewLabel_2_1_2_1 = new JLabel("");
		lblNewLabel_2_1_2_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\finish.png"));
		lblNewLabel_2_1_2_1.setBounds(99, 376, 50, 40);
		panel.add(lblNewLabel_2_1_2_1);
		
		
		txt_bdate.setSelectionColor(Color.WHITE);
		txt_bdate.setSelectedTextColor(Color.WHITE);
		txt_bdate.setPlaceholder("Enter Birth Date");
		txt_bdate.setPhColor(new Color(236, 236, 236));
		txt_bdate.setForeground(Color.WHITE);
		txt_bdate.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_bdate.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_bdate.setBackground(new Color(21, 57, 93));
		txt_bdate.setBounds(167, 384, 303, 32);
		panel.add(txt_bdate);
		
		JLabel lblEnterAddress = new JLabel("Enter Address ");
		lblEnterAddress.setForeground(Color.WHITE);
		lblEnterAddress.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterAddress.setBounds(167, 376, 146, 110);
		panel.add(lblEnterAddress);
		
		JLabel lblNewLabel_2_1_2_2 = new JLabel("");
		lblNewLabel_2_1_2_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\maps-and-flags.png"));
		lblNewLabel_2_1_2_2.setBounds(99, 446, 50, 40);
		panel.add(lblNewLabel_2_1_2_2);
		
		
		txt_address.setSelectionColor(Color.WHITE);
		txt_address.setSelectedTextColor(Color.WHITE);
		txt_address.setPlaceholder("Enter Address ");
		txt_address.setPhColor(new Color(236, 236, 236));
		txt_address.setForeground(Color.WHITE);
		txt_address.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_address.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_address.setBackground(new Color(21, 57, 93));
		txt_address.setBounds(167, 454, 303, 32);
		panel.add(txt_address);
		
		JLabel lblEnterGender = new JLabel("Enter Gender");
		lblEnterGender.setForeground(Color.WHITE);
		lblEnterGender.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterGender.setBounds(167, 449, 146, 110);
		panel.add(lblEnterGender);
		
		JLabel lblNewLabel_2_1_2_3 = new JLabel("");
		lblNewLabel_2_1_2_3.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\gender.png"));
		lblNewLabel_2_1_2_3.setBounds(99, 519, 50, 40);
		panel.add(lblNewLabel_2_1_2_3);
		
		
		txt_gender.setSelectionColor(Color.WHITE);
		txt_gender.setSelectedTextColor(Color.WHITE);
		txt_gender.setPlaceholder("Enter Gender");
		txt_gender.setPhColor(new Color(236, 236, 236));
		txt_gender.setForeground(Color.WHITE);
		txt_gender.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_gender.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_gender.setBackground(new Color(21, 57, 93));
		txt_gender.setBounds(167, 527, 303, 32);
		panel.add(txt_gender);
		
		JLabel lblEnterEmail = new JLabel("Enter Email");
		lblEnterEmail.setForeground(Color.WHITE);
		lblEnterEmail.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblEnterEmail.setBounds(167, 519, 146, 110);
		panel.add(lblEnterEmail);
		
		JLabel lblNewLabel_2_1_2_4 = new JLabel("");
		lblNewLabel_2_1_2_4.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\email.png"));
		lblNewLabel_2_1_2_4.setBounds(99, 589, 50, 40);
		panel.add(lblNewLabel_2_1_2_4);
		
		
		txt_email.setSelectionColor(Color.WHITE);
		txt_email.setSelectedTextColor(Color.WHITE);
		txt_email.setPlaceholder("Enter Email");
		txt_email.setPhColor(new Color(236, 236, 236));
		txt_email.setForeground(Color.WHITE);
		txt_email.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_email.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_email.setBackground(new Color(21, 57, 93));
		txt_email.setBounds(167, 597, 303, 32);
		panel.add(txt_email);
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Department", "D1", "D2", "D3", "D4", "D5"}));
		comboBox.setToolTipText("");
		comboBox.setForeground(Color.BLACK);
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 16));
		comboBox.setBounds(153, 683, 303, 32);
		panel.add(comboBox);
		
		JLabel lblSelectDepartmentId = new JLabel("Select  Department ID");
		lblSelectDepartmentId.setForeground(Color.WHITE);
		lblSelectDepartmentId.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblSelectDepartmentId.setBounds(153, 605, 217, 110);
		panel.add(lblSelectDepartmentId);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\hierarchy.png"));
		lblNewLabel_3.setBounds(99, 666, 50, 48);
		panel.add(lblNewLabel_3);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(538, 0, 988, 791);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		student_table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int rowNo = student_table.getSelectedRow();
				TableModel model = student_table.getModel();
				
				txt_usn.setText(model.getValueAt(rowNo, 0).toString());
				txt_fname.setText(model.getValueAt(rowNo, 1).toString());
				txt_mname.setText(model.getValueAt(rowNo, 2).toString());
				txt_lname.setText(model.getValueAt(rowNo, 3).toString());
				txt_bdate.setText(model.getValueAt(rowNo, 4).toString());
				txt_address.setText(model.getValueAt(rowNo, 5).toString());
				txt_gender.setText(model.getValueAt(rowNo, 6).toString());
				txt_email.setText(model.getValueAt(rowNo, 7).toString());
				comboBox.setSelectedItem(model.getValueAt(rowNo, 8).toString());
				
			}
		});
		scrollPane.setBounds(29, 212, 924, 470);
		panel_2.add(scrollPane);
		
		//RSTableMetro project_table = new RSTableMetro();
		student_table.setForeground(new Color(0, 0, 0));
		student_table.setRowHeight(40);
		student_table.setColorSelBackgound(new Color(85, 150, 206));
		student_table.setColorFilasBackgound2(new Color(255, 255, 255));
		student_table.setFuenteHead(new Font("Verdana", Font.PLAIN, 15));
		student_table.setFont(new Font("Yu Gothic Light", Font.PLAIN, 25));
		student_table.setColorBackgoundHead(new Color(21, 57, 93));
		student_table.setColorFilasForeground2(new Color(21, 57, 93));
		student_table.setColorFilasForeground1(new Color(21, 57, 93));
		scrollPane.setViewportView(student_table);
		student_table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"USN", "First Name", "Middle Name", "Last Name", "Birth Date", "Address", "Gender", "Email", "D_id"
			}
		));
		student_table.getColumnModel().getColumn(6).setPreferredWidth(59);
		student_table.getColumnModel().getColumn(7).setPreferredWidth(121);
		student_table.getColumnModel().getColumn(8).setPreferredWidth(45);
		
		JPanel panel_1_1 = new JPanel();
		panel_1_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		panel_1_1.setLayout(null);
		panel_1_1.setBackground(new Color(85, 150, 206));
		panel_1_1.setBounds(868, 0, 110, 47);
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
		panel_3.setBounds(222, 30, 468, 63);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel(" Manage Students");
		lblNewLabel_1.setBounds(79, -10, 379, 63);
		panel_3.add(lblNewLabel_1);
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\project.png"));
		lblNewLabel_1.setForeground(new Color(85, 150, 206));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		
	}
}
