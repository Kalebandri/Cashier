import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

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
import javax.swing.table.DefaultTableModel;

public class JIFviewItem extends JInternalFrame {

public connect conn;
ResultSet rs;

private JLabel lblTitle;
private JButton btnRef;
private JTable tbMain;
private DefaultTableModel mdl;
private JPanel pnlTop;
private JPanel pnlCenter;
private JPanel pnlEast;
private JPanel pnlWest;
private JScrollPane spTable;
private JPanel pnlBotom;
private JPanel pnlLbl;
private JPanel pnlLblName;
private JPanel pnlLblPrice;
private JPanel pnlLblStok;
private JPanel pnlTxtField;
private JPanel pnlTxtFieldName;
private JPanel pnlTxtFieldPrice;
private JPanel pnlTxtFieldStok;


	public Vector<Vector> fillTable(){
		Vector<Vector> contentTable = new Vector<Vector>();
		//data
		try {
			rs = conn.readTable("SELECT * from item");
			while (rs.next()) {
				Vector<String> data = new Vector<String>();
				data.add(rs.getString(1));
				data.add(rs.getString(2));
				data.add(rs.getString(3));
				data.add(rs.getString(4));
				contentTable.add(data);
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e);
			return null;
		}
		return contentTable;
	}

	public JIFviewItem(){
		conn = new connect();
		Container cnt = this.getContentPane();
		cnt.setLayout(new BorderLayout());

		lblTitle = new JLabel("Item List");
		lblTitle.setFont(new Font("ARIAL",Font.BOLD,14));
		btnRef = new JButton("Refresh");

		final Vector<String> headCol = new Vector<String>();
		headCol.add("Item Code");
		headCol.add("Item Name");
		headCol.add("Price");
		headCol.add("Qty");
		mdl = new DefaultTableModel(fillTable(), headCol){
			public boolean isCellEditable(int row,int col) {
				return false;
			}
		};
		
		
		tbMain = new JTable(mdl);
		tbMain.getTableHeader().setReorderingAllowed(false);
		tbMain.setCellSelectionEnabled(true);
		fillTable();
		
		
		pnlTop = new JPanel();
		cnt.add(pnlTop, BorderLayout.NORTH);
		pnlTop.add(lblTitle);
		pnlCenter = new JPanel();
		pnlCenter.setLayout(new BorderLayout());
		cnt.add(pnlCenter, BorderLayout.CENTER);
		pnlEast = new JPanel();
		pnlCenter.add(pnlEast, BorderLayout.EAST);
		pnlWest = new JPanel();
		pnlCenter.add(pnlWest, BorderLayout.WEST);
		spTable = new JScrollPane();
		pnlCenter.add(spTable, BorderLayout.CENTER);
		spTable.setViewportView(tbMain);
		tbMain.setFillsViewportHeight(true);
		pnlBotom =  new JPanel();
		
		cnt.add(pnlBotom,BorderLayout.SOUTH);
		pnlBotom.add(btnRef);
		
		setBounds(60, 25, 50, 50);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setTitle("Add Item");
		setSize(600,550);
		setVisible(true);
		setClosable(true);
		
		btnRef.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				mdl.setDataVector(fillTable(), headCol);
			}
		});
		
		int newItemPrice = 0;
		boolean priceIsAngka = true;
		try {
		
		} catch (Exception e2) {
			
			priceIsAngka = false;
		}
		
		try {
			String kodeItem = "I00001";
			rs = conn.readTable("SELECT kodeBarang from item Order By kodeBarang desc");
			if(!rs.next()){
				
			}
			else{
				String kodeTrAkhir = rs.getString(1);
				int intKode = Integer.parseInt(kodeTrAkhir.substring(1, 6));
				intKode++;
				kodeItem = "I"+String.format("%05d", intKode);
				
			}
			
			mdl.setDataVector(fillTable(), headCol);
		}catch (Exception e2) {
			JOptionPane.showMessageDialog(null, e2);
		}

	}


	}
