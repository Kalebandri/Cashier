import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuKeyEvent;
import javax.swing.event.MenuKeyListener;
import javax.swing.event.MenuListener;

public class ProgramClient extends JFrame{
	public static final int STEPS = 1000;
	
		Connection con;
		Statement st;
		ResultSet rs;
		
		public void connect(){
			try {
				Class.forName("com.mysql.jdbc.Driver").newInstance();
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/","root","");
				st = con.createStatement();
				
				st.executeQuery("USE db_Uts");

			} catch (Exception e) {
				System.out.println(e);
			}
		}
	
	
	private JDesktopPane dp;
	private JMenuBar mnBar;
	private JMenu menuLogin;
	private JMenu menuEmployee;
	private JMenu menuItem;
	private JMenuItem menuAddItem;
	private JMenu menuTrans;
	private JMenuItem menuViewTr;
	private JMenuItem menuCreateTr;
	private JMenu menuLogout;
	private JLabel welcome;	
	private JLabel date;
	private JPanel pnlDate;

	public String userID;
	
	

	public void loginState(Boolean state){
		menuLogin.setVisible(!state);
		menuEmployee.setVisible(state);
		menuItem.setVisible(state);
		menuTrans.setVisible(state);
		menuLogout.setVisible(state);
		
	}
	
	
	public ProgramClient(){
		Container cnt = this.getContentPane();
		cnt.setLayout(new BorderLayout());
		
		
		userID = "";
		
		dp = new JDesktopPane();
		mnBar = new JMenuBar();
		menuLogin = new JMenu("Login");
		menuEmployee = new JMenu("Employee");
		menuItem = new JMenu("Item");
		menuAddItem = new JMenuItem("View Item");
		menuTrans = new JMenu("Transaction");
		menuViewTr  = new JMenuItem("View Transaction History");
		menuCreateTr  = new JMenuItem("Create New Order");
		menuLogout = new JMenu("Logout");
		date = new JLabel("");
		welcome=new JLabel("Welcome "+userID);
		
		
		pnlDate = new JPanel();
		pnlDate.add(date);
		pnlDate.add(welcome);
		add(pnlDate,BorderLayout.NORTH);
		add(dp,BorderLayout.CENTER);
		setJMenuBar(mnBar);
		mnBar.add(menuLogin);
		mnBar.add(menuItem);
		menuItem.add(menuAddItem);
		mnBar.add(menuTrans);
		menuTrans.add(menuViewTr);
		menuTrans.add(menuCreateTr);
		mnBar.add(menuLogout);
		
		
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Cashier Program");
		setSize(900, 700);
		setVisible(true);
		
		loginState(false);
		
		menuLogin.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent arg0) {
				
				
				
				
				JIFlogin login = new JIFlogin(ProgramClient.this);
				login.setVisible(true);
				dp.add(login);
				
			}
			
			@Override
			public void menuDeselected(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void menuCanceled(MenuEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		
		menuAddItem.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JIFviewItem addItem = new JIFviewItem();
				addItem.setVisible(true);
				dp.add(addItem);
				
			}
		});
		
		
		menuViewTr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				JIFviewTransaction viewTr = new JIFviewTransaction();
				viewTr.setVisible(true);
				dp.add(viewTr);
				
			}
		});
		
		menuCreateTr.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				JIFcreateTransaction createTr = new JIFcreateTransaction(ProgramClient.this);
				createTr.setVisible(true);
				dp.add(createTr);
			}
		});
		
		
		menuLogout.addMenuListener(new MenuListener() {
			
			@Override
			public void menuSelected(MenuEvent e) {
				dp.removeAll();
				dp.repaint();
				userID = "";
				loginState(false);
				
			}
			
			@Override
			public void menuDeselected(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void menuCanceled(MenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		while(true){
			try {
				Thread.sleep(STEPS);
				} catch (Exception e) {
				// TODO: handle exception
				}
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
				date.setText(""+new Date());
				welcome.setText("Welcome "+userID);
				}
				});
				}
				}
	

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ProgramClient();

		}
		}
		

	
	
	
	
	
	