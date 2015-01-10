package ObatHerbal;


import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;

import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MUser extends JFrame {
	private JTextField txtPassword;
	private JTable table;
	private DefaultTableModel tabelModel;
	private DefaultComboBoxModel model = new DefaultComboBoxModel(); 
	private JTextField txtUsername;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MUser frame = new MUser();
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
	public MUser() {
		super("Maintenance User");
		setBounds(0, 0, 500, 500);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 80, 470, 171);
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
		        
		        String ids = (String) tabelModel.getValueAt(a, 0);
		        txtUsername.setText(ids);
		        String nama= (String) tabelModel.getValueAt(a, 1);
		        txtPassword.setText(nama);
			}
		});
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("Username");
        tabelModel.addColumn("Password");
		table.setModel(tabelModel);	
        tampilTabel();
		
		JLabel lblPassword = new JLabel("Password  :");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblPassword.setBounds(43, 293, 71, 20);
		getContentPane().add(lblPassword);
		
		txtPassword = new JTextField();
		txtPassword.setColumns(10);
		txtPassword.setBounds(124, 294, 304, 20);
		getContentPane().add(txtPassword);
		
		JButton btnTambah = new JButton("Tambah");
		btnTambah.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try
		        {
		        Connection konek = Koneksi.getCon();
		        String query = "INSERT INTO Usr VALUES(?,?)";
		        PreparedStatement prepare = konek.prepareStatement(query);
		        
		        prepare.setString(1, txtUsername.getText());
		        prepare.setString(2, txtPassword.getText());

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
		btnTambah.setBounds(124, 335, 96, 38);
		getContentPane().add(btnTambah);
		
		JButton btnUbah = new JButton("Ubah");
		btnUbah.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try{
			        Connection konek = Koneksi.getCon();
			        String query = "UPDATE Usr SET pass = ? WHERE username = ?";
			        PreparedStatement prepare = konek.prepareStatement(query);
			       
			        prepare.setString(1, txtPassword.getText());
			        prepare.setString(2, txtUsername.getText());
			        
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
			            txtUsername.setEnabled(true);
			            //refresh();            
			        }
			}
		});
		btnUbah.setBounds(230, 335, 96, 38);
		getContentPane().add(btnUbah);
		
		JButton btnHapus = new JButton("Hapus");
		btnHapus.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				try
		        {
		            Connection konek = Koneksi.getCon();
		            String query = "DELETE FROM Usr WHERE username = ?";
		            PreparedStatement prepare = konek.prepareStatement(query);
		            
		            prepare.setString(1, txtUsername.getText());
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
		            txtUsername.setEnabled(true);
		            //refresh();
		        }
			}
		});
		btnHapus.setBounds(332, 335, 96, 38);
		getContentPane().add(btnHapus);
		
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblUsername.setBounds(43, 262, 71, 20);
		getContentPane().add(lblUsername);
		
		txtUsername = new JTextField();
		txtUsername.setBounds(124, 263, 304, 20);
		getContentPane().add(txtUsername);
		txtUsername.setColumns(10);
		
		JButton btnKembali = new JButton("Kembali");
		btnKembali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Menu menu = new Menu();
				menu.setVisible(true);
				setVisible(false);
			}
		});
		btnKembali.setBounds(336, 396, 96, 23);
		getContentPane().add(btnKembali);
		
		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtUsername.setText("");
				txtPassword.setText("");
				setVisible(true);
			}
		});
		btnReset.setBounds(230, 396, 96, 23);
		getContentPane().add(btnReset);
		
		JLabel lblMaintenanceUser = new JLabel("MAINTENANCE USER - Toko Obat Herbal");
		lblMaintenanceUser.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMaintenanceUser.setBounds(87, 21, 309, 38);
		getContentPane().add(lblMaintenanceUser);
	}
	
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = Koneksi.getCon();
            Statement state = konek.createStatement();
            String query = "SELECT * FROM Usr";
            
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
