package jframe1;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.SystemColor;
import javax.swing.border.MatteBorder;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;



public class Homepage extends JFrame {

	private JPanel contentPane;
	JPanel panel_6 = new JPanel();
	JLabel lbl_students = new JLabel(" 10");
	JLabel lbl_projects = new JLabel(" 10");
	JLabel lbl_department = new JLabel(" 10");
	Color mouseEnterColor= new Color(32,75,113);
	Color mouseExitColor= new Color(21,57,93);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Homepage frame = new Homepage();
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
	
	public Homepage() {
		initComponents();
		showPieChart();
		setDataToCards();
	}
	
	public void setDataToCards() {
		Statement st = null;
		ResultSet rs = null;
		
		long l = System.currentTimeMillis();
		Date todaysdate = new Date(1);
		try {
		    Connection con = DBConnection.getConnection();
		    st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		    rs = st.executeQuery("select * from project_ms.students");
		    rs.last();
		    lbl_students.setText(Integer.toString(rs.getRow()));
		    
		    rs = st.executeQuery("select * from project_ms.project");
		    rs.last();
		    lbl_projects.setText(Integer.toString(rs.getRow()));
		    
		    rs = st.executeQuery("select * from project_ms.department");
		    rs.last();
		    lbl_department.setText(Integer.toString(rs.getRow()));
		    
		}catch (Exception e) {
		    e.printStackTrace();
		}finally {
		    try {
		        if (rs != null) {
		            rs.close();
		        }
		        if (st != null) {
		            st.close();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

	}
	//pie chart
	
public void showPieChart(){
        
        //create data set
      DefaultPieDataset barDataset = new DefaultPieDataset( );
      try {
    	  Connection con = DBConnection.getConnection();
    	  String sql ="SELECT project_ms.controlled_by.Dept_id, COUNT(DISTINCT project_ms.controlled_by.pr_id) as p_count FROM project_ms.controlled_by GROUP BY Dept_id";
    	  Statement st = con.createStatement();
    	  ResultSet rs = st.executeQuery(sql);
    	  while(rs.next()) {
    		  barDataset.setValue(rs.getString("Dept_id"),new Double(rs.getDouble("p_count")));
    	  }
      }catch (Exception e) {
		  e.printStackTrace();
	  }
      
      //create chart
       JFreeChart piechart = ChartFactory.createPieChart("Projects Handled by each Department",barDataset, true,true,false);//explain
      
        PiePlot piePlot =(PiePlot) piechart.getPlot();
      
       //changing pie chart blocks colors
       piePlot.setSectionPaint("Information Science", new Color(255,255,102));
        piePlot.setSectionPaint("Computer Science", new Color(102,255,102));
        piePlot.setSectionPaint("Electronics and communication", new Color(255,102,153));
        piePlot.setSectionPaint("Mechanical", new Color(0,204,204));
      
       
        piePlot.setBackgroundPaint(Color.white);
        
        //create chartPanel to display chart(graph)
        ChartPanel barChartPanel = new ChartPanel(piechart);
        panel_6.removeAll();
        panel_6.setLayout(new BorderLayout(0, 0));
        panel_6.add(barChartPanel);
        panel_6.validate();
    }
	public  void initComponents() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1523, 823);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setLocationRelativeTo(null);

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 1509, 75);
		panel.setBackground(new Color(85, 150, 206));
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\r\n");
		lblNewLabel.setBounds(21, 23, 58, 42);
		lblNewLabel.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\adminIcons\\adminIcons\\icons8_menu_48px_1.png"));
		panel.add(lblNewLabel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(74, 15, 4, 50);
		panel.add(panel_1);
		panel_1.setBackground(new Color(21, 57, 93));
		
		JLabel lblNewLabel_1 = new JLabel("PROJECT MANAGEMENT SYSTEM");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(89, 25, 424, 27);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("WELCOME ADMIN");
		lblNewLabel_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\adminIcons\\adminIcons\\male_user_50px.png"));
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 20));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(1181, 7, 241, 65);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("X");
		lblNewLabel_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		lblNewLabel_3.setBounds(1467, 12, 42, 48);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setForeground(new Color(255, 255, 255));
		lblNewLabel_3.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 29));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(21, 57, 93));
		panel_2.setBounds(0, 74, 340, 712);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new MatteBorder(0, 10, 0, 0, (Color) new Color(85, 150, 206)));
		panel_3.setBackground(new Color(32, 75, 113));
		panel_3.setBounds(0, 59, 340, 60);
		panel_2.add(panel_3);
		panel_3.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel("   Home Page");
		lblNewLabel_4.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\adminIcons\\adminIcons\\icons8_Home_26px_2.png"));
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4.setBackground(new Color(240, 240, 240));
		lblNewLabel_4.setBounds(41, 10, 152, 35);
		panel_3.add(lblNewLabel_4);
		
		JPanel panel_3_1 = new JPanel();
		panel_3_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Evaluated_by student = new Evaluated_by ();
				student.setVisible(true);
				dispose();
			}
		});
		panel_3_1.setLayout(null);
		panel_3_1.setBackground(new Color(21, 57, 93));
		panel_3_1.setBounds(0, 409, 340, 60);
		panel_2.add(panel_3_1);
		
		JLabel lblNewLabel_4_1 = new JLabel("   Project Evaluated By");
		lblNewLabel_4_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\check.png"));
		lblNewLabel_4_1.setForeground(Color.WHITE);
		lblNewLabel_4_1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_1.setBackground(SystemColor.menu);
		lblNewLabel_4_1.setBounds(41, 10, 213, 35);
		panel_3_1.add(lblNewLabel_4_1);
		
		JPanel panel_3_2 = new JPanel();
		panel_3_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Managestudents student = new Managestudents();
				student.setVisible(true);
				dispose();
			}
		});
		panel_3_2.setLayout(null);
		panel_3_2.setBackground(new Color(21, 57, 93));
		panel_3_2.setBounds(0, 117, 340, 60);
		panel_2.add(panel_3_2);
		
		JLabel lblNewLabel_4_2 = new JLabel("   Manage Student");
		lblNewLabel_4_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_2.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_2.setBackground(mouseExitColor);
			}
		});
		lblNewLabel_4_2.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\reading-book.png"));
		lblNewLabel_4_2.setForeground(Color.WHITE);
		lblNewLabel_4_2.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_2.setBackground(SystemColor.menu);
		lblNewLabel_4_2.setBounds(41, 10, 194, 35);
		panel_3_2.add(lblNewLabel_4_2);
		
		JPanel panel_3_3 = new JPanel();
		panel_3_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Manageprojects project = new Manageprojects();
				project.setVisible(true);
				dispose();
			}
		});
		panel_3_3.setLayout(null);
		panel_3_3.setBackground(new Color(21, 57, 93));
		panel_3_3.setBounds(0, 176, 340, 60);
		panel_2.add(panel_3_3);
		
		JLabel lblNewLabel_4_3 = new JLabel("   Manage Project");
		lblNewLabel_4_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_3.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_3.setBackground(mouseExitColor);
			}
		});
		lblNewLabel_4_3.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\execute.png"));
		lblNewLabel_4_3.setForeground(Color.WHITE);
		lblNewLabel_4_3.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_3.setBackground(SystemColor.menu);
		lblNewLabel_4_3.setBounds(41, 10, 200, 35);
		panel_3_3.add(lblNewLabel_4_3);
		
		JPanel panel_3_4 = new JPanel();
		panel_3_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Projectresources resources = new Projectresources();
				resources.setVisible(true);
				dispose();
			}
		});
		panel_3_4.setLayout(null);
		panel_3_4.setBackground(new Color(21, 57, 93));
		panel_3_4.setBounds(0, 232, 340, 60);
		panel_2.add(panel_3_4);
		
		JLabel lblNewLabel_4_4 = new JLabel("   Project Resources");
		lblNewLabel_4_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_4.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_4.setBackground(mouseExitColor);
			}
		});
		lblNewLabel_4_4.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\teamwork.png"));
		lblNewLabel_4_4.setForeground(Color.WHITE);
		lblNewLabel_4_4.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_4.setBackground(SystemColor.menu);
		lblNewLabel_4_4.setBounds(41, 10, 189, 35);
		panel_3_4.add(lblNewLabel_4_4);
		
		JPanel panel_3_5 = new JPanel();
		panel_3_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Controlleddepartment student = new Controlleddepartment();
				student.setVisible(true);
				dispose();
			}
		});
		panel_3_5.setLayout(null);
		panel_3_5.setBackground(new Color(21, 57, 93));
		panel_3_5.setBounds(0, 291, 340, 60);
		panel_2.add(panel_3_5);
		
		JLabel lblNewLabel_4_5 = new JLabel("   Controlled Departments");
		lblNewLabel_4_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_5.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_5.setBackground(mouseExitColor);
			}
		});
		lblNewLabel_4_5.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\hierarchy.png"));
		lblNewLabel_4_5.setForeground(Color.WHITE);
		lblNewLabel_4_5.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_5.setBackground(new Color(21, 57, 93));
		lblNewLabel_4_5.setBounds(41, 10, 258, 35);
		panel_3_5.add(lblNewLabel_4_5);
		
		JPanel panel_3_6 = new JPanel();
		panel_3_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Project_Resources project = new Project_Resources();
				project.setVisible(true);
				dispose();
			}
		});
		panel_3_6.setLayout(null);
		panel_3_6.setBackground(new Color(21, 57, 93));
		panel_3_6.setBounds(0, 348, 340, 60);
		panel_2.add(panel_3_6);
		
		JLabel lblNewLabel_4_6 = new JLabel("   Project Evaluators");
		lblNewLabel_4_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_6.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_6.setBackground(mouseExitColor);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				Project_evaluators project = new Project_evaluators();
				project.setVisible(true);
				dispose();
			}
		});
		lblNewLabel_4_6.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\list.png"));
		lblNewLabel_4_6.setForeground(Color.WHITE);
		lblNewLabel_4_6.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_6.setBackground(SystemColor.menu);
		lblNewLabel_4_6.setBounds(41, 10, 260, 35);
		panel_3_6.add(lblNewLabel_4_6);
		
		JPanel panel_3_7 = new JPanel();
		panel_3_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				works_on student = new works_on();
				student.setVisible(true);
				dispose();
			}
		});
		panel_3_7.setLayout(null);
		panel_3_7.setBackground(new Color(21, 57, 93));
		panel_3_7.setBounds(0, 466, 340, 60);
		panel_2.add(panel_3_7);
		
		JLabel lblNewLabel_4_7 = new JLabel("   Student Working On");
		lblNewLabel_4_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_7.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_7.setBackground(mouseExitColor);
			}
		});
		lblNewLabel_4_7.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\man-working-on-a-laptop-from-side-view.png"));
		lblNewLabel_4_7.setForeground(Color.WHITE);
		lblNewLabel_4_7.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_7.setBackground(SystemColor.menu);
		lblNewLabel_4_7.setBounds(41, 10, 224, 35);
		panel_3_7.add(lblNewLabel_4_7);
		
		JPanel panel_3_8 = new JPanel();
		panel_3_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				industry_expert expert = new industry_expert();
				expert.setVisible(true);
				dispose();
			}
		});
		panel_3_8.setLayout(null);
		panel_3_8.setBackground(new Color(21, 57, 93));
		panel_3_8.setBounds(0, 524, 340, 60);
		panel_2.add(panel_3_8);
		
		JLabel lblNewLabel_4_8 = new JLabel("   Industry Expert Details");
		lblNewLabel_4_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_8.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_8.setBackground(mouseExitColor);
			}
		});
		lblNewLabel_4_8.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\marketing-agent.png"));
		lblNewLabel_4_8.setForeground(Color.WHITE);
		lblNewLabel_4_8.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_8.setBackground(SystemColor.menu);
		lblNewLabel_4_8.setBounds(41, 10, 237, 35);
		panel_3_8.add(lblNewLabel_4_8);
		
		JPanel panel_3_9 = new JPanel();
		panel_3_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Top_10 project = new Top_10();
				project.setVisible(true);
				dispose();
			}
		});
		panel_3_9.setLayout(null);
		panel_3_9.setBackground(new Color(21, 57, 93));
		panel_3_9.setBounds(0, 582, 340, 60);
		panel_2.add(panel_3_9);
		
		JLabel lblNewLabel_4_9 = new JLabel(" Top 10 Projects");
		lblNewLabel_4_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_3_9.setBackground(mouseEnterColor);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel_3_9.setBackground(mouseExitColor);
			}
		});
		lblNewLabel_4_9.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\icons1\\execute.png"));
		lblNewLabel_4_9.setForeground(Color.WHITE);
		lblNewLabel_4_9.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_9.setBackground(SystemColor.menu);
		lblNewLabel_4_9.setBounds(41, 10, 187, 35);
		panel_3_9.add(lblNewLabel_4_9);
		
		JPanel panel_3_9_1 = new JPanel();
		panel_3_9_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginPage project = new LoginPage();
				project.setVisible(true);
				dispose();
				
			}
		});
		panel_3_9_1.setLayout(null);
		panel_3_9_1.setBackground(new Color(85, 150, 206));
		panel_3_9_1.setBounds(0, 642, 340, 60);
		panel_2.add(panel_3_9_1);
		
		JLabel lblNewLabel_4_9_1 = new JLabel("   Logout");
		lblNewLabel_4_9_1.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\adminIcons\\adminIcons\\icons8_Exit_26px_2.png"));
		lblNewLabel_4_9_1.setForeground(Color.WHITE);
		lblNewLabel_4_9_1.setFont(new Font("Yu Gothic UI Semilight", Font.PLAIN, 18));
		lblNewLabel_4_9_1.setBackground(SystemColor.menu);
		lblNewLabel_4_9_1.setBounds(41, 10, 152, 35);
		panel_3_9_1.add(lblNewLabel_4_9_1);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 255, 255));
		panel_4.setBounds(338, 74, 1171, 712);
		contentPane.add(panel_4);
		panel_4.setLayout(null);
		
		JPanel panel_5 = new JPanel();
		panel_5.setBorder(new MatteBorder(15, 0, 0, 0, (Color) new Color(32, 75, 113)));
		panel_5.setBounds(91, 87, 275, 167);
		panel_4.add(panel_5);
		panel_5.setLayout(null);
		
		
		lbl_students.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\adminIcons\\adminIcons\\icons8_People_50px.png"));
		lbl_students.setBounds(60, 60, 147, 59);
		lbl_students.setForeground(new Color(102, 102, 102));
		lbl_students.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 50));
		panel_5.add(lbl_students);
		
		JPanel panel_5_1 = new JPanel();
		panel_5_1.setBorder(new MatteBorder(15, 0, 0, 0, (Color) new Color(85, 150, 206)));
		panel_5_1.setBounds(462, 87, 275, 167);
		panel_4.add(panel_5_1);
		panel_5_1.setLayout(null);
		
		
		lbl_projects.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\adminIcons\\adminIcons\\icons8_Book_Shelf_50px.png"));
		lbl_projects.setForeground(new Color(102, 102, 102));
		lbl_projects.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 50));
		lbl_projects.setBounds(55, 48, 142, 75);
		panel_5_1.add(lbl_projects);
		
		JPanel panel_5_2 = new JPanel();
		panel_5_2.setBorder(new MatteBorder(15, 0, 0, 0, (Color) new Color(32, 75, 113)));
		panel_5_2.setBounds(840, 87, 275, 167);
		panel_4.add(panel_5_2);
		panel_5_2.setLayout(null);
		
		
		lbl_department.setIcon(new ImageIcon("C:\\Users\\rachana\\eclipse-workspace\\Project_Management_System\\src\\adminIcons\\adminIcons\\icons8_List_of_Thumbnails_50px.png"));
		lbl_department.setForeground(new Color(102, 102, 102));
		lbl_department.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 50));
		lbl_department.setBounds(66, 53, 142, 75);
		panel_5_2.add(lbl_department);
		
		JLabel lblNewLabel_5 = new JLabel("No of Students");
		lblNewLabel_5.setForeground(new Color(102, 102, 102));
		lblNewLabel_5.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5.setBounds(91, 55, 174, 22);
		panel_4.add(lblNewLabel_5);
		
		JLabel lblNewLabel_5_2 = new JLabel("No of Projects");
		lblNewLabel_5_2.setForeground(new Color(102, 102, 102));
		lblNewLabel_5_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5_2.setBounds(462, 55, 174, 22);
		panel_4.add(lblNewLabel_5_2);
		
		JLabel lblNewLabel_5_3 = new JLabel("No of Departments");
		lblNewLabel_5_3.setForeground(new Color(102, 102, 102));
		lblNewLabel_5_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_5_3.setBounds(840, 55, 191, 22);
		panel_4.add(lblNewLabel_5_3);
		
		//JPanel panel_6 = new JPanel();
		panel_6.setBounds(327, 289, 574, 413);
		panel_4.add(panel_6);
	}
}
