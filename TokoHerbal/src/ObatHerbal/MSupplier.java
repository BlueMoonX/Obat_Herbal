package ObatHerbal;

import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTable;

import java.awt.BorderLayout;

import javax.swing.JScrollPane;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

public class MSupplier extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTextField txtIDSupplier;
	private JTextField txtNamaSupplier;
	private DefaultTableModel tabelModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MSupplier frame = new MSupplier();
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
	public MSupplier() {
		super ("Maintenance Supplier");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 470, 480);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 249, 420, 171);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = table.getSelectedRow();
		        
		        if(a == -1)
		        {
		            return;
		        }
		        
		        String ids = (String) tabelModel.getValueAt(a, 0);
		        txtIDSupplier.setText(ids);
		        String nama= (String) tabelModel.getValueAt(a, 1);
		        txtNamaSupplier.setText(nama);
		        txtIDSupplier.setEnabled(false);
			}
		});
		
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Supplier");
        tabelModel.addColumn("Nama Supplier");
		table.setModel(tabelModel);	
        tampilTabel();
		
		JLabel lblSupplier = new JLabel("MAINTENANCE SUPPLIER - Toko Obat Herbal");
		lblSupplier.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblSupplier.setBounds(58, 11, 327, 58);
		getContentPane().add(lblSupplier);
		
		JLabel lblIDSupplier = new JLabel("ID Supplier          :");
		lblIDSupplier.setBounds(38, 93, 103, 14);
		getContentPane().add(lblIDSupplier);
		
		JLabel lblNamaSupplier = new JLabel("Nama Supplier   :");
		lblNamaSupplier.setBounds(38, 141, 119, 14);
		getContentPane().add(lblNamaSupplier);
		
		txtIDSupplier = new JTextField();
		txtIDSupplier.setBounds(146, 90, 197, 20);
		getContentPane().add(txtIDSupplier);
		txtIDSupplier.setColumns(10);
		
		txtNamaSupplier = new JTextField();
		txtNamaSupplier.setColumns(10);
		txtNamaSupplier.setBounds(146, 138, 278, 20);
		getContentPane().add(txtNamaSupplier);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				 try
			        {
			        Connection konek = Koneksi.getCon();
			        String query = "INSERT INTO Supplier VALUES(?,?)";
			        PreparedStatement prepare = konek.prepareStatement(query);
			        
			        prepare.setString(1, txtIDSupplier.getText());
			        prepare.setString(2, txtNamaSupplier.getText());

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
			            //txtIDSupplier.setEnabled(true);
			            //refresh();
			        }
			}
		});
		btnTambah.setBounds(146, 169, 89, 36);
		getContentPane().add(btnTambah);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				try
		        {
		            Connection konek = Koneksi.getCon();
		            String query = "DELETE FROM Supplier WHERE ids = ?";
		            PreparedStatement prepare = konek.prepareStatement(query);
		            
		            prepare.setString(1, txtIDSupplier.getText());
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
		            txtIDSupplier.setEnabled(true);
		            //refresh();
		        }
			}
		});
		btnHapus.setBounds(340, 169, 89, 36);
		getContentPane().add(btnHapus);
		
		JButton btnUbah = new JButton("Ubah");
		btnUbah.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = Koneksi.getCon();
			        String query = "UPDATE Supplier SET namas = ? WHERE ids = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setString(1, txtNamaSupplier.getText());
			        prepare.setString(2, txtIDSupplier.getText());
			        
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
			            txtIDSupplier.setEnabled(true);
			            //refresh();            
			        }
			}
		});
		btnUbah.setBounds(241, 169, 89, 36);
		getContentPane().add(btnUbah);
		
		JButton btnKembali = new JButton("Kembali");
		btnKembali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				setVisible(false);
			}
		});
		btnKembali.setBounds(340, 215, 89, 23);
		getContentPane().add(btnKembali);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				txtNamaSupplier.setText("");
				txtIDSupplier.setText("");
				setVisible(true);

			}
		});
		btnReset.setBounds(241, 215, 89, 23);
		getContentPane().add(btnReset);
	}
	
	
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = Koneksi.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM Supplier";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[2];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
