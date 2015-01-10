package ObatHerbal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;

import javax.swing.JScrollPane;
import javax.swing.JTable;

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

public class Laporan extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private DefaultTableModel tabelModel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Laporan frame = new Laporan();
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
	public Laporan() {
		super("Laporan Penjualan");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 350);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JButton btnKembali = new JButton("Kembali");
		btnKembali.setBounds(325, 278, 89, 23);
		btnKembali.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Menu menu = new Menu();
				menu.setVisible(true);
				setVisible(false);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnKembali);
		
		JLabel lblLaporanPenjualan = new JLabel("LAPORAN PENJUALAN - Toko Obat Herbal");
		lblLaporanPenjualan.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLaporanPenjualan.setBounds(185, 11, 306, 37);
		contentPane.add(lblLaporanPenjualan);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 59, 738, 198);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		tabelModel = new DefaultTableModel();
        tabelModel.addColumn("ID Transaksi");
        tabelModel.addColumn("Tanggal Transaksi");
        tabelModel.addColumn("Nama Pembeli");
        tabelModel.addColumn("ID Produk");
        tabelModel.addColumn("Nama Produk");
        tabelModel.addColumn("Jumlah Beli");
        tabelModel.addColumn("Harga Sub Total");
        tabelModel.addColumn("Harga Total");
		table.setModel(tabelModel);	
        tampilTabel();
        table.setEnabled(false);
	}
	
	public void tampilTabel()
    {
        tabelModel.getDataVector().removeAllElements();
        tabelModel.fireTableDataChanged();
        try
        {
            Connection konek = Koneksi.getCon();
            Statement state = konek.createStatement();
            String query = " SELECT t.idtrans, t.tanggal, t.pelanggan, dt.idbarang, p.nama, dt.jumlah, dt.subtotal, t.total FROM Transaksi t join dTransaksi dt on t.idtrans=dt.idtrans join Product p on dt.idbarang=p.idp";
            
            ResultSet rs = state.executeQuery(query);
            
            while(rs.next())
            {
                Object obj[] = new Object[8];
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                obj[3] = rs.getString(4);
                obj[4] = rs.getString(5);
                obj[5] = rs.getInt(6);
                obj[6] = rs.getInt(7);
                obj[7] = rs.getInt(8);
                
                
                tabelModel.addRow(obj);
            }
        }
        catch(Exception ex)
        {
            System.out.println(ex);
        }
    }
}
