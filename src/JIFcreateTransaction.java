import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class JIFcreateTransaction extends JInternalFrame {

	
	public connect conn;
	ResultSet rs;
	
	
	private JLabel lblTitle;
	private JLabel lblUserId;
	private JLabel lblUserIdIsi;
	private JLabel lblDate;
	private JLabel lblDateIsi;
	private JLabel lblTitleCart;
	private JLabel lblItemCode;
	private JLabel lblQty;
	private JLabel lblSubtotal;
	private JLabel lblSubtotalIsi;
	private JLabel lblCash;
	private JLabel lblChange;
	private JLabel lblChangeIsi;
	
	private JTextField txtFieldItemCode;
	private JTextField txtFieldCash;
	
	private JSpinner spinnerQty;
	
	private JButton btnAddcart;
	private JButton btnPaid;
	private JButton btnFinish;
	
	private JTable tableCart;
	private DefaultTableModel mdl;
	
	
	
	private JPanel pnlHead;
		private JPanel pnlTitle;
		private JPanel pnlInfo;
			private JPanel pnlInfoUserId;
			private JPanel pnlInfoDate;
	private JPanel pnlCenter;
		private JPanel pnlCartTitle;
		private JPanel pnlCart;			
			private JPanel pnlInputCart;
				private JPanel pnlBlnk1;
				private JPanel pnlBlnk2;
				private JPanel pnlLblItemCode;
				private JPanel pnlTxtFieldItemCode;
				private JPanel pnlLblQty;
				private JPanel pnlCounterQty;
				private JPanel pnlBlank1;
				private JPanel pnlBtnAddCart;
			private JPanel pnlTableCart;
				private JPanel pnlBlankNor;
				private JPanel pnlBlankWes;
				private JPanel pnlBlankEas;
				private JPanel pnlBlankSou;
				private JScrollPane SPtableCen;
				
	private JPanel pnlBottom;
		private JPanel pnlBlank;
		private JPanel pnlTotal;
			private JPanel pnlLblTotal;
			private JPanel pnlLblTotalIsi;
			private JPanel pnlLblCash;
			private JPanel pnlTxtCash;
			private JPanel pnlLblChange;
			private JPanel pnlLblChangeIsi;
			private JPanel pnlBtnFinish;
			private JPanel pnlBtnPaid;
	
	public JIFcreateTransaction(ProgramClient a){
		conn = new connect();
		Container cnt = this.getContentPane();
		cnt.setLayout(new BorderLayout());
		
		DateFormat df = new SimpleDateFormat("EEEE, dd/MM/yyyy HH:mm");
		Date det = new Date();
	
		String dateNow = new String(df.format(det));
		
		
		lblTitle = new JLabel("Create Order");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblDate = new JLabel("Date");
		
		lblDateIsi = new JLabel(dateNow);
		
		lblUserId = new JLabel("User ID");
		
		lblUserIdIsi = new JLabel(a.userID);
		
		lblTitleCart = new JLabel("Cart");
		lblTitleCart.setFont(new Font("Arial", Font.BOLD, 14));
		
		lblItemCode = new JLabel("Item Code");
		
		txtFieldItemCode = new JTextField();
		txtFieldItemCode.setBounds(10, 0, 150, 20);
		
		lblQty = new JLabel("Quantity");
		
		spinnerQty = new JSpinner();
			Dimension wide = spinnerQty.getPreferredSize();
			wide.width=150;
			spinnerQty.setPreferredSize(wide);
			
			spinnerQty.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		
		btnAddcart = new JButton("Add Cart");
		
		String[] tableHead = {"Item Code","Item Name","Qty","Price","Total Price"};
		
		mdl = new DefaultTableModel(tableHead, 0);
		
		tableCart = new JTable(mdl);
		tableCart.getColumnModel().getColumn(1).setPreferredWidth(112);
		tableCart.getColumnModel().getColumn(2).setPreferredWidth(38);		
		tableCart.setEnabled(false);
		tableCart.getTableHeader().setReorderingAllowed(false);
		
		lblSubtotal = new JLabel("Subtotal Rp. ");
		lblSubtotalIsi = new JLabel("-");
		
		lblCash = new JLabel("Cash");
		
		txtFieldCash = new JTextField();
		txtFieldCash.setBounds(0, 0, 150, 20);
		
		lblChange = new JLabel("Change Rp. ");
		lblChangeIsi = new JLabel("-");
		
		btnPaid = new JButton("Paid");
		
		btnFinish = new JButton("Finish Transaction"); 
		btnFinish.setEnabled(false);
		
		
		pnlHead = new JPanel();
		cnt.add(pnlHead,BorderLayout.NORTH);
		pnlHead.setLayout(new GridLayout(2, 1));
		
			pnlTitle = new JPanel();
			pnlHead.add(pnlTitle);
				
				pnlTitle.add(lblTitle);
			
			pnlInfo = new JPanel();
			pnlInfo.setLayout(new GridLayout(1, 2));
			pnlHead.add(pnlInfo);
			
				pnlInfoUserId = new JPanel();
				pnlInfo.add(pnlInfoUserId);
				
					pnlInfoUserId.add(lblUserId);
					pnlInfoUserId.add(lblUserIdIsi);
				
				pnlInfoDate = new JPanel();
				pnlInfo.add(pnlInfoDate);
				
					pnlInfoDate.add(lblDate);
					pnlInfoDate.add(lblDateIsi);
		
		pnlCenter = new JPanel();
		cnt.add(pnlCenter, BorderLayout.CENTER);
		pnlCenter.setLayout(new BorderLayout());
			
			pnlCartTitle = new JPanel();
			pnlCartTitle.setBackground(SystemColor.WHITE);
			pnlCenter.add(pnlCartTitle, BorderLayout.NORTH);
			
				pnlCartTitle.add(lblTitleCart);
			
			pnlCart = new JPanel();
			pnlCart.setLayout(new GridLayout(1, 2));
			pnlCenter.add(pnlCart, BorderLayout.CENTER);
				
				pnlInputCart = new JPanel();
				pnlInputCart.setLayout(new GridLayout(4, 2));
				pnlCart.add(pnlInputCart);
				
					pnlBlnk1 = new JPanel();
					pnlInputCart.add(pnlBlnk1);
					
					pnlBlnk2 = new JPanel();
					pnlInputCart.add(pnlBlnk2);
				
					pnlLblItemCode = new JPanel();
					pnlInputCart.add(pnlLblItemCode);
					
						pnlLblItemCode.add(lblItemCode);
					
					pnlTxtFieldItemCode = new JPanel();
					pnlTxtFieldItemCode.setLayout(null);
					pnlInputCart.add(pnlTxtFieldItemCode);
					
						pnlTxtFieldItemCode.add(txtFieldItemCode);
					
					pnlLblQty = new JPanel();
					pnlInputCart.add(pnlLblQty);
					
						pnlLblQty.add(lblQty);
					
					pnlCounterQty = new JPanel();
					pnlInputCart.add(pnlCounterQty);
						
						pnlCounterQty.add(spinnerQty);
					
					pnlBlank1 = new JPanel();
					pnlInputCart.add(pnlBlank1);
					
					pnlBtnAddCart = new JPanel();
					pnlInputCart.add(pnlBtnAddCart);
						
						pnlBtnAddCart.add(btnAddcart);
				
				pnlTableCart = new JPanel();
				pnlTableCart.setLayout(new BorderLayout());
				pnlCart.add(pnlTableCart);
				
					pnlBlankNor = new JPanel();
					pnlTableCart.add(pnlBlankNor, BorderLayout.NORTH);
					
					pnlBlankSou = new JPanel();
					pnlTableCart.add(pnlBlankSou, BorderLayout.SOUTH);
					
					pnlBlankWes = new JPanel();
					pnlTableCart.add(pnlBlankWes, BorderLayout.WEST);
					
					pnlBlankEas = new JPanel();
					pnlTableCart.add(pnlBlankEas, BorderLayout.EAST);
					
					SPtableCen = new JScrollPane();
					pnlTableCart.add(SPtableCen, BorderLayout.CENTER);
						
						SPtableCen.setViewportView(tableCart);
						tableCart.setFillsViewportHeight(true);
					
	
		pnlBottom = new JPanel();
		cnt.add(pnlBottom, BorderLayout.SOUTH);
		pnlBottom.setLayout(new GridLayout(1, 2));
		
			pnlBlank= new JPanel();
			pnlBottom.add(pnlBlank);
			
			pnlTotal = new JPanel();
			pnlTotal.setLayout(new GridLayout(4, 2));
			pnlBottom.add(pnlTotal);
			
				pnlLblTotal = new JPanel();
				pnlTotal.add(pnlLblTotal);
					
					pnlLblTotal.add(lblSubtotal);
				
				pnlLblTotalIsi = new JPanel();
				pnlTotal.add(pnlLblTotalIsi);
				
					pnlLblTotalIsi.add(lblSubtotalIsi);

				pnlLblCash = new JPanel();
				pnlTotal.add(pnlLblCash);
				
					pnlLblCash.add(lblCash);
				
				pnlTxtCash = new JPanel();
				pnlTxtCash.setLayout(null);
				pnlTotal.add(pnlTxtCash);
				
					pnlTxtCash.add(txtFieldCash);
				
				pnlLblChange = new JPanel();
				pnlTotal.add(pnlLblChange);
				
					pnlLblChange.add(lblChange);
				
				pnlLblChangeIsi = new JPanel();
				pnlTotal.add(pnlLblChangeIsi);
				
					pnlLblChangeIsi.add(lblChangeIsi);
				
					
				pnlBtnPaid = new JPanel();
				pnlTotal.add(pnlBtnPaid);
				
					pnlBtnPaid.add(btnPaid);
				
				pnlBtnFinish = new JPanel();
				pnlTotal.add(pnlBtnFinish);
				
					pnlBtnFinish.add(btnFinish);
					
											
		
		
		setBounds(70, 25, 50, 50);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Create Order");
		setSize(700,500);
		setVisible(true);
		setClosable(true);
		
		
		btnAddcart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String itemCode = txtFieldItemCode.getText();
				int qty = (Integer)spinnerQty.getValue();
				
				
				if (itemCode.equals("")) {
					JOptionPane.showMessageDialog(null, "Item code must be filled");
				}
				else if (qty==0) {
					JOptionPane.showMessageDialog(null, "Quantity must bigger than 0");
				}
				else {
					
					String query = "SELECT * from item WHERE kodeBarang like '"+itemCode+"'";
	
					try {
						rs = conn.readTable(query);
						
						if (rs.next()) {
							String itemName = rs.getString(2);
							int price = Integer.parseInt(rs.getString(3)) ;
							int stok = Integer.parseInt(rs.getString(4));
							
							if (qty>stok) {
								JOptionPane.showMessageDialog(null, "Item "+itemName+" has only "+stok+" left");
							}
							else{
								JOptionPane.showMessageDialog(null, "item name "+rs.getString(2)+" item price "+rs.getString(3));
								int totalPrice = qty*price;
								
								mdl.addRow(new Object[]{itemCode,itemName,qty,price,totalPrice});
								
								
								int subtotal=0;
								
								for (int i = 0; i < mdl.getRowCount(); i++) {
									subtotal += Integer.parseInt(tableCart.getModel().getValueAt(i, 4).toString()) ;
									lblSubtotalIsi.setText(subtotal+"");
								}
								
								txtFieldItemCode.setText("");
								spinnerQty.setValue(0);
								
							}
													
							
						}
						else{
							
							JOptionPane.showMessageDialog(null, "Item code salah");
						}
						
					} catch (Exception e2) {
						// TODO: handle exception
					}

				}
				
			}
		});
		
		btnPaid.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				if(lblSubtotalIsi.getText().equals("-")){
					JOptionPane.showMessageDialog(null, "Apakah tidak membeli sesuatu?");
				}
				else{
					int subtotal =Integer.parseInt(lblSubtotalIsi.getText());
					
					try {
						int cash = Integer.parseInt(txtFieldCash.getText());
						
						if (subtotal<=0) {
							JOptionPane.showMessageDialog(null, "Apakah tidak membeli sesuatu?");
						}
						else if (subtotal>cash) {
							JOptionPane.showMessageDialog(null, "Uang anda tidak cukup untuk membayar");
						}
						else {
							int response = JOptionPane.showConfirmDialog(null, "Are you sure? you can't add item or make change after this",null,JOptionPane.YES_NO_OPTION);
							
							if(response==JOptionPane.YES_OPTION){
								txtFieldCash.setEnabled(false);
								txtFieldItemCode.setEnabled(false);
								spinnerQty.setEnabled(false);
								btnPaid.setEnabled(false);
								btnAddcart.setEnabled(false);
								btnFinish.setEnabled(true);
								
								int change = cash-subtotal;
								
								lblChangeIsi.setText(change+"");
							}			
									
						}
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Pembayaran hanya dalam nomimal angka");
					}
				}
				
			}
		});
		
		btnFinish.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				String userid = lblUserIdIsi.getText();
				int totalHarga = Integer.parseInt(lblSubtotalIsi.getText());
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
				Date det = new Date();
				String dateNow = new String(df.format(det));
				
				try {
					
					String kodeTr = "T00001";
				
					rs = conn.readTable("SELECT kodeTransaksi from transaksi Order By kodeTransaksi desc");
					
					if(!rs.next()){
						
						conn.updateTable("INSERT into transaksi values ('"+kodeTr+"','"+dateNow+"','"+userid+"','"+totalHarga+"')");
					
					}
					else{						
						
						String kodeTrAkhir = rs.getString(1);
						int intKode = Integer.parseInt(kodeTrAkhir.substring(1, 6));
						intKode++;
						
						kodeTr = "T"+String.format("%05d", intKode);
						
						conn.updateTable("INSERT into transaksi values ('"+kodeTr+"','"+dateNow+"','"+userid+"','"+totalHarga+"')");
					}
					
					
					
					
					for (int i = 0; i < tableCart.getRowCount(); i++) {
						
						String itemCode = tableCart.getModel().getValueAt(i, 0).toString().trim();
						int qty =Integer.parseInt(tableCart.getModel().getValueAt(i, 2).toString());
						
						conn.updateTable("INSERT into detailTransaksi values ('"+kodeTr+"','"+itemCode+"','"+qty+"')");
						conn.updateTable("UPDATE item set stok = stok-"+qty+" WHERE kodeBarang LIKE '"+itemCode+"' ");
					}
					
					
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, ex);
				}
				
				JOptionPane.showMessageDialog(null, "Terimakasih");
				dispose();
				
			}
		});
	}
	

}
