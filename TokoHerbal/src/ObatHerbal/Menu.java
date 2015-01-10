package ObatHerbal;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Menu extends JFrame {



	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu frame = new Menu();
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
	public Menu() {
		super("Aplikasi Penjualan Pada Toko Obat Herbal");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 550, 350);
		getContentPane().setLayout(null);
		
		JLabel lblMenuUtama = new JLabel("MENU UTAMA - Toko Obat Herbal");
		lblMenuUtama.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMenuUtama.setBounds(156, 11, 247, 32);
		getContentPane().add(lblMenuUtama);
		
		JButton btnMProduk = new JButton("Maintenance Product & Stock");
		btnMProduk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MProduk mprod = new MProduk();
				mprod.setVisible(true);
				setVisible(false);
			}
		});
		btnMProduk.setBounds(34, 66, 221, 70);
		getContentPane().add(btnMProduk);
		
		JButton btnMUser = new JButton("Maintenance User");
		btnMUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MUser musr = new MUser();
				musr.setVisible(true);
				setVisible(false);
			}
		});
		btnMUser.setBounds(34, 160, 221, 70);
		getContentPane().add(btnMUser);
		
		JButton btnMSupplier = new JButton("Maintenance Supplier");
		btnMSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MSupplier msup = new MSupplier();
				msup.setVisible(true);
				setVisible(false);
			}
		});
		btnMSupplier.setBounds(286, 66, 221, 70);
		getContentPane().add(btnMSupplier);
		
		JButton btnLihatLaporan = new JButton("Lihat Laporan");
		btnLihatLaporan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Laporan lap = new Laporan();
				lap.setVisible(true);
				setVisible(false);
			}
		});
		btnLihatLaporan.setBounds(286, 160, 221, 70);
		getContentPane().add(btnLihatLaporan);
		
		JButton btnLogout = new JButton("Log Out");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login login = new Login();
				login.setVisible(true);
				setVisible(false);
			}
		});
		btnLogout.setBounds(187, 269, 161, 32);
		getContentPane().add(btnLogout);
	}
}
