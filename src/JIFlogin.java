import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class JIFlogin extends JInternalFrame {
	
	public connect conn;
	ResultSet rs;
	
	
	private JLabel lblTitle;
	private JLabel lblUsername;
	private JLabel lblPassword;
	private JTextField TFusername;
	private JPasswordField PFpassword;
	private JButton btnLogin;
	
	
	private JPanel pnlTitle;
	private JPanel pnlCenter;
		private JPanel pnlLblUser;
		private JPanel pnlLblPass;
		private JPanel pnlTxtUser;
		private JPanel pnlTxtPass;
	private JPanel pnlButton;	
	
	
	public JIFlogin(final ProgramClient a){
		
		Container cnt = this.getContentPane();
		cnt.setLayout(new BorderLayout());
		
		
		lblTitle = new JLabel("Login");
		lblTitle.setFont(new Font("ARIAL",Font.BOLD,14));
		
		lblUsername = new JLabel("User Name");
		lblPassword = new JLabel("Password");
		
		TFusername = new JTextField();
		TFusername.setBounds(0, 0, 170, 20);
		
		PFpassword = new JPasswordField();
		PFpassword.setBounds(0, 0, 170, 20);
		
		btnLogin = new JButton("Login");
		
		
		pnlTitle = new JPanel();
		cnt.add(pnlTitle,BorderLayout.NORTH);
			pnlTitle.add(lblTitle);
		
		pnlCenter = new JPanel(); 
		cnt.add(pnlCenter,BorderLayout.CENTER); 
		pnlCenter.setLayout(new GridLayout(2, 2)); 
		
			pnlLblUser = new JPanel();
			pnlCenter.add(pnlLblUser);
				pnlLblUser.add(lblUsername);
			
			pnlTxtUser = new JPanel();
			pnlCenter.add(pnlTxtUser);
			pnlTxtUser.setLayout(null);
				pnlTxtUser.add(TFusername);
			
			pnlLblPass = new JPanel();
			pnlCenter.add(pnlLblPass);
				pnlLblPass.add(lblPassword);
			
			pnlTxtPass = new JPanel();
			pnlCenter.add(pnlTxtPass);
			pnlTxtPass.setLayout(null);
				pnlTxtPass.add(PFpassword);
		
		pnlButton = new JPanel();
		cnt.add(pnlButton,BorderLayout.SOUTH); 		
			pnlButton.add(btnLogin);
		
		
		
		setBounds(200, 200, 50, 50);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Login");
		setSize(400, 150);
		setVisible(true);
		setClosable(true);
		
		
		
		
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String usn = TFusername.getText();
				String pass = new String(PFpassword.getPassword());
				
					if (usn.equals("")||pass.equals("")) {
						JOptionPane.showMessageDialog(null, "Username and Password must be filled");
					} else {
						String query ="SELECT * from pegawai WHERE userid like '"+usn+"' AND password like '"+pass+"'";
						conn = new connect();
						rs = conn.readTable(query);
							
						try{
							if (rs.next()) {
								JOptionPane.showMessageDialog(null, "Hello "+rs.getString(3));
								a.loginState(true);
								a.userID=rs.getString(1);
								dispose();
							}
							else{
								JOptionPane.showMessageDialog(null, "Incorrect username or password");
							}
						}catch (Exception e) {
							
						}
					}
			}
		});
	}
}
