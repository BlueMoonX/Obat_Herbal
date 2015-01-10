package ObatHerbal;

import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Font;

public class MProduk extends JFrame {
	
	private JTable table;
	private JTextField txtIdProd;
	private JTextField txtIdSup;
	private JTextField txtNamaProd;
	private JTextField txtHarga;
	private JLabel lblIdProduk;
	private JLabel lblIdSupplier;
	private JLabel lblNamaProduk;
	private JLabel lblHarga;
	private DefaultTableModel tabelModel;
	DefaultComboBoxModel model = new DefaultComboBoxModel(); 
	private JButton btnReset;
	private JLabel lblStok;
	private JTextField Stok;
	private JTextField txtCari;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MProduk frame = new MProduk();
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
	public MProduk() {
		setBounds(100, 100, 500, 600);
		this.setTitle("Maintenance Product");
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 89, 452, 219);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = table.getSelectedRow();
		        
		        if(a == -1)
		        {
		            return;
		        }
		        
		        String idp = (String) tabelModel.getValueAt(a, 0);
		        txtIdProd.setText(idp);
		        String nama= (String) tabelModel.getValueAt(a, 1);
		        txtNamaProd.setText(nama);
		        String ids = (String) tabelModel.getValueAt(a, 2);
		        txtIdSup.setText(ids);
		        int hrgP = (Integer) tabelModel.getValueAt(a, 3);
		        txtHarga.setText(""+hrgP);
		        int stok = (Integer) tabelModel.getValueAt(a, 4);
		        Stok.setText(""+stok);
		        
		        txtIdSup.setEnabled(false);
		        txtIdProd.setEnabled(false);
            	
		       
			}
		});

		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Produk");
        tabelModel.addColumn("Nama Produk");
        tabelModel.addColumn("ID Supplier");
        tabelModel.addColumn("Harga Produk");
        tabelModel.addColumn("Stok");
		table.setModel(tabelModel);	
        tampilTabel();
		
		txtIdProd = new JTextField();
		txtIdProd.setBounds(109, 314, 165, 20);
		getContentPane().add(txtIdProd);
		txtIdProd.setColumns(10);
		
		txtIdSup = new JTextField();
		txtIdSup.setColumns(10);
		txtIdSup.setBounds(109, 345, 127, 20);
		getContentPane().add(txtIdSup);
		
		txtNamaProd = new JTextField();
		txtNamaProd.setColumns(10);
		txtNamaProd.setBounds(109, 376, 287, 20);
		getContentPane().add(txtNamaProd);
		
		txtHarga = new JTextField();
		txtHarga.setColumns(10);
		txtHarga.setBounds(109, 407, 353, 20);
		getContentPane().add(txtHarga);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			        Connection konek = Koneksi.getCon();
			        String query = "INSERT INTO Product VALUES(?,?,?,?,?)";
			        PreparedStatement prepare = konek.prepareStatement(query);
			        
			        prepare.setString(1, txtIdProd.getText());
			        prepare.setString(2, txtNamaProd.getText());
			        prepare.setString(3, txtIdSup.getText());
			        prepare.setInt(4, Integer.parseInt(txtHarga.getText()));
			        prepare.setInt(5, Integer.parseInt(Stok.getText()));

			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Data gagal disimpan");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            //refresh();
			        }
			}
		});
		btnTambah.setBounds(109, 465, 89, 23);
		getContentPane().add(btnTambah);
		
		JButton btnUbah = new JButton("Ubah");
		btnUbah.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = Koneksi.getCon();
			        String query = "UPDATE Product SET nama = ?, hrgP = ?, stok = ? WHERE idp = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setString(1, txtNamaProd.getText());
			        prepare.setInt(2, Integer.parseInt(txtHarga.getText()));
			        prepare.setInt(3, Integer.parseInt(Stok.getText()));
			        prepare.setString(4, txtIdProd.getText());
			        
			        
			        prepare.executeUpdate();
			        JOptionPane.showMessageDialog(null, "Data berhasil diubah");
			        prepare.close();
			        }
			        
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Data gagal diubah");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            txtIdProd.setEnabled(true);
			            //refresh();            
			        }
			}
		});
		btnUbah.setBounds(208, 465, 89, 23);
		getContentPane().add(btnUbah);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 try
			        {
			            Connection konek = Koneksi.getCon();
			            String query = "DELETE FROM Product WHERE idp = ?";
			            PreparedStatement prepare = konek.prepareStatement(query);
			            
			            prepare.setString(1, txtIdProd.getText());
			            prepare.executeUpdate();
			            JOptionPane.showMessageDialog(null, "Data berhasil dihapus");
			            prepare.close();
			        }
			        catch(Exception ex)
			        {
			            JOptionPane.showMessageDialog(null, "Data gagal dihapus");
			            System.out.println(ex);
			        }
			        finally
			        {
			            tampilTabel();
			            txtIdProd.setEnabled(true);
			            //refresh();
			        }
			}
		});
		btnHapus.setBounds(307, 465, 89, 23);
		getContentPane().add(btnHapus);
		// end
		
		lblIdProduk = new JLabel("ID Produk :");
		lblIdProduk.setBounds(10, 317, 67, 14);
		getContentPane().add(lblIdProduk);
		
		lblIdSupplier = new JLabel("ID Supplier :");
		lblIdSupplier.setBounds(10, 349, 67, 14);
		getContentPane().add(lblIdSupplier);
		
		lblNamaProduk = new JLabel("Nama Produk :");
		lblNamaProduk.setBounds(10, 382, 89, 14);
		getContentPane().add(lblNamaProduk);
		
		lblHarga = new JLabel("Harga :");
		lblHarga.setBounds(10, 410, 46, 14);
		getContentPane().add(lblHarga);
		
		
		
		JButton btnKembali = new JButton("Kembali");
		btnKembali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				setVisible(false);
			}
		});
		btnKembali.setBounds(307, 506, 89, 23);
		getContentPane().add(btnKembali);
		
		btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtIdProd.setText("");
				txtIdSup.setText("");
				txtNamaProd.setText("");
				txtHarga.setText("");
				Stok.setText("");
				setVisible(true);
			}
		});
		btnReset.setBounds(208, 506, 89, 23);
		getContentPane().add(btnReset);
		
		JLabel lblMaintenanceProduct = new JLabel("MAINTENANCE PRODUCT - Toko Obat Herbal");
		lblMaintenanceProduct.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaintenanceProduct.setBounds(69, 11, 327, 34);
		getContentPane().add(lblMaintenanceProduct);
		
		lblStok = new JLabel("Stok :");
		lblStok.setBounds(10, 437, 46, 14);
		getContentPane().add(lblStok);
		
		Stok = new JTextField();
		Stok.setBounds(109, 434, 67, 20);
		getContentPane().add(Stok);
		Stok.setColumns(10);
		
		txtCari = new JTextField();
		txtCari.setBounds(217, 58, 131, 20);
		getContentPane().add(txtCari);
		txtCari.setColumns(10);
		
		JLabel lblCariProduk = new JLabel("Cari Produk (Masukan ID Produk) : ");
		lblCariProduk.setBounds(10, 58, 204, 18);
		getContentPane().add(lblCariProduk);
		
		JButton btnCari = new JButton("Cari");
		btnCari.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnCari.setBounds(369, 55, 58, 23);
		getContentPane().add(btnCari);
		
		

	}
	
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = Koneksi.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM Product";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[5];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
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
}
