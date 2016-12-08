import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import org.omg.CORBA.PUBLIC_MEMBER;

import com.mysql.jdbc.ResultSetMetaData;

public class JIFviewTransaction extends JInternalFrame {
	
	public connect conn;
	ResultSet rsM;
	ResultSet rsD;
	
	
	private JLabel lblTitleHead;
	private JLabel lblTitleDetil;
	private JLabel lblSearch;
	private JLabel lblSearchBy;
	
	private JTextField txtSearch;
	
	private JComboBox<String> cbSearch;
	
	private JButton btnSearch;
	
	private JTable tbMain;
	private JTable tbDetil;

	private DefaultTableModel mdlMain;
	private DefaultTableModel mdlDetil;
	
	
	private JPanel pnlTop;	
		private JPanel pnlTitleHead;
		private JPanel pnlSearch;
		
	private JPanel pnlBottom;
	
	private JPanel pnlCenter;
		private JPanel pnlMainTable;
			private JPanel pnlMnorth;
			private JPanel pnlMeast;
			private JPanel pnlMwest;
			private JScrollPane spMain;
		private JPanel pnlDetilTable;
			private JPanel pnlDnorth;
			private JPanel pnlDeast;
			private JPanel pnlDwest;
			private JScrollPane spDetil;
	
	
			
	public JIFviewTransaction(){
		conn = new connect();
		Container cnt = this.getContentPane();
		cnt.setLayout(new BorderLayout());
		
		
		lblTitleHead = new JLabel("View Transaction");
		lblTitleHead.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblTitleDetil = new JLabel("Detail Transaction");
		lblTitleDetil.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblSearch = new JLabel("Search");
		
		lblSearchBy = new JLabel("Search By");
		
		txtSearch = new JTextField();
		txtSearch.setBounds(0, 0, 150, 20);
			Dimension txWidth = txtSearch.getPreferredSize();
			txWidth.width=150;
		txtSearch.setPreferredSize(txWidth);
		
		btnSearch = new JButton("Search");
		
		String searchList[]= {"Transaction Code","User ID","Total Price"};
		cbSearch = new JComboBox<>(searchList);
		
		
		
		Vector<String> judulKolomMain = new Vector<String>();
		final Vector<Vector> isiTabelMain = new Vector<Vector>();
		judulKolomMain.add("Transaction Code");
		judulKolomMain.add("Date");
		judulKolomMain.add("User ID");
		judulKolomMain.add("Subtotal");
		
		mdlMain = new DefaultTableModel(isiTabelMain, judulKolomMain){
			
			public boolean isCellEditable(int row,int col) {
				return false;
			}
		};
		
		try {
			rsM = conn.readTable("SELECT * from transaksi");

			while (rsM.next()) {
				
				Vector<String> data = new Vector<String>();
				data.add(rsM.getString(1));
				data.add(rsM.getString(2));
				data.add(rsM.getString(3));
				data.add(rsM.getString(4));
				isiTabelMain.add(data);
				
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
		}		
		

		tbMain = new JTable(mdlMain);
		tbMain.getTableHeader().setReorderingAllowed(false);
		
		
		tbMain.setCellSelectionEnabled(true);
	
		
		Vector<String> judulKolomDetil = new Vector<String>();
		final Vector<Vector> isiTabelDetil = new Vector<Vector>();
		judulKolomDetil.add("Item Code");
		judulKolomDetil.add("Qty");
		
		mdlDetil = new DefaultTableModel(isiTabelDetil, judulKolomDetil);
		tbDetil = new JTable(mdlDetil);
		tbDetil.setEnabled(false);
		tbDetil.getTableHeader().setReorderingAllowed(false);
		
				
		pnlTop = new JPanel();
		cnt.add(pnlTop,BorderLayout.NORTH);
		pnlTop.setLayout(new GridLayout(2, 1));
		
			pnlTitleHead = new JPanel();
			pnlTop.add(pnlTitleHead);
			
				pnlTitleHead.add(lblTitleHead);
			
			pnlSearch = new JPanel();
			pnlSearch.setBackground(SystemColor.WHITE);
			pnlTop.add(pnlSearch);
				
				pnlSearch.add(lblSearch);
				pnlSearch.add(txtSearch);
				pnlSearch.add(btnSearch);
				pnlSearch.add(lblSearchBy);
				pnlSearch.add(cbSearch);
			
		pnlCenter = new JPanel();
		cnt.add(pnlCenter,BorderLayout.CENTER);
		pnlCenter.setLayout(new GridLayout(1, 2));
		
			pnlMainTable = new JPanel();
			pnlMainTable.setLayout(new BorderLayout());
			pnlCenter.add(pnlMainTable);
			
				pnlMnorth = new JPanel();
				pnlMainTable.add(pnlMnorth, BorderLayout.NORTH);
				
				pnlMeast = new JPanel();
				pnlMainTable.add(pnlMeast, BorderLayout.EAST);
				
				pnlMwest = new JPanel();
				pnlMainTable.add(pnlMwest, BorderLayout.WEST);
				
				spMain = new JScrollPane();
				pnlMainTable.add(spMain, BorderLayout.CENTER);
					
					spMain.setViewportView(tbMain);
					tbMain.setFillsViewportHeight(true);
				
			
			pnlDetilTable = new JPanel();
			pnlDetilTable.setLayout(new BorderLayout());
			pnlCenter.add(pnlDetilTable);
			
				pnlDnorth = new JPanel();
				pnlDetilTable.add(pnlDnorth, BorderLayout.NORTH);
				
					pnlDnorth.add(lblTitleDetil);
				
				pnlDeast = new JPanel();
				pnlDetilTable.add(pnlDeast, BorderLayout.EAST);
				
				pnlDwest = new JPanel();
				pnlDetilTable.add(pnlDwest, BorderLayout.WEST);
				
				spDetil = new JScrollPane();
				pnlDetilTable.add(spDetil, BorderLayout.CENTER);
				
					spDetil.setViewportView(tbDetil);
					tbDetil.setFillsViewportHeight(true);
				
		pnlBottom = new JPanel();
		cnt.add(pnlBottom,BorderLayout.SOUTH);
			
		
			
			setBounds(60, 25, 50, 50);
			setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			setTitle("View Transaction History");
			setSize(600,550);
			setVisible(true);
			setClosable(true);
			
		
		tbMain.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int r = tbMain.rowAtPoint(e.getPoint());
				
				String selectedCode = tbMain.getModel().getValueAt(r, 0).toString();
				
				isiTabelDetil.clear();
				try {
					rsD = conn.readTable("SELECT kodebarang, qty from detailTransaksi where kodeTransaksi like '"+selectedCode+"'");

					while (rsD.next()) {
						
						Vector<String> data = new Vector<String>();
						data.add(rsD.getString(1));
						data.add(rsD.getString(2));
						isiTabelDetil.add(data);
						
					}
					
					mdlDetil.fireTableDataChanged();
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
				
			}
		});
		
		
		
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String keyword = txtSearch.getText();
				
				if (keyword.equals("")) {
					
					
				} else {
					int searchList = cbSearch.getSelectedIndex();
					
					String keySearch = "";
					
					switch (searchList) {
					case 0:
						keySearch = "kodeTransaksi";
						break;
						
					case 1:
						keySearch = "userId";
						break;
						
					case 2:
						keySearch = "totalHarga";
						break;

					default:
						break;
					}
					
					isiTabelMain.clear();
					
					try {						
						rsM = conn.readTable("SELECT * from transaksi WHERE "+keySearch+" like '"+keyword+"'");

						while (rsM.next()) {
							
							Vector<String> data = new Vector<String>();
							data.add(rsM.getString(1));
							data.add(rsM.getString(2));
							data.add(rsM.getString(3));
							data.add(rsM.getString(4));
							isiTabelMain.add(data);
						
						}
						
						mdlMain.fireTableDataChanged();
					}catch (Exception e2) {
						// TODO: handle exception
					}
				}
				
				
				
				
			}
		});
		
	}
}
