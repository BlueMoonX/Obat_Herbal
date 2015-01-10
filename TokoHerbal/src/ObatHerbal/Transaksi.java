package ObatHerbal;

import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.Font;

public class Transaksi extends JFrame {
	
	private JTextField namabeli;
	private JLabel lblNewLabel;
	private JLabel lblIdTransaksi;
	private JLabel lblNamaProduct;
	private JTextField namaproduk;
	private JLabel lblHarga;
	private JTextField harga;
	private JLabel lblJumlah;
	private JLabel lblTotal;
	private JLabel label_1;
	private JLabel lblJumlahBarang;
	private JLabel label_2;
	private JLabel lblBayar;
	private JTextField textField_1;
	private JLabel lblRp;
	private JButton btnBayar;
	private JTable table;
	private DefaultTableModel tabelModel;
	private JLabel label;
	private JLabel lblNamaPembeli;
	private JButton btnBeli;
	private JLabel lblIdProduct;
	private JSpinner spinner;
	private JButton btnTambah;
	private static String dtrans;
	private static int jumlahBeli=0;
	private JComboBox cmbKasir;
	private DefaultComboBoxModel model = new DefaultComboBoxModel(); 
	private JLabel lblTanggalTransaksi;
	private JButton btnLogout;
	private JButton btnReset;
	/**
	 * Create the frame.
	 */
	public void disable()
	{
		lblNamaPembeli.setEnabled(false);
		namabeli.setEnabled(false);
		btnBeli.setEnabled(false);
	}
	public void enabled()
	{
		table.setEnabled(true);
		lblIdProduct.setEnabled(true);
		cmbKasir.setEnabled(true);
		lblNamaProduct.setEnabled(true);
		lblHarga.setEnabled(true);
		lblJumlah.setEnabled(true);
		spinner.setEnabled(true);
		btnTambah.setEnabled(true);
		lblJumlahBarang.setEnabled(true);
		lblTotal.setEnabled(true);
		lblBayar.setEnabled(true);
		textField_1.setEnabled(true);
		btnBayar.setEnabled(true);
	}
	public void setTanggal()
    {
        java.util.Date skrng = new java.util.Date();
        java.text.SimpleDateFormat kal = new java.text.SimpleDateFormat("yyyy-MM-dd");
        lblNewLabel.setText(kal.format(skrng));
    }
	public void autoNumber()
	{
		try
		{
			Connection konek = Koneksi.getCon();
			Statement state = konek.createStatement();
			String query = "SELECT count(*) FROM Transaksi ";
			ResultSet rs = state.executeQuery(query);
			if(rs.next()==false)
			{
				label.setText("T001");
			}
			else{	
					rs.last();
					int IDPesan = rs.getInt(1) + 1;
					label.setText("T00"+IDPesan);

			}
			rs.close();
			state.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
        }
	public void autoDtrans()
	{
		try
		{
			Connection konek = Koneksi.getCon();
			Statement state = konek.createStatement();
			String query = "SELECT count(*) FROM dTransaksi ";
			
			ResultSet rs = state.executeQuery(query);
			if(rs.next()==false)
			{
				dtrans="dt001";
			}
				else
				{
					rs.last();
					int IDPesan = rs.getInt(1) + 1;
					String IDFix = "00" + IDPesan;
					dtrans="dt" + IDFix;
				}
					
			rs.close();
			state.close();
		}
		catch(Exception ex)
		{
			System.out.println(ex);
		}
        }
	
	public void tampilTotal()
    {
        String kpesan=label.getText();
         try {
            Connection konek = Koneksi.getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery("select SUM(subtotal) from dTransaksi where idtrans='"+kpesan+"'");
            while (rs.next()) {
                label_1.setText(""+rs.getInt(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

	/**
	 * Create the frame.
	 */
	public Transaksi() {
		super("Transaksi Penjualan");
		setBounds(100, 100, 820, 600);
		
		autoDtrans();
		setBounds(100, 100, 801, 490);
		getContentPane().setLayout(null);
		lblNamaPembeli = new JLabel("Nama Pembeli :");
		lblNamaPembeli.setBounds(10, 66, 90, 14);
		getContentPane().add(lblNamaPembeli);
		
		namabeli = new JTextField();
		namabeli.setBounds(110, 63, 170, 20);
		getContentPane().add(namabeli);
		namabeli.setColumns(10);
		
		btnBeli = new JButton("Beli");
		btnBeli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		        Connection konek = Koneksi.getCon();
		        String query = "INSERT INTO Transaksi VALUES(?,?,?,?,0)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, label.getText());
		        prepare.setString(2, lblNewLabel.getText());
		        prepare.setString(3, Login.nama);
		        prepare.setString(4, namabeli.getText());


		        prepare.executeUpdate();
		        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
		        prepare.close();
		        tampilTabel();
		        disable();
		        enabled();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
		            System.out.println(ex);
		        }
		        finally
		        {
		        }
			}
		});
		btnBeli.setBounds(302, 62, 89, 23);
		getContentPane().add(btnBeli);
		
		lblNewLabel = new JLabel("-");
		lblNewLabel.setBounds(607, 74, 83, 18);
		getContentPane().add(lblNewLabel);
		setTanggal();
		
		lblIdTransaksi = new JLabel("ID Transaksi :");
		lblIdTransaksi.setBounds(479, 48, 99, 14);
		getContentPane().add(lblIdTransaksi);
		
		Panel panel = new Panel();
		panel.setBounds(0, 98, 782, 344);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		lblIdProduct = new JLabel("ID Product :");
		lblIdProduct.setEnabled(false);
		lblIdProduct.setBounds(10, 11, 69, 20);
		panel.add(lblIdProduct);
		
		lblNamaProduct = new JLabel("Nama Product :");
		lblNamaProduct.setEnabled(false);
		lblNamaProduct.setBounds(10, 45, 95, 14);
		panel.add(lblNamaProduct);
		
		namaproduk = new JTextField();
		namaproduk.setEnabled(false);
		namaproduk.setBounds(109, 39, 275, 20);
		panel.add(namaproduk);
		namaproduk.setColumns(10);
		
		lblHarga = new JLabel("Harga :");
		lblHarga.setEnabled(false);
		lblHarga.setBounds(10, 73, 46, 14);
		panel.add(lblHarga);
		
		harga = new JTextField();
		harga.setEnabled(false);
		harga.setBounds(109, 70, 95, 20);
		panel.add(harga);
		harga.setColumns(10);
		
		lblJumlah = new JLabel("Jumlah");
		lblJumlah.setEnabled(false);
		lblJumlah.setBounds(10, 104, 46, 14);
		panel.add(lblJumlah);
		
		spinner = new JSpinner();
		spinner.setEnabled(false);
		spinner.setBounds(109, 101, 46, 20);
		panel.add(spinner);
		
		btnTambah = new JButton("Add To Cart");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
		        {
		        Connection konek = Koneksi.getCon();
		        String query = "INSERT INTO dTransaksi VALUES(?,?,?,?,?)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, dtrans);
		        prepare.setString(2, label.getText());
		        prepare.setString(3, (String) cmbKasir.getSelectedItem());
		        prepare.setInt(4, (Integer)spinner.getValue());
		        prepare.setInt(5,  Integer.valueOf(harga.getText())*(Integer) spinner.getValue());

		        prepare.executeUpdate();
		        jumlahBeli+=1;
		        label_2.setText(""+jumlahBeli);
		        JOptionPane.showMessageDialog(null, "Data pembelian disimpan");
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data pembelian gagal disimpan");
		            System.out.println(ex);
		        }
		        finally
		        {
		        }
		        
		        //tampil total
				autoDtrans();
		        tampilTotal();
		         
		         //update total database
		          try{
		        Connection konek = Koneksi.getCon();
		        String query = "UPDATE Transaksi SET total = ? WHERE idtrans = ?";
		        PreparedStatement prepare = konek.prepareStatement(query);
		       
		        prepare.setInt(1, Integer.parseInt(label_1.getText()));
		        prepare.setString(2, label.getText());
		        
		        prepare.executeUpdate();
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Data gagal diubah");
		            System.out.println(ex);
		        }
		        finally
		        {        
		        }
		          
		          
		        //kurang stok  
		        try{
		        Connection konek = Koneksi.getCon();
		        String query = "UPDATE Product SET stok = stok-? WHERE idp = ?";
		        PreparedStatement prepare = konek.prepareStatement(query);
		       
		        prepare.setInt(1, (Integer) spinner.getValue());
		        prepare.setString(2, (String) cmbKasir.getSelectedItem());
		        
		        prepare.executeUpdate();
		        tampilTabel();
	            table.setModel(tabelModel);	
		        prepare.close();
		        }
		        
		        catch(Exception ex)
		        {
		            JOptionPane.showMessageDialog(null, "Stok gagal dikurangi");
		            System.out.println(ex);
		        }
		        finally
		        {        
		        }
			}
		});
		btnTambah.setEnabled(false);
		btnTambah.setBounds(273, 100, 111, 23);
		panel.add(btnTambah);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 163, 759, 181);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);	
        tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Product");
        tabelModel.addColumn("Nama Product");
        tabelModel.addColumn("Harga");
        tabelModel.addColumn("Jumlah");
        tabelModel.addColumn("Total Harga");
		table.setModel(tabelModel);	
        tampilTabel();
        table.setEnabled(false);
		
		lblTotal = new JLabel("Total :");
		lblTotal.setEnabled(false);
		lblTotal.setBounds(435, 42, 117, 20);
		panel.add(lblTotal);
		
		label_1 = new JLabel("-");
		label_1.setBounds(609, 45, 46, 14);
		panel.add(label_1);
		
		lblJumlahBarang = new JLabel("Jumlah Jenis Barang :");
		lblJumlahBarang.setEnabled(false);
		lblJumlahBarang.setBounds(435, 11, 151, 20);
		panel.add(lblJumlahBarang);
		
		label_2 = new JLabel("-");
		label_2.setBounds(609, 11, 63, 20);
		panel.add(label_2);
		
		lblBayar = new JLabel("Jumlah Bayar :");
		lblBayar.setEnabled(false);
		lblBayar.setBounds(435, 71, 117, 19);
		panel.add(lblBayar);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setBounds(609, 70, 95, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		lblRp = new JLabel("Rp.");
		lblRp.setBounds(585, 73, 23, 14);
		panel.add(lblRp);
		
		btnBayar = new JButton("Bayar");
		btnBayar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int kembalian =Integer.parseInt(textField_1.getText())-Integer.parseInt(label_1.getText());
	            JOptionPane.showMessageDialog(null, kembalian);
				autoNumber();
				tampilTabel();
				jumlahBeli=0;
			}
		});
		btnBayar.setEnabled(false);
		btnBayar.setBounds(609, 100, 95, 23);
		panel.add(btnBayar);
		
		cmbKasir = new JComboBox();
		cmbKasir.addItemListener(new ItemListener() {
			
			public void itemStateChanged(ItemEvent arg0) {
				String nama=cmbKasir.getSelectedItem().toString();
				try {
		            Connection konek = Koneksi.getCon();
		            Statement st = konek.createStatement();
		            ResultSet rs = st.executeQuery("select nama, hrgP from Product where idp='"+nama+"'");
		            while (rs.next()) {
		                namaproduk.setText(rs.getString(1));
		                harga.setText(""+rs.getInt(2));
		            }
		        } catch (Exception ex) {
		            JOptionPane.showMessageDialog(null, ex);
		        }
			}
		});
		cmbKasir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		cmbKasir.setBounds(110, 11, 174, 20);
		panel.add(cmbKasir);
		
		label = new JLabel("-");
		label.setBounds(594, 48, 46, 14);
		getContentPane().add(label);
		
		autoNumber();
		
		//akses method isiSupplier
		isiProduk();
		//model comboBox di set sesuai comboBoxModel pada method isiSupplier
		cmbKasir.setModel(model);
		
		btnLogout = new JButton("Log Out");
		btnLogout.setBounds(665, 134, 89, 23);
		panel.add(btnLogout);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Transaksi trans = new Transaksi();
				trans.setVisible(true);
				setVisible(false);
			}
		});
		btnReset.setBounds(566, 134, 89, 23);
		panel.add(btnReset);
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login login = new Login();
				login.setVisible(true);
				setVisible(false);
			}
		});
		
		JLabel lblPointOfSales = new JLabel("POINT OF SALES - Toko Obat Herbal");
		lblPointOfSales.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPointOfSales.setBounds(252, 11, 262, 26);
		getContentPane().add(lblPointOfSales);
		
		lblTanggalTransaksi = new JLabel("Tanggal Transaksi :");
		lblTanggalTransaksi.setBounds(479, 73, 118, 19);
		getContentPane().add(lblTanggalTransaksi);
	}
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = Koneksi.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT dt.idbarang,p.nama,p.hrgP,dt.jumlah,dt.subtotal FROM Transaksi t join dTransaksi dt on t.idtrans=dt.idtrans join Product p on dt.idbarang=p.idp where dt.idtrans='"+label.getText()+"'";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getInt(3);
                obj[3] = rs.getInt(4);
                obj[4] = rs.getInt(5);
             
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
	
	public void isiProduk()
	{
		try {
            Connection konek = Koneksi.getCon();
            Statement st = konek.createStatement();
            ResultSet rs = st.executeQuery("select idp from Product");
            while (rs.next()) {
                model.addElement(rs.getString(1));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
	}
}
