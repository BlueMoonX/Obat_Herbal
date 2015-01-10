package ObatHerbal;

import java.awt.*;

import javax.swing.*;

import java.awt.Font;

import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;



public class Login extends JFrame {
	private JTextField txtUser;
	private JPasswordField txtPass;
	private JButton btnLogin;
	public static String nama;
	//public static javax.swing.JFrame form_menu;
	//public static javax.swing.JFrame form_transaksi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		super("Login");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 350, 220);
		getContentPane().setLayout(null);
		//form_menu=new Menu();
		//form_transaksi=new Transaksi();
				
		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUsername.setBounds(23, 64, 73, 20);
		getContentPane().add(lblUsername);
		
		JLabel lblPass = new JLabel("Password :");
		lblPass.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblPass.setBounds(23, 110, 73, 20);
		getContentPane().add(lblPass);
		
		txtUser = new JTextField();
		txtUser.setBounds(106, 65, 200, 20);
		getContentPane().add(txtUser);
		txtUser.setColumns(10);
		
		txtPass = new JPasswordField();
		txtPass.setBounds(106, 111, 200, 20);
		getContentPane().add(txtPass);
		txtPass.setColumns(10);
		
		btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 13));
		btnLogin.setBounds(217, 148, 89, 23);
		getContentPane().add(btnLogin);
		
		JLabel lblLogin = new JLabel("LOGIN - Toko Obat Herbal");
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setBounds(78, 11, 189, 34);
		getContentPane().add(lblLogin);
		
		btnLogin.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent arg0) {
				try{
			        Connection Con=Koneksi.getCon();
			        String sql = "Select * from Usr where username='"+txtUser.getText()+"' and pass='" +txtPass.getText()+ "'";
			        Statement st=Con.createStatement();
			        ResultSet rs = st.executeQuery(sql);
			        if (rs.next()){
			        if (rs.getString(1).equalsIgnoreCase("admin"))
			        {
			            //JOptionPane.showMessageDialog(null, "Login berhasil");
			            //form_menu.show();
			            
			            Menu m = new Menu();
			            m.show();
						//m.setVisible(true);
						//txtUser.setText("");
						///txtPass.setText("");
						setVisible(false);
			  
			        }else{
			        	//JOptionPane.showMessageDialog(null, "Login Kasir Berhasil");
			        	//form_transaksi.show();
			        	Transaksi t = new Transaksi();
			        	t.show();
						//t.setVisible(true);
						//txtUser.setText("");
						//txtPass.setText("");
						setVisible(false);
			          
			        }
			        }else{
			        JOptionPane.showMessageDialog(null, "Login gagal");
			        }
			        }catch (Exception ex){
			        JOptionPane.showMessageDialog(null, "Login gagal (koneksi)");
			        }
				
			}
		});
	}
}
