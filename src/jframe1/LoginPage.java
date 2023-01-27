package jframe1;

import java.awt.BorderLayout;
import java.sql.*;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.ImageIcon;
import app.bolivia.swing.JCTextField;
import javax.swing.border.MatteBorder;
import necesario.RSMaterialButtonCircle;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;

public class LoginPage extends JFrame {

	private JPanel contentPane;
	private JCTextField txt_username= new JCTextField();
	private JCTextField txt_password= new JCTextField();
	JComboBox comboBox = new JComboBox();
	private final Action action = new SwingAction();

	/**
	 * Launch the application.
	 */
	public LoginPage() {
		setUndecorated(true);
		initialize();
	}

	
	
	//validation
	public boolean validateLogin() {
		String name = txt_username.getText();
		String pwd = txt_password.getText();
		String option = comboBox.getSelectedItem().toString();
		
		if (name.equals("")) {
			JOptionPane.showMessageDialog(this,"please enter username");
			return false;
		}
		if (pwd.equals("")) {
			JOptionPane.showMessageDialog(this,"please enter password");
			return false;
		}
		if (option.equals("Select")){
			JOptionPane.showMessageDialog(this,"please select user");
			return false;
		}
		return true;
	}
	
	public void login() {
		String name = txt_username.getText();
		String pwd = txt_password.getText();
		String option = comboBox.getSelectedItem().toString();
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/project_ms","root","rachana");
			PreparedStatement pst = con.prepareStatement("select * from users where name = ? and password = ? and user1 = ?");
			
			pst.setString(1, name);
			pst.setString(2, pwd);
			pst.setString(3, option);
			
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				JOptionPane.showMessageDialog(this, "login sucessful");
				String s1 = rs.getString("user1");
				if(option.equalsIgnoreCase("Teacher") && s1.equalsIgnoreCase("Teacher")) {
					Homepage home = new Homepage();
					home.setVisible(true);
					this.dispose();
				}
				if(option.equalsIgnoreCase("Student") && s1.equalsIgnoreCase("Student")) {
					Homepage2 home2 = new Homepage2();
					home2.setVisible(true);
					this.dispose();
				}
				
			} else {
				JOptionPane.showMessageDialog(this,"incorrect username or password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	//check duplicate users
	
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage frame = new LoginPage();
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
		public void initialize(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1523, 830);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 990, 793);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME TO");
		lblNewLabel.setForeground(new Color(21, 57, 93));
		lblNewLabel.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 30));
		lblNewLabel.setBounds(357, 0, 253, 110);
		panel.add(lblNewLabel);
		
		JLabel lblProjectManagementSystem = new JLabel("COLLEGE ACADEMIC PROJECT MANAGEMENT SYSTEM ");
		lblProjectManagementSystem.setForeground(new Color(85, 150, 206));
		lblProjectManagementSystem.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 30));
		lblProjectManagementSystem.setBounds(0, 55, 990, 89);
		panel.add(lblProjectManagementSystem);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\rachana\\OneDrive\\Desktop\\Project-Management-Journey.jpg"));
		lblNewLabel_1.setBounds(0, 130, 990, 663);
		panel.add(lblNewLabel_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(21, 57, 93));
		panel_1.setBounds(991, 0, 518, 793);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblWelcome = new JLabel("WELCOME !");
		lblWelcome.setForeground(new Color(255, 255, 255));
		lblWelcome.setFont(new Font("Copperplate Gothic Light", Font.PLAIN, 25));
		lblWelcome.setBounds(192, 10, 164, 110);
		panel_1.add(lblWelcome);
		
		JLabel lblCreateNewAccount = new JLabel("Login To Your Account");
		lblCreateNewAccount.setForeground(Color.WHITE);
		lblCreateNewAccount.setFont(new Font("Verdana", Font.PLAIN, 17));
		lblCreateNewAccount.setBounds(174, 47, 230, 110);
		panel_1.add(lblCreateNewAccount);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblUsername.setBounds(131, 155, 116, 110);
		panel_1.add(lblUsername);
		txt_username.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				
			}
		});
		
		//JCTextField txt_username = new JCTextField();
		txt_username.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_username.setSelectionColor(new Color(255, 255, 255));
		txt_username.setSelectedTextColor(new Color(255, 255, 255));
		txt_username.setPlaceholder("Enter Username");
		txt_username.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_username.setForeground(new Color(255, 255, 255));
		txt_username.setPhColor(new Color(236, 236, 236));
		txt_username.setBackground(new Color(21, 57, 93));
		txt_username.setBounds(131, 233, 303, 32);
		panel_1.add(txt_username);
		
		JLabel lblNewLabel_2 = 	new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\icons\\icons8_Account_50px.png"));
		lblNewLabel_2.setBounds(63, 210, 62, 72);
		panel_1.add(lblNewLabel_2);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblPassword.setBounds(131, 250, 116, 110);
		panel_1.add(lblPassword);
		
		//JCTextField txt_password = new JCTextField();
		txt_password.setSelectionColor(Color.WHITE);
		txt_password.setSelectedTextColor(Color.WHITE);
		txt_password.setPlaceholder("Enter password");
		txt_password.setPhColor(new Color(236, 236, 236));
		txt_password.setForeground(Color.WHITE);
		txt_password.setFont(new Font("Verdana", Font.PLAIN, 16));
		txt_password.setBorder(new MatteBorder(0, 0, 2, 0, (Color) new Color(255, 255, 255)));
		txt_password.setBackground(new Color(21, 57, 93));
		txt_password.setBounds(131, 328, 303, 32);
		panel_1.add(txt_password);
		
		JLabel lblNewLabel_2_1 = new JLabel("");
		lblNewLabel_2_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\icons\\icons8_Secure_50px.png"));
		lblNewLabel_2_1.setBounds(63, 305, 62, 72);
		panel_1.add(lblNewLabel_2_1);
		
		JLabel lblNewPassword = new JLabel("Select  User");
		lblNewPassword.setForeground(Color.WHITE);
		lblNewPassword.setFont(new Font("Verdana", Font.PLAIN, 16));
		lblNewPassword.setBounds(131, 347, 164, 110);
		panel_1.add(lblNewPassword);
		
		RSMaterialButtonCircle mtrlbtncrclAdd = new RSMaterialButtonCircle();
		mtrlbtncrclAdd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				SignupPage project = new SignupPage();
				project.setVisible(true);
				dispose();
			}
		});
		mtrlbtncrclAdd.setAction(action);
		mtrlbtncrclAdd.setBackground(new Color(85, 150, 206));
		mtrlbtncrclAdd.setText("New User");
		mtrlbtncrclAdd.setFont(new Font("Verdana", Font.PLAIN, 17));
		mtrlbtncrclAdd.setBounds(112, 601, 303, 48);
		panel_1.add(mtrlbtncrclAdd);
		
		RSMaterialButtonCircle mtrlbtncrclLogin = new RSMaterialButtonCircle();
		mtrlbtncrclLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (validateLogin()) {
					login();
				}
			}
		});
		mtrlbtncrclLogin.setAction(action);
		mtrlbtncrclLogin.setText("Login");
		mtrlbtncrclLogin.setFont(new Font("Verdana", Font.PLAIN, 17));
		mtrlbtncrclLogin.setBackground(new Color(32, 75, 113));
		mtrlbtncrclLogin.setBounds(112, 527, 303, 48);
		panel_1.add(mtrlbtncrclLogin);
		
		JLabel lblX = new JLabel("X");
		lblX.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblX.setForeground(Color.WHITE);
		lblX.setFont(new Font("Arial", Font.BOLD, 28));
		lblX.setBounds(482, 0, 36, 48);
		panel_1.add(lblX);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\icons\\icons8_Account_50px.png"));
		lblNewLabel_3.setBounds(63, 409, 62, 48);
		panel_1.add(lblNewLabel_3);
		
		//JComboBox comboBox = new JComboBox();
		comboBox.setForeground(new Color(0, 0, 0));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Select", "Teacher", "Student"}));
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 16));
		comboBox.setBounds(131, 425, 303, 32);
		panel_1.add(comboBox);
	}

	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "SwingAction");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			
			
		}
	}
}
